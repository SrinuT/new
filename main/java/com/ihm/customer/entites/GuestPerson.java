package com.ihm.customer.entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     this class mapped with table guest_pers
 * </p>
 *
 * @author Artur Yolchyan
 */
@Entity
@Table(name = "guest_person")
public class GuestPerson implements Serializable {

    /**
     * the serialVersionUID unique key for this class
     */
    private static final long serialVersionUID = 3317683739761322178L;


    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Date createDate;

    private Date birthDate;

    private String contactNumber;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", length = 19)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "primary_contact_number")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
