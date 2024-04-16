package com.junghwan.springbootdeveloper.dto;

import com.junghwan.springbootdeveloper.domain.Article;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> fileNames;

    private Long commentCounting;

    public ArticleViewResponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.writer = article.getWriter();
        this.category = article.getCategory();
        this.createdAt = article.getCreatedAt();
    }



}
