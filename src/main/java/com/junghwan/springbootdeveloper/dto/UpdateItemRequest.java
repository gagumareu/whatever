package com.junghwan.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateItemRequest {

    private String department;
    private Long serial;
    private String name;
    private String importCode;
    private String material;
    private String color;
    private String size;
    private Long price;

}
