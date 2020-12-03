package com.eatza.review.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String reviewDescription;
	private Long customerId;
	private Long restaurantId;
	private float rating;

	public Review(Long id, String reviewDescription, Long customerId, Long restaurantId, float rating) {
		super();
		this.id = id;
		this.reviewDescription = reviewDescription;
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.rating = rating;
	}

	public Review(String reviewDescription, Long customerId, Long restaurantId, float rating) {
		super();
		this.reviewDescription = reviewDescription;
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewDescription=" + reviewDescription + ", customerId=" + customerId
				+ ", restaurantId=" + restaurantId + ", rating=" + rating + "]";
	}

	
	
}
