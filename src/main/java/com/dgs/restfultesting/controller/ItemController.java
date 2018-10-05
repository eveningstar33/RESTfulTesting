package com.dgs.restfultesting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgs.restfultesting.model.Item;

@RestController
public class ItemController {

	@GetMapping("dummy-item")
	public Item dummyItem() {
		return new Item(1, "Book", 10, 100);
	}
}
