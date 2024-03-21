package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.domain.Item;
import com.junghwan.springbootdeveloper.dto.AddItemRequest;
import com.junghwan.springbootdeveloper.dto.ItemResponse;
import com.junghwan.springbootdeveloper.dto.UpdateItemRequest;
import com.junghwan.springbootdeveloper.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Log4j2
public class ItemApiController {

    private final ItemService itemService;

    @PostMapping("/api/item")
    public ResponseEntity<Item> addItem(@RequestBody AddItemRequest request){
       log.info("request: " + request);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(request));
    }

    @GetMapping("/api/items")
    public ResponseEntity<List<ItemResponse>> findAll(){
        List<ItemResponse> items = itemService.findAll().stream().map(ItemResponse::new).toList();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/api/item/{id}")
    public ResponseEntity<ItemResponse> findById(@PathVariable Long id){
        Item item = itemService.findById(id);
        return ResponseEntity.ok().body(new ItemResponse(item));
    }

    @DeleteMapping("/api/item/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        itemService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/item/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody UpdateItemRequest request){

        Item updatedItem = itemService.update(id, request);

        return ResponseEntity.ok().body(updatedItem);
    }

}
