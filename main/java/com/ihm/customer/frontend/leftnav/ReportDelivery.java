package com.ihm.customer.frontend.leftnav;

import java.io.Serializable;

public class ReportDelivery implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean email;
	private boolean home;
	private boolean phrIntegration;
	
	
	public ReportDelivery() {
		
	}


	public boolean isEmail() {
		return email;
	}


	public void setEmail(boolean email) {
		this.email = email;
	}


	public boolean isHome() {
		return home;
	}


	public void setHome(boolean home) {
		this.home = home;
	}


	public boolean isPhrIntegration() {
		return phrIntegration;
	}


	public void setPhrIntegration(boolean phrIntegration) {
		this.phrIntegration = phrIntegration;
	}
	
	

}
