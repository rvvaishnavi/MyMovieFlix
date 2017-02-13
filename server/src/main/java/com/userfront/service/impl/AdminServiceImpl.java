package com.userfront.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userfront.dao.MovieDao;
import com.userfront.domain.Genre;
import com.userfront.domain.ImdbRating;
import com.userfront.domain.Movie;
import com.userfront.exception.EntityNotFoundException;
import com.userfront.model.MovieUI;
import com.userfront.service.AdminService;



@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private MovieDao movieDao;
	
	
	@Override
	public Movie updateMovie(Long id, Movie movie) {
		com.userfront.model.Movie existing = movieDao.findByMovieId(id);
		if (existing == null) {
			throw new EntityNotFoundException("Movie not found");
		}
		return movieDao.UpdateMovie(id,movie);
	}


	@Override
	public void deleteMovie(Long id) {
		com.userfront.model.Movie existing = movieDao.findByMovieId(id);
		if (existing == null) {
			throw new EntityNotFoundException("Movie not found");
		}
		movieDao.deleteMovie(id);
		
	}
	
	
	@Override
	public Movie addMovie(MovieUI movie) {
		
		Movie newMovie = new Movie();
		newMovie.setMovieId(movie.getMovieId());
		newMovie.setActors(movie.getActors());
		newMovie.setAwards(movie.getAwards());
		newMovie.setCountries(movie.getCountries());
		newMovie.setDirector(movie.getDirector());
		newMovie.setLanguages(movie.getLanguages());
		newMovie.setMetaScore(movie.getMetaScore());
		newMovie.setPlot(movie.getPlot());
		newMovie.setPoster(movie.getPoster());
		newMovie.setRated(movie.getRated());
		newMovie.setReleased(movie.getReleased());
		newMovie.setRuntime(movie.getRuntime());
		newMovie.setTitle(movie.getTitle());
		newMovie.setType(movie.getType());
		newMovie.setWriters(movie.getWriters());
		newMovie.setYear(movie.getYear());
		
		Set<Genre> genreSet = new HashSet<Genre>();
		
		int[] genreArr = movie.getGenre();
		HashMap<Long,String> map = new HashMap<Long,String>();
		map = movieDao.getGenreList();
		for(int i=0;i<genreArr.length;i++)
		{
			Genre genre = new Genre();
			
			genre.setId(new Long(genreArr[i]));
			genre.setGenre(map.get(new Long(genreArr[i])));
			
		    genreSet.add(genre);
		}
		
		
		newMovie.setGenres(genreSet);
		ImdbRating imdbRating = new ImdbRating();
		imdbRating.setImdbId(movie.getImdbId());
		imdbRating.setImdbRating(movie.getImdbRatingDouble());
		imdbRating.setImdbVotes(movie.getImdbVotes());
		
		newMovie.setImdbRating(imdbRating);
		
		
		return movieDao.addMovie(newMovie);
		
	}


	@Override
	public Movie updateTheMovie(MovieUI movie) {
		Movie updatedMovie = new Movie();
		updatedMovie.setMovieId(movie.getMovieId());
		updatedMovie.setActors(movie.getActors());
		updatedMovie.setAwards(movie.getAwards());
		updatedMovie.setCountries(movie.getCountries());
		updatedMovie.setDirector(movie.getDirector());
		updatedMovie.setLanguages(movie.getLanguages());
		updatedMovie.setMetaScore(movie.getMetaScore());
		updatedMovie.setPlot(movie.getPlot());
		updatedMovie.setPoster(movie.getPoster());
		updatedMovie.setRated(movie.getRated());
		updatedMovie.setReleased(movie.getReleased());
		updatedMovie.setRuntime(movie.getRuntime());
		updatedMovie.setTitle(movie.getTitle());
		updatedMovie.setType(movie.getType());
		updatedMovie.setWriters(movie.getWriters());
		updatedMovie.setYear(movie.getYear());
		
		Set<Genre> genreSet = new HashSet<Genre>();
		
		int[] genreArr = movie.getGenre();
		HashMap<Long,String> map = new HashMap<Long,String>();
		map = movieDao.getGenreList();
		for(int i=0;i<genreArr.length;i++)
		{
			Genre genre = new Genre();
			
			genre.setId(new Long(genreArr[i]));
			genre.setGenre(map.get(new Long(genreArr[i])));
			
		    genreSet.add(genre);
		}
		
		
		updatedMovie.setGenres(genreSet);
		ImdbRating imdbRating = new ImdbRating();
		imdbRating.setImdbId(movie.getImdbId());
		imdbRating.setImdbRating(movie.getImdbRatingDouble());
		imdbRating.setImdbVotes(movie.getImdbVotes());
		
		updatedMovie.setImdbRating(imdbRating);
		
		
		return movieDao.UpdateMovie(movie.getMovieId(),updatedMovie);
	}
	

}
