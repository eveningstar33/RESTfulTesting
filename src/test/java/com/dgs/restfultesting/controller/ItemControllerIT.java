package com.dgs.restfultesting.controller;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/*
Writing an Integration Test using @SpringBootTest
This launches up the entire Spring Boot application which is present in here. This RestfulTestingApplication is completely
launched up when we run this test. @SpringBootTest does all the magic. 
The package of ItemControllerIT is com.dgs.restfultesting.controller. @SpringBootTest starts looking at this packages and 
the parents of these packages for classes which have the annotation @SpringBootApplication. First it searches the package 
com.dgs.restfultesting.controller and it will not find anything annotated with @SpringBootApplication, then it goes to this
package com.dgs.restfultesting and it finds the Spring Boot app: RestfulTestingApplication. It launches the app so it launches
the entire Spring Boot app context. When you launch this context up, all the components which are present in src/main/java, 
the components, the services, the repositories will launched up, also it will launch up the in memory database and the data that 
are present in src/main/resources/data.sql as well as src/test/resources/data.sql
*/

/*
We are configuring a web environment and we want to run it on random port.
The reason we are using random port is because if we are using a continous integration server. That's where the unit tests will
running. When we comit some changes the tests are running on the continous integration server, we don't want 2 applications to
be using the same port. And the random port will help for that. And SpringBootTest will pick up an available port and use it 
to run the web application. And the unit test will not fail because that port is in use by other application. 
*/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)        
public class ItemControllerIT {
	
	@Autowired
	private TestRestTemplate restTemplate;   // It allows us to make calls

	@Test
	public void contextLoads_CheckIds() throws JSONException { 
		
		String response = this.restTemplate.getForObject("/all-items-from-database", String.class);
		JSONAssert.assertEquals("[{id:1001}, {id:1002}, {id:1003}]", response, false);
	}
	
	@Test
	public void contextLoads_CheckIdsNamesPrices() throws JSONException { 
		
		String response = this.restTemplate.getForObject("/all-items-from-database", String.class);
		JSONAssert.assertEquals("[{id:1001, name:Pen, price:15}, {id:1002, name:Notebook, price:10}, {id:1003, name:Pencil, price:5}]", 
				response, false);
	}
	
	@Test
	public void contextLoads_CheckValues() throws JSONException { 
		
		String response = this.restTemplate.getForObject("/all-items-from-database", String.class);
		JSONAssert.assertEquals("[{value:300}, {value:400}, {value:75}]", 
				response, false);
	}
}
