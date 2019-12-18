package io.gtech.ratingdataservice.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.gtech.ratingdataservice.model.Rating;
import io.gtech.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating(movieId, 5);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable String userId) {
		
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4), 
				new Rating("4567", 5)
				);	
		UserRating userRating = new UserRating();
		userRating.setUserRatings(ratings);
		return userRating;
		
	}

}
