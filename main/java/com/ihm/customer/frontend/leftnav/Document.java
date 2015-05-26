package com.ihm.customer.frontend.leftnav;
/*
 * This is for left Navigation Dynamic population Object
 * 
 * 
 */
import java.io.Serializable;
@SuppressWarnings("all")
public class Document implements Serializable {

	private boolean status;
	private String name;
	private String iconPath;
	
	public Document() {
		status = false;
		name = "";
		iconPath = "";
	}

	public Document(boolean status, String name, String iconPath) {
		super();
		this.status = status;
		this.name = name;
		this.iconPath = iconPath;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
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
