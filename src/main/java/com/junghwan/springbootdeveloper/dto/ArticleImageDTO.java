package com.junghwan.springbootdeveloper.dto;

import com.junghwan.springbootdeveloper.domain.ArticleImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleImageDTO {

    private String uuid;

    private String fileName;

    private int ord;

    private String s3url;


    public String getThumbnailUrl(){
        return "https://whatever-s3.s3.ap-northeast-2.amazonaws.com/" + "s_" + uuid + "_" + fileName;
    }

    public ArticleImageDTO(ArticleImage articleImage){
        this.uuid = articleImage.getUuid();
        this.fileName = articleImage.getFileName();
        this.s3url = articleImage.getS3Url();
    }

}
