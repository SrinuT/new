package com.ihm.customer.entites;

// Generated 30 Jan, 2015 2:34:08 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * UserProfile generated by hbm2java
 */
@Entity
@Table(name = "user_profile")
public class UserProfile implements java.io.Serializable {

	private long id;
	private Integer version;
	private String createdBy;
	private Date createdOn;
	private String disableFlg;
	private Date dob;
	private String emailAddress;
	private String emergencyContactNo;
	private String gender;
	private String givenName;
	private String lastName;
	private String primaryPhoneNo;
	private String secondaryPhoneNo;
	private String title;
	private String updatedBy;
	private Date updatedOn;
	private SlrUserProfile slrUserProfile;
	private CstUserProfile cstUserProfile;
	private Set<IhmUserRole> ihmUserRoles = new HashSet<IhmUserRole>(0);
	private Set<IhmLoginHistory> ihmLoginHistories = new HashSet<IhmLoginHistory>(
			0);

	public UserProfile() {
	}

	public UserProfile(long id) {
		this.id = id;
	}

	public UserProfile(long id, String createdBy, Date createdOn,
			String disableFlg, Date dob, String emailAddress,
			String emergencyContactNo, String gender, String givenName,
			String lastName, String primaryPhoneNo, String secondaryPhoneNo,
			String title, String updatedBy, Date updatedOn,
			SlrUserProfile slrUserProfile, CstUserProfile cstUserProfile,
			Set<IhmUserRole> ihmUserRoles,
			Set<IhmLoginHistory> ihmLoginHistories) {
		this.id = id;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.disableFlg = disableFlg;
		this.dob = dob;
		this.emailAddress = emailAddress;
		this.emergencyContactNo = emergencyContactNo;
		this.gender = gender;
		this.givenName = givenName;
		this.lastName = lastName;
		this.primaryPhoneNo = primaryPhoneNo;
		this.secondaryPhoneNo = secondaryPhoneNo;
		this.title = title;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.slrUserProfile = slrUserProfile;
		this.cstUserProfile = cstUserProfile;
		this.ihmUserRoles = ihmUserRoles;
		this.ihmLoginHistories = ihmLoginHistories;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", length = 19)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "disable_flg")
	public String getDisableFlg() {
		return this.disableFlg;
	}

	public void setDisableFlg(String disableFlg) {
		this.disableFlg = disableFlg;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dob", length = 19)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "email_address")
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name = "emergency_contact_no")
	public String getEmergencyContactNo() {
		return this.emergencyContactNo;
	}

	public void setEmergencyContactNo(String emergencyContactNo) {
		this.emergencyContactNo = emergencyContactNo;
	}

	@Column(name = "gender")
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "given_name")
	public String getGivenName() {
		return this.givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "primary_phone_no")
	public String getPrimaryPhoneNo() {
		return this.primaryPhoneNo;
	}

	public void setPrimaryPhoneNo(String primaryPhoneNo) {
		this.primaryPhoneNo = primaryPhoneNo;
	}

	@Column(name = "secondary_phone_no")
	public String getSecondaryPhoneNo() {
		return this.secondaryPhoneNo;
	}

	public void setSecondaryPhoneNo(String secondaryPhoneNo) {
		this.secondaryPhoneNo = secondaryPhoneNo;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "updated_by")
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on", length = 19)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userProfile")
	public SlrUserProfile getSlrUserProfile() {
		return this.slrUserProfile;
	}

	public void setSlrUserProfile(SlrUserProfile slrUserProfile) {
		this.slrUserProfile = slrUserProfile;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userProfile")
	public CstUserProfile getCstUserProfile() {
		return this.cstUserProfile;
	}

	public void setCstUserProfile(CstUserProfile cstUserProfile) {
		this.cstUserProfile = cstUserProfile;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ihm_user_role_xref", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public Set<IhmUserRole> getIhmUserRoles() {
		return this.ihmUserRoles;
	}

	public void setIhmUserRoles(Set<IhmUserRole> ihmUserRoles) {
		this.ihmUserRoles = ihmUserRoles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userProfile")
	public Set<IhmLoginHistory> getIhmLoginHistories() {
		return this.ihmLoginHistories;
	}

	public void setIhmLoginHistories(Set<IhmLoginHistory> ihmLoginHistories) {
		this.ihmLoginHistories = ihmLoginHistories;
	}

}
