package com.ihm.customer.frontend.leftnav;

import java.io.Serializable;

public class DoorStepCollection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isDoorStepYes;
	private boolean isDoorStepNo;
	
	
	public DoorStepCollection() {
		
	}


	public boolean isDoorStepYes() {
		return isDoorStepYes;
	}


	public void setDoorStepYes(boolean isDoorStepYes) {
		this.isDoorStepYes = isDoorStepYes;
	}


	public boolean isDoorStepNo() {
		return isDoorStepNo;
	}


	public void setDoorStepNo(boolean isDoorStepNo) {
		this.isDoorStepNo = isDoorStepNo;
	}
	
	
	
}
