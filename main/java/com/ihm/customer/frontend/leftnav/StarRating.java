package com.ihm.customer.frontend.leftnav;

import java.io.Serializable;

public class StarRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String iconPath;
	private int clinicCount;
	private String starRating;
	private boolean selected;
	
	public StarRating() {
		
	}
	
	

	public StarRating(String iconPath, int clinicCount, boolean selected) {
		this.iconPath = iconPath;
		this.clinicCount = clinicCount;
		this.selected = selected;
	}



	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public int getClinicCount() {
		return clinicCount;
	}

	public void setClinicCount(int clinicCount) {
		this.clinicCount = clinicCount;
	}

	public String getStarRating() {
		return starRating;
	}

	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}



}
