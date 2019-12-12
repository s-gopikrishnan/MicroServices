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

import io.gtech.moviecatalogservice.model.CatalogItem;
import io.gtech.moviecatalogservice.model.Movie;
import io.gtech.moviecatalogservice.model.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Value("${ratingUrl}")
	private String ratingUrl;
	
	@Value("${movieUrl}")
	private String movieUrl;
	
	@Autowired
	private RestTemplate rest;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {
		
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4), 
				new Rating("4567", 5)
				);	
		
		return ratings.stream().map(
				rating -> {
					Movie movie = rest.getForObject(movieUrl + rating.getMovieId(), Movie.class);
					return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
				}).collect(Collectors.toList());
		
	}
}
