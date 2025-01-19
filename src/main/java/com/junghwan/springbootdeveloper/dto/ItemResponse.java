package com.junghwan.springbootdeveloper.dto;

import com.junghwan.springbootdeveloper.domain.Item;
import lombok.Getter;

@Getter
public class ItemResponse {

    private String department;
    private Long serial;
    private String name;
    private String importCode;
    private String material;
    private String color;
    private String size;
    private Long price;

    public ItemResponse(Item item){
        this.department = item.getDepartment();
        this.serial = item.getSerial();
        this.name = item.getName();
        this.importCode = item.getImportCode();
        this.material = item.getMaterial();
        this.color = item.getColor();
        this.size = item.getSize();
        this.price = item.getPrice();
    }

}
