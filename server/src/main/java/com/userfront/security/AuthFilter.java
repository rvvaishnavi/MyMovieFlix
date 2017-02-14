package com.userfront.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.userfront.dao.UserDao;
import com.userfront.domain.UserAuthentication;

public class AuthFilter extends GenericFilterBean {


	private TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();
	private UserDao userDao;
	
	public AuthFilter(){}
	
	public AuthFilter(UserDao userDao){
		this.userDao = userDao;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		UserAuthentication auth = (UserAuthentication) tokenAuthenticationService.getAuthentication(httpRequest,userDao);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		if(auth.isAuthenticated())
			filterChain.doFilter(request, response);
		else{
			System.out.println("Not Authenticated");
		}
		
	}

}