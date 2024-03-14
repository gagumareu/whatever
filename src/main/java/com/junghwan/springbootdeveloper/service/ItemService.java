package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.domain.Item;
import com.junghwan.springbootdeveloper.dto.AddItemRequest;
import com.junghwan.springbootdeveloper.repository.ItemRepository;
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

}
