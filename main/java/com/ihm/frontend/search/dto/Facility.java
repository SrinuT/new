package com.ihm.frontend.search.dto;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Facility implements Serializable , Comparator<Facility> {  

	/**
	 * 
	 */
	private static final long serialVersionUID = 8948139896997851667L;
	private String facilityHeading;
	private List<FacilityDocument> documents = null;
	
	public Facility() {
		init();
	}

	public String getFacilityHeading() {
		return facilityHeading;
	}

	public void setFacilityHeading(String facilityHeading) {
		this.facilityHeading = facilityHeading;
	}

	private void init() {
		
		facilityHeading = "";
		documents = new ArrayList<FacilityDocument>();
	}

	public List<FacilityDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<FacilityDocument> documents) {
		this.documents = documents;
	}


	@Override
	public int compare(Facility o1, Facility o2) {
		return o1.getFacilityHeading().compareTo(o2.getFacilityHeading());
	}
	
}

