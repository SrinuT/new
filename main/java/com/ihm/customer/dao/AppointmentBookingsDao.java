/**
 * 
 */
package com.ihm.customer.dao;

import com.ihm.customer.entites.SlrAppointmentBookings;

import java.util.List;

/**
 * <p>
 * this dao interface for appointment bookings
 * 
 * @author Artur Yolchyan
 */
public interface AppointmentBookingsDao extends GenericDAO<SlrAppointmentBookings, Long> {
	
	/**
	 * this method return List of SlrAppointmentBookings by confirmationNumber
	 * 
	 * @param confirmationNumber - String
	 * @return - List
	 */
	SlrAppointmentBookings getAppointmentsByConfirmationNumber(String confirmationNumber);

    /**
     * return appointment dtos from database by person Id
     *
     * @param personId - Long
     * @return - List
     */
    List<SlrAppointmentBookings> getAppointmentsByPersonId(Long personId);
	
}
