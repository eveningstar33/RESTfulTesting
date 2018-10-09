package com.dgs.restfultesting.business;

import java.util.List;
import java.util.Optional;

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
						
		List<Item> items = repository.findAll(); 
				
		for (Item item : items) {
			item.setValue(item.getPrice() * item.getQuantity());  
		}
		
		return items;  
	}
	
	public Item retrieveAnItem(int id) {
		
		Optional<Item> optionalItem = repository.findById(id);
		Item item = optionalItem.get();
		
		return item;
	}
	
	public Item addAnItem(String name, int price, int quantity) {
		
		Item item = new Item(name, price, quantity);
				
	    repository.save(item);
	    
		item.setValue(item.getPrice() * item.getQuantity()); 

		return item; 
	}
}
