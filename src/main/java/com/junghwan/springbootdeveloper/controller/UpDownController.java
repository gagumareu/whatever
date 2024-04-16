package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.dto.upload.UploadFileDTO;
import com.junghwan.springbootdeveloper.dto.upload.UploadResultDTO;
import com.junghwan.springbootdeveloper.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@Log4j2
@RequiredArgsConstructor
public class UpDownController {

    @Value("${com.junghwan.springbootdeveloper.upload.path}")
    private String uploadPath;

    private final S3Uploader s3Uploader;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO){

        log.info("---------- uploadFile --------------");
        log.info("uploadFile: " + uploadFileDTO);

        if (uploadFileDTO.getFiles() != null){

            final List<UploadResultDTO> list = new ArrayList<>();

//            String s3Url = "";

            uploadFileDTO.getFiles().forEach(multipartFile -> {

                String originalName = multipartFile.getOriginalFilename();
                log.info(originalName);

                String uuid = UUID.randomUUID().toString();

                String saveName = uploadPath + File.separator + uuid + "_" + originalName;
                log.info(saveName);

                Path savePath = Paths.get(uploadPath, uuid + "_" + originalName);
                log.info(savePath);

                boolean image = false;

                try{
                    multipartFile.transferTo(savePath);

                    String s3Url = s3Uploader.upload(saveName);

                    if (Files.probeContentType(savePath).startsWith("image")){

                        File thubFile = new File(uploadPath, "s_" + uuid + "_" + originalName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thubFile, 200, 200);
                        String thumbnailName = "S_" + uuid + "_" + originalName;
                        s3Uploader.upload(thumbnailName);

                    }

                    list.add(UploadResultDTO.builder()
                            .uuid(uuid)
                            .fileName(originalName)
                            .img(image)
                            .s3Url(s3Url)
                            .build()
                    );

                } catch (IOException e) {
                    e.printStackTrace();
                }


            });
            return list;
        }
        return null;
    }


    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){

        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @DeleteMapping("/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName){

        log.info("remove");
        log.info(fileName);

        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();

        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;

        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete();

            if (contentType.startsWith("image")){
                File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
                thumbnailFile.delete();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        resultMap.put("result", removed);

        return resultMap;
    }

    private String makeFolder(){

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        File uploadPathFolder = new File(uploadPath, folderPath);

        if (uploadPathFolder.exists() == false){
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }
    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(UploadFileDTO uploadFiles){

        log.info("---------------------uploadAjax--------------------");

        List<UploadResultDTO> uploadResultDTOList = new ArrayList<>();

        uploadFiles.getFiles().forEach(multipartFile ->{

            String originalName = multipartFile.getOriginalFilename();

            String fileName = originalName.substring(originalName.lastIndexOf("/") +1);

            log.info("originalName: " + originalName);
            log.info("fileName: " + fileName);

            String folderPath = makeFolder();

            String uuid = UUID.randomUUID().toString();

            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            log.info("saveName: " + saveName);

            Path savePath = Paths.get(saveName);

            try {
                multipartFile.transferTo(savePath);

                String thumbnailName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;

                File thumbnailFile = new File(thumbnailName);

                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 200, 200);

                String s3Url = s3Uploader.upload(saveName);
                s3Uploader.upload(thumbnailName);

                uploadResultDTOList.add(new UploadResultDTO(uuid, fileName , true, s3Url));


                log.info("---------------------end for uploadAjax--------------------");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return new ResponseEntity<>(uploadResultDTOList, HttpStatus.OK);
    }  // uploadResultDTOList

    @PostMapping("/uploadAjax2")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles){

        log.info("---------------------uploadAjax--------------------");
        log.info("bucket: " + bucket);

        List<UploadResultDTO> uploadResultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles){

            String originalName = uploadFile.getOriginalFilename();

            String fileName = originalName.substring(originalName.lastIndexOf("/") +1);

            log.info("originalName: " + originalName);
            log.info("fileName: " + fileName);

            String folderPath = makeFolder();

            String uuid = UUID.randomUUID().toString();

            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            log.info("saveName: " + saveName);

            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);

                String thumbnailName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;

                File thumbnailFile = new File(thumbnailName);

                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 200, 200);

                String s3Url = s3Uploader.upload(saveName);
                s3Uploader.upload(thumbnailName);

                uploadResultDTOList.add(new UploadResultDTO(uuid, fileName , true, s3Url));


                log.info("---------------------end for uploadAjax--------------------");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(uploadResultDTOList, HttpStatus.OK);
    }  // uploadResultDTOList
    @DeleteMapping(value = "/removeS3")
    public ResponseEntity<String> removeS3(@RequestBody String files){

        log.info("-----------removeS3-----------------");
        log.info(files);

        String result = files;


        try {
            s3Uploader.removeS3File(files);
            result = "success";
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }










}
