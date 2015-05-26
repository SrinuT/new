/**
 * 
 */
package com.ihm.customer.service;


/**
 * @author Srinivasulu T
 *
 */
public interface SMSService {
	/**
	 * For sending the Pramotional SMS
	 * @param userName Your login username for smsgateway.
	 * @param password Your login password.
	 * @param messageTo Single mobile number or multiple mobile numbers separated by comma(10 digits or +91).
	 * @param message Your message content(Minimum 459 characters/3 messages).
	 * @param senderId Approved sender id(Only 6 characters).
	 * @param FlashFlag: if flash message then 1 or else 0
	 * @return String sms response(delivery status)
	 */
	public abstract String pramotionalSMS(String userName,String password,String messageTo,String messageBody,String senderId,String flashFlag);
	
	/**
	 * For sending the Pramotional SMS
	 * @param userName Your login username for smsgateway.
	 * @param password Your login password.
	 * @param messageTo Single mobile number or multiple mobile numbers separated by comma(10 digits or +91).
	 * @param message Your message content(Minimum 459 characters/3 messages).
	 * @param senderId Approved sender id(Only 6 characters).
	 * @param FlashFlag: if flash message then 1 or else 0
	 * @param gwid: 2 (its for Transactions route.)
	 * @return String sms response(delivery status)
	 */
	public abstract String transactionlSMS(String userName,String password,String messageTo,String messageBody,String senderId,String flashFlag,String gwid);
	/**
	 * To send the sms to user based on the type
	 * @param mobileNumber
	 * @param Message
	 * @param type
	 * @return String
	 */
	public abstract String sendSMS(String mobileNumber,String Message,String type);
	
}
