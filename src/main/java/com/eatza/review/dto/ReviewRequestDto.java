package com.eatza.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {

	private String reviewDescription;
	private Long customerId;
	private Long restaurantId;
	private float rating;

	public ReviewRequestDto(String reviewDescription, Long customerId, Long restaurantId, float rating) {
		super();
		this.reviewDescription = reviewDescription;
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "ReviewRequestDto [reviewDescription=" + reviewDescription + ", customerId=" + customerId
				+ ", restaurantId=" + restaurantId + ", rating=" + rating + "]";
	}

	
}
