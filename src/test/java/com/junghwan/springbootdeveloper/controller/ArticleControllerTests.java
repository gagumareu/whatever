package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ArticleControllerTests {

    @Autowired
    private ArticleService articleService;


}
