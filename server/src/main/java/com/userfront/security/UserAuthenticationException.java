package com.userfront.security;

import org.springframework.security.core.AuthenticationException;

import com.userfront.domain.UserAuthentication;

public class UserAuthenticationException extends AuthenticationException{

	private static final long serialVersionUID = 3356606184917713565L;
	
	private final UserAuthentication authentication;
	
	public UserAuthenticationException(String msg) {
		super(msg);
		this.authentication = null;
	}
	
	public UserAuthenticationException(String msg, UserAuthentication authentication) {
		super(msg);
		this.authentication = authentication;
		
	}

	public UserAuthentication getAuthentication() {
		return authentication;
	}


}

