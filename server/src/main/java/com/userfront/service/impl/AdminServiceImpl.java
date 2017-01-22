package com.userfront.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userfront.dao.MovieDao;
import com.userfront.domain.Movie;
import com.userfront.exception.EntityNotFoundException;
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
	public Movie addMovie(Movie movie) {
		
		return movieDao.addMovie(movie);
		
	}
	

}
