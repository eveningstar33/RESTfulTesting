package com.dgs.restfultesting.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.dgs.restfultesting.business.ItemBusinessService;
import com.dgs.restfultesting.model.Item;

@RestController
public class ItemController {
	
	@Autowired
	private ItemBusinessService businessService;

	@GetMapping("/dummy-item")
	public Item dummyItem() {
		return new Item(1, "Book", 10, 100);
	}
	
	@GetMapping("/item-from-business-service")
	public Item itemFromBusinessService() {
		return businessService.retrieveHardcodedItem();
	}
	
	@GetMapping("/items")
	public List<Item> retrieveAllItems() {
		return businessService.retrieveAllItems(); 
	}
	
	@GetMapping("/items/{id}")
	public Item retrieveAnItem(@PathVariable int id) {
		return businessService.retrieveAnItem(id); 
	}
	
	@PostMapping("/items")
	public ResponseEntity<Object> addItem(@RequestBody Item item) {
		Item savedItem = businessService.addAnItem(item); 
        HttpHeaders httpHeaders = new HttpHeaders();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        
        UriComponents uriComponents =
                uriComponentsBuilder.path("/item/").buildAndExpand("/item/");
        httpHeaders.setLocation(uriComponents.toUri());
                        
		return new ResponseEntity<>(savedItem, httpHeaders, HttpStatus.CREATED); 
	}
}
