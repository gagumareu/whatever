package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.domain.Item;
import com.junghwan.springbootdeveloper.dto.AddItemRequest;
import com.junghwan.springbootdeveloper.dto.UpdateItemRequest;
import com.junghwan.springbootdeveloper.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Item findBySerial(long serial){
        return itemRepository.findBySerial(serial);
    }

    public void delete(Long id){
        itemRepository.deleteById(id);
    }

    @Transactional
    public Item update(long id, UpdateItemRequest request){

        Item item = itemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("not found: " + id));

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

    }

}
