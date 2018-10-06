package com.dgs.restfultesting.business;

import org.springframework.stereotype.Component;

import com.dgs.restfultesting.model.Item;

@Component
public class ItemBusinessService {

	public Item retrieveHardcodedItem() {
		return new Item(1, "Book", 10, 100); 
	}

}
