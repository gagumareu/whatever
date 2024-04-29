package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ItemViewController {

    private final ItemService itemService;

    @GetMapping("/priceCard")
    public String getItems(Model model){
        return "muji/priceCard";
    }

    @GetMapping("/foodExpiry")
    public String foodExpiry(){
        return "muji/foodExpiry";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/notice")
    public String notice(){
        return "muji/notice";
    }





}
