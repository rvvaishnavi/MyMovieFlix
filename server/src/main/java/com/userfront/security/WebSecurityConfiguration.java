package com.userfront.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.userfront.dao.UserDao;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	


	public WebSecurityConfiguration() {
		super(true);
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.exceptionHandling()
			.and()
			.anonymous()
			.and()
			.servletApi()
			.and()
			.headers().cacheControl()
			.and().and()
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new LoginFilter(userDao), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new AuthFilter(userDao), UsernamePasswordAuthenticationFilter.class);
	}


}
