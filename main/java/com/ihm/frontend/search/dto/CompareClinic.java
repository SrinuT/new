package com.ihm.frontend.search.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author SARDAR WAQAS AHMED
* @email  architect_pakistan@hotmail.com
* @since  12 Jan, 2015
* @version 1.0
*/
public class CompareClinic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6453322045982123790L;

    private String id;
	private String clinicFullName;
	private String clinicAppointmentContactNo;
	private String testPrice;
	private String clinicRating;
	private String clinicRatingImage;
	private String clinicDistance;
	
	private List<FacilityDocument> reportDelivery;
	private List<FacilityDocument> doorStepCollection;
	private List<FacilityDocument> customerWaitingLounge;
	private List<FacilityDocument> entertainment;
	private List<FacilityDocument> carParking;
	private List<FacilityDocument> twoWheelerParking;
	private List<Appointment> timeSlot;
	/**
	 * 
	 */
	public CompareClinic() {
		reportDelivery = new ArrayList<FacilityDocument>();
		doorStepCollection = new ArrayList<FacilityDocument>();
		customerWaitingLounge = new ArrayList<FacilityDocument>();
		entertainment = new ArrayList<FacilityDocument>();
		carParking = new ArrayList<FacilityDocument>();
		twoWheelerParking = new ArrayList<FacilityDocument>();
		timeSlot = new ArrayList<Appointment>();
	}
	public String getClinicFullName() {
		return clinicFullName;
	}
	public void setClinicFullName(String clinicFullName) {
		this.clinicFullName = clinicFullName;
	}
	public String getClinicAppointmentContactNo() {
		return clinicAppointmentContactNo;
	}
	public void setClinicAppointmentContactNo(String clinicAppointmentContactNo) {
		this.clinicAppointmentContactNo = clinicAppointmentContactNo;
	}
	public String getTestPrice() {
		return testPrice;
	}
	public void setTestPrice(String testPrice) {
		this.testPrice = testPrice;
	}
	public String getClinicRating() {
		return clinicRating;
	}
	public void setClinicRating(String clinicRating) {
		this.clinicRating = clinicRating;
	}
	public String getClinicRatingImage() {
		return clinicRatingImage;
	}
	public void setClinicRatingImage(String clinicRatingImage) {
		this.clinicRatingImage = clinicRatingImage;
	}
	public String getClinicDistance() {
		return clinicDistance;
	}
	public void setClinicDistance(String clinicDistance) {
		this.clinicDistance = clinicDistance;
	}
	public List<FacilityDocument> getReportDelivery() {
		return reportDelivery;
	}
	public void setReportDelivery(List<FacilityDocument> reportDelivery) {
		this.reportDelivery = reportDelivery;
	}
	public List<FacilityDocument> getDoorStepCollection() {
		return doorStepCollection;
	}
	public void setDoorStepCollection(List<FacilityDocument> doorStepCollection) {
		this.doorStepCollection = doorStepCollection;
	}
	public List<FacilityDocument> getCustomerWaitingLounge() {
		return customerWaitingLounge;
	}
	public void setCustomerWaitingLounge(
			List<FacilityDocument> customerWaitingLounge) {
		this.customerWaitingLounge = customerWaitingLounge;
	}
	public List<FacilityDocument> getEntertainment() {
		return entertainment;
	}
	public void setEntertainment(List<FacilityDocument> entertainment) {
		this.entertainment = entertainment;
	}
	public List<FacilityDocument> getCarParking() {
		return carParking;
	}
	public void setCarParking(List<FacilityDocument> carParking) {
		this.carParking = carParking;
	}
	public List<FacilityDocument> getTwoWheelerParking() {
		return twoWheelerParking;
	}
	public void setTwoWheelerParking(List<FacilityDocument> twoWheelerParking) {
		this.twoWheelerParking = twoWheelerParking;
	}

public List<Appointment> getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(List<Appointment> timeSlot) {
		this.timeSlot = timeSlot;
	}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
