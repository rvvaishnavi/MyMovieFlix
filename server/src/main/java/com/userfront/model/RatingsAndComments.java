package com.userfront.model;

public class RatingsAndComments {
	
	private String username;
	
	private String comment;
	
	private int rating ;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "RatingsAndComments [username=" + username + ", comment=" + comment + ", rating=" + rating + "]";
	}
	
	

}
