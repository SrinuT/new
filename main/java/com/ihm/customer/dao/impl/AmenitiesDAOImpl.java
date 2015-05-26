package com.ihm.customer.dao.impl;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ihm.customer.dao.AbstractHibernateDAO;
import com.ihm.customer.dao.AmenitiesDAO;
import com.ihm.customer.entites.SlrAmenities;
import com.ihm.customer.entites.SlrSeller;


@Repository
@SuppressWarnings("all")
public class AmenitiesDAOImpl extends  AbstractHibernateDAO<SlrAmenities, Integer> implements AmenitiesDAO , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<SlrSeller> simpleSearchSeller(String testName, String city,String locality, String zip, Date date) {

		List<SlrSeller> clinicList = null;

		try{
			Criteria clinicCriteria = getSession().createCriteria(SlrSeller.class,"clinic");
			Criteria testCriteria   = clinicCriteria.createAlias("clinic.SlrProducts", "tests")
									  .add(Restrictions.eq("tests.serviceCode", testName));
			testCriteria.setFetchMode("clinic.SlrProducts.slrDeals", FetchMode.SELECT);
			testCriteria.setFetchMode("clinic.SlrProducts.SlrProductReviews", FetchMode.SELECT);
			clinicCriteria.createAlias("clinic.slrTimeSlots", "timeSlots");
			clinicCriteria.createAlias("clinic.slrAmenitieses", "facilities");
			if(city != null && city.length() > 0)
			clinicCriteria.setFetchMode("slrAddress",FetchMode.JOIN)
									   .createAlias("slrAddress.ihmStateInfo", "state")
									   .createAlias("state.ihmCountry", "country");
			
			
//			clinicCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			clinicList = (List<SlrSeller>) clinicCriteria.list();

		}catch(Exception e){
			throw e;
		}
		
		return clinicList;
	}


	@Override
	public List<String> getDistinctAmenitiesName() {
		try {
			Criteria amenitiesCriteria = getSession().createCriteria(
					SlrAmenities.class, "amenities");
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("amenities.name"));
			amenitiesCriteria.setProjection(Projections.distinct(projList));

			return (List<String>) amenitiesCriteria.list();
		} catch (Exception e) {
			throw e;
		}
	}


}
