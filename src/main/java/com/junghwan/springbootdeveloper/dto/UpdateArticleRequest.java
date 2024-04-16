package com.junghwan.springbootdeveloper.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Builder
public class UpdateArticleRequest {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private String category;
    private List<String> fileNames;
}
