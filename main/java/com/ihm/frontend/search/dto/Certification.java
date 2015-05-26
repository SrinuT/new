package com.ihm.frontend.search.dto;

import java.io.Serializable;

/**
 * @author SARDAR WAQAS AHMED
 * @email  architect_pakistan@hotmail.com
 * @since  15-01-2015
 * @version 1.0
 */
public class Certification implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7250480303592200824L;
	private String certificateName;
	private String certificateDate;
	private String description;
	private String image;
	
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	public String getCertificateDate() {
		return certificateDate;
	}
	public void setCertificateDate(String certificateDate) {
		this.certificateDate = certificateDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
