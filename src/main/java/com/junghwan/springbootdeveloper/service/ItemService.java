package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.domain.Item;
import com.junghwan.springbootdeveloper.dto.AddItemRequest;
import com.junghwan.springbootdeveloper.dto.UpdateItemRequest;
import com.junghwan.springbootdeveloper.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Log4j2
public class ItemService {

    private final ItemRepository itemRepository;

    public Item save(AddItemRequest request){
       log.info("request: " + request);
        return itemRepository.save(request.toEntity());
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Item findById(Long id){
        log.info("------ findById -------");
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(Long id){
        itemRepository.deleteById(id);
    }

    @Transactional
    public Item update(long id, UpdateItemRequest request){

        log.info("------------ findById --------------");
        log.info("request: " + request);

        Item item = itemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("not found: " + id));

        log.info("serial1: " + item.getSerial());
        log.info("name1: " + item.getName());

        log.info("------------ update --------------");

        log.info("name2: " + request.getName());
        log.info("serial2: " + request.getSerial());
        log.info("department2: " + request.getDepartment());
        log.info("price: " + request.getPrice());

        item.update(
                request.getDepartment(),
                request.getSerial(),
                request.getName(),
                request.getImportCode(),
                request.getMaterial(),
                request.getColor(),
                request.getSize(),
                request.getPrice());

        return item;

//        return itemRepository.save(item);
    }

}
