package com.ihm.frontend.search.dto;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.Comparator;

public class ServiceDeals implements Serializable , Comparator<ServiceDeals> {  

	/**
	 * 
	 */
	private static final long serialVersionUID = -7152536627841718071L;
	private String dealName;
	private String dealDescription;
	private String dealDiscount;
	private String dealPercent;
	
	public ServiceDeals() {
		// TODO Auto-generated constructor stub
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public String getDealDescription() {
		return dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}

	public String getDealDiscount() {
		return dealDiscount;
	}

	public void setDealDiscount(String dealDiscount) {
		this.dealDiscount = dealDiscount;
	}
	
	@Override
	public int compare(ServiceDeals o1, ServiceDeals o2) {
		return o1.getDealName().compareTo(o2.getDealName());
	}

	public String getDealPercent() {
		return dealPercent;
	}

	public void setDealPercent(String dealPercent) {
		this.dealPercent = dealPercent;
	}
}
