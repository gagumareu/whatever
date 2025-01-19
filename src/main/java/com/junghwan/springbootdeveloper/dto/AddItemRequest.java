package com.junghwan.springbootdeveloper.dto;

import com.junghwan.springbootdeveloper.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest {

    private String department;
    private Long serial;
    private String name;
    private String importCode;
    private String material;
    private String color;
    private String size;
    private Long price;


    public Item toEntity(){
        return Item.builder()
                .department(department)
                .serial(serial)
                .name(name)
                .importCode(importCode)
                .material(material)
                .color(color)
                .size(size)
                .price(price)
                .build();
    }

}
