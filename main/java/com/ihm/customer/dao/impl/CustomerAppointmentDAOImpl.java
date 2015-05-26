package com.ihm.customer.dao.impl;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.ihm.customer.dao.AbstractHibernateDAO;
import com.ihm.customer.dao.CustomerAppointmentDAO;
import com.ihm.customer.entites.SlrAppointmentBookings;


@Repository
@SuppressWarnings("all")
public class CustomerAppointmentDAOImpl extends  AbstractHibernateDAO<SlrAppointmentBookings, Integer> implements CustomerAppointmentDAO , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
