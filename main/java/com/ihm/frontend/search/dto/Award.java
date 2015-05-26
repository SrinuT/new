package com.ihm.frontend.search.dto;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;

public class Award  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1814960270562700466L;
	private String awardName;
	private String awardDate;
	private String description;
	private String image;
	
	public Award() {
		
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
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
