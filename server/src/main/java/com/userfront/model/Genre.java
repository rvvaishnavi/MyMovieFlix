package com.userfront.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.userfront.model.Movie;


public class Genre implements Serializable {

	   
		
		private Long id;
		
		private String genre;
		
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

