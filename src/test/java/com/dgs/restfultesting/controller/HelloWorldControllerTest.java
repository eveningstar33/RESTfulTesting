package com.dgs.restfultesting.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// I only want to load and test HelloWorldController. Spring Mock Mvc framework help us to do that 

@RunWith(SpringRunner.class)              // We launched up a Spring context
@WebMvcTest(HelloWorldController.class)   // We only launched up the HelloWorldController
public class HelloWorldControllerTest {
	
	@Autowired
	private MockMvc mockMvc;                       // We use the MockMvc framework

	@Test
	public void helloWorld_basic() throws Exception {
		
		// We build the request, it says: send the GET request to "/hello-world" and we want the data in application/json format
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/hello-world") 
				.accept(MediaType.APPLICATION_JSON); 
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Hello World"))
				.andReturn();  
		
		// We need to verify if the response contains "Hello World"
		
		// assertEquals("Hello World", result.getResponse().getContentAsString()); 
	}
}
