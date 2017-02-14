package com.userfront.service;

import java.util.List;
import java.util.Set;

import com.userfront.domain.Genre;
import com.userfront.domain.Movie;



public interface MovieService {
	
	List<com.userfront.model.Movie> getAllMoviesAndSeries();

	com.userfront.model.Movie findByMovieId(Long id);

}
