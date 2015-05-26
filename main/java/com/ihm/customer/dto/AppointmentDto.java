/**
 * 
 */
package com.ihm.customer.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Artur Yolchyan
 */
public class AppointmentDto implements Serializable {

	/**
	 * the serialVersionUID unique key for this class
	 */
	private static final long serialVersionUID = 8429979119825672274L;
	
	
	private Long id;
	
	private String clinicName;
	
	private String clinicPhone;
	
	private String clinicEmail;
	
	private BigDecimal price;
	
	private String customerCare;
	
	private String clinicAddress;
	
	private String confirmationNumber;

    private Date appDate;

    private Long clinicId;

    private boolean canceled;

    private Date guestPersonBirthDate;

    private String time;

    private Long slotId;

    private String testName;

	
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
	 * @return the clinicName
	 */
	public String getClinicName() {
		return clinicName;
	}

	/**
	 * @param clinicName the clinicName to set
	 */
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	/**
	 * @return the clinicPhone
	 */
	public String getClinicPhone() {
		return clinicPhone;
	}

	/**
	 * @param clinicPhone the clinicPhone to set
	 */
	public void setClinicPhone(String clinicPhone) {
		this.clinicPhone = clinicPhone;
	}

	/**
	 * @return the clinicEmail
	 */
	public String getClinicEmail() {
		return clinicEmail;
	}

	/**
	 * @param clinicEmail the clinicEmail to set
	 */
	public void setClinicEmail(String clinicEmail) {
		this.clinicEmail = clinicEmail;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the customerCare
	 */
	public String getCustomerCare() {
		return customerCare;
	}

	/**
	 * @param customerCare the customerCare to set
	 */
	public void setCustomerCare(String customerCare) {
		this.customerCare = customerCare;
	}

	/**
	 * @return the clinicAddress
	 */
	public String getClinicAddress() {
		return clinicAddress;
	}

	/**
	 * @param clinicAddress the clinicAddress to set
	 */
	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	/**
	 * @return the confirmationNumber
	 */
	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	/**
	 * @param confirmationNumber the confirmationNumber to set
	 */
	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public Date getGuestPersonBirthDate() {
        return guestPersonBirthDate;
    }

    public void setGuestPersonBirthDate(Date guestPersonBirthDate) {
        this.guestPersonBirthDate = guestPersonBirthDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}