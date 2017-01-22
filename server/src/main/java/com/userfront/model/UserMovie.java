package com.userfront.model;


public class UserMovie {
	
	// composite-id key
	private UserMovieId primaryKey = new UserMovieId();
	
	// additional fields
    private int rating;
    private String comment;
	
   
    public UserMovieId getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(UserMovieId primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	
	public User getUser() {
		return getPrimaryKey().getUser();
	}
	public void setUser(User user) {
		getPrimaryKey().setUser(user);
	}
	
	
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
		return "UserMovie [primaryKey=" + primaryKey + ", rating=" + rating + ", comment=" + comment + "]";
	}
    
    

}
