package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
