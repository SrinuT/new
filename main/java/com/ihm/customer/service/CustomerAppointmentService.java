package com.ihm.customer.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ihm.customer.entites.SlrAppointmentBookings;

/**
 * @author SARDAR WAQAS AHMED
 * @email  architect_pakistan@hotmail.com
 * @since  23-Jan-2015
 * @version 1.0
 */
@Service
public interface CustomerAppointmentService {

	public String bookedAppointmentForCustomer(SlrAppointmentBookings slotBookedDTO);

    public void deleteById(String id);

    SlrAppointmentBookings getById(Long id);
}
