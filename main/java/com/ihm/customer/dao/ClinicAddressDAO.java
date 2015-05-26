package com.ihm.customer.dao;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ihm.customer.entites.SlrAddress;

@Repository
public interface ClinicAddressDAO extends GenericDAO<SlrAddress, Integer> {

	public List<String> getAllCity();
	public List<String> getAllLocality();
	public List<String> getAllLocalityByCity(String city);
}