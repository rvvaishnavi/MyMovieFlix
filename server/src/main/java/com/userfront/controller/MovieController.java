package com.userfront.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.userfront.service.MovieService;


@RestController
@RequestMapping("/movie")
public class MovieController {
     
	@Autowired
	private MovieService movieService;
	

	@RequestMapping(value="/movieList",method = RequestMethod.GET)
    public List<com.userfront.model.Movie> getAllMoviesAndSeries(){
	     return  movieService.getAllMoviesAndSeries();
       
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public com.userfront.model.Movie findByMovieId(@PathVariable("id") Long id) {
		return movieService.findByMovieId(id);
	}
	
}
