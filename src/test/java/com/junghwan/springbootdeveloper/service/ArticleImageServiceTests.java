package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.dto.ArticleImageDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class ArticleImageServiceTests {

    @Autowired
    private ArticleImageService articleImageService;

    @Test
    public void getImages(){

        List<ArticleImageDTO> response = articleImageService.findByArticleId(181L).stream().map(ArticleImageDTO::new).toList();

        response.forEach(log::info);

    }




}
