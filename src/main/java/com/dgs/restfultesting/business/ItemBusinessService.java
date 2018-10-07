package com.dgs.restfultesting.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dgs.restfultesting.data.ItemRepository;
import com.dgs.restfultesting.model.Item;

@Component
public class ItemBusinessService {
	
	@Autowired
	private ItemRepository repository;

	public Item retrieveHardcodedItem() {
		return new Item(1, "Book", 10, 100); 
	}
	
	public List<Item> retrieveAllItems() {
		return repository.findAll();  
	}
}
