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

    public Item toEntity(){
        return Item.builder().department(department).serial(serial).name(name).build();
    }

}
