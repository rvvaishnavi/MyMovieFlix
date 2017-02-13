package com.userfront.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "USERS_RATINGS_MOVIES")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.user",
        joinColumns = @JoinColumn(name = "userId")),
    @AssociationOverride(name = "primaryKey.movie",
        joinColumns = @JoinColumn(name = "movieId")) })
public class UserMovie {
	
	// composite-id key
	private UserMovieId primaryKey = new UserMovieId();
	
	// additional fields
    private int rating;
    private String comment;
	
    @EmbeddedId
    public UserMovieId getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(UserMovieId primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	@Transient
	public User getUser() {
		return getPrimaryKey().getUser();
	}
	public void setUser(User user) {
		getPrimaryKey().setUser(user);
	}
	
	 @Transient
	public Movie getMovie() {
		return getPrimaryKey().getMovie();
	}
	public void setMovie(Movie movie) {
		getPrimaryKey().setMovie(movie);
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "UserMovie [primaryKey=" + primaryKey.toString() + ", rating=" + rating + ", comment=" + comment + "]";
	}
    
    

}
