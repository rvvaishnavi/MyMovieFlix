package com.userfront.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.userfront.dao.MovieDao;
import com.userfront.domain.Genre;
import com.userfront.domain.Movie;

import com.userfront.domain.User;
import com.userfront.domain.UserMovie;

@Repository
public class MovieDaoImpl implements MovieDao {
	
	@Autowired
    private SessionFactory sessionFactory;


	@Override
	public List<com.userfront.model.Movie> getAllMoviesAndSeries() {
		Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Movie");
        List<Movie> movies = query.list();
        List<com.userfront.model.Movie> movieUIList = new ArrayList<com.userfront.model.Movie>();
        for(Movie movie : movies)
        {
        	com.userfront.model.Movie movieUI = new com.userfront.model.Movie();
        	try {
				BeanUtils.copyProperties(movieUI, movie);
			} catch (IllegalAccessException e1) {
			    e1.printStackTrace();
			} catch (InvocationTargetException e1) {
			    e1.printStackTrace();
			}
        	Set<com.userfront.model.Genre> genreSet = new HashSet<com.userfront.model.Genre>();
        	for(Genre genre : movie.getGenres())
        	{
        		com.userfront.model.Genre genreUI = new com.userfront.model.Genre();
        		try {
        			
        			//BeanUtils.copyProperties(genreUI,genre );
        			 genreUI.setGenre(genre.getGenre());
        			
    			} catch (Exception e) {
    				e.printStackTrace();
    			} 
        		genreSet.add(genreUI);
        	}
        	movieUI.setGenre(genreSet);
        	movieUIList.add(movieUI);
        }
        session.flush();
        return movieUIList;
	}

	@Override
	public com.userfront.model.Movie findByMovieId(Long Id) {
		Session session = sessionFactory.getCurrentSession();
		Movie movie = (Movie) session.get(Movie.class, Id);
		com.userfront.model.Movie movieUI = new com.userfront.model.Movie();
    	try {
			BeanUtils.copyProperties(movieUI, movie);
		} catch (IllegalAccessException e1) {
		    e1.printStackTrace();
		} catch (InvocationTargetException e1) {
		    e1.printStackTrace();
		}
    	Set<com.userfront.model.Genre> genreSet = new HashSet<com.userfront.model.Genre>();
    	for(Genre genre : movie.getGenres())
    	{
    		com.userfront.model.Genre genreUI = new com.userfront.model.Genre();
    		try {
    			
    			//BeanUtils.copyProperties(genreUI,genre, new String[]{"movies"} );
    			 genreUI.setGenre(genre.getGenre());
    			
			} catch (Exception e) {
				e.printStackTrace();
			} 
    		genreSet.add(genreUI);
    	}
    	movieUI.setGenre(genreSet);
        session.flush();
        
        return movieUI;
	}

	@Override
	public Movie UpdateMovie(Long id,Movie movie) {
		Session session = sessionFactory.getCurrentSession();
        session.merge(movie);
        Movie updatedMovie = (Movie) session.get(Movie.class, id);
        session.flush();
        
        return updatedMovie;
	}

	@Override
	public void deleteMovie(Long id) {
		Session session = sessionFactory.getCurrentSession();	
        Movie movie = (Movie) session.get(Movie.class, id);
        session.delete(movie);
        session.flush();
		
	}

	@Override
	public Movie addMovie(Movie movie) {
		 Session session = sessionFactory.getCurrentSession();
		 session.save(movie);
		 session.flush();
		 return movie;
	}

	@Override
	public String getImdbPage(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Movie movie = (Movie) session.get(Movie.class, id);
		String imdbLink ="http://www.imdb.com/title/"+movie.getImdbRating().getImdbId();
		session.flush();
		return imdbLink;
	}

	@Override
	public UserMovie rateMovie(UserMovie userMovie) {
		 Session session = sessionFactory.getCurrentSession();
		 session.merge(userMovie);
		 session.flush();
		 return userMovie;
	}

