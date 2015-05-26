package com.ihm.customer.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.ihm.customer.entites.CstUsers;

/**
 * @author SARDAR WAQAS AHMED
 * @email  architect_pakistan@hotmail.com
 * @since  19-Jan-2015
 * @version 1.0
 */
@Repository
public interface UserDAO extends GenericDAO<CstUsers, Integer> {

	public CstUsers loadUserByUsername(String userName) throws UsernameNotFoundException;
}
