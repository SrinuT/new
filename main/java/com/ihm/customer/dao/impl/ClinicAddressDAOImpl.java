package com.ihm.customer.dao.impl;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ihm.customer.dao.AbstractHibernateDAO;
import com.ihm.customer.dao.ClinicAddressDAO;
import com.ihm.customer.entites.SlrAddress;


@Repository
@SuppressWarnings("all")
public class ClinicAddressDAOImpl extends  AbstractHibernateDAO<SlrAddress, Integer> implements ClinicAddressDAO , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public List<String> getAllCity() {
		try {
			Criteria cityCriteria = getSession().createCriteria(SlrAddress.class, "address");
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("address.city"));
			cityCriteria.setProjection(Projections.distinct(projList));

			return (List<String>) cityCriteria.list();

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<String> getAllLocality() {
		try {
			Criteria localityCriteria = getSession().createCriteria(SlrAddress.class, "address");
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("address.locality"));
			localityCriteria.setProjection(Projections.distinct(projList));

			return (List<String>) localityCriteria.list();

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<String> getAllLocalityByCity(String city) {
		try {
			Criteria localityCriteria = getSession().createCriteria(SlrAddress.class, "address");
			localityCriteria.add(Restrictions.eq("address.city", city));
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("address.locality"));
			localityCriteria.setProjection(Projections.distinct(projList));

			return (List<String>) localityCriteria.list();

		} catch (Exception e) {
			throw e;
		}
	}

	
	
}
