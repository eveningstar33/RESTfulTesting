package com.dgs.restfultesting.jsonassert;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {

	private String actualResponse = "{\"id\":1,\"name\":\"Book\",\"price\":10,\"quantity\":100}";
	
	@Test
	public void jsonAssert_StrictTrue_ExactMatchExceptForSpaces() throws JSONException { 
		
		String expectedResponse = "{\"id\":1, \"name\":\"Book\", \"price\":10, \"quantity\":100}";
		
		// When strict is true, you can have spaces, but the exact structure should match: id, name, price, quantity 
		
		JSONAssert.assertEquals(expectedResponse, actualResponse, true); 
	}
	
	@Test
	public void jsonAssert_StrictFalse() throws JSONException { 
		
		String expectedResponse = "{\"id\":1, \"name\":\"Book\", \"price\":10}";
		
		// When strict is false, JSONAssert checks only these values: id, name, price 
		
		JSONAssert.assertEquals(expectedResponse, actualResponse, false); 
	}
	
	@Test
	public void jsonAssert_WithoutEscapeCharacters() throws JSONException { 
		
		// The only reason why you should use escape characters is when you have space in your value: 
		// ex: String expectedResponse = "{id:1, name:\"Book 3\", price:10}";
		
		String expectedResponse = "{id:1, name:Book, price:10}";
				
		JSONAssert.assertEquals(expectedResponse, actualResponse, false); 
	}
}
