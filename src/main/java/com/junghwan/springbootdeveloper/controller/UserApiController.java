package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.domain.User;
import com.junghwan.springbootdeveloper.dto.AddUserRequest;
import com.junghwan.springbootdeveloper.dto.UpdateUserRequest;
import com.junghwan.springbootdeveloper.security.dto.UserSecurityDTO;
import com.junghwan.springbootdeveloper.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@RestController
@Log4j2
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{email}")
    public ResponseEntity<User> update(@PathVariable String email, @RequestBody UpdateUserRequest request){

        log.info(email);
        log.info(request);

        User updatedUser = userService.update(request, email);

        return ResponseEntity.ok().body(updatedUser);
    }



}
