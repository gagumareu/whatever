package com.junghwan.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "serial", nullable = false)
    private Long serial;

    @Column(name = "name", nullable = false)
    private String name;

    @Builder
    public Item(String department, Long serial, String name){
        this.department = department;
        this.serial = serial;
        this.name = name;
    }





}
