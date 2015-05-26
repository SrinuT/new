package com.ihm.frontend.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ihm.util.Constants;

/**
 * Class for paging implementation.
 * 
 * @author Artur Yolchyan 
 */
public class PagingBean implements Serializable {

	/** use serialVersionUID from JDK 1.0.2 for interoperability */
	private static final long serialVersionUID = 4418280000389265381L;
	
	private int pageCount = 1; // page count of any dataTable.
	private int visiblePageCount = Constants.PAGING_VISIBLE_PAGE_COUNT;
	private int rowCount = Constants.PAGE_SIZE; // row count in the page.
	private int currentPage = 1; // current page number.
	private int dataCount; // count of found data
	
	/* property for rendering (or not) corresponding 
	 * element for navigation to the previous page.*/
	private boolean previousPageDisabled; 
	
	/* property for rendering (or not) corresponding 
	 * element for navigation to the first page.*/
	private boolean firstPageDisabled; 
	
	/* property for rendering (or not) corresponding 
	 * element for navigation to the next page.*/
	private boolean nextPageDisabled; 
	
	/* property for rendering (or not) corresponding 
	 * element for navigation to the last page.*/
	private boolean lastPageDisabled;

	private List<PageBean> pageBeanList = new ArrayList<PageBean>(); // list of pages to iterate in the corresponding page... 		
	
	/**
	 * Initializes boolean properties to render (or not) 
	 * corresponding view controls.
	 */
	public void initControlsRenderingCases() {		
				
		if (pageCount == 1) {
			previousPageDisabled = true;
			firstPageDisabled = true;
			nextPageDisabled = true;
			lastPageDisabled = true;
		} else if (currentPage == 1) {
			previousPageDisabled = true;
			firstPageDisabled = true;
			nextPageDisabled = false;
			lastPageDisabled = false;
		} else if (currentPage == pageCount) {
			previousPageDisabled = false;
			firstPageDisabled = false;
			nextPageDisabled = true;
			lastPageDisabled = true;
		} else {
			previousPageDisabled = false;
			firstPageDisabled = false;
			nextPageDisabled = false;
			lastPageDisabled = false;
		}					
	}		
	
	/**
	 * Initializes page count by given data count.
	 * @param count
	 */
	public void initPageCountByDataCount(int count) {			
		dataCount = count;
		
		if(count <= rowCount) {
			pageCount = 1;
			
			return;
		}
		
		pageCount = count / rowCount;
		if((count % rowCount) > 0) {
			pageCount += 1;
		}				
	}
	
	/**
	 * Initializes pageBeanList.
	 */
	public void initPageBeanList() {
		int startIndex = 1;
		int endIndex = startIndex + visiblePageCount;
		if(pageCount > visiblePageCount) {
			if((currentPage / visiblePageCount) > 0) {
				startIndex *= (visiblePageCount * (currentPage / visiblePageCount));
								
				endIndex = startIndex + visiblePageCount;
				if(endIndex > pageCount) {
					endIndex = pageCount;
					startIndex = pageCount - visiblePageCount;
				}
			}
			
			if(currentPage == endIndex) {
				if(currentPage < pageCount) {
					if((pageCount - currentPage) >= Constants.PAGE_AFTER_SHOW) {
						startIndex += Constants.PAGE_AFTER_SHOW;
						endIndex += Constants.PAGE_AFTER_SHOW;
					} else {
						startIndex += 1;
						endIndex += 1;
					}
				}
			} else if(currentPage == startIndex) {
				if(currentPage > 1) {
					if((currentPage - 1) >= Constants.PAGE_AFTER_SHOW) {
						startIndex -= Constants.PAGE_AFTER_SHOW;
						endIndex -= Constants.PAGE_AFTER_SHOW;
					} else {
						startIndex -= 1;
						endIndex -= 1;
					}
				}
			}
		} else {
			endIndex = pageCount;
		}
		
		pageBeanList = new ArrayList<PageBean>();
		for(int i = startIndex; i <= endIndex; i++) {
			if(i == currentPage) {
				pageBeanList.add(new PageBean(i, Constants.PAGING_SELECTED_PAGE_STYLE_CLASS, true));
			} else {
				pageBeanList.add(new PageBean(i, Constants.PAGING_NO_SELECTED_PAGE_STYLE_CLASS, false));
			}
		}
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the visiblePageCount
	 */
	public int getVisiblePageCount() {
		return visiblePageCount;
	}

	/**
	 * @param visiblePageCount the visiblePageCount to set
	 */
	public void setVisiblePageCount(int visiblePageCount) {
		this.visiblePageCount = visiblePageCount;
	}

	/**
	 * @return the rowCount
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * @param rowCount the rowCount to set
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the dataCount
	 */
	public int getDataCount() {
		return dataCount;
	}

	/**
	 * @param dataCount the dataCount to set
	 */
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	/**
	 * @return the previousPageDisabled
	 */
	public boolean isPreviousPageDisabled() {
		return previousPageDisabled;
	}

	/**
	 * @param previousPageDisabled the previousPageDisabled to set
	 */
	public void setPreviousPageDisabled(boolean previousPageDisabled) {
		this.previousPageDisabled = previousPageDisabled;
	}

	/**
	 * @return the firstPageDisabled
	 */
	public boolean isFirstPageDisabled() {
		return firstPageDisabled;
	}

	/**
	 * @param firstPageDisabled the firstPageDisabled to set
	 */
	public void setFirstPageDisabled(boolean firstPageDisabled) {
		this.firstPageDisabled = firstPageDisabled;
	}

	/**
	 * @return the nextPageDisabled
	 */
	public boolean isNextPageDisabled() {
		return nextPageDisabled;
	}

	/**
	 * @param nextPageDisabled the nextPageDisabled to set
	 */
	public void setNextPageDisabled(boolean nextPageDisabled) {
		this.nextPageDisabled = nextPageDisabled;
	}

	/**
	 * @return the lastPageDisabled
	 */
	public boolean isLastPageDisabled() {
		return lastPageDisabled;
	}

	/**
	 * @param lastPageDisabled the lastPageDisabled to set
	 */
	public void setLastPageDisabled(boolean lastPageDisabled) {
		this.lastPageDisabled = lastPageDisabled;
	}

	/**
	 * @return the pageBeanList
	 */
	public List<PageBean> getPageBeanList() {
		return pageBeanList;
	}

	/**
	 * @param pageBeanList the pageBeanList to set
	 */
	public void setPageBeanList(List<PageBean> pageBeanList) {
		this.pageBeanList = pageBeanList;
	}

}
