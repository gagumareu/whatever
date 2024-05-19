package com.junghwan.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.junghwan.springbootdeveloper.domain.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponse {

    @NotNull
    private Long articleId;

    private Long id;

    @NotEmpty
    private String comment;

    @NotEmpty
    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public CommentResponse(Comment comment){
        this.articleId = comment.getArticle().getId();
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.author = comment.getAuthor();
        this.createdAt = comment.getCreatedAt();
    }

}
