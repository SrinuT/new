package com.ihm.customer.service.impl;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihm.customer.dao.CalendarDAO;
import com.ihm.customer.entites.SlrAppointmentSlots;
import com.ihm.customer.service.CalendarService;
import com.ihm.frontend.search.dto.Appointment;

@Service
public class CalendarServiceImpl implements CalendarService,Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1945591295195330936L;
	@Autowired
	private CalendarDAO calendarDAO;



	public CalendarDAO getCalendarDAO() {
		return calendarDAO;
	}



	public void setCalendarDAO(CalendarDAO calendarDAO) {
		this.calendarDAO = calendarDAO;
	}



	@Override
	public List<Appointment> getClinicCalendar(String id,String startDate,String endDate) {
		
		try {
			return calendarDAO.getClinicCalendar(id,startDate,endDate);
		} catch (Exception e) {
			 throw e;
		}
		
	}



	@Override
	public boolean updateAppointmentSlots(SlrAppointmentSlots slot) {
		boolean update = false;
		try {
			SlrAppointmentSlots updateObject = calendarDAO.update(slot);
			if(null != updateObject)
				update = true;
		} catch (Exception e) {
			 throw e;
		}
		return update;
	}

	@Override
	public SlrAppointmentSlots getSlotById(Long id) {
		try {
			return calendarDAO.getById(id);
		} catch (Exception e) {
			 throw e;
		}
	}

		

	@Override
	public List<Appointment> getClinicCalendarwithoutslot(String id,
			String startDate, String endDate) {
		try {
			return calendarDAO.getClinicCalendarwithoutslot(id,startDate,endDate);
		} catch (Exception e) {
			 throw e;
		}
	}
	@Override
	public String getClinicCalendarforstartdate(String id) {
		try {
			return calendarDAO.getClinicCalendarforstartdate(id);
		} catch (Exception e) {
			 throw e;
		}
	}
		
}
