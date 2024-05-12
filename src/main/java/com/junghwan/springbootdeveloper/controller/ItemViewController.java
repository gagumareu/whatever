package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ItemViewController {

    private final ItemService itemService;

    @GetMapping("/items")
    public String getItems(Model model){
        return "muji/priceCard";
    }

    @GetMapping("/")
    public String home(){
        return "muji/priceCard";
    }

    @GetMapping("/foodExpiry")
    public String foodExpiry(){
        return "muji/foodExpiry";
    }

    @GetMapping("/article")
    public String article(){
        return "muji/article";
    }

    @GetMapping("/notice")
    public String notice(){
        return "muji/notice";
    }





}
