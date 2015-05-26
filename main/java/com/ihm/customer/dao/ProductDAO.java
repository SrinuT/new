package com.ihm.customer.dao;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ihm.customer.entites.SlrProduct;

@Repository
public interface ProductDAO extends GenericDAO<SlrProduct, Integer> {
	
	public List<String> getAllTestByClinic(String name);

    SlrProduct getSlrProduct(String testName, Long slrId);
}