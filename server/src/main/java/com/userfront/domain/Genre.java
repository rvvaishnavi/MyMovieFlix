package com.userfront.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Genre implements Serializable {

	   
		@Id
		@GeneratedValue
		private Long id;
		
		private String genre;
	
		@ManyToMany( fetch = FetchType.LAZY )
		@JoinTable( 
			name = "movie_genre", 
			joinColumns = {@JoinColumn(name="genre_id")}, 
			inverseJoinColumns = {@JoinColumn(name="movie_id")}  
		)
		@JsonIgnore
		private Set<Movie> movies = new HashSet<Movie>();

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}
		
		public Set<Movie> getMovies() {
			return movies;
		}

		public void setMovies(Set<Movie> movies) {
			this.movies = movies;
		}

		@Override
		public String toString() {
			return "Genre [id=" + id + ", genre=" + genre + ", movies=" + movies + "]";
		}
		
		
}
