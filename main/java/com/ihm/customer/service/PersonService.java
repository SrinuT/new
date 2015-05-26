/**
 * 
 */
package com.ihm.customer.service;

import com.ihm.customer.dto.PersonDto;

/**
 * <p>
 * this service class 
 * 
 * @author Artur Yolchyan
 */
public interface PersonService {
	

	/**
	 * return PersonDto from db by username or password
	 * 
	 * @param email - String
	 * @param password - String
	 * @return - PersonDto
	 */
	PersonDto personByCredentials(String email, String password);
	
	/**
	 * this method created new person
	 * 
	 * @param personDto - PersonDto
	 */
	void createOrUpdatePerson(PersonDto personDto);

    /**
     * return PersonDto by email address
     *
     * @param email - String
     * @return - PersonDto
     */
    PersonDto getByEmail(String email);
	
}