package com.ihm.customer.service;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ihm.customer.entites.SlrAppointmentSlots;
import com.ihm.frontend.search.dto.Appointment;

@Service
public interface CalendarService {

	public abstract List<Appointment> getClinicCalendar(String id,String startDate,String endDate);
	public abstract List<Appointment> getClinicCalendarwithoutslot(String id,String startDate,String endDate);
	public abstract String getClinicCalendarforstartdate(String id);
	public boolean updateAppointmentSlots(SlrAppointmentSlots slot);
	public SlrAppointmentSlots getSlotById(Long id);
}
