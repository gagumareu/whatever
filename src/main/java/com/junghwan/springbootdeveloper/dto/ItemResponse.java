package com.junghwan.springbootdeveloper.dto;

import com.junghwan.springbootdeveloper.domain.Item;
import lombok.Getter;

@Getter
public class ItemResponse {

    private String department;
    private Long serial;
    private String name;

    public ItemResponse(Item item){
        this.department = item.getDepartment();
        this.serial = item.getSerial();
        this.name = item.getName();
    }

}
