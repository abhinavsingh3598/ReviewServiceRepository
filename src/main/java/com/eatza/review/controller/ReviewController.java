package com.eatza.review.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.review.dto.ReviewRequestDto;
import com.eatza.review.dto.ReviewResponseDto;
import com.eatza.review.dto.ReviewUpdateDto;
import com.eatza.review.dto.ReviewsFetchDto;
import com.eatza.review.exception.ReviewException;
import com.eatza.review.service.reviewservice.ReviewService;

@RestController
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	private KafkaTemplate<String, ReviewResponseDto> kafkaTemplate;

	private static final String TOPIC = "ReviewsTopic";

	@PostMapping("/review")
	public ResponseEntity<String> provideReview(@RequestBody ReviewRequestDto reviewRequestDto) throws ReviewException {
		logger.debug("In place review method, calling the service");
		reviewService.saveReview(reviewRequestDto);
		logger.debug("Order Placed Successfully");

		return ResponseEntity.status(HttpStatus.OK).body("Review added successfully");

	}

	@GetMapping("/review/{restaurantId}")
	public ResponseEntity<List<ReviewsFetchDto>> getRestaurantReviews(@PathVariable Long restaurantId)
			throws ReviewException {
		logger.debug("In place review method, calling the service");
		List<ReviewsFetchDto> reviewList = reviewService.getRestaurantReviews(restaurantId);
		return ResponseEntity.status(HttpStatus.OK).body(reviewList);

	}

	@PutMapping("/update/{customerId}/{restaurantId}")
	public ResponseEntity<String> updateReview(@RequestBody ReviewUpdateDto reviewUpdateDto,
			@PathVariable long customerId, @PathVariable long restaurantId) throws ReviewException {

		logger.debug(" In updateReview method, calling service");
		ReviewResponseDto updatedReview = reviewService.updateReview(reviewUpdateDto, customerId, restaurantId);
		logger.debug("Returning back the object");
		kafkaTemplate.send(TOPIC, updatedReview);
		return ResponseEntity.status(HttpStatus.OK).body("Review updated successfully");

	}

}
