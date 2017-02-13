package com.userfront.domain;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movieId", nullable = false, updatable = false)
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
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="imdbId")
	private ImdbRating imdbRating;
	
	
	@ManyToMany( fetch = FetchType.LAZY ,cascade={CascadeType.ALL})
	@JoinTable( 
		name = "movie_genre", 
		joinColumns = {@JoinColumn(name="movie_id",updatable=false)}, 
		inverseJoinColumns = {@JoinColumn(name="genre_id",updatable=false)}  
	)
	@JsonIgnore
	private Set<Genre> genres = new HashSet<Genre>();
	

    @OneToMany(mappedBy = "primaryKey.movie",cascade = CascadeType.ALL)
    @JsonIgnore
	 private Set<UserMovie> userMovies = new HashSet<UserMovie>();

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

	public ImdbRating getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(ImdbRating imdbRating) {
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
    

	public Set<UserMovie> getUserMovies() {
		return userMovies;
	}

	public void setUserMovies(Set<UserMovie> userMovies) {
		this.userMovies = userMovies;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", year=" + year + ", rated=" + rated + ", released="
				+ released + ", runtime=" + runtime + ", director=" + director + ", plot=" + plot + ", awards=" + awards
				+ ", poster=" + poster + ", metaScore=" + metaScore + ", type=" + type + ", writers=" + writers
				+ ", actors=" + actors + ", languages=" + languages + ", countries=" + countries + ", imdbRating="
				+ imdbRating + ", genres=" + genres + ", userMovies=" + userMovies + "]";
	}
	 
	 

}
