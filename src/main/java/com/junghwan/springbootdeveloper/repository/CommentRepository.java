package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.article.id = :articleId")
    Page<Comment> listOfArticle(Long articleId, Pageable pageable);

    void deleteByArticle_id(Long id);

}
