/**
 * 
 */
package com.ihm.customer.faces.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.ihm.customer.entites.SlrAppointmentSlots;
import com.ihm.customer.util.DateUtil;
import com.ihm.frontend.search.dto.Clinic;
import com.ihm.frontend.search.dto.CompareClinic;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.restfaces.annotation.ViewListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ihm.customer.dto.AppointmentDto;
import com.ihm.customer.dto.PersonDto;
import com.ihm.customer.faces.bean.AppointmentConfPageRender;
import com.ihm.customer.service.AppointmentBookingsService;
import com.ihm.jsf.JsfUtil;
import com.ihm.util.Constants;

/**
 * <p>
 * this class implements from {@link Serializable}
 * 
 * <p>
 * this class for management appointmentguestconf.xhtml page
 * 
 * @author Artur Yolchyan
 */
public class AppointmentConfController extends BaseController implements Serializable {

	/**
	 * the serialVersionUID unique key for this class
	 */
	private static final long serialVersionUID = 5410039956146565819L;
	
	
	private AppointmentConfPageRender pageRender;
	
	private String confirmationNumber;
	
	private AppointmentDto appointment;
	
	private boolean previewDialogVisible;
	
	private Date newDate;
	
	private List<AppointmentDto> appointments;

    private Date bitrhDate;

    private boolean notFirstTime;

    private ScheduleModel eventModel;
	
	/**
	 * this method for initialization logined person
	 */
    @ViewListener(value = "/appointmentguestconf.xhtml",
                  when = "#{facesContext.externalContext.request.parameterMap['appointmentGuestForm:forViewListener'][0] == null}")
	public void init() {
        if (!notFirstTime) {
            pageRender = new AppointmentConfPageRender();
            AppointmentController appController = (AppointmentController) applicationContext.getBean("appController");

            if (sessionBean.getPersonDto() == null) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null) {
                    PersonDto personDto = personService.personByCredentials((String) authentication.getPrincipal(), (String) authentication.getCredentials());
                    sessionBean.setPersonDto(personDto);
                    if (appController.getMakeAppointment() != null) {
                        try {
                            appController.setShowAppointmenttMake(true);
                            FacesContext.getCurrentInstance().getExternalContext().redirect("appointment/appointment.xhtml");
                        } catch (IOException e) {

                        }
                    }
                }
            }
            if (sessionBean.getPersonDto() != null) {
                initPageRenderProperties(false, true, false);
                appointments = appointmentBookingsService.getAppointmentsByPersonId(sessionBean.getPersonDto().getId());
            }

