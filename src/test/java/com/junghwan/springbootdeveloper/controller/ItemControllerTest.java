package com.junghwan.springbootdeveloper.controller;

<<<<<<< HEAD
import com.junghwan.springbootdeveloper.domain.Item;
import com.junghwan.springbootdeveloper.dto.AddItemRequest;
import com.junghwan.springbootdeveloper.dto.UpdateItemRequest;
import com.junghwan.springbootdeveloper.repository.ItemRepository;
import com.junghwan.springbootdeveloper.service.ItemService;
import org.junit.jupiter.api.Test;
=======
import com.junghwan.springbootdeveloper.repository.ItemRepository;
import com.junghwan.springbootdeveloper.service.ItemService;
>>>>>>> dc54783fc42fa3b199ea0f6acb17ba11ccc7c6b9
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
