package com.junghwan.springbootdeveloper.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class UserViewController {

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

        log.info("------ user/update -----");
        log.info(from);

        return "user/update";

//        if (social){
//            return "user/update";
//        }else {
//            return "article/articles";
//        }


    }

}
