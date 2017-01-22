package com.userfront.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userfront.domain.Movie;
import com.userfront.domain.UserMovie;
import com.userfront.model.Genre;
import com.userfront.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/navigateToIMDB/{id}")
	public String navigateToIMDB(@PathVariable("id") Long id) {
		
		return userService.getImdbPage(id);
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/getMovieRatings/{id}")
	public List<UserMovie> getMovieRatingsAndComments(@PathVariable("id") Long id) {
		
		return userService.getMovieRatingsAndComments(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getMovieAvgRating/{id}")
	public Double getMovieAverageRating(@PathVariable("id") Long id)
	{
		return userService.getMovieAverageRating(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/rateMovie")
	public UserMovie rateMovie(@RequestBody UserMovie userMovie) {
		
		return userService.rateMovie(userMovie);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getTopRatedMoviesAndSeries")
	public List<Movie> getTopRatedMoviesAndSeries () {
		
		return userService.getTopRatedMoviesAndSeries();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getTopRatedMovies")
	public List<com.userfront.model.Movie> getTopRatedMovies () {
		
		return userService.getTopRatedMovies();
	}
	@RequestMapping(method = RequestMethod.GET, value = "/getTopRatedSeries")
	public List<Movie> getTopRatedSeries () {
		
		return userService.getTopRatedSeries();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/filterByGenre/{id}")
	public List<Genre> filterByGenre (@PathVariable("id") String id) {
		
		return userService.filterByGenre(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/filterByType/{id}")
	public List<Movie> filterByType (@PathVariable("id") String id) {
		
		return userService.filterByType(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/filterByYear/{id}")
	public List<Movie> filterByYear (@PathVariable("id") String id) {
		
		return userService.filterByYear(id);
	}
    
	@RequestMapping(method = RequestMethod.GET, value="filter" ,params={"type","year","genre"})
	public List<com.userfront.model.Genre> filterByTypeYearGenre(@RequestParam(value="type") String type,
	@RequestParam(value="year") String year,@RequestParam(value="genre") String genre)
	{
		return userService.filterByTypeYearGenre(type,year,genre);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = {"/sortBy","sortBy/{id}"})
	public List<Movie> sortMoviesBy (@PathVariable("id") Optional<String> id) {
		if (id.isPresent()) {
		    return userService.sortMoviesBy(id.get());
		  } else {
		    return userService.sortMoviesBy();
		  }
		
	}
}
