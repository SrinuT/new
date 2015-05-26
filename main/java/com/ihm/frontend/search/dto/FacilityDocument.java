package com.ihm.frontend.search.dto;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
@SuppressWarnings("all")
public class FacilityDocument implements Serializable {

	private String status;
	private String name;
	private String iconPath;
	
	public FacilityDocument() {
		status = "";
		name = "";
		iconPath = "";
	}

	public FacilityDocument(String status, String name, String iconPath) {
		super();
		this.status = status;
		this.name = name;
		this.iconPath = iconPath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	
	
}
