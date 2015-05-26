package com.ihm.customer.service.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ihm.customer.dao.UserDAO;
import com.ihm.customer.entites.CstUsers;
import com.ihm.customer.util.IHMConstants;

/**
 * @author SARDAR WAQAS AHMED
 * @email  architect_pakistan@hotmail.com
 * @since  19-Jan-2015
 * @version 1.0
 */
@Service("myUserDetailService")
public class UserDetailServiceImpl implements UserDetailsService,Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1329163266224369378L;
	
	@Autowired
	private UserDAO userDao;
	
	
	
	public UserDAO getUserDao() {
		return userDao;
	}



	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		CstUsers user;
		try {
			user = userDao.loadUserByUsername(userName);
			if (user == null)
				throw new UsernameNotFoundException("User Name Not Found");

		} catch (Exception e) {
			throw new UsernameNotFoundException("database error ");
		}
		return buildUserFromUserEntity(user);

	}
	private User buildUserFromUserEntity(CstUsers userEntity) {
		
		// convert model user to spring security user
		
		String username = userEntity.getUsername();
		String password = userEntity.getPassword();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		String role = userEntity.getCstRoles().getRole();
		GrantedAuthority authorityCustomer = new SimpleGrantedAuthority(role);
		GrantedAuthority authorityGuest = new SimpleGrantedAuthority(IHMConstants.ROLE_GUEST);

		Set<GrantedAuthority> authoritiesSet = new HashSet<GrantedAuthority>();
		authoritiesSet.add(authorityCustomer);
		authoritiesSet.add(authorityGuest);
		User springUser = new User(username, password, enabled,accountNonExpired, credentialsNonExpired, accountNonLocked,authoritiesSet);
		
		return springUser;
	}
}
