/**
 * 
 */
package com.ihm.customer.faces.controller;

import com.ihm.customer.service.CalendarService;
import com.ihm.customer.service.GuestPersonService;
import com.ihm.mail.SendMail;
import org.springframework.beans.factory.annotation.Autowired;

import com.ihm.customer.faces.bean.SessionBean;
import com.ihm.customer.service.AppointmentBookingsService;
import com.ihm.customer.service.PersonService;
import org.springframework.context.ApplicationContext;

/**
 * <p>
 * this controller base for all jsf controllers
 * 
 * @author Artur Yolchyan
 */
public abstract class BaseController {
	
	@Autowired
	protected AppointmentBookingsService appointmentBookingsService;
	
	@Autowired
	protected PersonService personService;
	
	@Autowired
	protected SessionBean sessionBean;

    @Autowired
    protected SendMail sendMail;

    @Autowired
    protected GuestPersonService guestPersonService;

    @Autowired
    protected CalendarService calendarService;

    @Autowired
    protected ApplicationContext applicationContext;


    /**
     * this method for spring init-method
     *
     * marked abstract because if we do not do it
     * this method in subclasses will call multiple time
     */
    public abstract void init();
	
}