package com.ihm.customer.dao.impl;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ihm.customer.dao.AbstractHibernateDAO;
import com.ihm.customer.dao.CalendarDAO;
import com.ihm.customer.entites.SlrAppointmentSlots;
import com.ihm.customer.util.IHMQueries;
import com.ihm.frontend.search.dto.Appointment;


@Repository
@SuppressWarnings("all")
public class CalendarDAOImpl extends  AbstractHibernateDAO<SlrAppointmentSlots, Integer> implements CalendarDAO , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CalendarDAOImpl.class);

	@Override
	public List<Appointment> getClinicCalendar(String id,String startDate,String endDate) {
		try {
			List<Appointment> appointmentsList = new ArrayList<Appointment>();

			String query = new String(IHMQueries.SELECT_APPOINTMENT);
			query = query.replace("<SELLER_ID>", id+"").replace("<START_DATE>", "'"+startDate+"'").replace("<END_DATE>", "'"+endDate+"'");
			logger.debug("Query <======> "+query);
			logger.info("Query <======> "+query);
			Query queryy = getSession().createQuery(query);
			List objects = queryy.list();
			Iterator iterator= objects.iterator();
			Appointment appointment;
		    while(iterator.hasNext()){
		        Object[] tuple= (Object[]) iterator.next();
		        appointment = new Appointment();
		        appointment.setSlotIdPK((Long)tuple[0]);
		        appointment.setClinicIdFK((Long)tuple[1]);
		        appointment.setAppointmentIdFK((Long)tuple[2]);
		        appointment.setActive((boolean)tuple[3]);
		        appointment.setDuration((Double)tuple[4]);
		        appointment.setServiceDay((String)tuple[5]);
		        appointment.setStartDate((Date)tuple[6]);
		        appointment.setEndDate((Date)tuple[7]);
		        appointment.setSlotTime((Time)tuple[8]);
		        appointmentsList.add(appointment);
		    }

			return appointmentsList; 

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String getClinicCalendarforstartdate(String id) {
		try {
			List<Appointment> appointmentsList = new ArrayList<Appointment>();

			String slotHQLQuery = new String(IHMQueries.SELECT_APPOINTMENT_SLOT_DATE);
			slotHQLQuery = slotHQLQuery.replace("<SELLER_ID>", id+"");
			
			logger.debug("Query <======> "+slotHQLQuery);
			logger.info("Query <======> "+slotHQLQuery);
			Query query = getSession().createQuery(slotHQLQuery);
			query.setFirstResult(0);
		    query.setMaxResults(1);
			List objects = query.list();
			logger.info("Query List output=========================>>>>>>>>>>"+objects);
			Iterator iterator= objects.iterator();
			Date startdate = null;
		    while(iterator.hasNext()){
		        startdate =  (Date) iterator.next();
		       logger.info("startDate================="+startdate);
		      
		      
		    }

			return startdate!=null?startdate.toString():"";
					} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public List<Appointment> getClinicCalendarwithoutslot(String id,String startDate,String endDate) {
		try {
			logger.info("Start Date getClinicCalendarwithoutslot<======> "+startDate + "End Date : "+ endDate);
			List<Appointment> appointmentsList = new ArrayList<Appointment>();
			String query = new String(IHMQueries.SELECT_APPOINTMENT_SLOT);
			logger.info("Appointment With out Time Slot Query return =====>"+ query);
			query = query.replace("<SELLER_ID>", id+"").replace("<START_DATE>", "'"+startDate+"'").replace("<END_DATE>", "'"+endDate+"'");
			logger.debug("Query getClinicCalendarwithoutslot<======> "+query);
			logger.info("Query getClinicCalendarwithoutslot <======> "+query);
			Query queryy = getSession().createQuery(query);
			List objects = queryy.list();
			Iterator iterator= objects.iterator();
			Appointment appointment;
		    while(iterator.hasNext()){
		        Object[] tuple= (Object[]) iterator.next();
		        appointment = new Appointment();
		        appointment.setSlotIdPK((Long)tuple[0]);
		        appointment.setClinicIdFK((Long)tuple[1]);
		        appointment.setAppointmentIdFK((Long)tuple[2]);
		        appointment.setActive((boolean)tuple[3]);
		        appointment.setDuration((Double)tuple[4]);
		        appointment.setServiceDay((String)tuple[5]);
		        appointment.setStartDate((Date)tuple[6]);
		        appointment.setEndDate((Date)tuple[7]);
		        appointment.setSlotTime((Time)tuple[8]);
		        appointmentsList.add(appointment);
		    }

			return appointmentsList; 

		} catch (Exception e) {
			throw e;
		}
	}
	

}
