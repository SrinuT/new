/**
 * 
 */
package com.ihm.customer.dto;


import java.util.Date;

/**
 * <p>
 * this class for representation person on web layer
 * 
 * @author Artur Yolchyan
 */
public class PersonDto extends BaseDto {

	/**
	 * the serialVersionUID unique key for this class
	 */	
	private static final long serialVersionUID = 1757185382954814739L;

	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String confPassword;
	
	private boolean termsAndConditions;


	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confPassword
	 */
	public String getConfPassword() {
		return confPassword;
	}

	/**
	 * @param confPassword the confPassword to set
	 */
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	/**
	 * @return the termsAndConditions
	 */
	public boolean isTermsAndConditions() {
		return termsAndConditions;
	}

	/**
	 * @param termsAndConditions the termsAndConditions to set
	 */
	public void setTermsAndConditions(boolean termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

}