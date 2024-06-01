package com.junghwan.springbootdeveloper.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class S3UploaderTest {

    @Autowired
    private S3Uploader s3Uploader;

//    @Test
//    public void testUpload(){
//
//        try {
//
//            String filePath = "C:\\upload\\zzz.jpg";
//
//            String uploadName = s3Uploader.upload(filePath);
//
//            log.info(uploadName);
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//    }

}
