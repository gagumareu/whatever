package com.junghwan.springbootdeveloper.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class UpdateCommentRequest {

    private Long id;
    private String comment;


}
