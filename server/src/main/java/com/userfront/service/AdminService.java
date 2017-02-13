package com.userfront.service;

import com.userfront.domain.Movie;
import com.userfront.model.MovieUI;

public interface AdminService {
    
	Movie updateMovie( Long id, Movie movie);

	void deleteMovie(Long id);

	Movie addMovie(MovieUI movie);

	Movie updateTheMovie(MovieUI movie);
}
