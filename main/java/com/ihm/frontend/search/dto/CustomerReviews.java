package com.ihm.frontend.search.dto;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.Comparator;

public class CustomerReviews  implements Serializable , Comparator<CustomerReviews> { 


	/**
	 * 
	 */
	private static final long serialVersionUID = -4430295323247539672L;
	private String customerReview;
	private float customerStarRating;
	private int percentProgress;
	
	public int getPercentProgress() {
		return percentProgress;
	}

	public void setPercentProgress(int percentProgress) {
		this.percentProgress = percentProgress;
	}

	public CustomerReviews() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCustomerReview() {
		return customerReview;
	}

	public void setCustomerReview(String customerReview) {
		this.customerReview = customerReview;
	}

	public float getCustomerStarRating() {
		return customerStarRating;
	}

	public void setCustomerStarRating(float customerStarRating) {
		this.customerStarRating = customerStarRating;
	}

	@Override
	public int compare(CustomerReviews o1, CustomerReviews o2) {
		return o1.getCustomerStarRating() < o2.getCustomerStarRating() ? 0:-1;
	}
	
}
