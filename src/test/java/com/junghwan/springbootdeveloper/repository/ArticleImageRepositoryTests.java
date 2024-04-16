package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.ArticleImage;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class ArticleImageRepositoryTests {

    @Autowired
    private ArticleImageRepository articleImageRepository;

    @Test
    public void findById(){

        List<ArticleImage> articleImages = articleImageRepository.findByArticleId(177L);

        articleImages.forEach(articleImage -> {
            log.info(articleImage);
        });

    }


}
