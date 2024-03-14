package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.domain.Item;
import com.junghwan.springbootdeveloper.dto.AddItemRequest;
import com.junghwan.springbootdeveloper.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemControllerTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void addItem2(){

        AddItemRequest item = new AddItemRequest();

        item.setDepartment("112");
        item.setSerial(45456777789L);
        item.setName("가위");

        Item result = itemService.save(item);

        System.out.println(result);
    }

}
