/**
 * 
 */
package com.ihm.util;

/**
 * <p>
 * this class for constants, marked final
 * because nobody can extends of this class
 * </p>
 * 
 * @author Artur Yolchyan
 * @version 1.0
 */
public class Constants {
	
	/**
	 * nobody can not create instance of this class
	 */
	private Constants() {		
	}
	

	/**
	 * constants for role ids
	 */
	public static final long USER_ROLE_ID = 1L;
	
	
	/** paging constants */
	public static final int PAGE_SIZE = 5;
	public static final int PAGING_VISIBLE_PAGE_COUNT = 2;
	public static final int PAGE_AFTER_SHOW = 1;
	public static final String PAGING_SELECTED_PAGE_STYLE_CLASS = "active";
	public static final String PAGING_NO_SELECTED_PAGE_STYLE_CLASS = "";
	public static final String PAGING_ATTRIBUTE = "pageNumber";
	
	/** appointment status constants */
	public static final String CANCEL_STATUS = "CANCEL";
}
