package com.userfront.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.userfront.domain.Genre;
import com.userfront.domain.Movie;
import com.userfront.model.MovieUI;
import com.userfront.service.AdminService;





@RestController
@RequestMapping("/admin/movie")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public Movie updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
		return adminService.updateMovie(id, movie);
	}
	

	@RequestMapping(method = RequestMethod.PUT, value = "/update2")
	public Movie updateMovie2(@RequestBody MovieUI movie) {
		
		return adminService.updateTheMovie(movie);
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public void deleteMovie(@PathVariable("id") Long id) {
		adminService.deleteMovie(id);
	}
	
/*
	@RequestMapping(method = RequestMethod.POST, value = "/create2")
	public Movie addMovie2(@RequestBody Movie movie) {
		//return adminService.addMovie(movie);
		return null;
	}*/
	
	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public Movie addMovie(@RequestBody MovieUI movie) {
		return adminService.addMovie(movie);
	}

}
