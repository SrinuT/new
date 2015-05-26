package com.ihm.customer.dao;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ihm.customer.entites.SlrAppointmentSlots;
import com.ihm.frontend.search.dto.Appointment;

@Repository
public interface CalendarDAO extends GenericDAO<SlrAppointmentSlots, Integer> {

	public abstract List<Appointment> getClinicCalendar(String id,String startDate,String endDate);
	public abstract List<Appointment> getClinicCalendarwithoutslot(String id,String startDate,String endDate);
	String getClinicCalendarforstartdate(String id);
}