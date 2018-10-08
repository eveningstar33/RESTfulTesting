package com.dgs.restfultesting.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dgs.restfultesting.business.ItemBusinessService;
import com.dgs.restfultesting.model.Item;

@RunWith(SpringRunner.class)             
@WebMvcTest(ItemController.class)  
public class ItemControllerTest {
	
	@Autowired
	private MockMvc mockMvc;    
	
	@MockBean
	private ItemBusinessService businessService;

	@Test
	public void dummyItem_basic() throws Exception {
				
		RequestBuilder request = MockMvcRequestBuilders
				.get("/dummy-item") 
				.accept(MediaType.APPLICATION_JSON); 
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":1,\"name\":\"Book\",\"price\":10,\"quantity\":100}"))
				.andReturn();  
	}
	
	/*
	How do we unit test the ItemCOntroller? The ItemController is now talking to a business service. When I write a unit test 
	for the ItemController I should not depend on the ItemBusinessService. How can I write a unit test for the ItemController
	without depending the data which is returned by the ItemBusinessService? We can do this with mocking. 
	*/
	
	@Test
	public void itemFromBusinessService_basic() throws Exception {
		
		// A mock by default for objects will return null. So we need to mock:
		
		when(businessService.retrieveHardcodedItem()).thenReturn(
				new Item(2, "iPhone", 1000, 10));
				
		RequestBuilder request = MockMvcRequestBuilders
				.get("/item-from-business-service") 
				.accept(MediaType.APPLICATION_JSON); 
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{id:2, name:iPhone, price:1000}"))
				.andReturn();  
		
		/*
		If we run this unit test now, it does not work. The reason it doesn't work is because busines service component will not
		be available for this unit test. We said to the @WebMvcTest to only load up the ItemController, it will not try to load up
		any bean other than ItemController. It only loads all the web stuff related to the ItemController. It will not load up
		the business service at all. This is a good thing because when we're testing the ItemController we don't want to test 
		the code inside the business service. So we are mocking the business service out and we are macking it return whaever value
		we want and checking wheter the response is as expected. We only want to test the code which is present inside 
		itemFromBusinessService() method. So we need to create a mock for the business service and Spring Boot helps us with @MockBean.
		*/ 
	}
	
	/*
	We write an unit test for Web Layer - Controller. Even though the controller is talking to a Business Layer which is
	talking to the database, the controller is talking to a mock of the busines service. So it doesn't really matter to it
	that the business service is talking to the database. So the fact that the business service is talking to the database 
	does not affect the unit test. 
	*/
	
	@Test
	public void retrieveAllItems_basic() throws Exception {
				
		when(businessService.retrieveAllItems()).thenReturn(
				Arrays.asList(new Item(2, "iPhone", 1000, 10),
						new Item(3, "Huawei", 500, 17)));
				
		RequestBuilder request = MockMvcRequestBuilders
				.get("/items") 
				.accept(MediaType.APPLICATION_JSON); 
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[{id:2, name:iPhone, price:1000}, {id:3, name:Huawei, price:500}]"))  // This will return an array back, so this data should be within an array
				.andReturn();  
	}	
	
	@Test
	public void retrieveAnItem_basic() throws Exception {
				
		when(businessService.retrieveAnItem(1002)).thenReturn(
				new Item(1002, "Notebook", 10, 40));
				
		RequestBuilder request = MockMvcRequestBuilders
				.get("/items/1002") 
				.accept(MediaType.APPLICATION_JSON); 
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{id:1002, name:Notebook, price:10}")) 
				.andReturn();  
	}
	
	@Test
	public void createItem() throws Exception {
				
		RequestBuilder request = MockMvcRequestBuilders
				.post("/items")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"name\":\"Book\",\"price\":10,\"quantity\":100}")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("/item/")))
				.andReturn();
	}
}
