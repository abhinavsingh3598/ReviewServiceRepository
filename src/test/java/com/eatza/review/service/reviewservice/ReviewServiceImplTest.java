package com.eatza.review.service.reviewservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.eatza.review.config.CustomerClient;
import com.eatza.review.config.RestaurantClient;
import com.eatza.review.dto.CustomerDto;
import com.eatza.review.dto.RestaurantDto;
import com.eatza.review.dto.ReviewRequestDto;
import com.eatza.review.dto.ReviewUpdateDto;
import com.eatza.review.exception.ReviewException;
import com.eatza.review.model.Review;
import com.eatza.review.repository.ReviewRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ReviewServiceImplTest {

	@Mock
	ReviewRepository reviewRepository;

	@Mock
	CustomerClient customerClient;

	@Mock
	RestaurantClient restaurantClient;

	@InjectMocks
	ReviewServiceImpl reviewServiceImpl;

	@Mock
	private RestTemplate restTemplate;

	ReviewRequestDto reviewRequestDto;

	Review reviewDatabase;

	@Before
	public void setup() {

		reviewRequestDto = new ReviewRequestDto("Hello", 1l, 1l, 4);
		reviewDatabase = getReview(reviewRequestDto);
	}

	@Test
	public void testSaveReview() throws ReviewException {
		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review("any", 22l, 56l, 4));
		when(reviewRepository.findAll()).thenReturn(reviews);

		Mockito.when(reviewRepository.save(Mockito.any())).thenReturn(reviewDatabase);
		reviewServiceImpl.saveReview(reviewRequestDto);

		assertNotNull(reviews);
		assertEquals(1, reviews.size());

	}

	private Review getReview(ReviewRequestDto reviewRequestDto) {
		Review reviewDatabase = new Review();
		reviewDatabase.setCustomerId(reviewRequestDto.getCustomerId());
		reviewDatabase.setId(1l);
		reviewDatabase.setRating(reviewRequestDto.getRating());
		reviewDatabase.setRestaurantId(reviewRequestDto.getRestaurantId());
		reviewDatabase.setReviewDescription(reviewRequestDto.getReviewDescription());
		return reviewDatabase;
	}

	@Test(expected = ReviewException.class)
	public void testSaveReviewException() throws ReviewException {

		List<Review> reviews = new ArrayList<>();
		reviews.add(reviewDatabase);
		Mockito.when(reviewRepository.findAll()).thenReturn(reviews);
		reviewServiceImpl.saveReview(reviewRequestDto);
	

		
	}

	@Test
	public void testGetRestaurantReviews() throws ReviewException {
		long restaurantId = 1l;
		List<Review> reviews = new ArrayList<>();
		reviews.add(reviewDatabase);
		Mockito.when(reviewRepository.findAll()).thenReturn(reviews);
		reviewServiceImpl.getRestaurantReviews(restaurantId);

	}

	@Test
	public void testUpdateReview() throws ReviewException {
		ReviewUpdateDto reviewUpdateDto = new ReviewUpdateDto(1l, "reviewDesc", 4);
		long customerId = 1l;
		long restaurantId = 1l;
		Mockito.when(reviewRepository.findById(Mockito.any())).thenReturn(Optional.of(reviewDatabase));
		Mockito.when(reviewRepository.saveAndFlush(Mockito.any())).thenReturn(reviewDatabase);
		reviewServiceImpl.updateReview(reviewUpdateDto, customerId, restaurantId);
		assertNotNull(reviewUpdateDto);
		assertEquals(1, customerId);

	}

	@Test(expected = ReviewException.class)
	public void testUpdateReviewForCustomerException() throws ReviewException {
		ReviewUpdateDto reviewUpdateDto = new ReviewUpdateDto(1l, "reviewDesc", 4);
		long customerId = 11l;
		long restaurantId = 1l;
		Mockito.when(reviewRepository.findById(Mockito.any())).thenReturn(Optional.of(reviewDatabase));

		reviewServiceImpl.updateReview(reviewUpdateDto, customerId, restaurantId);
	}

	@Test(expected = ReviewException.class)
	public void testUpdateReviewForRestaurantException() throws ReviewException {
		ReviewUpdateDto reviewUpdateDto = new ReviewUpdateDto(1l, "reviewDesc", 4);
		long customerId = 1l;
		long restaurantId = 11l;
		Mockito.when(reviewRepository.findById(Mockito.any())).thenReturn(Optional.of(reviewDatabase));

		reviewServiceImpl.updateReview(reviewUpdateDto, customerId, restaurantId);
	}

//	@Test(expected = ReviewException.class)
//	public void testValidateCustomerDto() throws ReviewException {
//		Mockito.when(restTemplate.getForObject("anyurl", CustomerDto.class))
//				.thenThrow(new RestClientException("exception"));
//		reviewServiceImpl.saveReview(reviewRequestDto);
//	}

//	@Test(expected = ReviewException.class)  
//	public void testValidateRestaurantDto() throws ReviewException {
//		CustomerDto customerDto = new CustomerDto(1L, "Xyz", 23, "Registered");
//		Mockito.when(restTemplate.getForObject(Mockito.anyString(),Mockito.any())).thenReturn(customerDto);
////		Mockito.when(restTemplate.getForObject("any url", RestaurantDto.class))
////				.thenThrow(new ReviewException("restaurant not found"));
//		reviewServiceImpl.saveReview(reviewRequestDto);
//	}

}
