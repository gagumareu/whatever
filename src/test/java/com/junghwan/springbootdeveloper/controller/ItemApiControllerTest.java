package com.junghwan.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junghwan.springbootdeveloper.domain.Item;
import com.junghwan.springbootdeveloper.dto.AddItemRequest;
import com.junghwan.springbootdeveloper.repository.ItemRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
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

    @DisplayName("addItem: 제품 추가 성공한다.")
    @Test
    public void addItem() throws Exception{

        // given
        final String url = "/api/item";
        final String department = "113";
        final Long serial = 999999L;
        final String name = "test_name";
        final AddItemRequest userRequest = new AddItemRequest(department, serial, name);

        final String requestBody = objectMapper.writeValueAsString(userRequest);

        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isCreated());

        List<Item> items = itemRepository.findAll();

        assertThat(items.size()).isEqualTo(1);
        assertThat(items.get(0).getDepartment()).isEqualTo(department);
        assertThat(items.get(0).getSerial()).isEqualTo(serial);
        assertThat(items.get(0).getName()).isEqualTo(name);

    }

    @DisplayName("item : 상품들 정보 불러오기 성공한다.")
    @Test
    public void findAllItems() throws Exception{

        // given 블로그 글을 저장한다.
        final String url = "/api/items";
        final String department = "113";
        final Long serial = 123123123L;
        final String name = "테스트 상품";

        itemRepository.save(Item.builder()
                .department(department)
                .serial(serial)
                .name(name)
                .build());

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].department").value(department))
                .andExpect(jsonPath("$[0].serial").value(serial))
                .andExpect(jsonPath("$[0].name").value(name));

    }




}