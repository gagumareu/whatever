package com.junghwan.springbootdeveloper.dto;

import com.junghwan.springbootdeveloper.domain.Article;
import lombok.Getter;

@Getter
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final String category;

    public ArticleListViewResponse(Article article){
        this.id = article.getId();;
        this.title = article.getTitle();
        this.content = article.getContent();
        this.writer = article.getWriter();
        this.category = article.getCategory();
    }

}
