package com.junghwan.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Comment", indexes = {@Index(name = "idx_comment_article_id", columnList = "article_id")})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString(exclude = "article")
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @Column(length = 1000)
    private String comment;

    private String author;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;

    public void update(String comment){
        this.comment = comment;
    }

}
