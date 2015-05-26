/**
 * 
 */
package com.ihm.customer.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ihm.customer.dao.AbstractHibernateDAO;
import com.ihm.customer.dao.PersonDao;
import com.ihm.customer.entites.Person;

/**
 * <p>
 * this class for management persons in db
 * 
 * @author Artur Yolchyan
 */
@SuppressWarnings("unchecked")
@Repository("personDao")
public class PersonDaoImpl extends AbstractHibernateDAO<Person, Long> implements PersonDao {
	
	/*
	 * (non-Javadoc)
	 * @see com.ihm.customer.dao.PersonDao#getPersonByUsernameAndPassword(java.lang.String, java.lang.String)
	 */
	public Person getPersonByUsernameAndPassword(String email, String password) {
		String hql = "FROM Person ps WHERE ps.email = :email AND ps.password = :password";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email).setParameter("password", password);
		List<Person> persons = query.list();
		return persons.isEmpty() ? null : persons.get(0);
	}

    /*
	 * (non-Javadoc)
	 * @see com.ihm.customer.dao.PersonDao#getByEmail(java.lang.Stringg)
	 */
    @Override
    public Person getByEmail(String email) {
        String hql = "FROM Person ps WHERE ps.email = :email";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("email", email);
        List<Person> persons = query.list();
        return persons.isEmpty() ? null : persons.get(0);
    }
}
