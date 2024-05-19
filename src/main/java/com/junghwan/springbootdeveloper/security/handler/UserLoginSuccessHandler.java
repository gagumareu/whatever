package com.junghwan.springbootdeveloper.security.handler;

import com.junghwan.springbootdeveloper.dto.UserAuthResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Log4j2
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private PasswordEncoder passwordEncoder;

    public UserLoginSuccessHandler(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserLoginSuccessHandler(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("------------------------------------");
        log.info("onAuthenticationSuccess");

        UserAuthResponseDTO userAuthResponseDTO = (UserAuthResponseDTO) authentication.getPrincipal();

        boolean formSocial =  userAuthResponseDTO.isSocial();

        log.info("should be update user's info: " + formSocial);

        boolean passwordResult = passwordEncoder.matches("1111", userAuthResponseDTO.getPassword());

        if (formSocial && passwordResult){
            redirectStrategy.sendRedirect(request, response, "/user/update?from=social");
        }

    }
}
