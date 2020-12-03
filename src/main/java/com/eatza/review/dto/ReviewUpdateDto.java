package com.eatza.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewUpdateDto {

	private Long id;
	private String reviewDescription;
	private float rating;

	public ReviewUpdateDto(Long id, String reviewDescription, float rating) {
		super();
		this.id = id;
		this.reviewDescription = reviewDescription;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "ReviewUpdateDto [id=" + id + ", reviewDescription=" + reviewDescription + ", rating=" + rating + "]";
	}

	
	
}
