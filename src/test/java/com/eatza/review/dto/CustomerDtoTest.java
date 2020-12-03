package com.eatza.review.dto;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class CustomerDtoTest {

	CustomerDto customerUpdateDto;

	@Before
	public void setUp() throws Exception {
		customerUpdateDto = new CustomerDto();
	}

	@Test
	public void testGetCustomerId() {
		customerUpdateDto.setCustomerId(1);
		assertNotNull(customerUpdateDto.getCustomerId());
	}

	@Test
	public void testGetCustomerName() {
		customerUpdateDto.setCustomerName("abhinav");
		assertNotNull(customerUpdateDto.getCustomerName());
	}

	@Test
	public void testGetAge() {
		customerUpdateDto.setAge(12);
		assertNotNull(customerUpdateDto.getAge());
	}

	@Test
	public void testGetStatus() {
		customerUpdateDto.setStatus("shsh");
		assertNotNull(customerUpdateDto.getStatus());
	}

}
