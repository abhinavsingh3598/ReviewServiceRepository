package com.eatza.review.service.reviewservice;

import java.util.List;

import com.eatza.review.dto.ReviewRequestDto;
import com.eatza.review.dto.ReviewResponseDto;
import com.eatza.review.dto.ReviewUpdateDto;
import com.eatza.review.dto.ReviewsFetchDto;
import com.eatza.review.exception.ReviewException;

public interface ReviewService {

	ReviewResponseDto saveReview(ReviewRequestDto reviewRequestDto) throws ReviewException;

	List<ReviewsFetchDto> getRestaurantReviews(Long restaurantId) throws ReviewException;

	ReviewResponseDto updateReview(ReviewUpdateDto reviewUpdateDto, long customerId, long restaurantId)
			throws ReviewException;

}
