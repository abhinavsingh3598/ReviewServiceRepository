package com.eatza.review.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReviewResponseDtoTest {

	ReviewResponseDto responseDto;
	
	@Before
	public void setUp() throws Exception {
		responseDto=new ReviewResponseDto();
	}

	@Test
	public void testGetId() {
		responseDto.setId(1l);
		assertNotNull(responseDto.getId());
	}

	@Test
	public void testGetReviewDescription() {
		responseDto.setReviewDescription("shdh");
		assertNotNull(responseDto.getReviewDescription());
	}

	@Test
	public void testGetCustomerId() {
		responseDto.setCustomerId(1l);
		assertNotNull(responseDto.getCustomerId());
	}

	@Test
	public void testGetRestaurantId() {
		responseDto.setRestaurantId(10l);
		assertNotNull(responseDto.getRestaurantId());
	}

	@Test
	public void testGetRating() {
		responseDto.setRating(10.9f);
		assertNotNull(responseDto.getRating());
	}

}
