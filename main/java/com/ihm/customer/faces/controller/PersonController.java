/**
 * 
 */
package com.ihm.customer.faces.controller;

import java.io.IOException;
import java.io.Serializable;

import com.ihm.customer.dto.PersonDto;
import com.ihm.jsf.JsfUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

/**
 * <p>
 * this class for management person functions on front end
 * 
 * @author Artur Yolchyan
 */
public class PersonController extends BaseController implements Serializable {

	/**
	 * the serialVersionUID unique key for this class
	 */
	private static final long serialVersionUID = 8912303937368581405L;
	
	
	private PersonDto personDto;

    private String emailForgotPassword;

	/**
	 * initialize properties for first start
	 */
	public PersonController() {
		personDto = new PersonDto();
	}


    public void init() {
    }

	/**
	 * this method for registration person, in case
	 * registration ok returned registered page, otherwise
	 * return same page with error messages
	 * 
	 * @return - String
	 */
	public void onRegistration(ActionEvent actionEvent) {
        if (personDto.isTermsAndConditions()) {
            try {
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                personService.createOrUpdatePerson(personDto);
                sendMail.send("ihelthmantra@gmail.com", personDto.getEmail(), "IHM registration", "Congratulations, you have successfully registered in IHM ");
                JsfUtil.addMessage("You have successfully registered, you receive email", FacesMessage.SEVERITY_INFO);
                context.getFlash().setKeepMessages(true);
                context.redirect(context.getRequestContextPath() + "/pages/login/login.xhtml");
            } catch (IOException e) {
            }
        } else {
            JsfUtil.addMessage("Please accept terms and conditions", FacesMessage.SEVERITY_ERROR);
        }
    }

    public String onSubmitPassword() {
        PersonDto forgotPasswordPerson = personService.getByEmail(emailForgotPassword);
        if (forgotPasswordPerson == null) {
            JsfUtil.addErrorMessage("This email is not registered in ihm system");
            return "/pages/login/forgotPassword.xhtml";
        } else {
            sendMail.send("ihelthmantra@gmail.com", emailForgotPassword, "IHM forgot password", "Your password is " + forgotPasswordPerson.getPassword());
            JsfUtil.addMessage("We sent your password to your email, check and login!", FacesMessage.SEVERITY_INFO);
            return "/pages/login/login.xhtml";
        }
    }

    /**
     * this method for checking is are equals email and confirm email
     *
     * @param event - ComponentSystemEvent
     */
    public void validatePassword(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        // get password
        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();

        // get confirm email
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? "" : uiInputConfirmPassword.getLocalValue().toString();

        // Let required="true" do its job.
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }

        if (!password.equals(confirmPassword)) {
            FacesMessage msg = new FacesMessage("Password must match password email");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg);
            fc.renderResponse();
        }
    }

	/**
	 * @return the personDto
	 */
	public PersonDto getPersonDto() {
		return personDto;
	}

	/**
	 * @param personDto the personDto to set
	 */
	public void setPersonDto(PersonDto personDto) {
		this.personDto = personDto;
	}

    public String getEmailForgotPassword() {
        return emailForgotPassword;
    }

    public void setEmailForgotPassword(String emailForgotPassword) {
        this.emailForgotPassword = emailForgotPassword;
    }
}
