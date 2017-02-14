package com.userfront.model;

import java.io.Serializable;

public class ImdbRating implements Serializable{
    
	
	private String imdbId;
	
	private Long imdbVotes;
	
	private Double imdbRating;

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public Long getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(Long imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public Double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Double imdbRating) {
		this.imdbRating = imdbRating;
	}

	@Override
	public String toString() {
		return "ImdbRating [imdbId=" + imdbId + ", imdbVotes=" + imdbVotes + ", imdbRating=" + imdbRating + "]";
	} 
	
	
}
