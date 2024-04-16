package com.junghwan.springbootdeveloper.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleListCommentCountDTO {

    private Long id;
    private String title;
    private String writer;
    private String category;
    private LocalDateTime createdAt;

    private Long commentCounting;

}
