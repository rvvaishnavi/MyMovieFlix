package com.userfront.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.userfront.dao.RoleDao;
import com.userfront.domain.Role;
import com.userfront.domain.User;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	 @Autowired
	  private SessionFactory sessionFactory;

	@Override
	public Role findByRole(String role) {
		Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Role where role = ?");
        query.setString(0, role);
        session.flush();
        return (Role) query.uniqueResult();
	}

	@Override
	public String findByRole(int id) {
		
		return null;
	}

}
