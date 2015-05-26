package com.ihm.customer.dao;

import org.springframework.stereotype.Repository;

import com.ihm.customer.entites.SlrAppointmentBookings;

/**
 * @author SARDAR WAQAS AHMED
 * @email  architect_pakistan@hotmail.com
 * @since 23-Jan-2015
 * @version 1.0
 */
@Repository
public interface CustomerAppointmentDAO extends GenericDAO<SlrAppointmentBookings, Integer> {

}
