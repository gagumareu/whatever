package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ItemViewController {

    private final ItemService itemService;

    @GetMapping("/items")
    public String getItems(Model model){

        return "/priceCard";
    }



}
