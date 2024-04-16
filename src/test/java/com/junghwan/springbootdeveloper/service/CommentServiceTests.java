package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.dto.AddCommentRequest;
import com.junghwan.springbootdeveloper.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class CommentServiceTests {

    @Autowired
    private CommentService commentService;

    @Test
    public void save(){

        AddCommentRequest request = AddCommentRequest.builder()
                .articleId(7L)
                .comment("뎃글 테스트1.5")
                .author("정환")
                .build();
        log.info(commentService.save(request));
    }
}
