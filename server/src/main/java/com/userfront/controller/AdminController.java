package com.userfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.userfront.domain.Movie;
import com.userfront.service.AdminService;





@RestController
@RequestMapping("/admin/movie")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public Movie updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
		return adminService.updateMovie(id, movie);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public void deleteMovie(@PathVariable("id") Long id) {
		adminService.deleteMovie(id);
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public Movie addMovie(@RequestBody Movie movie) {
		return adminService.addMovie(movie);
	}

}
