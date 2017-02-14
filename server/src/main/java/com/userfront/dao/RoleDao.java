package com.userfront.dao;



import com.userfront.domain.Role;


public interface RoleDao  {
	
	Role findByRole(String role);
	
	String findByRole(int id);
}
