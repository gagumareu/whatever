package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.domain.Item;
import com.junghwan.springbootdeveloper.dto.AddItemRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void addItem(){

        AddItemRequest request = new AddItemRequest("1123", 99929L, "test2");

        Item result = itemService.save(request);

        System.out.println(result);

    }

}
