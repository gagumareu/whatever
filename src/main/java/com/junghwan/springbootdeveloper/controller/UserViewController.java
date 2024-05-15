package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.dto.AddUserRequest;
import com.junghwan.springbootdeveloper.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
public class UserViewController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(String error, String logout){

       log.info("login .........");
       log.info("logout: " + logout);

        return "user/login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "user/signup";
    }

    @GetMapping("/user")
    public String user(){


        return "user/user";
    }

    @GetMapping("/user/update")
    public String updateInfo(@RequestParam(required = false) String from){

        log.info("------ update -----");
        log.info(from);

        return "user/update";

    }

    @PostMapping("/user")
    public String signup(AddUserRequest request, RedirectAttributes redirectAttributes){

        log.info("signup.....");
        log.info(request);

        try {
            userService.signup(request);
        } catch (UserService.UseridExistException e) {
            redirectAttributes.addFlashAttribute("error", "userId");
            return "redirect:/signup";
        }

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/login";
    }


}
