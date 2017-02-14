package com.userfront.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.userfront.dao.UserDao;
import com.userfront.domain.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	
	  @Autowired
	   private SessionFactory sessionFactory;

	@Override
	public User findByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where username = ?");
        query.setString(0, username);
        session.flush();
        return (User) query.uniqueResult();
	}

	@Override
	public User createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
	    session.save(user);
	    session.flush();
		return user;
	}

}
