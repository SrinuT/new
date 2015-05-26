/**
 * 
 */
package com.ihm.customer.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * this class base for all dto and implements from Serializable
 * 
 * @author Artur Yolchyan
 */
public class BaseDto implements Serializable {

	/**
	 * the serialVersionUID unique key for this class
	 */
	private static final long serialVersionUID = 5597280901720385321L;
	
	protected Long id;
	
	protected Date createDate;
	
	protected Date updateDate;

	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
