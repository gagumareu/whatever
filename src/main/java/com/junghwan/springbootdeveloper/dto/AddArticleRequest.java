package com.junghwan.springbootdeveloper.dto;

import com.junghwan.springbootdeveloper.domain.Article;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.net.URLDecoder;
import java.util.List;

@Data
@Builder
public class AddArticleRequest {

    @Size(min = 5,max = 500)
    private String title;
    @Size(max = 10000)
    private String content;
    private String writer;
    private String category;

    private List<String> fileNames;

    public Article toEntity(){

        Article article = Article.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .category(category).
                build();

        if (this.fileNames != null){
            this.fileNames.forEach(fileName -> {
                String uuid = fileName.substring(55, 91);
//                String name = URLDecoder.decode(fileName.substring(92));
                String name = (fileName.substring(92));
                article.addImage(uuid, name, fileName);
            });
        }

        return article;
    }



}
