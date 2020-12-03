package com.eatza.review.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eatza.review.dto.ReviewRequestDto;
import com.eatza.review.dto.ReviewResponseDto;
import com.eatza.review.dto.ReviewUpdateDto;
import com.eatza.review.dto.ReviewsFetchDto;
import com.eatza.review.service.reviewservice.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ReviewController.class)
public class ReviewControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ReviewService reviewService;

	@Autowired
	private ObjectMapper objectMapper;

	ReviewRequestDto reviewRequestDto;

	@MockBean
	private KafkaTemplate<String, ReviewResponseDto> kafkaTemplate;

	@Before
	public void setUp() throws Exception {
		reviewRequestDto = new ReviewRequestDto("any", 1L, 1L, 3);
	}

	@Test
	public void testAddReview() throws Exception {
		ReviewRequestDto reviewRequestDto = new ReviewRequestDto("any", 1L, 1L, 3);
		when(reviewService.saveReview(reviewRequestDto)).thenReturn(new ReviewResponseDto());
		RequestBuilder request = MockMvcRequestBuilders.post("/review").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((reviewRequestDto)));

		mockMvc.perform(request).andExpect(status().is(200)).andExpect(content().string("Review added successfully"))
				.andReturn();
	}

	@Test
	public void testGetRestaurantReviews() throws Exception {
		List<ReviewsFetchDto> reviewList = new ArrayList<ReviewsFetchDto>();
		reviewList.add(new ReviewsFetchDto(1L, "any", 1L, 3));
		when(reviewService.getRestaurantReviews(1L)).thenReturn(reviewList);

		RequestBuilder request = MockMvcRequestBuilders.get("/review/1").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();

	}

	@Test
	public void testUpdateReview() throws Exception {

		ReviewUpdateDto reviewUpdateDto = new ReviewUpdateDto(1L, "any", 3);
		when(reviewService.updateReview(reviewUpdateDto, 1L, 1L))
				.thenReturn(new ReviewResponseDto(1L, "any", 1L, 1L, 3));

		RequestBuilder request = MockMvcRequestBuilders.put("/update/1/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((reviewRequestDto)));

//        mockMvc.perform(request).andExpect(status().is(200)).andReturn();
		mockMvc.perform(request).andExpect(status().is(200)).andExpect(content().string("Review updated successfully"))
				.andReturn();

	}

}