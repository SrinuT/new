package com.ihm.customer.service;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface AmenitiesService {

	public abstract List<String> getDistinctAmenitiesName();
	
}
