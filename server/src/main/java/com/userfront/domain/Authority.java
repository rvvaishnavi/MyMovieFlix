package com.userfront.domain;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;


public class Authority implements GrantedAuthority, Serializable{
	
	private static final long serialVersionUID = -4446929343152142811L;

	private Long id;

	private String authorityName;

	
	@Override
	public String getAuthority() {
		return authorityName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}


}
