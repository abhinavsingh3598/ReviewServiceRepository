package com.eatza.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantDto {

	private Long id;
	private String name;
	private String location;
	private String cuisine;
	private int budget;
	private double rating;

	public RestaurantDto(Long id, String name, String location, String cuisine, int budget, double rating) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.cuisine = cuisine;
		this.budget = budget;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "RestaurantDto [id=" + id + ", name=" + name + ", location=" + location + ", cuisine=" + cuisine
				+ ", budget=" + budget + ", rating=" + rating + "]";
	}

	
	
}
