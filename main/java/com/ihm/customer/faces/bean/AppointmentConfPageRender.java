/**
 * 
 */
package com.ihm.customer.faces.bean;

import java.io.Serializable;

/**
 * <p>
 * this class implements from {@link Serializable}
 * 
 *  <p>
 *  this class for information about current content for page
 * 
 * @author Artur Yolchyan
 */
public class AppointmentConfPageRender implements Serializable {

	/**
	 * the serialVersionUID unique key for this class
	 */
	private static final long serialVersionUID = -3233476339500557377L;
	
	
	private boolean appointmentGuestConf;
	
	private boolean appointmentChange;
	
	private boolean appointmentEditList;

	
	/**
	 * public constructor initialize appointmentGuestConf
	 */
	public AppointmentConfPageRender() {
		appointmentGuestConf = true;
	}
	
	
	/**
	 * @return the appointmentGuestConf
	 */
	public boolean isAppointmentGuestConf() {
		return appointmentGuestConf;
	}

	/**
	 * @param appointmentGuestConf the appointmentGuestConf to set
	 */
	public void setAppointmentGuestConf(boolean appointmentGuestConf) {
		this.appointmentGuestConf = appointmentGuestConf;
	}

	/**
	 * @return the appointmentChange
	 */
	public boolean isAppointmentChange() {
		return appointmentChange;
	}

	/**
	 * @param appointmentChange the appointmentChange to set
	 */
	public void setAppointmentChange(boolean appointmentChange) {
		this.appointmentChange = appointmentChange;
	}

	/**
	 * @return the appointmentEditList
	 */
	public boolean isAppointmentEditList() {
		return appointmentEditList;
	}

	/**
	 * @param appointmentEditList the appointmentEditList to set
	 */
	public void setAppointmentEditList(boolean appointmentEditList) {
		this.appointmentEditList = appointmentEditList;
	}
}