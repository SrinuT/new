/**
 * 
 */
package com.ihm.customer.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ihm.customer.dao.AbstractHibernateDAO;
import com.ihm.customer.dao.AppointmentBookingsDao;
import com.ihm.customer.entites.SlrAppointmentBookings;

import java.util.List;

/**
 * <p>
 * this dao class for implementation AppointmentBookingsDao
 * 
 * @author Artur Yolchyan
 */
@Repository("appointmentBookingsDao")
public class AppointmentBookingsDaoImpl extends AbstractHibernateDAO<SlrAppointmentBookings, Long> implements AppointmentBookingsDao {
	
	/*
	 * (non-Javadoc)
	 * @see com.ihm.customer.dao.AppointmentBookingsDao#getAppointmentsByConfirmationNumber(java.lang.String)
	 */
	@Override
	public SlrAppointmentBookings getAppointmentsByConfirmationNumber(String confirmationNumber) {
		String hql = "FROM SlrAppointmentBookings AS sab WHERE sab.confirmationNumber = :confirmationNumber AND sab.bookingStatus != :bookingStatus";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("confirmationNumber", confirmationNumber);
        query.setParameter("bookingStatus", "CANCEL");
		return query.list().isEmpty() ? null : (SlrAppointmentBookings) query.list().get(0);
	}

    /*
	 * (non-Javadoc)
	 * @see com.ihm.customer.dao.AppointmentBookingsDao#getAppointmentsByPersonId(java.lang.Long)
	 */
    @Override
    public List<SlrAppointmentBookings> getAppointmentsByPersonId(Long personId) {
        String hql = "FROM SlrAppointmentBookings AS sab WHERE sab.person.id = :personId AND sab.bookingStatus != :bookingStatus";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("personId", personId);
        query.setParameter("bookingStatus", "CANCEL");
        return query.list();
    }

}
