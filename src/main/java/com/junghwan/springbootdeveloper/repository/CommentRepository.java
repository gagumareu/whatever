package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, QCommentRepository {

    @Query("SELECT c FROM Comment c WHERE c.article.id = :articleId")
    Page<Comment> listOfArticle(Long articleId, Pageable pageable);

    @Query("SELECT c, u FROM Comment c LEFT OUTER JOIN User u ON u.userId = c.author WHERE c.article.id = :articleId")
    Page<Object[]> listOfArticle2(Long articleId, Pageable pageable);

    void deleteByArticle_id(Long id);

}