	@Override
	public List<com.userfront.model.Movie> getTopRatedMovies() {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Movie.class);
		cr.add(Restrictions.eq("type","movie"));
		cr.createAlias("imdbRating", "alias");
        cr.addOrder(Order.desc("alias.imdbRating"));
		cr.setMaxResults(5);
        List<Movie> movies = cr.list();
        List<com.userfront.model.Movie> movieUIList = new ArrayList<com.userfront.model.Movie>();
        for(Movie movie : movies)
        {
        	com.userfront.model.Movie movieUI = new com.userfront.model.Movie();
        	try {
				BeanUtils.copyProperties(movieUI, movie);
			} catch (IllegalAccessException e1) {
			    e1.printStackTrace();
			} catch (InvocationTargetException e1) {
			    e1.printStackTrace();
			}
        	Set<com.userfront.model.Genre> genreSet = new HashSet<com.userfront.model.Genre>();
        	for(Genre genre : movie.getGenres())
        	{
        		com.userfront.model.Genre genreUI = new com.userfront.model.Genre();
        		try {
        			
        			//BeanUtils.copyProperties(genreUI,genre );
        			 genreUI.setGenre(genre.getGenre());
        			
    			} catch (Exception e) {
    				e.printStackTrace();
    			} 
        		genreSet.add(genreUI);
        	}
        	movieUI.setGenre(genreSet);
        	movieUIList.add(movieUI);
        }
        session.flush();
		return movieUIList;
	}

	@Override
	public List<Movie> getTopRatedMoviesAndSeries() {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Movie.class);
		cr.createAlias("imdbRating", "alias");
        cr.addOrder(Order.desc("alias.imdbRating"));
		cr.setMaxResults(5);
        List<Movie> movies = cr.list();
        session.flush();
		return movies;
	}

	@Override
	public List<Movie> getTopRatedSeries() {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Movie.class);
		cr.add(Restrictions.eq("type","series"));
		cr.createAlias("imdbRating", "alias");
        cr.addOrder(Order.desc("alias.imdbRating"));
        List<Movie> movies = cr.list();
        session.flush();
		return movies;
	}

	@Override
	public List<com.userfront.model.Genre> filterByGenre(String id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Genre.class);
		cr.add(Restrictions.eq("genre",id));
	    List<Genre> genres = cr.list();
        List<com.userfront.model.Genre> genreUIList = new ArrayList<com.userfront.model.Genre>();
        for(Genre genre: genres)
        {
        	com.userfront.model.Genre genreUI = new com.userfront.model.Genre();
        	Set<com.userfront.model.Movie> movieSet = new HashSet<com.userfront.model.Movie>();
        	for(Movie movie : genre.getMovies())
        	{
        		com.userfront.model.Movie movieUI = new com.userfront.model.Movie();
        		try {
					BeanUtils.copyProperties(movieUI, movie);
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
            	movieSet.add(movieUI);
        	}
        	genreUI.setMovies(movieSet);
        	genreUIList.add(genreUI);
        }
        session.flush();
		return genreUIList;
	}

	@Override
	public List<UserMovie> getMovieRatingsAndComments(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UserMovie.class);
		cr.add(Restrictions.eq("primaryKey.movie.movieId",id));
		List<UserMovie> userMovies = cr.list();
		session.flush();
		return  userMovies;
	}

	@Override
	public List<Movie> filterByType(String id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Movie.class);
		cr.add(Restrictions.eq("type",id));
		List<Movie> movies = cr.list();
		session.flush();
		return movies;
	}

	@Override
	public List<Movie> filterByYear(String id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Movie.class);
		cr.add(Restrictions.eq("year",id));
		List<Movie> movies = cr.list();
		session.flush();
		return movies;
	}

	@Override
	public List<com.userfront.model.Genre> filterByTypeYearGenre(String type, String year, String genreType) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Genre.class);
		cr.add(Restrictions.eq("genre",genreType));
	    List<Genre> genres = cr.list();
        List<com.userfront.model.Genre> genreUIList = new ArrayList<com.userfront.model.Genre>();
        for(Genre genre: genres)
        {
        	com.userfront.model.Genre genreUI = new com.userfront.model.Genre();
        	Set<com.userfront.model.Movie> movieSet = new HashSet<com.userfront.model.Movie>();
        	for(Movie movie : genre.getMovies())
        	{
        		com.userfront.model.Movie movieUI = new com.userfront.model.Movie();
        		try {
					BeanUtils.copyProperties(movieUI, movie);
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
        		if(movieUI.getType().equals(type) && movieUI.getYear().equals(year))
            	movieSet.add(movieUI);
        		if(movieSet==null || movieSet.isEmpty())
        			return null;
        	}
        	genreUI.setMovies(movieSet);
        	genreUIList.add(genreUI);
        }
        session.flush();
		return genreUIList;
		
	}

	@Override
	public List<Movie> sortMoviesBy(String string) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Movie.class);
		if(string.equals("imdbVotes"))
		{
			 cr.createAlias("imdbRating", "alias");
	         cr.addOrder(Order.desc("alias.imdbVotes"));
		}else if(string.equals("year"))
		{
		    cr.addOrder(Order.desc(string));
		}else if(string.equals("imdbRating"))
		{
			cr.createAlias("imdbRating", "alias");
	        cr.addOrder(Order.desc("alias.imdbRating"));
		}
        List<Movie> movies = cr.list();
        session.flush();
		return movies;
	}

	@Override
	public List<Movie> sortMoviesBy() {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Movie.class);
		         cr.addOrder(Order.desc("year"));
		         cr.createAlias("imdbRating", "alias");
		         cr.addOrder(Order.desc("alias.imdbRating"));
		         cr.addOrder(Order.desc("alias.imdbVotes"));
		List<Movie> movies = cr.list();
        session.flush();
		return movies;
		
	}

	@Override
	public Double getMovieAverageRating(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UserMovie.class);
		cr.add(Restrictions.eq("primaryKey.movie.movieId",id));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.avg("rating"));
		cr.setProjection(projList);
		session.flush();
		return (Double) cr.uniqueResult();
	
	}

}
