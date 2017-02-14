package com.userfront.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.userfront.dao.MovieDao;
import com.userfront.domain.Genre;
import com.userfront.domain.Movie;
import com.userfront.service.MovieService;


@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;
	
	
	@Override
	public List<com.userfront.model.Movie> getAllMoviesAndSeries() {
		return movieDao.getAllMoviesAndSeries();
	}


	@Override
	public com.userfront.model.Movie findByMovieId(Long id) {
	    return movieDao.findByMovieId(id);
	}

}
