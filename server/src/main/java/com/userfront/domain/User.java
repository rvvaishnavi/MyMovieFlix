package com.userfront.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false, updatable = false)
	private Long userId;
	
	private String username;
	    
	private String password;
	
	private String firstName;
	
	private boolean enabled=true;
	
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	@OneToMany(mappedBy = "primaryKey.user",
            cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<UserMovie> userMovies = new HashSet<UserMovie>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getRoles() {
		return userRoles;
	}

	public void setRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
   
	
	public Set<UserMovie> getUserMovies() {
		return userMovies;
	}

	public void setUserMovies(Set<UserMovie> userMovies) {
		this.userMovies = userMovies;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", enabled=" + enabled + ", roles=" + userRoles + ", userMovies=" + userMovies + "]";
	}
    
    
	
}
