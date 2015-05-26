package com.ihm.customer.frontend.leftnav;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Sardar Waqas Ahmed
 * 
 */
public class HospitalAmenities implements Serializable , Comparator<HospitalAmenities> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7162344562773443433L;
	private String amenitiesUIId;
	private String heading;
	private List<Document> documnetList;
	
	public HospitalAmenities() {
		heading = "";
		documnetList = new ArrayList<Document>();

	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getAmenitiesUIId() {
		return amenitiesUIId;
	}

	public void setAmenitiesUIId(String amenitiesUIId) {
		this.amenitiesUIId = amenitiesUIId;
	}

	public List<Document> getDocumnetList() {
		return documnetList;
	}

	public void setDocumnetList(List<Document> documnetList) {
		this.documnetList = documnetList;
	}

	@Override
	public int compare(HospitalAmenities o1, HospitalAmenities o2) {
		return o1.getHeading().compareTo(o2.getHeading());
	}


	
	
	
}
