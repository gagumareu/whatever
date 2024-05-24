package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.dto.AddCommentRequest;
import com.junghwan.springbootdeveloper.dto.CommentResponse;
import com.junghwan.springbootdeveloper.dto.PageRequestDTO;
import com.junghwan.springbootdeveloper.dto.PageResponseDTO;
import com.junghwan.springbootdeveloper.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Log4j2
public class CommentServiceTests {

    @Autowired
    private CommentService commentService;

//    @Test
//    public void save(){
//
//        AddCommentRequest request = AddCommentRequest.builder()
//                .articleId(7L)
//                .comment("뎃글 테스트1.5")
//                .author("정환")
//                .build();
//        log.info(commentService.save(request));
//    }

    @Test
    public void listWithUser(){

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResponseDTO<CommentResponse> list = commentService.getListOfArticle(270L, pageRequestDTO);

        log.info(list.getTotal());

        list.getDtoList().forEach(commentResponse -> {
            log.info(commentResponse);
        });
        list.getDtoList().forEach(commentResponse -> {
            log.info(commentResponse.toString());
        });

    }
}
