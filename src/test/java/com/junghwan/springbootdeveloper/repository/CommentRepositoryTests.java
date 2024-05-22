package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.Article;
import com.junghwan.springbootdeveloper.domain.Comment;
import com.junghwan.springbootdeveloper.dto.CommentResponse;
import com.junghwan.springbootdeveloper.dto.PageRequestDTO;
import com.junghwan.springbootdeveloper.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
@Log4j2
public class CommentRepositoryTests {

    @Autowired
    private CommentRepository commentRepository;

//    @Test
//    public void save(){
//
//        Long article_id = 23L;
//
//        Article article = Article.builder().id(article_id).build();
//
//        Comment comment = Comment.builder()
//                .article(article)
//                .comment("댓글 입니다.")
//                .author("정환").build();
//
//        commentRepository.save(comment);
//    }
//
    @Transactional
    @Test
    public void testArticleComment(){

        Long articleId = 271L;

        Pageable pageable = PageRequest.of(1,10, Sort.by("id").ascending());

        Page<Object[]> result2 = commentRepository.listOfArticle2(articleId, pageable);

        result2.getContent().forEach(comment -> log.info(comment));


    }

    @Transactional
    @Test
    public void testListWithUser(){

//        Pageable pageable = PageRequest.of(1, 10, Sort.by("id").ascending() );

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        Page<CommentResponse>  result = commentRepository.listWithUser(270L, pageRequestDTO.getPageable());

//        result.getContent().forEach(log::info);

        result.getContent().forEach(comment -> {
           log.info(comment.toString());
        });

    }

    @Transactional
    @Test
    public void list(){

        Pageable pageable = PageRequest.of(1, 10, Sort.by("id").ascending());

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        Page<Comment> result = commentRepository.list(pageRequestDTO.getPageable());

        result.getContent().forEach(log::info);

        result.getContent().forEach(comment -> {
            log.info(Arrays.asList(comment));
        });
    }

    @Transactional
    @Test
    public void testListWithUser2() {
        List<Comment> comments = commentRepository.listWithUser2();
        assertFalse(comments.isEmpty());

        comments.forEach(comment -> {
            log.info(Arrays.asList(comment));
        });
    }




}
