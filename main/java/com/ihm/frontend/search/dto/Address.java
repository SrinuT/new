package com.ihm.frontend.search.dto;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;

public abstract  class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float lattitude;
	private float longitude;
	private String country;
	private String state;
	private String city;
	private String zipCode;
	
	public float getLattitude() {
		return lattitude;
	}
	public void setLattitude(float lattitude) {
		this.lattitude = lattitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