            notFirstTime = true;
        }
	}
		
	/**
	 * this method return page with search parameters
	 * 
	 * @param actionEvent - ActionEvent
	 */
	public void onGo(ActionEvent actionEvent) {
		if (sessionBean.getPersonDto() == null) {
			appointment = appointmentBookingsService.getAppointmentByConfirmationNumber(confirmationNumber);
            appointments = new ArrayList<>();
            appointments.add(appointment);
			if (appointment != null) {
				initPageRenderProperties(false, true, false);
			} else {
				JsfUtil.addErrorMessage(":appointmentGuestForm:confirmationNumberId", "Invalid Confirmation Number, not exists data with this confirmation number");
			}
		}
	}
	
	/**
	 * this method called when clicking on change/cancel button
	 * 
	 * @param actionEvent - ActionEvent
	 */
	public void onChangeCancelView(ActionEvent actionEvent) {
        previewDialogVisible = false;
        appointment = (AppointmentDto) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("appointment");
        initPageRenderProperties(false, false, true);
	}
	
	/**
	 * 
	 * @param actionEvent - ActionEvent
	 */
	public void onDialogShow(ActionEvent actionEvent) {
        appointment = (AppointmentDto) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("appointment");
		previewDialogVisible = true;
	}

	/**
	 * this method called when clicking on cancel appointment button
	 * 
	 * @param actionEvent - ActionEvent
	 */
	public void onCancelAppointment(ActionEvent actionEvent) {
        if (sessionBean.getPersonDto() != null) {
            appointmentBookingsService.updateAppointmentStatus(appointment.getId(), Constants.CANCEL_STATUS);
            appointment.setCanceled(true);
            SlrAppointmentSlots updateSlotDTO = calendarService.getSlotById(appointment.getSlotId());
            updateSlotDTO.setActive(true);
            calendarService.updateAppointmentSlots(updateSlotDTO);
            JsfUtil.addMessage("You have successfully canceled appointment", FacesMessage.SEVERITY_INFO);
        } else if (bitrhDate != null && bitrhDate.equals(appointment.getGuestPersonBirthDate())) {
            appointmentBookingsService.updateAppointmentStatus(appointment.getId(), Constants.CANCEL_STATUS);
            appointment.setCanceled(true);
            SlrAppointmentSlots updateSlotDTO = calendarService.getSlotById(appointment.getSlotId());
            updateSlotDTO.setActive(true);
            calendarService.updateAppointmentSlots(updateSlotDTO);
            JsfUtil.addMessage("You have successfully canceled appointment", FacesMessage.SEVERITY_INFO);
        } else {
            JsfUtil.addErrorMessage("Please enter valid birth date");
        }
	}
	
	
	/**
	 * initialize pageRender properties by given values
	 * 
	 * @param guestConf - boolean
	 * @param editList - boolean
	 * @param editList - boolean
	 */
	private void initPageRenderProperties(boolean guestConf, boolean editList, boolean changeAppointment) {
		pageRender.setAppointmentChange(changeAppointment);
		pageRender.setAppointmentEditList(editList);
		pageRender.setAppointmentGuestConf(guestConf);
	}
	
	/**
	 * @return the pageRender
	 */
	public AppointmentConfPageRender getPageRender() {
		return pageRender;
	}

	/**
	 * @param pageRender the pageRender to set
	 */
	public void setPageRender(AppointmentConfPageRender pageRender) {
		this.pageRender = pageRender;
	}

	/**
	 * @return the confirmationNumber
	 */
	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	/**
	 * @param confirmationNumber the confirmationNumber to set
	 */
	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	/**
	 * @return the appointmentBookingsService
	 */
	public AppointmentBookingsService getAppointmentBookingsService() {
		return appointmentBookingsService;
	}

	/**
	 * @param appointmentBookingsService the appointmentBookingsService to set
	 */
	public void setAppointmentBookingsService(AppointmentBookingsService appointmentBookingsService) {
		this.appointmentBookingsService = appointmentBookingsService;
	}

	/**
	 * @return the appointment
	 */
	public AppointmentDto getAppointment() {
		return appointment;
	}

	/**
	 * @param appointment the appointment to set
	 */
	public void setAppointment(AppointmentDto appointment) {
		this.appointment = appointment;
	}

	/**
	 * @return the previewDialogVisible
	 */
	public boolean isPreviewDialogVisible() {
		return previewDialogVisible;
	}

	/**
	 * @param previewDialogVisible the previewDialogVisible to set
	 */
	public void setPreviewDialogVisible(boolean previewDialogVisible) {
		this.previewDialogVisible = previewDialogVisible;
	}

	/**
	 * @return the newDate
	 */
	public Date getNewDate() {
		return newDate;
	}

	/**
	 * @param newDate the newDate to set
	 */
	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	/**
	 * @return the appointments
	 */
	public List<AppointmentDto> getAppointments() {
		return appointments;
	}

	/**
	 * @param appointments the appointments to set
	 */
	public void setAppointments(List<AppointmentDto> appointments) {
		this.appointments = appointments;
	}

    public Date getBitrhDate() {
        return bitrhDate;
    }

    public void setBitrhDate(Date bitrhDate) {
        this.bitrhDate = bitrhDate;
    }

    public boolean isNotFirstTime() {
        return notFirstTime;
    }

    public void setNotFirstTime(boolean notFirstTime) {
        this.notFirstTime = notFirstTime;
    }

}