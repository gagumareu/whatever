package com.junghwan.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Log4j2
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "serial", nullable = false, unique = true)
    private Long serial;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "import_code", nullable = true)
    private String importCode;

    @Column(name = "material", nullable = true)
    private String material;

    @Column(name = "color", nullable = true)
    private String color;

    @Column(name = "size", nullable = true)
    private String size;

    @Column(name = "price", nullable = false)
    private Long price;


    @Builder
    public Item(String department, long serial, String name, String importCode, String material, String color, String size, long price){
        this.department = department;
        this.serial = serial;
        this.name = name;
        this.importCode = importCode;
        this.material = material;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public void update(
            String department,
            long serial,
            String name,
            String importCode,
            String material,
            String color,
            String size,
            long price){

        this.department = department;
        this.serial = serial;
        this.name = name;
        this.importCode = importCode;
        this.material = material;
        this.color = color;
        this.size = size;
        this.price = price;
    }




}
