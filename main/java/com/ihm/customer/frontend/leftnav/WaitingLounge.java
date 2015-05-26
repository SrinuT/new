package com.ihm.customer.frontend.leftnav;

import java.io.Serializable;

public class WaitingLounge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean airConditioned;
	private boolean nonAirConditioned;
	private boolean wifiEnabled;
	
	public WaitingLounge() {
		
	}

	public boolean isAirConditioned() {
		return airConditioned;
	}

	public void setAirConditioned(boolean airConditioned) {
		this.airConditioned = airConditioned;
	}

	public boolean isNonAirConditioned() {
		return nonAirConditioned;
	}

	public void setNonAirConditioned(boolean nonAirConditioned) {
		this.nonAirConditioned = nonAirConditioned;
	}

	public boolean isWifiEnabled() {
		return wifiEnabled;
	}

	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}
	
	

}
