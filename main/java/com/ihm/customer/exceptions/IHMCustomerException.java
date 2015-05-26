package com.ihm.customer.exceptions;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
public class IHMCustomerException extends Exception {

	private static final long serialVersionUID = 1L;
	String exception = null;

	public IHMCustomerException(String err) {
		this.exception = err;
	}

	public String getExceptionMessage() {
		return this.exception;
	}
}