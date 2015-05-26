package com.ihm.customer.service;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ihm.customer.entites.CstUsers;
import com.ihm.customer.exceptions.IHMCustomerException;

@Service
public interface UserService {

	public CstUsers loadUserByUsername(String userName) throws UsernameNotFoundException;
	public CstUsers saveUser(CstUsers user) throws IHMCustomerException;
}
