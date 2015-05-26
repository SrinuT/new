package com.ihm.customer.dao;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ihm.customer.entites.SlrAmenities;

@Repository
public interface AmenitiesDAO extends GenericDAO<SlrAmenities, Integer> {

	public abstract List<String> getDistinctAmenitiesName();
}