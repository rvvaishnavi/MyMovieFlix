package com.userfront.dao;



import com.userfront.domain.User;

public interface UserDao 
{
	
	User findByUsername(String username);
	
    User createUser(User user);

	
}
