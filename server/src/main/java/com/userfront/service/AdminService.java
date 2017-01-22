package com.userfront.service;

import com.userfront.domain.Movie;

public interface AdminService {
    
	Movie updateMovie( Long id, Movie movie);

	void deleteMovie(Long id);

	Movie addMovie(Movie movie);
}
