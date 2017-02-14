package com.userfront.model;

import java.io.Serializable;
import java.util.Set;


public class Movie implements Serializable{
	
	private Long movieId;
	
	private String title;
	
	private String year;
	
	private String rated;
	
	private String released;
	
	private String runtime;
	
	private String director;
	
	private String plot;
	
	private String awards;
	
	private String poster;
	
	private Long metaScore;
	
	private String type;
	
	private String writers;
	
	private String actors;
	
	private String languages;
	
	private String countries;
	
	private String genreString;
	
    private String imdbId;
	
	private Long imdbVotes;
	
	private Double imdbRatingDouble;
	
	
	private com.userfront.domain.ImdbRating imdbRating;
	
	private Set<Genre> genre;
	
	

	public Set<Genre> getGenre() {
		return genre;
	}

	public void setGenre(Set<Genre> genre) {
		this.genre = genre;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Long getMetaScore() {
		return metaScore;
	}

	public void setMetaScore(Long metaScore) {
		this.metaScore = metaScore;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public com.userfront.domain.ImdbRating getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(com.userfront.domain.ImdbRating imdbRating) {
		this.imdbRating = imdbRating;
	}


	public String getWriters() {
		return writers;
	}

	public void setWriters(String writers) {
		this.writers = writers;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	
	public String getGenreString() {
		return genreString;
	}

	public void setGenreString(String genreString) {
		this.genreString = genreString;
	}

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

	public Double getImdbRatingDouble() {
		return imdbRatingDouble;
	}

	public void setImdbRatingDouble(Double imdbRatingDouble) {
		this.imdbRatingDouble = imdbRatingDouble;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", year=" + year + ", rated=" + rated + ", released="
				+ released + ", runtime=" + runtime + ", director=" + director + ", plot=" + plot + ", awards=" + awards
				+ ", poster=" + poster + ", metaScore=" + metaScore + ", type=" + type + ", writers=" + writers
				+ ", actors=" + actors + ", languages=" + languages + ", countries=" + countries + ", imdbRating="
				+ imdbRating + ", genre=" + genre + "]";
	}

	
    


	 
	 

}
