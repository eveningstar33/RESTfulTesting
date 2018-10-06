package com.dgs.restfultesting.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
}
