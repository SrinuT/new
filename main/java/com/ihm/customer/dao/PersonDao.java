/**
 * 
 */
package com.ihm.customer.dao;

import com.ihm.customer.entites.Person;

/**
 * <p>
 * this class for management persons in db
 * 
 * @author Artur Yolchyan
 */
public interface PersonDao extends GenericDAO<Person, Long> {
	
	/**
	 * this method return person from db by email and password
	 * 
	 * @param username - String
	 * @param email - String
	 * @return - Person
	 */
	Person getPersonByUsernameAndPassword(String email, String password);

    Person getByEmail(String email);
	
}
