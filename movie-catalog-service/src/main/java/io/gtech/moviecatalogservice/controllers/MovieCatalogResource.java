package io.gtech.moviecatalogservice.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.gtech.moviecatalogservice.model.CatalogItem;
import io.gtech.moviecatalogservice.model.Movie;
import io.gtech.moviecatalogservice.model.Rating;
import io.gtech.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Value("${ratingUrl}")
	private String ratingUrl;
	
	@Value("${movieUrl}")
	private String movieUrl;
	
	@Autowired
	private RestTemplate rest;
	
	@Autowired
	private WebClient.Builder builder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {
		
		UserRating userRating = rest.getForObject(ratingUrl + userId, UserRating.class);
		
		List<Rating> ratings = userRating.getUserRatings();	
		return ratings.stream().map(
				rating -> {
					Movie movie = rest.getForObject(movieUrl + rating.getMovieId(), Movie.class);
					
					/*
					 * Movie movie = builder.build() .get() .uri(movieUrl + rating.getMovieId())
					 * .retrieve() .bodyToMono(Movie.class) .block();
					 */
					
					
					return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
				}).collect(Collectors.toList());
		
	}
}
