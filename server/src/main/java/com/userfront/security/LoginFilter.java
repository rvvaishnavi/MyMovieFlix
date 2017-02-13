package com.userfront.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.userfront.dao.UserDao;
import com.userfront.domain.UserAuthentication;


public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();
	
	private UserDao userDao;

	public LoginFilter(UserDao userDao){
		
		this(new AntPathRequestMatcher("/login"), userDao);
	}

	public LoginFilter(RequestMatcher requiresAuthenticationRequestMatcher , UserDao userDao) {
		
		super(requiresAuthenticationRequestMatcher);
		this.userDao = userDao;
		//tokenAuthenticationService = new TokenAuthenticationService("myTopSecret");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException { // NOSONAR
		
		System.out.println("Login Filter attempt Authentication");
		
		UserAuthentication auth = (UserAuthentication) tokenAuthenticationService.getAuthenticationForLogin(request,
				response, userDao);
		if (!auth.isAuthenticated()) {
			throw new UserAuthenticationException("Auth to FTAPI is Failed.", auth);
		}

		return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			javax.servlet.FilterChain chain, Authentication authResult) throws IOException, ServletException {

		try {
			UserAuthentication authResultObject = (UserAuthentication) authResult;
			tokenAuthenticationService.addAuthentication(response, authResultObject);

			System.out.println("Login Filter attempt successfulAuthentication");
			// Add the authentication to the Security context
			SecurityContextHolder.getContext().setAuthentication(authResult);


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		System.out.println("Login Filter attempt unsuccessfulAuthentication");
		response.setHeader("X-AUTH-ERR-DESC", "ERROR_AUTHENTICATION");


	}

}
