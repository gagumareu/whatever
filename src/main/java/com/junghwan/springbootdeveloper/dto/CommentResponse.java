package com.junghwan.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.junghwan.springbootdeveloper.domain.Comment;
import com.junghwan.springbootdeveloper.domain.User;
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

    private String nickName;

    private String socialImg;

    private String profileImg;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public CommentResponse(Comment comment){
        this.articleId = comment.getArticle().getId();
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.author = comment.getAuthor();
        this.createdAt = comment.getCreatedAt();
    }

    public CommentResponse(Comment comment, User user){
        this.articleId = comment.getArticle().getId();
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.author = comment.getAuthor();
        this.createdAt = comment.getCreatedAt();
        this.nickName = user.getNickName();
        this.socialImg = user.getSocialImg();
        this.profileImg = user.getProfileImg();
    }

}
