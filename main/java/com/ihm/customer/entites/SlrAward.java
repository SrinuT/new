package com.ihm.customer.entites;

// Generated 30 Jan, 2015 2:34:08 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * SlrAward generated by hbm2java
 */
@Entity
@Table(name = "slr_award")
public class SlrAward implements java.io.Serializable {

	private long id;
	private Integer version;
	private Integer awardYear;
	private String createdBy;
	private Date createdOn;
	private String description;
	private String name;
	private String sourceObject;
	private String updatedBy;
	private Date updatedOn;
	
	private SlrUserProfile slrUserProfile;
	private SlrSeller slrSeller;

	public SlrAward() {
	}

	public SlrAward(long id) {
		this.id = id;
	}

	public SlrAward(long id, SlrUserProfile slrUserProfile,
			SlrSeller slrSeller, Integer awardYear, String createdBy,
			Date createdOn, String description, String name,
			String sourceObject, String updatedBy, Date updatedOn) {
		this.id = id;
		this.slrUserProfile = slrUserProfile;
		this.slrSeller = slrSeller;
		this.awardYear = awardYear;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.description = description;
		this.name = name;
		this.sourceObject = sourceObject;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_user_profile")
	public SlrUserProfile getSlrUserProfile() {
		return this.slrUserProfile;
	}

	public void setSlrUserProfile(SlrUserProfile slrUserProfile) {
		this.slrUserProfile = slrUserProfile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id")
	public SlrSeller getSlrSeller() {
		return this.slrSeller;
	}

	public void setSlrSeller(SlrSeller slrSeller) {
		this.slrSeller = slrSeller;
	}

	@Column(name = "award_year")
	public Integer getAwardYear() {
		return this.awardYear;
	}

	public void setAwardYear(Integer awardYear) {
		this.awardYear = awardYear;
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

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "source_object")
	public String getSourceObject() {
		return this.sourceObject;
	}

	public void setSourceObject(String sourceObject) {
		this.sourceObject = sourceObject;
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

}
