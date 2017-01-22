package com.userfront.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userfront.dao.MovieDao;
import com.userfront.dao.RoleDao;
import com.userfront.dao.UserDao;
import com.userfront.domain.Movie;
import com.userfront.domain.Role;
import com.userfront.domain.User;
import com.userfront.domain.UserMovie;
import com.userfront.domain.UserRole;
import com.userfront.exception.BadRequestException;
import com.userfront.model.Genre;

import com.userfront.service.UserService;



@Service
@Transactional
public class UserServiceImpl implements UserService  {


	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private MovieDao movieDao;
	
	@Override
	public User createUser(User user) {
		
		User localUser = userDao.findByUsername(user.getUsername());

        if (localUser != null) {
        	throw new BadRequestException("Employee with this email already exists");
        } else {
            
        	Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleDao.findByRole("ROLE_USER")));
            
            user.getRoles().addAll(userRoles);
            
            localUser=  userDao.createUser(user);
            
            
	}
        return localUser;
	}

	@Override
	public String getImdbPage(Long id) {
		
		return movieDao.getImdbPage(id);
	}

	@Override
	public UserMovie rateMovie(UserMovie userMovie) {
		
		return movieDao.rateMovie(userMovie);
	}

	@Override
	public List<com.userfront.model.Movie> getTopRatedMovies() {
		
		return movieDao.getTopRatedMovies();
	}

	@Override
	public List<Movie> getTopRatedMoviesAndSeries() {
		
		return movieDao.getTopRatedMoviesAndSeries();
	}

	@Override
	public List<Movie> getTopRatedSeries() {
		
		return movieDao.getTopRatedSeries();
	}

	@Override
	public List<Genre> filterByGenre(String id) {
		
		List<Genre> movies= movieDao.filterByGenre(id);
		if(movies==null || movies.isEmpty())
		throw new BadRequestException("no movies found");
		else
		return movies;
	}

	@Override
	public List<UserMovie> getMovieRatingsAndComments(Long id) {
		
		return movieDao.getMovieRatingsAndComments(id);
	}

	@Override
	public List<Movie> filterByType(String id) {
		
		List<Movie> movies= movieDao.filterByType(id);
		if(movies==null || movies.isEmpty())
		throw new BadRequestException("no movies found");
		else
		return movies;
	}

	@Override
	public List<Movie> filterByYear(String id) {
		
		List<Movie> movies= movieDao.filterByYear(id);
		if(movies==null || movies.isEmpty())
		throw new BadRequestException("no movies found");
		else
		return movies;
		
	}

	@Override
	public List<com.userfront.model.Genre> filterByTypeYearGenre(String type, String year, String genre) {
		List<com.userfront.model.Genre> movies = movieDao.filterByTypeYearGenre(type,year,genre);
		if(movies==null || movies.isEmpty())
		throw new BadRequestException("no movies found");
		else
		return movies;
	}

	@Override
	public List<Movie> sortMoviesBy(String string) {
		return movieDao.sortMoviesBy(string);
		
	}

	@Override
	public List<Movie> sortMoviesBy() {
		 return movieDao.sortMoviesBy();
		
	}

	@Override
	public Double getMovieAverageRating(Long id) {
		
		Double rating= movieDao.getMovieAverageRating(id);
		if(rating == null)
		{
			throw new BadRequestException("no ratings yet");	
		}
		return rating;
	}

	
}
