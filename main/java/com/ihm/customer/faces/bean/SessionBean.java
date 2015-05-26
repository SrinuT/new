/**
 * 
 */
package com.ihm.customer.faces.bean;

import java.io.Serializable;

import com.ihm.customer.dto.PersonDto;

/**
 * <p>
 * this class for session scope and added as a session
 * for keep values which should be kept an all session
 * time
 * 
 * @author Artur Yolchyan
 */
public class SessionBean implements Serializable {

	/**
	 * the serialVersionUID unique key for this class
	 */
	private static final long serialVersionUID = -5683670472043002461L;
	
	private PersonDto personDto;

	
	/**
	 * @return the personDto
	 */
	public PersonDto getPersonDto() {
		return personDto;
	}

	/**
	 * @param personDto the personDto to set
	 */
	public void setPersonDto(PersonDto personDto) {
		this.personDto = personDto;
	}
}
