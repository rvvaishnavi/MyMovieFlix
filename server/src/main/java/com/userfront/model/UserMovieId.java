package com.userfront.model;

import java.io.Serializable;


public class UserMovieId implements Serializable{
   
	private User user;
	
	private Movie movie;
   
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "UserMovieId [user=" + user + ", movie=" + movie + "]";
	}
	
	
}
