package com.userfront.service;

import java.util.List;

import com.userfront.domain.Movie;
import com.userfront.domain.User;
import com.userfront.domain.UserMovie;
import com.userfront.model.Genre;
import com.userfront.model.RatingsAndComments;
import com.userfront.model.UserMovieRating;

public interface UserService {

	User createUser(User user);

    String getImdbPage(Long id);

	UserMovie rateMovie(UserMovie userMovie);

	List<com.userfront.model.Movie> getTopRatedMovies();

	List<Movie> getTopRatedMoviesAndSeries();

	List<Movie> getTopRatedSeries();

	List<Genre> filterByGenre(String id);

	List<RatingsAndComments> getMovieRatingsAndComments(Long id);

	List<Movie> filterByType(String id);

	List<Movie> filterByYear(String id);

	List<com.userfront.model.Genre> filterByTypeYearGenre(String type, String year, String genre);

	List<Movie> sortMoviesBy(String string);

	List<Movie> sortMoviesBy();

	Double getMovieAverageRating(Long id);

	User validateUser(User user);

	List<com.userfront.model.Movie> filterByGenre2(String id);

	UserMovieRating rateTheMovie(UserMovieRating userMovieRating);
	
	

}
