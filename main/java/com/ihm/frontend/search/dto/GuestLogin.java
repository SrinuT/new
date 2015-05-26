package com.ihm.frontend.search.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SARDAR WAQAS AHMED
 * @email  architect_pakistan@hotmail.com
 * @since  @Date
 * @version 1.0
 */
public class GuestLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1529624660513224662L;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String primaryNumber;
	private Date dob;
	private Boolean appointmentReminder;
	private Boolean onlineSurveyGuest;
	private Boolean specialOfferQuestDig;
	private String confirmEmail;
	
	
	/**
	 * 
	 */
	public GuestLogin() {
		// TODO Auto-generated constructor stub
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPrimaryNumber() {
		return primaryNumber;
	}
	public void setPrimaryNumber(String primaryNumber) {
		this.primaryNumber = primaryNumber;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Boolean getAppointmentReminder() {
		return appointmentReminder;
	}
	public void setAppointmentReminder(Boolean appointmentReminder) {
		this.appointmentReminder = appointmentReminder;
	}
	public Boolean getOnlineSurveyGuest() {
		return onlineSurveyGuest;
	}
	public void setOnlineSurveyGuest(Boolean onlineSurveyGuest) {
		this.onlineSurveyGuest = onlineSurveyGuest;
	}

	public Boolean getSpecialOfferQuestDig() {
		return specialOfferQuestDig;
	}

	public void setSpecialOfferQuestDig(Boolean specialOfferQuestDig) {
		this.specialOfferQuestDig = specialOfferQuestDig;
	}
	
	/**
	 * @return the confirmEmail
	 */
	public String getConfirmEmail() {
		return confirmEmail;
	}

	/**
	 * @param confirmEmail the confirmEmail to set
	 */
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	@Override
	public String toString() {
		return "GuestLogin [firstName = " + firstName + "   | lastName = " + lastName
				+ "   | email = " + email + "   | password = " + password
				+ "   | primaryNumber = " + primaryNumber + "   | dob = " + dob
				+ "   | appointmentReminder = " + appointmentReminder
				+ "   | onlineSurveyGuest = " + onlineSurveyGuest
				+ "   | specialOfferQuestDig = " + specialOfferQuestDig + "]";
	}

	public void init(){
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.password = "";
		this.primaryNumber = "";
		this.dob = null;
		this.appointmentReminder = false;
		this.onlineSurveyGuest = false;
		this.specialOfferQuestDig = false;
	}

	
}
