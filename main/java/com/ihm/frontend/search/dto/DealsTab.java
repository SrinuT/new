package com.ihm.frontend.search.dto;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DealsTab implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3052887887719022519L;
	private List<ServiceDeals> serviceDealList ;
	
	public DealsTab() {
		init();
	}


	public List<ServiceDeals> getServiceDealList() {
		return serviceDealList;
	}

	public void setServiceDealList(List<ServiceDeals> serviceDealList) {
		this.serviceDealList = serviceDealList;
	}
	
	private void init() {

		serviceDealList = new ArrayList<ServiceDeals>();
		
	}
	
}
