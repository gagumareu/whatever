package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.domain.Article;
import com.junghwan.springbootdeveloper.dto.*;
import com.junghwan.springbootdeveloper.service.ArticleImageService;
import com.junghwan.springbootdeveloper.service.ArticleService;
import com.junghwan.springbootdeveloper.util.S3Uploader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ArticleApiController {

    private final ArticleService articleService;

    private final ArticleImageService articleImageService;

    private final S3Uploader s3Uploader;

    @PostMapping("/api/article")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        log.info("------api article ----------");
        log.info(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.save(request));
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAll(){

        List<ArticleResponse> articles = articleService.findAll().stream().map(ArticleResponse::new).toList();

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/article/{id}")
    public ResponseEntity<ArticleViewResponse> findById(@PathVariable long id){

        ArticleViewResponse article = articleService.findById(id);

//        ArticleResponse response = new ArticleResponse(article);

        return ResponseEntity.ok().body(article);
    }

    @PreAuthorize("principal.username == #writer")
    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){

        System.out.println("----- api delete id ------ " + id);

        ArticleViewResponse articleViewResponse = articleService.findById(id);

        String writer = articleViewResponse.getWriter();

//        log.info("writer: " + writer);

        List<ArticleImageDTO> responses = articleImageService.findByArticleId(id).stream().map(ArticleImageDTO::new).toList();

        if (responses != null && responses.size() > 0 ){
            responses.forEach(articleImageDTO -> {
                String fileName = articleImageDTO.getUuid() + "_" + articleImageDTO.getFileName();
                log.info(fileName);
                s3Uploader.removeS3File(fileName);
            });
        }

        articleService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("principal.username == #request.writer")
    @PutMapping("/api/article/{id}")
    public ResponseEntity<Article> update(@RequestBody UpdateArticleRequest request, @PathVariable long id){

        log.info(request);
        log.info(id);

        Article updatedArticle = articleService.update(request, id);

        return ResponseEntity.ok().body(updatedArticle);
    }

    @GetMapping("/api/articleImages/{id}")
    public ResponseEntity<List<ArticleImageDTO>> findByArticleId(@PathVariable long id){

        List<ArticleImageDTO> responses = articleImageService.findByArticleId(id).stream().map(ArticleImageDTO::new).toList();

        return ResponseEntity.ok().body(responses);
    }


}
