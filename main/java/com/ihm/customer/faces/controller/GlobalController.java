package com.ihm.customer.faces.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.ihm.customer.service.ManagerService;
import com.ihm.customer.service.SearchService;
import com.ihm.customer.util.RepositoryContext;

/**
 * @author Sardar Waqas Ahmed
 * 
 */
@ManagedBean(name="globalController")
@ViewScoped
public class GlobalController extends AbstractController  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6708971943127886599L;

	private static Logger logger = Logger.getLogger(GlobalController.class);
	
	/*
	 * Header Data Bindings
	 * 
	 */
	
	private List<String> testNameList ;
	private List<String> cityList ;
	private List<String> localityList ;
	
	/*
	 * Service Layer Objects
	 */
	private ManagerService managerService;
	private SearchService searchService;
	
	
	
	public GlobalController() {
		logger.info("===================================================================================");
		logger.info(">>>>>>>>>> GlobalController Created With View Context Scope       <<<<<<<<< ");
		logger.info("===================================================================================");
		testNameList = new ArrayList<String>();
		cityList = new ArrayList<String>();
		localityList = new ArrayList<String>();
		loadSearchMenu();
	}
	
	public List<String> getTestNameList() {
		return testNameList;
	}
	public void setTestNameList(List<String> testNameList) {
		this.testNameList = testNameList;
	}
	public List<String> getCityList() {
		return cityList;
	}
	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}
	public List<String> getLocalityList() {
		return localityList;
	}
	public void setLocalityList(List<String> localityList) {
		this.localityList = localityList;
	}
	

	private void  loadSearchMenu() {
		
		managerService = RepositoryContext.getBean(ManagerService.class);
		searchService = managerService.getSearchService();
		
		testNameList = searchService.getAllTestByClinic("");
		cityList = searchService.getAllCity();
		localityList = searchService.getAllLocality();
		
	}
	
	
	
}
