package com.userfront.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.Genre;
import com.userfront.domain.Movie;
import com.userfront.domain.UserMovie;
import com.userfront.model.RatingsAndComments;
import com.userfront.model.UserMovieRating;




public interface MovieDao {
  
	List<com.userfront.model.Movie> getAllMoviesAndSeries();
	 
	 com.userfront.model.Movie findByMovieId(Long Id);

	Movie UpdateMovie(Long id, Movie movie);

	 void deleteMovie(Long id);

	Movie addMovie(Movie movie);

	String getImdbPage(Long id);

	UserMovie rateMovie(UserMovie userMovie);

	List<com.userfront.model.Movie> getTopRatedMovies();

	List<Movie> getTopRatedMoviesAndSeries();

	List<Movie> getTopRatedSeries();

	List<com.userfront.model.Genre> filterByGenre(String id);

	List<RatingsAndComments> getMovieRatingsAndComments(Long id);

	List<Movie> filterByType(String id);

	List<Movie> filterByYear(String id);

	List<com.userfront.model.Genre> filterByTypeYearGenre(String type, String year, String genre);

	List<Movie> sortMoviesBy(String string);

	List<Movie> sortMoviesBy();

	Double getMovieAverageRating(Long id);

	List<com.userfront.model.Movie> filterByGenre2(String id);

	UserMovieRating rateTheMovie(UserMovieRating userMovieRating);

	HashMap<Long, String> getGenreList();
}
