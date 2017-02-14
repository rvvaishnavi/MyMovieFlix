package com.userfront.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserMovieId implements Serializable{
   
	private User user;
	
	private Movie movie;
   
	@ManyToOne(cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
	@ManyToOne(cascade = CascadeType.ALL)
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
