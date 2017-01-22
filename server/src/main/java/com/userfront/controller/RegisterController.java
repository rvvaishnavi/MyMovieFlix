package com.userfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.userfront.domain.User;
import com.userfront.service.UserService;





@RestController
@RequestMapping("/")
public class RegisterController {
	

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="register",method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

}
