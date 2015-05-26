package com.ihm.customer.service.impl;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihm.customer.dao.AmenitiesDAO;
import com.ihm.customer.service.AmenitiesService;

@Service
public class AmenitiesServiceImpl implements AmenitiesService,Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1945591295195330936L;
	@Autowired
    private AmenitiesDAO amenitiesDAO;

	
	public AmenitiesDAO getAmenitiesDAO() {
		return amenitiesDAO;
	}

	public void setAmenitiesDAO(AmenitiesDAO amenitiesDAO) {
		this.amenitiesDAO = amenitiesDAO;
	}


	@Override
	public List<String> getDistinctAmenitiesName() {
	
		try {
			
			return amenitiesDAO.getDistinctAmenitiesName();
			
		} catch (Exception e) {
			throw e;
		}
		
	}

		
}
