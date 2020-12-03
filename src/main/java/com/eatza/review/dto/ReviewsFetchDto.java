package com.eatza.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewsFetchDto {

	private Long id;
	private String reviewDescription;
	private Long customerId;
	private float rating;

	public ReviewsFetchDto(Long id, String reviewDescription, Long customerId, float rating) {
		super();
		this.id = id;
		this.reviewDescription = reviewDescription;
		this.customerId = customerId;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "ReviewsFetchDto [id=" + id + ", reviewDescription=" + reviewDescription + ", customerId=" + customerId
				+ ", rating=" + rating + "]";
	}

	
	
}
