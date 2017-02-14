package com.userfront.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Role implements Serializable{
      
	
	private Long id;
	
	private String role;
	
	
	private Set<UserRole> userRoles = new HashSet<UserRole>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<UserRole> getUsers() {
		return userRoles;
	}

	public void setUsers(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", users=" + userRoles + "]";
	}
	
	
	
}
