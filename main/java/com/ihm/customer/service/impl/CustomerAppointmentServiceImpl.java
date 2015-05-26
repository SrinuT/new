package com.ihm.customer.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihm.customer.dao.CustomerAppointmentDAO;
import com.ihm.customer.entites.SlrAppointmentBookings;
import com.ihm.customer.service.CustomerAppointmentService;
import com.ihm.customer.util.IHMConstants;

/**
 * @author SARDAR WAQAS AHMED
 * @email  architect_pakistan@hotmail.com
 * @since  23-Jan-2015
 * @version 1.0
 */
@Service
public class CustomerAppointmentServiceImpl implements CustomerAppointmentService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4130250912441983487L;

	@Autowired
	private CustomerAppointmentDAO customerAppointmentDAO;
	
	@Override
	public String bookedAppointmentForCustomer(SlrAppointmentBookings slotBookedDTO) {
		try {
            SlrAppointmentBookings persisitentObject = null;
            persisitentObject =  customerAppointmentDAO.saveOrEdit(slotBookedDTO);

			return (IHMConstants.SAVED);
		} catch (Exception e) {
			 throw e;
		}
		
	}

    public SlrAppointmentBookings getById(Long id) {
        return customerAppointmentDAO.getById(id);
    }

    @Override
    public void deleteById(String id) {

    }

}
