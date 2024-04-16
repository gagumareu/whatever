package com.junghwan.springbootdeveloper.dto;

import com.junghwan.springbootdeveloper.domain.Article;
import com.junghwan.springbootdeveloper.domain.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCommentRequest {

    @NotNull
    private Long articleId;

    @NotEmpty
    private String comment;

    @NotEmpty
    private String author;

    private LocalDateTime createdAt, updatedAt;

    public Comment toEntity(){
        return Comment.builder().article(Article.builder().id(articleId).build()).comment(comment).author(author).build();
    }


}
