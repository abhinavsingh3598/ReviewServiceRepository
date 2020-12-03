package com.eatza.review.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eatza.review.dto.RestaurantDto;

@FeignClient("http://localhost:8001/restaurant")
public interface RestaurantClient {

	@GetMapping("/get/{restaurantId}")
	public RestaurantDto getRestaurant(@PathVariable("restaurantId") long restaurantId);
	
}
