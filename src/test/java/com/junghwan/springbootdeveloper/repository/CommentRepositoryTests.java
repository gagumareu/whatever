package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.Article;
import com.junghwan.springbootdeveloper.domain.Comment;
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
import java.util.Optional;

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

        Long articleId = 261L;

        Pageable pageable = PageRequest.of(1,10, Sort.by("id").ascending());

        Page<Object[]> result2 = commentRepository.listOfArticle2(articleId, pageable);
        Page<Comment> result1 = commentRepository.listOfArticle(articleId, pageable);
        Optional<Comment> comment = commentRepository.findById(articleId);

        System.out.println(Arrays.asList(comment));

        result1.getContent().forEach(log::info);

        System.out.println(result1.getContent());

        result2.getContent().forEach(objects -> {
            System.out.println(Arrays.asList(objects));
        });

    }
}
