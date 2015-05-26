package com.ihm.customer.frontend.leftnav;

import java.io.Serializable;

public class RangeFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int distanceStart;
	private int distanceEnd;
	
	private int priceStart;
	private int priceEnd;
	
	
	public RangeFilter() {
	
		distanceStart = 0;
		distanceStart = 1000;
		priceStart = 0;
		priceEnd = 500;
	}


	public int getDistanceStart() {
		return distanceStart;
	}


	public void setDistanceStart(int distanceStart) {
		this.distanceStart = distanceStart;
	}


	public int getDistanceEnd() {
		return distanceEnd;
	}


	public void setDistanceEnd(int distanceEnd) {
		this.distanceEnd = distanceEnd;
	}


	public int getPriceStart() {
		return priceStart;
	}


	public void setPriceStart(int priceStart) {
		this.priceStart = priceStart;
	}


	public int getPriceEnd() {
		return priceEnd;
	}


	public void setPriceEnd(int priceEnd) {
		this.priceEnd = priceEnd;
	}
	
	
}
