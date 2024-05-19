package com.junghwan.springbootdeveloper.repository.search;

import com.junghwan.springbootdeveloper.domain.Article;
import com.junghwan.springbootdeveloper.dto.ArticleListAllDTO;
import com.junghwan.springbootdeveloper.dto.ArticleListCommentCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleSearch {

    Page<Article> searchAll(String[] types, String keyword, Pageable pageable);

    Page<ArticleListCommentCountDTO> searchWithCommentCount(String[] types, String keyword, Pageable pageable);

    Page<ArticleListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable);

}
