/**
 * 
 */
package com.ihm.customer.service;

import com.ihm.customer.dto.AppointmentDto;

import java.util.List;

/**
 * <p>
 * this service interface for appointment bookings
 * 
 * @author Artur Yolchyan
 */
public interface AppointmentBookingsService {
	
	/**
	 * this method return List of appointment by confirmationNumber
	 * 
	 * @param confirmationNumber - String
	 * @return - List
	 */
	AppointmentDto getAppointmentByConfirmationNumber(String confirmationNumber);
	
	/**
	 * this method updated appointment status by id
	 * 
	 * @param id - Long
	 * @param status - String
	 */
	void updateAppointmentStatus(Long id, String status);

    /**
     * return appointment dtos from database by person Id
     *
     * @param personId - Long
     * @return - List
     */
    List<AppointmentDto> getAppointmentsByPersonId(Long personId);
	
}
