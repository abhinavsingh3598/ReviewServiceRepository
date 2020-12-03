package com.eatza.review.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RestaurantDtoTest {

	RestaurantDto restaurantDto;

	@Before
	public void setUp() throws Exception {
		restaurantDto = new RestaurantDto();
	}

	@Test
	public void testGetId() {
		restaurantDto.setId(1l);
		assertNotNull(restaurantDto.getId());

	}

	@Test
	public void testGetName() {
		restaurantDto.setName("rr");
		assertNotNull(restaurantDto.getName());
	}

	@Test
	public void testGetLocation() {
		restaurantDto.setLocation("delhi");
		assertNotNull(restaurantDto.getLocation());
	}

	@Test
	public void testGetCuisine() {
		restaurantDto.setCuisine("ahsh");
		assertNotNull(restaurantDto.getCuisine());
	}

	@Test
	public void testGetBudget() {
		restaurantDto.setBudget(12);
		assertNotNull(restaurantDto.getBudget());
	}

	@Test
	public void testGetRating() {
		restaurantDto.setRating(2.70);
		assertNotNull(restaurantDto.getRating());
	}

}
