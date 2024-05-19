package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.dto.UpdateUserRequest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Log4j2
public class UserServiceTests {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Test
//    public void update(){
//
//        UpdateUserRequest request = UpdateUserRequest.builder()
//                        .nickName("testNickName2")
//                                .password(passwordEncoder.encode("wjdghks2")).build();
//
//        userService.update(request, "udekang@gmail.com");
//
//    }
}
