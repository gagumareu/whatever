package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.repository.ItemRepository;
import com.junghwan.springbootdeveloper.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemControllerTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

//    @Test
//    public void update(){
//
//        Item item = itemService.findById(44L);
//
//        UpdateItemRequest request = new UpdateItemRequest(
//                item.getDepartment(),
//                123123L,
//                "이름수정3999999",
//                item.getImportCode(),
//                item.getMaterial(),
//                item.getColor(),
//                item.getSize(),
//                item.getPrice());
//
//        itemService.update(44L, request);
//
//    }

}
