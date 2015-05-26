package com.ihm.frontend.search.dto;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.primefaces.model.map.MapModel;

public class Clinic implements Serializable , Comparator<Clinic> {  

	private static Logger logger = Logger.getLogger(Clinic.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String clinicName;
	private String testName;
	private String clinicUIId;
	private String totalCustomerRated;
	private int scoredRating;
	private String id;
	private Integer percentageProgress;
	private String ratingImage;
	private String testPrice;
	private String distanceDifference;
	private String productImage;
	private String testDiscount;
	private String testDiscountAmount;
	private String testDiscountPercent;
	private String primaryImage;
	private String clinicEmail;
	private String landLineNumber;
	
	private String imageUrl1;
	private String imageUrl2;
	private String imageUrl3;
	private String imageUrl4;
	private String imageUrl5;
	private String videoUrl;
    private Long slrSellerId;
	
	public String getImageUrl1() {
		return imageUrl1;
	}

	public void setImageUrl1(String imageUrl1) {
		this.imageUrl1 = imageUrl1;
	}

	public String getImageUrl2() {
		return imageUrl2;
	}

	public void setImageUrl2(String imageUrl2) {
		this.imageUrl2 = imageUrl2;
	}

	public String getImageUrl3() {
		return imageUrl3;
	}

	public void setImageUrl3(String imageUrl3) {
		this.imageUrl3 = imageUrl3;
	}

	public String getImageUrl4() {
		return imageUrl4;
	}

	public void setImageUrl4(String imageUrl4) {
		this.imageUrl4 = imageUrl4;
	}

	public String getImageUrl5() {
		return imageUrl5;
	}

	public void setImageUrl5(String imageUrl5) {
		this.imageUrl5 = imageUrl5;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	private ClinicAddress clinicAddress;
	private DetailTab detailTab;
	private MapTab mapTab;
	private MapModel clinicMap;
	private DealsTab dealsTab;
	private ReviewsTab reviewsTab;
	private Date nexttimeslot;
	private List<Appointment> timingSlot;
	private List<String> facilitesList;
	
	
	
	public Clinic() {
		logger.info(":: Clinic Created :: ");
		init();
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getTotalCustomerRated() {
		return totalCustomerRated;
	}

	public void setTotalCustomerRated(String totalCustomerRated) {
		this.totalCustomerRated = totalCustomerRated;
	}

	public int getScoredRating() {
		return scoredRating;
	}

	public void setScoredRating(int scoredRating) {
		this.scoredRating = scoredRating;
	}

	public String getRatingImage() {
		return ratingImage;
	}

	public void setRatingImage(String ratingImage) {
		this.ratingImage = ratingImage;
	}
	public Integer getPercentageProgress() {
		return percentageProgress;
	}

	public void setPercentageProgress(Integer percentageProgress) {
		this.percentageProgress = percentageProgress;
	}

	public String getTestPrice() {
		return testPrice;
	}

	public void setTestPrice(String testPrice) {
		this.testPrice = testPrice;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getDistanceDifference() {
		return distanceDifference;
	}

	public void setDistanceDifference(String distanceDifference) {
		this.distanceDifference = distanceDifference;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getTestDiscount() {
		return testDiscount;
	}

	public void setTestDiscount(String testDiscount) {
		this.testDiscount = testDiscount;
	}

	public String getPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(String primaryImage) {
		this.primaryImage = primaryImage;
	}

	public String getClinicEmail() {
		return clinicEmail;
	}

	public void setClinicEmail(String clinicEmail) {
		this.clinicEmail = clinicEmail;
	}

	public String getLandLineNumber() {
		return landLineNumber;
	}

	public void setLandLineNumber(String landLineNumber) {
		this.landLineNumber = landLineNumber;
	}

	public String getClinicUIId() {
		return clinicUIId;
	}

	public void setClinicUIId(String clinicUIId) {
		this.clinicUIId = clinicUIId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ClinicAddress getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(ClinicAddress clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	public DetailTab getDetailTab() {
		return detailTab;
	}

	public void setDetailTab(DetailTab detailTab) {
		this.detailTab = detailTab;
	}

	public MapTab getMapTab() {
		return mapTab;
	}

	public void setMapTab(MapTab mapTab) {
		this.mapTab = mapTab;
	}

	
	public DealsTab getDealsTab() {
		return dealsTab;
	}

	public void setDealsTab(DealsTab dealsTab) {
		this.dealsTab = dealsTab;
	}

	public ReviewsTab getReviewsTab() {
		return reviewsTab;
	}

	public void setReviewsTab(ReviewsTab reviewsTab) {
		this.reviewsTab = reviewsTab;
	}

	public List<Appointment> getTimingSlot() {
		return timingSlot;
	}

	public void setTimingSlot(List<Appointment> timingSlot) {
		this.timingSlot = timingSlot;
	}
	
	
	

	public List<String> getFacilitesList() {
		return facilitesList;
	}

	public void setFacilitesList(List<String> facilitesList) {
		this.facilitesList = facilitesList;
	}

	private void init() {
	
	clinicName = "";
	totalCustomerRated = "";
	scoredRating = 0;
	ratingImage = "";
	testPrice = "";
	testName = "";
	distanceDifference = "";
	productImage = "";
	testDiscount = "";
	primaryImage = "";
	clinicEmail = "";
	landLineNumber = "";
	
	clinicAddress = new ClinicAddress();
	detailTab = new DetailTab();
	dealsTab = new DealsTab();
	reviewsTab = new ReviewsTab();
	
	timingSlot = new ArrayList<Appointment>();
	facilitesList = new ArrayList<String>();
	}

	@Override
	public int compare(Clinic o1, Clinic o2) {
		return o1.getScoredRating() < o2.getScoredRating() ? 0:-1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clinicName == null) ? 0 : clinicName.hashCode());
		result = prime * result + scoredRating;
		result = prime * result
				+ ((testPrice == null) ? 0 : testPrice.hashCode());
		result = prime
				* result
				+ ((totalCustomerRated == null) ? 0 : totalCustomerRated
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clinic other = (Clinic) obj;
		if (clinicName == null) {
			if (other.clinicName != null)
				return false;
		} else if (!clinicName.equals(other.clinicName))
			return false;
		if (scoredRating != other.scoredRating)
			return false;
		if (testPrice == null) {
			if (other.testPrice != null)
				return false;
		} else if (!testPrice.equals(other.testPrice))
			return false;
		if (totalCustomerRated == null) {
			if (other.totalCustomerRated != null)
				return false;
		} else if (!totalCustomerRated.equals(other.totalCustomerRated))
			return false;
		return true;
	}

	public MapModel getClinicMap() {
		return clinicMap;
	}

	public void setClinicMap(MapModel clinicMap) {
		this.clinicMap = clinicMap;
	}

	public Date getNexttimeslot() {
		return nexttimeslot;
	}

	public void setNexttimeslot(Date startdatefromdb) {
		this.nexttimeslot = startdatefromdb;
	}

	public String getTestDiscountAmount() {
		return testDiscountAmount;
	}

	public void setTestDiscountAmount(String testDiscountAmount) {
		this.testDiscountAmount = testDiscountAmount;
	}

	public String getTestDiscountPercent() {
		return testDiscountPercent;
	}

	public void setTestDiscountPercent(String testDiscountPercent) {
		this.testDiscountPercent = testDiscountPercent;
	}

    public Long getSlrSellerId() {
        return slrSellerId;
    }
	
    public void setSlrSellerId(Long slrSellerId) {
        this.slrSellerId = slrSellerId;
    }
}
