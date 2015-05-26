package com.ihm.customer.frontend.leftnav;

import java.io.Serializable;

public class CarParking  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean self;
	private boolean shared;
	
	public CarParking() {
		
	}

	public boolean isSelf() {
		return self;
	}

	public void setSelf(boolean self) {
		this.self = self;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}
	
	
	
}
