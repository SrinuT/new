/**
 * 
 */
package com.ihm.customer.dao.impl;

import com.ihm.customer.dao.AbstractHibernateDAO;
import com.ihm.customer.dao.GuestPersonDao;
import com.ihm.customer.dao.PersonDao;
import com.ihm.customer.entites.GuestPerson;
import com.ihm.customer.entites.Person;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * this class for management persons in db
 * 
 * @author Artur Yolchyan
 */
@Repository("guestPersonDao")
public class GuestPersonDaoImpl extends AbstractHibernateDAO<GuestPerson, Long> implements GuestPersonDao {
}
