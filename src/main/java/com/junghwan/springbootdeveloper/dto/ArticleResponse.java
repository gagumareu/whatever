package com.junghwan.springbootdeveloper.dto;

import com.junghwan.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ArticleResponse {

    private final String title;
    private final String content;
    private final String writer;
    private final String category;

    public ArticleResponse(Article article){
        this.title = article.getTitle();
        this.content = article.getContent();
        this.writer = article.getWriter();
        this.category = article.getCategory();
    }


}
