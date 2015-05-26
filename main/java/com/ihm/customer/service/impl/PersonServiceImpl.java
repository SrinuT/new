/**
 * 
 */
package com.ihm.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ihm.customer.dao.PersonDao;
import com.ihm.customer.dto.PersonDto;
import com.ihm.customer.entites.Person;
import com.ihm.customer.service.PersonService;

/**
 * <p>
 * this class implements from {@link PersonService}
 * 
 * @author Artur Yolchyan
 */
@Service("personService")
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;
	
	/*
	 * (non-Javadoc)
	 * @see com.ihm.customer.service.PersonService#personByCredentials(java.lang.String, java.lang.String)
	 */
	@Override
	public PersonDto personByCredentials(String email, String password) {
		Person person = personDao.getPersonByUsernameAndPassword(email, password);
		PersonDto personDto = null;
		if (person != null) {
			personDto = initPersonDtoByPerson(person);
		}
		return personDto;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ihm.customer.service.PersonService#createOrUpdatePerson(com.ihm.customer.dto.PersonDto)
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void createOrUpdatePerson(PersonDto personDto) {
		Person person = initPersonByPersonDto(personDto);
		personDao.saveOrEdit(person);
	}

    /*
	 * (non-Javadoc)
	 * @see com.ihm.customer.service.PersonService#getByEmail(java.lang.String)
	 */
    @Override
    public PersonDto getByEmail(String email) {
        Person person = personDao.getByEmail(email);
        return person == null ? null : initPersonDtoByPerson(person);
    }

    /**
	 * this method return PersonDto by Person
	 * 
	 * @param person - Person
	 * @return - PersonDto
	 */
	private PersonDto initPersonDtoByPerson(Person person) {
		PersonDto personDto = new PersonDto();
		personDto.setId(person.getId());
		personDto.setPassword(person.getPassword());
		personDto.setCreateDate(person.getCreateDate());
		personDto.setEmail(person.getEmail());
		personDto.setLastName(person.getLastName());
		return personDto;
	}
	
	/**
	 * initialize Person by PersonDto
	 * 
	 * @param personDto - PersonDto
	 */
	private Person initPersonByPersonDto(PersonDto personDto) {
		Person person = new Person();
		person.setId(personDto.getId());
		person.setEmail(personDto.getEmail());
		person.setFirstName(personDto.getEmail());
		person.setLastName(personDto.getLastName());
		person.setPassword(personDto.getPassword());
		return person;
	}
}