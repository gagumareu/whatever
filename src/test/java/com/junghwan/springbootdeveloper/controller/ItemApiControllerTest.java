package com.junghwan.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< HEAD
import com.junghwan.springbootdeveloper.domain.Item;
import com.junghwan.springbootdeveloper.dto.AddItemRequest;
import com.junghwan.springbootdeveloper.dto.UpdateItemRequest;
import com.junghwan.springbootdeveloper.repository.ItemRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

=======
import com.junghwan.springbootdeveloper.repository.ItemRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

>>>>>>> dc54783fc42fa3b199ea0f6acb17ba11ccc7c6b9
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
<<<<<<< HEAD
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.junit.jupiter.api.Assertions.*;
=======
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

>>>>>>> dc54783fc42fa3b199ea0f6acb17ba11ccc7c6b9
@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class ItemApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        itemRepository.deleteAll();
    }

//    @DisplayName("addItem: 제품 추가 성공한다.")
//    @Test
//    public void addItem() throws Exception{
//
//        // given
//        final String url = "/api/item";
//        final String department = "113";
//        final Long serial = 999999L;
//        final String name = "test_name";
//        final String importCode = "aab4dda34a";
//        final String material = "steel";
//        final String color = "white_gray";
//        final String size = "w43xd30xh30";
//        final long price = 4555000L;
//        final AddItemRequest userRequest = new AddItemRequest(
//                department, serial, name, importCode, material, color, size, price);
//
//        final String requestBody = objectMapper.writeValueAsString(userRequest);
//
//        ResultActions result = mockMvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(requestBody));
//
//        result.andExpect(status().isCreated());
//
//        List<Item> items = itemRepository.findAll();
//
//        assertThat(items.size()).isEqualTo(1);
//        assertThat(items.get(0).getDepartment()).isEqualTo(department);
//        assertThat(items.get(0).getSerial()).isEqualTo(serial);
//        assertThat(items.get(0).getImportCode()).isEqualTo(importCode);
//        assertThat(items.get(0).getMaterial()).isEqualTo(material);
//        assertThat(items.get(0).getColor()).isEqualTo(color);
//        assertThat(items.get(0).getSize()).isEqualTo(size);
//        assertThat(items.get(0).getPrice()).isEqualTo(price);
//
//    }
//
//    @DisplayName("item : 상품들 정보 불러오기 성공한다.")
//    @Test
//    public void findAllItems() throws Exception{
//
//        // given 블로그 글을 저장한다.
//        final String url = "/api/items";
//        final String department = "113";
//        final long serial = 123123123L;
//        final String name = "테스트 상품";
//        final String importCode = "abc4dd5s4";
//        final String material = "steel";
//        final String color = "light Gray";
//        final String size = "w40xd42xh24";
//        final long price = 1200000L;
//
//        itemRepository.save(Item.builder()
//                .department(department)
//                .serial(serial)
//                .name(name)
//                .importCode(importCode)
//                .material(material)
//                .color(color)
//                .size(size)
//                .price(price)
//                .build());
//
//        final ResultActions resultActions = mockMvc.perform(get(url)
//                .accept(MediaType.APPLICATION_JSON));
//
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].department").value(department))
//                .andExpect(jsonPath("$[0].serial").value(serial))
//                .andExpect(jsonPath("$[0].name").value(name))
//                .andExpect(jsonPath("$[0].importCode").value(importCode))
//                .andExpect(jsonPath("$[0].material").value(material))
//                .andExpect(jsonPath("$[0].color").value(color))
//                .andExpect(jsonPath("$[0].size").value(size))
//                .andExpect(jsonPath("$[0].price").value(price));
//
//    }
//
//    @DisplayName("findItem: 아이템 조회에 성공한다.")
//    @Test
//    public void findItem()throws Exception{
//
//        // given
//        final String url = "/api/item/{id}";
//        final String department = "113";
//        final long serial = 333333333L;
//        final String name = "테스트 상품";
//        final String importCode = "abc3333";
//        final String material = "steel";
//        final String color = "light Gray";
//        final String size = "w40xd42xh24";
//        final long price = 3333333L;
//
//        Item savedItem = itemRepository.save(Item.builder()
//                .department(department)
//                .serial(serial)
//                .name(name)
//                .importCode(importCode)
//                .material(material)
//                .color(color)
//                .size(size)
//                .price(price).build());
//
//        // when
//        final ResultActions resultActions = mockMvc.perform(get(url, savedItem.getId()));
//
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.department").value(department))
//                .andExpect(jsonPath("$.serial").value(serial))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.importCode").value(importCode))
//                .andExpect(jsonPath("$.material").value(material))
//                .andExpect(jsonPath("$.color").value(color))
//                .andExpect(jsonPath("$.size").value(size))
//                .andExpect(jsonPath("$.price").value(price));
//    }
//
//    @DisplayName("deleteItem: 아이템 삭제에 성공한다.")
//    @Test
//    public void deleteItem()throws Exception{
//
//        // given
//        final String url = "/api/item/{id}";
//        final String department = "113";
//        final long serial = 333333333L;
//        final String name = "테스트 상품";
//        final String importCode = "abc3333";
//        final String material = "steel";
//        final String color = "light Gray";
//        final String size = "w40xd42xh24";
//        final long price = 3333333L;
//
//        Item savedItem = itemRepository.save(Item.builder()
//                .department(department)
//                .serial(serial)
//                .name(name)
//                .importCode(importCode)
//                .material(material)
//                .color(color)
//                .size(size)
//                .price(price).build());
//
//        // when
//        mockMvc.perform(delete(url, savedItem.getId()))
//                .andExpect(status().isOk());
//
//        // then
//        List<Item> items = itemRepository.findAll();
//
//        assertThat(items).isEmpty();
//
//    }
//
//    @DisplayName("updateItem: 아이템 정보 수정 성공한다.")
//    @Test
//    public void updateItem() throws Exception{
//
//        // given
//        final String url = "/api/item/{id}";
//        final String department = "9990";
//        final long serial = 9999999123L;
//        final String name = "테스트 상품990";
//        final String importCode = "abc9990";
//        final String material = "steel990";
//        final String color = "light Gray990";
//        final String size = "w40xd42xh24990";
//        final long price = 99999L;
//
//        Item savedItem = itemRepository.save(Item.builder()
//                .department(department)
//                .serial(serial)
//                .name(name)
//                .importCode(importCode)
//                .material(material)
//                .color(color)
//                .size(size)
//                .price(price).build());
//
//        final String newName = "테스트 상품 수정 123";
//        final String newColor = "블랙앤화이트123";
//        final String newMaterial = "pol123";
//
//        UpdateItemRequest request = new UpdateItemRequest(
//                department,
//                serial,
//                newName,
//                importCode,
//                newMaterial,
//                newColor,
//                size,
//                price);
//
//        log.info("name : " + request.getName());
//        log.info("serial : " + request.getSerial());
//
//        // when
//        ResultActions result = mockMvc.perform(put(url, savedItem.getId())
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(objectMapper.writeValueAsString(request)));
//
//        // then
//        result.andExpect(status().isOk());
//
//        log.info("----------result---------- " + result);
//
//        Item item = itemRepository.findById(savedItem.getId()).get();
//
//        log.info("item: " + item);
//
//        assertThat(item.getName()).isEqualTo(newName);
//        assertThat(item.getMaterial()).isEqualTo(newMaterial);
//        assertThat(item.getColor()).isEqualTo(newColor);
//
//    }





}