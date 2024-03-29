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
 * IhmStateInfo generated by hbm2java
 */
@Entity
@Table(name = "ihm_state_info")
public class IhmStateInfo implements java.io.Serializable {

	private long id;
	private Integer version;
	private IhmCountry ihmCountry;
	private String code;
	private String createdBy;
	private Date createdOn;
	private String description;
	private String disableFlg;
	private String updatedBy;
	private Date updatedOn;

	public IhmStateInfo() {
	}

	public IhmStateInfo(long id) {
		this.id = id;
	}

	public IhmStateInfo(long id, IhmCountry ihmCountry, String code,
			String createdBy, Date createdOn, String description,
			String disableFlg, String updatedBy, Date updatedOn) {
		this.id = id;
		this.ihmCountry = ihmCountry;
		this.code = code;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.description = description;
		this.disableFlg = disableFlg;
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
	@JoinColumn(name = "country")
	public IhmCountry getIhmCountry() {
		return this.ihmCountry;
	}

	public void setIhmCountry(IhmCountry ihmCountry) {
		this.ihmCountry = ihmCountry;
	}

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Column(name = "disable_flg")
	public String getDisableFlg() {
		return this.disableFlg;
	}

	public void setDisableFlg(String disableFlg) {
		this.disableFlg = disableFlg;
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
