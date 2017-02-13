package com.userfront.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
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
import com.userfront.domain.UserMovieId;
import com.userfront.model.RatingsAndComments;
import com.userfront.model.UserMovieRating;

@Repository
public class MovieDaoImpl implements MovieDao {
	
	@Autowired
    private SessionFactory sessionFactory;

	public static Logger LOGGER = Logger.getLogger(MovieDaoImpl.class);

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
		StringBuilder genreString = new StringBuilder();
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
    			 genreString.append(genre.getGenre()+",");
    			
			} catch (Exception e) {
				e.printStackTrace();
			} 
    		genreSet.add(genreUI);
    	}
    	movieUI.setGenre(genreSet);
    	movieUI.setGenreString(genreString.toString());
    	movieUI.setImdbId(movie.getImdbRating().getImdbId());
    	movieUI.setImdbVotes(movie.getImdbRating().getImdbVotes());
    	movieUI.setImdbRatingDouble(movie.getImdbRating().getImdbRating());
        session.flush();
        
        return movieUI;
	}

	@Override
	public Movie UpdateMovie(Long id,Movie movie) {
		Session session = sessionFactory.getCurrentSession();
        session.update(movie);
        session.flush();
        Movie updatedMovie = (Movie) session.get(Movie.class, id);
      
        
        session.clear();

        return updatedMovie;
	}

	@Override
	public void deleteMovie(Long id) {
		Session session = sessionFactory.getCurrentSession();	
        Movie movie = (Movie) session.get(Movie.class, id);
      
        movie.getGenres().clear();
        session.delete(movie);
        session.flush();
       
        session.clear();

	}

	@Override
	public Movie addMovie(Movie movie) {
		 Session session = sessionFactory.getCurrentSession();
		 session.save(movie);
		 session.flush();
		 session.clear();
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
	public List<com.userfront.model.Movie> filterByGenre2(String id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Genre.class);
		cr.add(Restrictions.eq("genre",id));
	    List<Genre> genres = cr.list();
	    List<com.userfront.model.Movie> movieList=null;
       // List<com.userfront.model.Genre> genreUIList = new ArrayList<com.userfront.model.Genre>();
        for(Genre genre: genres)
        {
        	com.userfront.model.Genre genreUI = new com.userfront.model.Genre();
        	movieList = new ArrayList<com.userfront.model.Movie>();
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
        		movieList.add(movieUI);
        	}
        	//genreUI.setMovies(movieSet);
        	//genreUIList.add(genreUI);
        }
        session.flush();
		return movieList;
	}

	@Override
	public List<RatingsAndComments> getMovieRatingsAndComments(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UserMovie.class);
		cr.add(Restrictions.eq("primaryKey.movie.movieId",id));
		List<UserMovie> userMovies = cr.list();
		
		List<RatingsAndComments> ratingsAndCommentsList = new ArrayList<RatingsAndComments>();
		for(UserMovie userMovie : userMovies )
		{
			RatingsAndComments ratingsAndComments = new RatingsAndComments();
			ratingsAndComments.setUsername(userMovie.getPrimaryKey().getUser().getFirstName());
			ratingsAndComments.setComment(userMovie.getComment());
			ratingsAndComments.setRating(userMovie.getRating());
			ratingsAndCommentsList.add(ratingsAndComments);
		}
		session.flush();
		return  ratingsAndCommentsList;
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
		LOGGER.info("Getting movie Average Rating for Id : "+id);
		cr.add(Restrictions.eq("primaryKey.movie.movieId",id));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.avg("rating"));
		cr.setProjection(projList);
		session.flush();
		return (Double) cr.uniqueResult();
	
	}

	@Override
	public UserMovieRating rateTheMovie(UserMovieRating userMovieRating) {
		 Session session = sessionFactory.getCurrentSession();
		 UserMovie userMovie = new UserMovie();
		 Movie movie = new Movie();
		 User user = new User();
		 UserMovieId userMovieId = new UserMovieId();
		 user.setUserId(userMovieRating.getUserId());
		 movie.setMovieId(userMovieRating.getMovieId());
		 userMovieId.setUser(user);
		 userMovieId.setMovie(movie);
		 
		 userMovie.setPrimaryKey(userMovieId);
		 userMovie.setMovie(movie);
		 userMovie.setRating(userMovieRating.getRating());
		 userMovie.setComment(userMovieRating.getComment());
		 userMovie.setUser(user);
		 
		 session.merge(userMovie);
		 session.flush();
		 return userMovieRating;
	}

	@Override
	public HashMap<Long, String> getGenreList() {
		Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Genre");
        List<Genre> genres= query.list();
      
        HashMap<Long,String> map = new HashMap<Long,String>();
        for(Genre genre : genres)
        {
        	map.put(new Long(genre.getId()), genre.getGenre());
        }
        session.flush();
      
        session.clear();
        
		return map;
	}



}
