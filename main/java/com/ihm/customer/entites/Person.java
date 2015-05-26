package com.ihm.customer.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;


/**
 * <p>
 * this class for representation person in db
 * 
 * @author Artur Yolchyan
 */
@Entity
@Table(name = "person")
public class Person implements Serializable {

	/**
	 * the serialVersionUID generated automatically by JVM
	 */
	private static final long serialVersionUID = 1820657225571372834L;
	
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private Date createDate;

    private Set<SlrAppointmentBookings> slrAppointmentBookings;

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the firstName
	 */
	@Column(name = "first_name", nullable = false)
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
	@Column(name = "last_name", nullable = false)
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
	@Column(name = "email", nullable = false)
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
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<SlrAppointmentBookings> getSlrAppointmentBookings() {
        return slrAppointmentBookings;
    }

    public void setSlrAppointmentBookings(Set<SlrAppointmentBookings> slrAppointmentBookings) {
        this.slrAppointmentBookings = slrAppointmentBookings;
    }

}