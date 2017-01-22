package com.userfront.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class User implements Serializable {
    
	private Long userId;
	
	private String username;
	    
	private String password;
	
	private String firstName;
	
	private boolean enabled=true;
	
   
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	
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
