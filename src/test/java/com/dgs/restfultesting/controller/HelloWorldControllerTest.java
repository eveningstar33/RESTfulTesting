package com.dgs.restfultesting.controller;

import static org.junit.Assert.assertEquals;

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

// This test is a Spring test and I only want to test HelloWorldController

@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void helloWorld_basic() throws Exception {
		
		// We build the request, it says: send the GET request to "/hello-world" and we want the data in application/json format
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/hello-world") 
				.accept(MediaType.APPLICATION_JSON); 
		
		MvcResult result = mockMvc.perform(request).andReturn();  
		
		// We need to verify if the response contains "Hello World"
		
		assertEquals("Hello World", result.getResponse().getContentAsString()); 
	}
}
