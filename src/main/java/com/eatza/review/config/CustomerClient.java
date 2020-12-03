package com.eatza.review.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eatza.review.dto.CustomerDto;

@FeignClient(value="http://localhost:8003/customer")
public interface CustomerClient {

	@GetMapping("/get/{customerId}")
	public CustomerDto getCustomer(@PathVariable("customerId") long customerId);
}
