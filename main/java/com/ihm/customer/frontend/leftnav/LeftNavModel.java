package com.ihm.customer.frontend.leftnav;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class LeftNavModel implements Serializable {

	static final long serialVersionUID = -1l;
	
	private RangeFilter rangeFilter;
	private ReportDelivery reportDelivery;
	private DoorStepCollection doorStepCollection;
	private WaitingLounge waitingLounge ;
	private Entertainment entertainment;
	private CarParking carParking;
	private CarParking twoWheelerParking;
	
	private List<StarRating> starRatingList;
	private List<HospitalAmenities> hosAmenitiesList;
	
	private double priceStart;
	private double priceEnd;
	private double distanceStart;
	private double distanceEnd;
	private int sortBy = 1;
	
	private boolean filterByPrice = false;
	private boolean filterByDistance = false;

	public LeftNavModel() {
		
		rangeFilter = new RangeFilter();
		reportDelivery = new ReportDelivery();
		doorStepCollection = new DoorStepCollection();
		waitingLounge = new WaitingLounge();
		entertainment = new Entertainment();
		carParking = new CarParking();
		twoWheelerParking = new CarParking();
		
		this.clearSliders();
		starRatingList = new ArrayList<StarRating>();
		hosAmenitiesList = new ArrayList<HospitalAmenities>();
	}

	public ReportDelivery getReportDelivery() {
		return reportDelivery;
	}

	public void setReportDelivery(ReportDelivery reportDelivery) {
		this.reportDelivery = reportDelivery;
	}

	public DoorStepCollection getDoorStepCollection() {
		return doorStepCollection;
	}

	public void setDoorStepCollection(DoorStepCollection doorStepCollection) {
		this.doorStepCollection = doorStepCollection;
	}

	public WaitingLounge getWaitingLounge() {
		return waitingLounge;
	}

	public void setWaitingLounge(WaitingLounge waitingLounge) {
		this.waitingLounge = waitingLounge;
	}

	public Entertainment getEntertainment() {
		return entertainment;
	}

	public void setEntertainment(Entertainment entertainment) {
		this.entertainment = entertainment;
	}

	public CarParking getCarParking() {
		return carParking;
	}

	public void setCarParking(CarParking carParking) {
		this.carParking = carParking;
	}

	public CarParking getTwoWheelerParking() {
		return twoWheelerParking;
	}

	public void setTwoWheelerParking(CarParking twoWheelerParking) {
		this.twoWheelerParking = twoWheelerParking;
	}

	public int getSortBy() {
		return sortBy;
	}

	public void setSortBy(int sortBy) {
		this.sortBy = sortBy;
	}

	public boolean isFilterByPrice() {
		return filterByPrice;
	}

	public void setFilterByPrice(boolean filterByPrice) {
		this.filterByPrice = filterByPrice;
	}

	public boolean isFilterByDistance() {
		return filterByDistance;
	}

	public void setFilterByDistance(boolean filterByDistance) {
		this.filterByDistance = filterByDistance;
	}

	public RangeFilter getRangeFilter() {
		return rangeFilter;
	}

	public double getPriceStart() {
		return priceStart;
	}

	public void setPriceStart(double priceStart) {
		this.priceStart = priceStart;
	}

	public double getPriceEnd() {
		return priceEnd;
	}

	public void setPriceEnd(double priceEnd) {
		this.priceEnd = priceEnd;
	}

	public double getDistanceStart() {
		return distanceStart;
	}

	public void setDistanceStart(double distanceStart) {
		this.distanceStart = distanceStart;
	}

	public double getDistanceEnd() {
		return distanceEnd;
	}

	public void setDistanceEnd(double distanceEnd) {
		this.distanceEnd = distanceEnd;
	}

	public void setRangeFilter(RangeFilter rangeFilter) {
		this.rangeFilter = rangeFilter;
	}

	public List<StarRating> getStarRatingList() {
		return starRatingList;
	}

	public void setStarRatingList(List<StarRating> list) {
		starRatingList = list;
	}

	public List<HospitalAmenities> getHosAmenitiesList() {
		return hosAmenitiesList;
	}

	public void setHosAmenitiesList(List<HospitalAmenities> list) {
		hosAmenitiesList = list;
	}

	public void clearSliders() {
	
		priceStart = 0.0;
		priceEnd = 0.0;
		distanceStart = 0.0;
		distanceEnd = 0.0;
		
	}

}
