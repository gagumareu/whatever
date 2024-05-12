package com.junghwan.springbootdeveloper.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRequest {

    private String nickName;
    private String password;
    private String profileImg;

}
