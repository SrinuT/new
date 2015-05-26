package com.ihm.customer.dao.impl;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ihm.customer.dao.AbstractHibernateDAO;
import com.ihm.customer.dao.ProductDAO;
import com.ihm.customer.entites.SlrProduct;


@Repository
@SuppressWarnings("all")
public class ProductDAOImpl extends  AbstractHibernateDAO<SlrProduct, Integer> implements ProductDAO , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public List<String> getAllTestByClinic(String name) {
		try {
			
			Criteria testCriteria = getSession().createCriteria(SlrProduct.class, "test");
			testCriteria.setProjection(Projections.property("test.serviceCode"));
			if(null != name && !name.isEmpty()) 
			testCriteria.add(Restrictions.like("test.serviceCode", name,MatchMode.ANYWHERE));
			return (List<String>) testCriteria.list();

		} catch (Exception e) {
			throw e;
		}
	}

    public SlrProduct getSlrProduct(String testName, Long slrId) {
        String hql = "FROM SlrProduct AS sp WHERE sp.slrSeller.id = :slrId AND sp.name = :name";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).
                setParameter("slrId", slrId).setParameter("name", testName);
        return (SlrProduct) query.list().get(0);
    }
	
}
