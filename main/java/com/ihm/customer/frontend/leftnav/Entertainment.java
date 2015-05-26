package com.ihm.customer.frontend.leftnav;

import java.io.Serializable;

public class Entertainment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean television;
	private boolean newsPaper;
	private boolean magzine;
	
	
	public Entertainment() {
		
	}


	public boolean isTelevision() {
		return television;
	}


	public void setTelevision(boolean television) {
		this.television = television;
	}


	public boolean isNewsPaper() {
		return newsPaper;
	}


	public void setNewsPaper(boolean newsPaper) {
		this.newsPaper = newsPaper;
	}


	public boolean isMagzine() {
		return magzine;
	}


	public void setMagzine(boolean magzine) {
		this.magzine = magzine;
	}
	
	
	
	

}
