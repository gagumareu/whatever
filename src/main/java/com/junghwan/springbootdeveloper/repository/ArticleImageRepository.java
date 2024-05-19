package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.ArticleImage;
import com.junghwan.springbootdeveloper.dto.ArticleImageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {

    @Query("SELECT aim FROM ArticleImage aim WHERE aim.article.id = :id")
    List<ArticleImage> findByArticleId(long id);

}
