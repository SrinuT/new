/**
 * 
 */
package com.ihm.customer.service.impl;

import com.ihm.frontend.search.dto.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihm.customer.dao.AppointmentBookingsDao;
import com.ihm.customer.dto.AppointmentDto;
import com.ihm.customer.entites.SlrAppointmentBookings;
import com.ihm.customer.entites.SlrSeller;
import com.ihm.customer.service.AppointmentBookingsService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * this class implements from AppointmentBookingsService and
 * do all needed methods implementation
 * 
 * @author Artur Yolchyan
 */
@Service("appointmentBookingsService")
public class AppointmentBookingsServiceImpl implements AppointmentBookingsService {
	
	@Autowired
	private AppointmentBookingsDao appointmentBookingsDao;
	
	/*
	 * (non-Javadoc)
	 * @see com.ihm.customer.service.AppointmentBookingsService#getAppointmentsByConfirmationNumber(java.lang.String)
	 */
	@Override
	public AppointmentDto getAppointmentByConfirmationNumber(String confirmationNumber) {
		SlrAppointmentBookings booking = appointmentBookingsDao.getAppointmentsByConfirmationNumber(confirmationNumber);
		return booking == null ? null : initAppointmentDtoBySlrAppointmentBookings(booking);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ihm.customer.service.AppointmentBookingsService#updateAppointmentStatus(java.lang.Long, java.lang.String)
	 */
	@Override
	public void updateAppointmentStatus(Long id, String status) {
		SlrAppointmentBookings booking = appointmentBookingsDao.getById(id, false);
		booking.setBookingStatus(status);
		appointmentBookingsDao.saveOrUpdate(booking);
	}

    /*
	 * (non-Javadoc)
	 * @see com.ihm.customer.service.AppointmentBookingsService#getAppointmentsByPersonId(java.lang.Long)
	 */
    public List<AppointmentDto> getAppointmentsByPersonId(Long personId) {
        List<SlrAppointmentBookings> appointments = appointmentBookingsDao.getAppointmentsByPersonId(personId);
        return initAppointmentDtoListByAppointmentList(appointments);
    }

	/**
	 * initialize AppointmentDto by SlrAppointmentBookings
	 * 
	 * @param booking - SlrAppointmentBookings
	 * @return - AppointmentDto
	 */
	private AppointmentDto initAppointmentDtoBySlrAppointmentBookings(SlrAppointmentBookings booking) {
		AppointmentDto dto = new AppointmentDto();
		
		SlrSeller clinic = booking.getSlrSeller();
		
		dto.setId(booking.getId());
		dto.setClinicPhone(clinic.getMobileNumber());
		dto.setClinicEmail(clinic.getEmailAddress());
		dto.setCustomerCare("some info");
		dto.setClinicAddress(clinic.getSlrAddress().getCity() + " " + clinic.getSlrAddress().getAddress1());
		dto.setConfirmationNumber(booking.getConfirmationNumber());
        dto.setAppDate(booking.getApptDate());
        dto.setClinicId(clinic.getId());
        dto.setSlotId(booking.getSlrAppointmentSlots().getId());
        dto.setTime(booking.getSlrAppointmentSlots().getSlotTime().toString());
        dto.setClinicName(clinic.getName());
        dto.setPrice(booking.getSlrProduct().getPrice());
        dto.setTestName(booking.getSlrProduct().getName());

        if (booking.getGuestPerson() != null) {
            dto.setGuestPersonBirthDate(booking.getGuestPerson().getBirthDate());
        }

		return dto;
	}

    /**
     * initialize and return AppointmentDto list by SlrAppointmentBookings list
     *
     * @param appointments - List
     * @return - List
     */
    private List<AppointmentDto> initAppointmentDtoListByAppointmentList(List<SlrAppointmentBookings> appointments) {
        List<AppointmentDto> dtos = new ArrayList<>();

        for (SlrAppointmentBookings appointment : appointments) {
            dtos.add(initAppointmentDtoBySlrAppointmentBookings(appointment));
        }

        return dtos;
    }

}