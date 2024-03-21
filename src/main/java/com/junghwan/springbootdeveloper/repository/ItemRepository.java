package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select item FROM Item item where item.serial = :serial")
    Item findBySerial(long serial);

}
