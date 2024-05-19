package com.junghwan.springbootdeveloper.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "article")
public class ArticleImage implements Comparable<ArticleImage>{

    @Id
    private String uuid;

    private String fileName;

    private int ord;

    private String s3Url;

    @ManyToOne
    private Article article;

    @Override
    public int compareTo(ArticleImage other) {
        return this.ord - other.ord;
    }

    public void changeArticle(Article article){
        this.article = article;
    }

}
