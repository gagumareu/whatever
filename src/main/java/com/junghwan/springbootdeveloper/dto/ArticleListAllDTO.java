package com.junghwan.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleListAllDTO {

    private Long id;

    private String title;

    private String writer;

    private String category;

    private LocalDateTime createdAt;

    private Long commentCounting;

    private List<ArticleImageDTO> articleImages;
}
