package com.userfront.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		System.out.println("user"+user);
		return userService.createUser(user);
	}

	
	@RequestMapping(value="login",method = RequestMethod.POST)
	public void validateLoginAndGenToken(HttpServletResponse response,@RequestParam(value = "username") String userName,
    		@RequestParam(value = "password") String password) {
    	
        
    }
	
	/*
	@RequestMapping(value="loginCheck",method = RequestMethod.POST)
	public boolean validateUser(@RequestBody User user) {
    //	User user = new User();
    //	user.setUsername(username);
    //	user.setPassword(password);
    	
      //  return userService.validateUser(user);
		return true;
    }*/
	
	
	
	@RequestMapping(value="loginCheck",method = RequestMethod.GET)
	public User validateUser1(HttpServletResponse response,@RequestParam(value = "username") String username,
    		@RequestParam(value = "password") String password) {
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(password);

        return userService.validateUser(user);
    }
	
}
