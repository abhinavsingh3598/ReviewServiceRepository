package com.eatza.review.service.reviewservice;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eatza.review.config.CustomerClient;
import com.eatza.review.config.RestaurantClient;
import com.eatza.review.dto.CustomerDto;
import com.eatza.review.dto.RestaurantDto;
import com.eatza.review.dto.ReviewRequestDto;
import com.eatza.review.dto.ReviewResponseDto;
import com.eatza.review.dto.ReviewUpdateDto;
import com.eatza.review.dto.ReviewsFetchDto;
import com.eatza.review.exception.ReviewException;
import com.eatza.review.model.Review;
import com.eatza.review.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

	@Autowired
	ReviewRepository reviewRepository;

	@Value("${restaurant.search.id.url}")
	private String restaurantServiceUrl;

	@Value("${customer.search.id.url}")
	private String customerServiceUrl;

	@Autowired
	CustomerClient customerClient;

	@Autowired
	RestaurantClient restaurantClient;

	@Autowired
	RestTemplate restTemplate;

	RestaurantDto restaurantDto;

	CustomerDto customerDto;

	@Override
	public ReviewResponseDto saveReview(ReviewRequestDto reviewRequestDto) throws ReviewException {
		logger.debug("In saveReview, creating object of customer to save");

		validateCustomerDto(reviewRequestDto.getCustomerId());

		validateRestaurantDto(reviewRequestDto.getRestaurantId());

		if (!reviewRepository.findAll().stream()
				.noneMatch(review -> review.getCustomerId() == reviewRequestDto.getCustomerId()
						&& review.getRestaurantId() == reviewRequestDto.getRestaurantId())) {
			throw new ReviewException("Customer Already added Review For this Restaurant you can update only");
		}

		Review review = new Review(reviewRequestDto.getReviewDescription(), reviewRequestDto.getCustomerId(),
				reviewRequestDto.getRestaurantId(), reviewRequestDto.getRating());
		logger.debug("calling repository save Review method");
		review = reviewRepository.save(review);

		return new ReviewResponseDto(review.getId(), review.getReviewDescription(), review.getCustomerId(),
				review.getRestaurantId(), review.getRating());
	}

	private RestaurantDto validateRestaurantDto(long restaurantId) throws ReviewException {
		RestaurantDto restaurantDtoValue;
		try {
			restaurantDtoValue = restTemplate.getForObject(restaurantServiceUrl + restaurantId, RestaurantDto.class);
		} catch (Exception e) {
			throw new ReviewException("Restaurant Not Found for Review Please Enter Valid RestaurantId");
		}
		return restaurantDtoValue;
	}

	private CustomerDto validateCustomerDto(long customerId) throws ReviewException {
		CustomerDto customerDtoValue;
		try {
			customerDtoValue = restTemplate.getForObject(customerServiceUrl + customerId, CustomerDto.class);
		} catch (Exception e) {
			throw new ReviewException("Customer Not Found for Review Please Enter Valid CustomerId");
		}
		return customerDtoValue;
	}

	@Override
	public List<ReviewsFetchDto> getRestaurantReviews(Long restaurantId) throws ReviewException {
		logger.debug("In getRestaurantReviews, creating object of customer to save");
		validateRestaurantDto(restaurantId);

		logger.debug("calling repository for fetching reviews");
		return reviewRepository.findAll().stream().filter(review -> review.getRestaurantId() == restaurantId)
				.map(review -> new ReviewsFetchDto(review.getId(), review.getReviewDescription(),
						review.getCustomerId(), review.getRating()))
				.collect(Collectors.toList());
	}

	@Override
	public ReviewResponseDto updateReview(ReviewUpdateDto reviewUpdateDto, long customerId, long restaurantId)
			throws ReviewException {
		logger.debug("In updateReview, creating object of customer to save");
		validateCustomerDto(customerId);
		validateRestaurantDto(restaurantId);

		Review review = reviewRepository.findById(reviewUpdateDto.getId())
				.orElseThrow(() -> new ReviewException("Review ID is Wrong"));

		if (review.getCustomerId() != customerId) {
			throw new ReviewException("Customer Id is not present for this review");
		}

		if (review.getRestaurantId() != restaurantId) {
			throw new ReviewException("Restaurant Id is not present for this review");
		}

		review.setReviewDescription(reviewUpdateDto.getReviewDescription());
		review.setRating(reviewUpdateDto.getRating());

		logger.debug("calling repository for updating Review method");
		review = reviewRepository.saveAndFlush(review);

		return new ReviewResponseDto(review.getId(), review.getReviewDescription(), review.getCustomerId(),
				review.getRestaurantId(), review.getRating());
	}

}
