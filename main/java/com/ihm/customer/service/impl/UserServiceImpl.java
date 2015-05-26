package com.ihm.customer.service.impl;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ihm.customer.dao.UserDAO;
import com.ihm.customer.entites.CstUsers;
import com.ihm.customer.exceptions.IHMCustomerException;
import com.ihm.customer.service.UserService;

@Service
public class UserServiceImpl implements UserService,Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8456410067911453649L;
	@Autowired
	private UserDAO userDao;
	
	
	
	public UserDAO getUserDao() {
		return userDao;
	}



	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}



	@Override
	public CstUsers loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		return userDao.loadUserByUsername(userName);
	}



	@Override
	public CstUsers saveUser(CstUsers user) throws IHMCustomerException {
		
		return userDao.save(user);
	}

	
	
		
}
