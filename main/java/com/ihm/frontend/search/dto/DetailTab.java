package com.ihm.frontend.search.dto;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailTab  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5897130473569114306L;

	private String clinicDescription;
    private String productDescription;
    private String emailAddress;
    private String webAddress;
    private String productName;
    private String mobileNumber;
    private String openingHours;
	
	private List<String> clinicImagesList ;
	private List<Facility> clinicFacilites;
	private List<Award> awardList;
	private List<Certification> certificationList;
	
	public DetailTab() {
		init();
	}

	public String getClinicDescription() {
		return clinicDescription;
	}

	public void setClinicDescription(String clinicDescription) {
		this.clinicDescription = clinicDescription;
	}

	public List<String> getClinicImagesList() {
		return clinicImagesList;
	}

	public void setClinicImagesList(List<String> clinicImagesList) {
		this.clinicImagesList = clinicImagesList;
	}
	
	public List<Facility> getClinicFacilites() {
		return clinicFacilites;
	}

	public void setClinicFacilites(List<Facility> clinicFacilites) {
		this.clinicFacilites = clinicFacilites;
	}

	public List<Certification> getCertificationList() {
		return certificationList;
	}

	public void setCertificationList(List<Certification> certificationList) {
		this.certificationList = certificationList;
	}

	public List<Award> getAwardList() {
		return awardList;
	}

	public void setAwardList(List<Award> awardList) {
		this.awardList = awardList;
	}

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    private void init() {
		
		clinicDescription = "";
		clinicImagesList = new ArrayList<String>();
		clinicFacilites = new ArrayList<Facility>();
		awardList = new ArrayList<Award>();
		certificationList = new ArrayList<Certification>();
	}
	
	
}
