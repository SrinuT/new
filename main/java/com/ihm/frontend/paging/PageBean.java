package com.ihm.frontend.paging;

import java.io.Serializable;

/**
 * Class containing information of only one page.
 * 
 * @author Artur Yolchyan
 */
public class PageBean implements Serializable {
	
	/** use serialVersionUID from JDK 1.0.2 for interoperability */
	private static final long serialVersionUID = -1963211933530223409L;
	
	private int pageNumber; // page number.	
	private String cssClassName; //css class name for current page (selected or not)
	private boolean pageDisabled; // whether page is disabled or not (if it is current page - it is disabled)	
	
	
	/**
	 * initialize pageNumber, cssClassName, pageDisabled by given parameters
	 *  
	 * @param pageNumber - int
	 * @param cssClassName - String
	 * @param pageDisabled - boolean
	 */
	public PageBean(int pageNumber, String cssClassName, boolean pageDisabled) {				
		this.pageNumber = pageNumber;
		this.cssClassName = cssClassName;
		this.pageDisabled = pageDisabled;
	}

	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the cssClassName
	 */
	public String getCssClassName() {
		return cssClassName;
	}

	/**
	 * @param cssClassName the cssClassName to set
	 */
	public void setCssClassName(String cssClassName) {
		this.cssClassName = cssClassName;
	}

	/**
	 * @return the pageDisabled
	 */
	public boolean isPageDisabled() {
		return pageDisabled;
	}

	/**
	 * @param pageDisabled the pageDisabled to set
	 */
	public void setPageDisabled(boolean pageDisabled) {
		this.pageDisabled = pageDisabled;
	}	
			
}
