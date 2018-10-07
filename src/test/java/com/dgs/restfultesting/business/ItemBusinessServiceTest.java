package com.dgs.restfultesting.business;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.dgs.restfultesting.data.ItemRepository;
import com.dgs.restfultesting.model.Item;

@RunWith(MockitoJUnitRunner.class)
public class ItemBusinessServiceTest {
	
	@InjectMocks
	ItemBusinessService business;
	
	@Mock
	ItemRepository repository;
	
	/*
	Writig unit test for Business Layer
	Writing an unit test for business service is very simple. All that we are testing is the business logic in that. So what we
	are doing is mocking the repository making t return whatever value that we want and checking this out. 
	Business Layers are typically easier to unit test because I'm not related to Spring framework in any way here. We're not
	even launching up a context. All that we're doing is using the MockitoJUnitRunner and launching the whole thing up. All that
	we are doing is we are testing all the logic which is present retrieveAllItems() method from ItemBusinessService class.
	*/

	@Test
	public void retrieveAllItems_basic() {

		when(repository.findAll()).thenReturn(
				Arrays.asList(new Item(2, "iPhone", 1000, 10),
						new Item(3, "Huawei", 500, 17)));
		
		List<Item> items = business.retrieveAllItems();

		assertEquals(10000, items.get(0).getValue());
		assertEquals(8500, items.get(1).getValue());  
	}
}
