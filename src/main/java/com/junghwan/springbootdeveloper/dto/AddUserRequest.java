package com.junghwan.springbootdeveloper.dto;

import com.junghwan.springbootdeveloper.domain.User;
import lombok.Data;

@Data
public class AddUserRequest {

    private String userId;
    private String email;
    private String password;
    private boolean del;
    private boolean social;

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .email(email)
                .password(password)
                .del(false)
                .social(false)
                .build();
    }

}
