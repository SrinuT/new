package com.ihm.frontend.search.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * @author Sardar Waqas Ahmed
 * 
 */

public class Appointment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1907459328102139053L;
	
	private boolean active;
	private Double duration;
	private String serviceDay;
	private Date startDate;
	private Date endDate;
	private Time slotTime;
	
	private Long slotIdPK;
	private Long clinicIdFK;
	private Long appointmentIdFK;
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public String getServiceDay() {
		return serviceDay;
	}
	public void setServiceDay(String serviceDay) {
		this.serviceDay = serviceDay;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Time getSlotTime() {
		return slotTime;
	}
	public void setSlotTime(Time slotTime) {
		this.slotTime = slotTime;
	}
	
	public Long getSlotIdPK() {
		return slotIdPK;
	}
	public void setSlotIdPK(Long slotIdPK) {
		this.slotIdPK = slotIdPK;
	}
	
	public Long getClinicIdFK() {
		return clinicIdFK;
	}
	public void setClinicIdFK(Long clinicIdFK) {
		this.clinicIdFK = clinicIdFK;
	}
	public Long getAppointmentIdFK() {
		return appointmentIdFK;
	}
	public void setAppointmentIdFK(Long appointmentIdFK) {
		this.appointmentIdFK = appointmentIdFK;
	}
	@Override
	public String toString() {
		return "Appointment [active=" + active + ", duration=   " + duration
				+ ", serviceDay=" + serviceDay + "       , startDate=" + startDate
				+ ", endDate=" + endDate + ", slotTime=" + slotTime + "]";
	}
	
	
	
}
