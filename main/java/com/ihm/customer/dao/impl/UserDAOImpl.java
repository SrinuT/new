package com.ihm.customer.dao.impl;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import com.ihm.customer.dao.AbstractHibernateDAO;
import com.ihm.customer.dao.UserDAO;
import com.ihm.customer.entites.CstUsers;


@Repository
@SuppressWarnings("all")
public class UserDAOImpl extends  AbstractHibernateDAO<CstUsers, Integer> implements UserDAO , Serializable {

	@Override
	public CstUsers loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		Criteria userCriteria = getSession().createCriteria(CstUsers.class,"user");
		userCriteria.add(Restrictions.eq("username", userName));
		List<CstUsers> userList = userCriteria.list();
		return  (userList.size() == 0 ? null : userList.get(0));
	}


	
	
}
