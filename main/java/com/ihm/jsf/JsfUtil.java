/**
 * 
 */
package com.ihm.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Artur Yolchyan
 */
public final class JsfUtil {
	
	public static void addErrorMessage(String comonentId, String message) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, comonentId);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	public static void addErrorMessage(String message) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "");
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

    public static void addMessage(String message, FacesMessage.Severity serverity) {
        FacesMessage fm = new FacesMessage(serverity, message, "");
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

}
