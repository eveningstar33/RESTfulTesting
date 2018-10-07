package com.dgs.restfultesting.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgs.restfultesting.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
