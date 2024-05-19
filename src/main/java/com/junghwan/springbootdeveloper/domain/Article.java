package com.junghwan.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@ToString(exclude = "imageSet")
@Log4j2
public class Article extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    @Column(name = "writer", nullable = false)
    private String writer;

    @Column(name = "category", nullable = false)
    private String category;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonIgnore
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Article(String title, String content, String writer, String category){
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;
    }

    public void update(String title, String content, String writer, String category){
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;
    }

    @OneToMany(mappedBy = "article", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20)
    @JsonIgnore
    private Set<ArticleImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName, String s3Url){

        ArticleImage articleImage = ArticleImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .article(this)
                .ord(imageSet.size())
                .s3Url(s3Url)
                .build();
        imageSet.add(articleImage);
    }

    public void clearImages(){

        log.info("---- clear images -----");

        imageSet.forEach(articleImage -> articleImage.changeArticle(null));

        this.imageSet.clear();
    }


}
