package com.ihm.frontend.search.dto;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReviewsTab  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1059792362893441824L;
	private List<CustomerReviews> customerReviewsList;
	
	public ReviewsTab() {
		init();
	}

	public List<CustomerReviews> getCustomerReviewsList() {
		return customerReviewsList;
	}

	public void setCustomerReviewsList(List<CustomerReviews> customerReviewsList) {
		this.customerReviewsList = customerReviewsList;
	}

	private void init() {
		
		customerReviewsList = new ArrayList<CustomerReviews>();
		
	}

	
	

}
