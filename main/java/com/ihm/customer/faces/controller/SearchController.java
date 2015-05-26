package com.ihm.customer.faces.controller;

/**
* @author SARDAR WAQAS AHMED
* @email  architect_pakistan@hotmail.com
* @since  01 NOV, 2014
* @version 1.0
*/

import com.ihm.customer.entites.SlrSeller;
import com.ihm.customer.exceptions.IHMCustomerException;
import com.ihm.customer.frontend.leftnav.Document;
import com.ihm.customer.frontend.leftnav.HospitalAmenities;
import com.ihm.customer.frontend.leftnav.LeftNavModel;
import com.ihm.customer.frontend.leftnav.StarRating;
import com.ihm.customer.search.helper.FilterCriteriaExtractor;
import com.ihm.customer.search.helper.IHMListHelper;
import com.ihm.customer.search.helper.SearchHelper;
import com.ihm.customer.service.AmenitiesService;
import com.ihm.customer.service.CalendarService;
import com.ihm.customer.service.ManagerService;
import com.ihm.customer.service.SearchService;
import com.ihm.customer.util.DateUtil;
import com.ihm.customer.util.IHMConstants;
import com.ihm.customer.util.RepositoryContext;
import com.ihm.customer.util.StringFunctions;
import com.ihm.frontend.paging.PagingBean;
import com.ihm.frontend.search.dto.*;
import com.ihm.jsf.JsfUtil;
import com.ihm.util.Constants;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name="searchController")
@SessionScoped
public class SearchController extends AbstractController  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 9136722830172684509L;

	private static Logger logger = Logger.getLogger(SearchController.class);
	
	/*
	 * Header Data Bindings
	 * 
	 */
	
	private String testName;
  private String txtSource;
	private List<String> advancedSearchTestNameList = new ArrayList<String>();
	private String city;
	private String locality;
	private String zip;
	private Date date; 
	private String sourceDistance; 
	private String sourceAddress;
	private List<Clinic> clinicsListDB; // Non Changeable List
	private List<Clinic> clinicsList;//for Advance search
	private List<Clinic> clinicsViewList; // Change On Each Filter Click
	private LeftNavModel leftNavModel;
	private String clientLocation;
	
	private Integer totalItemsFound = 0;
	private Integer activeIndex = 0;
	private Integer itemsPerPage = 5;
	private Integer itemsPerRow = 1;
	private Integer pageNumber = 1;
	private String searchCriteria;
	private List<String> advancedSearchCriteria;
	
	
	private Integer totalPages = 1;
	private Integer currentPage = 1;
	private String resultRange = "";
	
	private Integer sortBy;
	private String itemSelectErrorMessage = null;

	/*
	 * Main Search Page Rendering Options
	 */
	
	private boolean showSearchView;
	private boolean showAdvancedSearchView;
	
	private boolean showMapView;
	private boolean showLeftNav;
	private boolean showCompareView;
	
	/*
	 * BL : Service Layer Objects
	 */
	private ManagerService managerService;
	private SearchService searchService;
	private AmenitiesService amenitiesService;
	private CalendarService calendarService;
	
	/*
	 * ViewMore
	 */
	private DealsTab viewMoreDeals;
	private ReviewsTab viewMoreReviews;
	
	/*
	 * Google Maps DataModel
	 */
	private MapModel advancedModel;
	private Marker marker;
	
	/*
	 * Scheduler Object
	 */
	private ScheduleModel eventModel;
	private List<Appointment> appointmentList;
	private Clinic clinicAppointmentSelected;

    /*
     * Sort search result
     */
    private String selectedSortId;
	
	/*
	 * Compare Section
	 */
	private List<CompareClinic> compareClinicList;
    private boolean isComparePage;
    private CompareClinic compareClinic;
	
	// paging implementation
	private PagingBean paging;

    private Object appointmentUpdateId;
    private Object clinicId;
    private List<String> testNameList = new ArrayList<>();
    private List<String> cityList = new ArrayList<>();
    private List<String> localityList = new ArrayList<>();


    public SearchController() {
    	clinicsListDB = new ArrayList<Clinic>();
		clinicsViewList = new ArrayList<Clinic>();
		leftNavModel = new LeftNavModel();
		compareClinicList = new ArrayList<CompareClinic>();
		appointmentList = new ArrayList<Appointment>();
        isComparePage = false;
//		loadPageData();
	}

	public void finalize() throws Throwable {
		super.finalize();		
	}

	public void loadPageData() { // will used for fetching all clinics when no criteria matched
		logger.info("<><><>  loadPageData Start <><><>"+this.date);
		//clearSearchForm();
		managerService = RepositoryContext.getBean(ManagerService.class);
		searchService = managerService.getSearchService();
		
		searchService.getSlrSellerCount(null, null, null, null, null);
		
		this.activeIndex = 0;
		this.showSearchView = true;
		this.showMapView = false;
		this.showLeftNav = true;
		
		String dateParam = DateUtil.formatIHMDate(this.date, "start");
		

		List<SlrSeller> serviceList = new ArrayList<>();

        if(selectedSortId == null) {
            serviceList = searchService.simpleSearchSeller(testName, city, locality, zip, dateParam,0,5, 0);
        } else {
            serviceList = searchService.simpleSearchSeller(testName, city, locality, zip, dateParam,0,5, new Integer(selectedSortId));
        }
		if(null != serviceList && serviceList.size() > 0) {
			logger.info("Total "+ serviceList.size() + " Clinic Found >>>>");
			logger.debug("Total "+ serviceList.size() + "Clinic Found >>>>");
			clinicsListDB = SearchHelper.parseSearchResults(serviceList,managerService,this.date,testName,clientLocation);
//			generateStarRatingLeftNav(clinicsListDB);
//			loadClinicAmenities();
			loadLeftNavModel(clinicsListDB);
			clinicsViewList.addAll(clinicsListDB);			
		}
	}
	
	public void loadAdvancedPageData() { // will used for fetching all clinics when no criteria matched
		logger.info("<><><>  loadPageData Start <><><>"+this.date);
		//clearSearchForm();
		managerService = RepositoryContext.getBean(ManagerService.class);
		searchService = managerService.getSearchService();
		
		searchService.getAdvancedSlrSellerCount(advancedSearchTestNameList, null, null, null, null);
		
		this.activeIndex = 0;
		this.showAdvancedSearchView = true;
		this.showMapView = false;
		this.showLeftNav = true;
		
		String dateParam = DateUtil.formatIHMDate(this.date, "start");
		

		List<SlrSeller> serviceList = new ArrayList<>();
		
		System.out.println("TestNameList Size   :   "+advancedSearchTestNameList.size());
		
        if(selectedSortId == null) {
        	serviceList = searchService.advancedSearchSeller(advancedSearchTestNameList, city, locality, zip, dateParam,0,5, 0);
        } else {
        	serviceList = searchService.advancedSearchSeller(advancedSearchTestNameList, city, locality, zip, dateParam,0,5, new Integer(selectedSortId));
        }
		if(null != serviceList && serviceList.size() > 0) {
			logger.info("Total "+ serviceList.size() + " Clinic Found >>>>");
			logger.debug("Total "+ serviceList.size() + "Clinic Found >>>>");
			clinicsListDB = SearchHelper.parseAdvancedSearchResults(serviceList,managerService,this.date,advancedSearchTestNameList,clientLocation);
//			generateStarRatingLeftNav(clinicsListDB);
//			loadClinicAmenities();
			//clinicsListDB.get(0).getDealsTab().getServiceDealList().get(0)
			
			loadLeftNavModel(clinicsListDB);
			clinicsViewList.addAll(clinicsListDB);
			
		}
	}
	
	public String simpleSearch() {
		FacesMessage msg = null;
        appointmentUpdateId = null;
        clinicId = null;
		managerService = RepositoryContext.getBean(ManagerService.class);
		searchService = managerService.getSearchService();
		this.isComparePage = false;
		this.showSearchView = true;
		this.showMapView = false;
		this.showLeftNav = true;
		this.showCompareView = false;
		String testNameParam = this.testName;
		this.searchCriteria = this.testName;
		String cityParam = this.city;
		String localityParam = this.locality;
		String zipParam = this.zip;
		String dateParam = "";
		if (this.date != null){
			dateParam = DateUtil.formatIHMDate(this.date, "start");
		}
		else{
			dateParam = DateUtil.formatIHMDate(new Date(), "start");
		}
        List<SlrSeller> clinicList = new ArrayList<>();
		
		//String dateParam = DateUtil.formatIHMDate(this.date, "start");
		paging = new PagingBean();
		logger.info("Count ======== Pagging=====:"+searchService.getSlrSellerCount(testNameParam, cityParam, localityParam, zipParam, dateParam));
		paging.initPageCountByDataCount(searchService.getSlrSellerCount(testNameParam, cityParam, localityParam, zipParam, dateParam));
		paging.initControlsRenderingCases();
		paging.initPageBeanList();
		
		if(selectedSortId == null) {
            clinicList = searchService.simpleSearchSeller(testNameParam, cityParam, localityParam, zipParam, dateParam,0,5, 0);
        } else {
            clinicList = searchService.simpleSearchSeller(testNameParam, cityParam, localityParam, zipParam, dateParam,0,5, new Integer(selectedSortId));
        }

		if (null != clinicList && clinicList.size() > 0) {
			if (this.date == null){
				this.date = new Date();
			}
			String dateParam1 = DateUtil.formatIHMDate(this.date, "start");
			/*if(clinicList.size() >5){
				paging = new PagingBean();
				paging.initPageCountByDataCount(searchService.getSlrSellerCount(testNameParam, cityParam, localityParam, zipParam, dateParam1));
				paging.initControlsRenderingCases();
				paging.initPageBeanList();
			}*/
			clinicsListDB = SearchHelper.parseSearchResults(clinicList,managerService,this.date,this.testName,clientLocation);
			loadLeftNavModel(clinicsListDB);
			this.activeIndex = 0;
			clinicsViewList.clear();
			clinicsViewList.addAll(clinicsListDB);
			this.searchCriteria = StringFunctions.appendCharAtLeadNTrailEnd(searchCriteria, "(",")");
			
		} else {
			clinicsViewList.clear();
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"No Clinic Found ! Default Results", " ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			this.searchCriteria = StringFunctions.appendCharAtLeadNTrailEnd(testName, "(",")");
			loadPageData();
		}
		
		//clearSearchForm();

		//return "searchresults";
		return "/searchresults.xhtml?faces-redirect=true";
	}
	
	public String advanceSearch() {
		FacesMessage msg = null;
        appointmentUpdateId = null;
        clinicId = null;
		managerService = RepositoryContext.getBean(ManagerService.class);
		searchService = managerService.getSearchService();
		this.isComparePage = false;
		this.showAdvancedSearchView = true;
		this.showMapView = false;
		this.showLeftNav = true;
		this.showCompareView = false;
		List<String> testNameParam = this.advancedSearchTestNameList;
		this.advancedSearchCriteria = this.advancedSearchTestNameList;
		String cityParam = this.city;
		String localityParam = this.locality;
		String zipParam = this.zip;
		String dateParam = "";
		if (this.date != null){
			dateParam = DateUtil.formatIHMDate(this.date, "start");
		}
		else{
			dateParam = DateUtil.formatIHMDate(new Date(), "start");
		}
        List<SlrSeller> clinicList = new ArrayList<>();
		
		//String dateParam = DateUtil.formatIHMDate(this.date, "start");
//		paging = new PagingBean();
//		logger.info("Count ======== Pagging=====:"+searchService.getAdvancedSlrSellerCount(testNameParam, cityParam, localityParam, zipParam, dateParam));
//		paging.initPageCountByDataCount(searchService.getAdvancedSlrSellerCount(testNameParam, cityParam, localityParam, zipParam, dateParam));
//		paging.initControlsRenderingCases();
//		paging.initPageBeanList();
//		
		if(selectedSortId == null) {
            clinicList = searchService.advancedSearchSeller(testNameParam, cityParam, localityParam, zipParam, dateParam,0,5, 0);
        } else {
            clinicList = searchService.advancedSearchSeller(testNameParam, cityParam, localityParam, zipParam, dateParam,0,5, new Integer(selectedSortId));
        }
		System.out.println("Clinic List Size ======>"+clinicList.size());

		if (null != clinicList && clinicList.size() > 0) {
			
			if (this.date == null){
				this.date = new Date();
			}
			//String dateParam1 = DateUtil.formatIHMDate(this.date, "start");
			/*if(clinicList.size() >5){
				paging = new PagingBean();
				paging.initPageCountByDataCount(searchService.getSlrSellerCount(testNameParam, cityParam, localityParam, zipParam, dateParam1));
				paging.initControlsRenderingCases();
				paging.initPageBeanList();
			}*/
			clinicsListDB = SearchHelper.parseAdvancedSearchResults(clinicList,managerService,this.date,testNameParam,clientLocation);
			paging = new PagingBean();
			paging.initPageCountByDataCount(clinicsListDB.size());
			paging.initControlsRenderingCases();
			paging.initPageBeanList();
			
			loadLeftNavModel(clinicsListDB);
			this.activeIndex = 0;
			clinicsViewList.clear();
			
			ArrayList<Clinic> selectedList = new ArrayList<Clinic>();
			int endIndex = paging.getRowCount();
			
			if(endIndex > clinicsListDB.size()){
				endIndex = clinicsListDB.size();
			}
			
			for(int i=0;i<endIndex; i++){
				selectedList.add(clinicsListDB.get(i));
			}
			
			clinicsViewList.addAll(selectedList);
			//this.advancedSearchCriteria = StringFunctions.advancedAppendCharAtLeadNTrailEnd(advancedSearchCriteria, "(",")");
			System.out.println("Clinic List Size ======>"+selectedList.size());			
		} else {
			clinicsViewList.clear();
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"No Clinic Found ! Default Results", " ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			//this.advancedSearchCriteria = StringFunctions.appendCharAtLeadNTrailEnd(advancedSearchCriteria, "(",")");
			loadAdvancedPageData();
		}
		//clearSearchForm();
		return "advance_search_results";
	}
	
	public void searchClinic() { // will change to sampleSearch()

		
		managerService = RepositoryContext.getBean(ManagerService.class);
		searchService = managerService.getSearchService();
        this.isComparePage = false;
		this.showSearchView = true;
		this.showMapView = false;
		this.showLeftNav = true;
		this.showCompareView = false;
		String testNameParam = this.testName;
		this.searchCriteria = this.testName;
		String cityParam = this.city;
		String localityParam = this.locality;
		String zipParam = this.zip;
		String dateParam = "";
		
		if (this.date != null){
			dateParam = DateUtil.formatIHMDate(this.date, "start");
		}
		else{
			dateParam = DateUtil.formatIHMDate(new Date(), "start");
		}
        List<SlrSeller> clinicList = new ArrayList<>();
        paging = new PagingBean();
		logger.info("Count ======== Pagging=====:"+searchService.getSlrSellerCount(testNameParam, cityParam, localityParam, zipParam, dateParam));
		paging.initPageCountByDataCount(searchService.getSlrSellerCount(testNameParam, cityParam, localityParam, zipParam, dateParam));
		paging.initControlsRenderingCases();
		paging.initPageBeanList();

		try {

			logger.info("======== Values =====:"+testNameParam + ", " + cityParam + ", " + localityParam + ", " + zipParam + ", " + dateParam + "," + selectedSortId);
        if(selectedSortId == null) {
            clinicList = searchService.simpleSearchSeller(testNameParam, cityParam, localityParam, zipParam, dateParam,0,5, 0);
        } else {
            clinicList = searchService.simpleSearchSeller(testNameParam, cityParam, localityParam, zipParam, dateParam,0,5, new Integer(selectedSortId));
        }
			if (null != clinicList && clinicList.size() > 0) {
				if (this.date == null){
					this.date = new Date();
				}
				String dateParam1 = DateUtil.formatIHMDate(this.date, "start");
				/*if(clinicList.size() >5){
					paging = new PagingBean();
					paging.initPageCountByDataCount(searchService.getSlrSellerCount(testNameParam, cityParam, localityParam, zipParam, dateParam1));
					paging.initControlsRenderingCases();
					paging.initPageBeanList();
				}*/
				clinicsListDB = SearchHelper.parseSearchResults(clinicList,managerService,this.date,this.testName,clientLocation);

			loadLeftNavModel(clinicsListDB);
			this.activeIndex = 0;
			clinicsViewList.clear();
			clinicsViewList.addAll(clinicsListDB);
			this.searchCriteria = StringFunctions.appendCharAtLeadNTrailEnd(searchCriteria, "(",")");
		} else {
			clinicsViewList.clear();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"No Clinic Found ! Default Results", " ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			this.searchCriteria = StringFunctions.appendCharAtLeadNTrailEnd(testName, "(",")");
			loadPageData();
		}

		} catch (Exception e) {
			logger.info(":: Controller Layer Error Caught :: ");
			e.printStackTrace();
		}

		//clearSearchForm();

	}

	/**
	 * this method calls when changing items count, which
	 * should be shown in view
	 */
	public void onChangeCount() {
		search(true);
	}
	public void onChangeAdvanceCount() {
		advancedSearch(true);
	}
	
	/**
	 * this method calls when clicking on some of paging buttons
	 * 
	 * @param actionEvent - ActionEvent
	 */
	public void onClickPaging(ActionEvent actionEvent) {
		String selectedPage = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get(Constants.PAGING_ATTRIBUTE).toString();
		paging.setCurrentPage(Integer.parseInt(selectedPage));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@"+selectedPage);
		search(false);
	}
	
	/**
	 * this method calls when clicking on some of paging buttons At Advanced Search Result page
	 * 
	 * @param actionEvent - ActionEvent
	 */
	
	public void onClickAdvancedPaging(ActionEvent actionEvent) {
		String selectedPage = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get(Constants.PAGING_ATTRIBUTE).toString();
		paging.setCurrentPage(Integer.parseInt(selectedPage));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@"+selectedPage);
		advancedSearch(false);
	}
	
	private void advancedSearch(boolean newSearch) {
		if (clinicsListDB != null) {
			
		    if(newSearch){
			   paging.setCurrentPage(1);
			   paging.initPageCountByDataCount(clinicsListDB.size());
		    } 
			
			clinicsViewList.clear();
			paging.initControlsRenderingCases();
			paging.initPageBeanList();
			
			
				
	            ArrayList<Clinic> selectedList = new ArrayList<Clinic>();
	            int startIndex = (paging.getCurrentPage() - 1) * paging.getRowCount();
	            int endIndex = startIndex + paging.getRowCount();
	            
	            if(endIndex >= clinicsListDB.size()){
	            	endIndex = clinicsListDB.size();
	            }
				
				
				for(int i=startIndex;i<endIndex; i++){
					selectedList.add(clinicsListDB.get(i));
				}
				loadLeftNavModel(selectedList);
				clinicsViewList.addAll(selectedList);
				//this.searchCriteria = StringFunctions.appendCharAtLeadNTrailEnd(searchCriteria, "(",")");
			}
		
		
	}
	
	/**
	 * this method for searching clinics and render in view
	 * 
	 * @param newSearch - boolean
	 */
	private void search(boolean newSearch) {
		if (newSearch) {
			paging.setCurrentPage(1);
			paging.initPageCountByDataCount(searchService.getSlrSellerCount(testName, city, locality, zip, DateUtil.formatIHMDate(this.date, "start")));
		}
		
		int startIndex = (paging.getCurrentPage() - 1) * paging.getRowCount();
		//sysout
        List<SlrSeller> clinicList = new ArrayList<>();
		
        logger.info("======== Values =====:"+testName + ", " + city + ", " + locality + ", " + zip + ", " + DateUtil.formatIHMDate(this.date, "start") + "," + selectedSortId + ", " + startIndex + ", " + paging.getRowCount());
        if(selectedSortId == null) {
            clinicList = searchService.simpleSearchSeller(testName, city, locality, zip, DateUtil.formatIHMDate(this.date, "start"), startIndex, paging.getRowCount(), 0);
        } else {
            clinicList = searchService.simpleSearchSeller(testName, city, locality, zip, DateUtil.formatIHMDate(this.date, "start"), startIndex, paging.getRowCount(), new Integer(selectedSortId));
        }
		
		paging.initControlsRenderingCases();
		paging.initPageBeanList();
		
		if (clinicList != null) {
			clinicsListDB = SearchHelper.parseSearchResults(clinicList,managerService,this.date,this.testName,clientLocation);
			loadLeftNavModel(clinicsListDB);
			clinicsViewList = new ArrayList<>();
			clinicsViewList.addAll(clinicsListDB);
			this.searchCriteria = StringFunctions.appendCharAtLeadNTrailEnd(searchCriteria, "(",")");
		}
	}
	
	private void clearSearchForm() {	
		this.localityList = null;
		this.testName = "";
		this.city = "";
		this.locality = "";
		this.zip = "";
		this.date = null;
		this.txtSource ="";
		this.clientLocation ="";
		this.clinicsList = null;
	}
	public String init() {
		   clearSearchForm();
		  return null;
		  
		  }
	public void generateStarRatingLeftNav(List<Clinic> clinicsListDB) {		
		int clinicSuperiorCount = 0;
		int clinicAboveAveraegeCount = 0;
		int clinicAveraegeCount = 0;
		int clinicBelowAveraegeCount = 0;
		int clinicPoorCount = 0;
		int clinicUnratedCount = 0;
		
		for(Clinic clinic:clinicsListDB) {
			if(clinic.getScoredRating() >9) {
				clinicSuperiorCount++;
			} else if(clinic.getScoredRating() >= 8 && clinic.getScoredRating() < 9) {
				clinicAboveAveraegeCount++;
			} else if(clinic.getScoredRating() >= 6 && clinic.getScoredRating() < 8) {
				clinicAveraegeCount++;
			} else if(clinic.getScoredRating() >= 4 && clinic.getScoredRating() < 6 ) {
				clinicBelowAveraegeCount++;
			} else if(clinic.getScoredRating() >= 1 && clinic.getScoredRating() < 4 ) {
				clinicPoorCount++;
			} else if(clinic.getScoredRating() == 0 ) {
				clinicUnratedCount++;
			}
		}

		leftNavModel.getStarRatingList().clear();
		leftNavModel.getStarRatingList().add(new StarRating(IHMConstants.SUPERIOR, clinicSuperiorCount, false));
		leftNavModel.getStarRatingList().add(new StarRating(IHMConstants.ABOVE_AVERAGE, clinicAboveAveraegeCount, false));
		leftNavModel.getStarRatingList().add(new StarRating(IHMConstants.AVERAGE, clinicAveraegeCount, false));
		leftNavModel.getStarRatingList().add(new StarRating(IHMConstants.BEL0W_AVERAGE, clinicBelowAveraegeCount, false));
		leftNavModel.getStarRatingList().add(new StarRating(IHMConstants.POOR, clinicPoorCount, false));
		leftNavModel.getStarRatingList().add(new StarRating(IHMConstants.NOIMAGE, clinicPoorCount, false));
		
		logger.info("Lef Nav Star Rating List Size "+leftNavModel.getStarRatingList().size());
	}
	
	private void loadLeftNavModel(List<Clinic> clinicsListDB) {
		reSetSliders();
		generateStarRatingLeftNav(clinicsListDB);
		loadClinicAmenities();
	}
	
	private void reSetSliders() {
		this.leftNavModel.clearSliders();
		
	}

	private void loadClinicAmenities() {
		
		managerService = RepositoryContext.getBean(ManagerService.class);
		amenitiesService = managerService.getAmenitiesService();
		
		List<String> amenitiesListDB = amenitiesService.getDistinctAmenitiesName();
		HospitalAmenities hospitalAmenity = null;
		List<HospitalAmenities> hosAmenitiesList  = new ArrayList<HospitalAmenities>();
		int amenitiesUIIdCounter = 0;
		for(String amenity:amenitiesListDB) {
			hospitalAmenity = new HospitalAmenities();
			amenitiesUIIdCounter +=1;
			hospitalAmenity.setHeading(amenity);
			hospitalAmenity.setAmenitiesUIId("amenity-accordian-"+amenitiesUIIdCounter);
			if(null != amenity && amenity.equals(IHMConstants.REPORT_DELIVERY)) {
				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.REPORT_DELIVERY_FEATURES));
			}else if(null != amenity && amenity.equals(IHMConstants.DOORSTEP_SAMPLE_COLL)){
				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.DOORSTEP_SAMPLE_COLL_FEATURES));
			}else if(null != amenity && amenity.equals(IHMConstants.CUSTOMER_WAITING_LOUNGE)){
				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.CUSTOMER_WAITING_LOUNGE_FEATURES));
			}else  if(null != amenity && amenity.equals(IHMConstants.ENTERTAINMENT)){
				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.ENTERTAINMENT_FEATURES));
			}else  if(null != amenity && amenity.equals(IHMConstants.CAR_PARKING)){
				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.CAR_PARKING_FEATURES));
			}else  if(null != amenity && amenity.equals(IHMConstants.TWO_WHEELER_PARKING)){
				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.TWO_WHEELER_PARKING_FEATURES));
			}
			
			hosAmenitiesList.add(hospitalAmenity);
		}
		Collections.sort(hosAmenitiesList, new HospitalAmenities());
		leftNavModel.setHosAmenitiesList(hosAmenitiesList);
	}
	
	
	
	/*
	 * 
	 * Filter Area Selection Logic
	 * 
	 * 
	 */
	
	// Filter Area Select Yes || Not
	private boolean isPriceSlideRangeClick(){
		boolean flag = false;
		if(leftNavModel.getPriceStart() > 0 || leftNavModel.getPriceEnd() > 0){
			flag = true;
		}
		return flag;
	}
	private boolean isDistanceSlideRangeClick(){
		boolean flag = false;
		if(leftNavModel.getDistanceStart() > 0 || leftNavModel.getDistanceEnd() > 0){
			flag = true;
		}
		return flag;
	}
	private boolean isStarRatingCheckBoxesClick(){
		boolean flag = false;
		for(StarRating star:leftNavModel.getStarRatingList()) {
			if(star.isSelected())
				flag = true;
		}
		return flag;
	}
	
	private boolean isHospitalAmenitiesCheckBoxesClick() {
		boolean flag = false;
		for (HospitalAmenities parent : leftNavModel.getHosAmenitiesList()) {
			for (Document child : parent.getDocumnetList()) {
				if (child.isStatus()) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	public String calculateDistance() { // will change to sampleSearch()
		
		
		this.clientLocation = this.txtSource;
		simpleSearch();
		return "/searchresults.xhtml?faces-redirect=true";
		//clearSearchForm();

	}
	
	public void onLeftNavClick() {		
		FacesMessage msg = null;
		
		this.showSearchView = true;
		this.showMapView = false;
		this.showCompareView = false;
		this.showLeftNav = true;
		boolean priceRangeClick = isPriceSlideRangeClick();
		boolean distanceRangeClick = isDistanceSlideRangeClick();
		boolean starRatingClick = isStarRatingCheckBoxesClick();
		boolean hospitalAmenitiesClick = isHospitalAmenitiesCheckBoxesClick();
		
		// Based on area click Extract all the true value flags
		clinicsViewList.clear();  // Clear the old filtered results and add all newly applicable filtered result
		this.activeIndex = 0;
		
		if(priceRangeClick && distanceRangeClick && starRatingClick && hospitalAmenitiesClick){
			clinicsViewList = FilterCriteriaExtractor.dstRngScenarios(leftNavModel,clinicsListDB);		
			clinicsViewList = FilterCriteriaExtractor.strRtgScenarios(leftNavModel,clinicsViewList);
			clinicsViewList = FilterCriteriaExtractor.hosAmnScenarios(leftNavModel,clinicsViewList);
		}else{
			
			if(priceRangeClick && distanceRangeClick && starRatingClick){
				clinicsViewList = FilterCriteriaExtractor.prRngScenarios(leftNavModel,clinicsListDB);			
				clinicsViewList = FilterCriteriaExtractor.dstRngScenarios(leftNavModel,clinicsViewList);
				clinicsViewList = FilterCriteriaExtractor.strRtgScenarios(leftNavModel,clinicsViewList);
			}else if(priceRangeClick && starRatingClick && hospitalAmenitiesClick){
				clinicsViewList = FilterCriteriaExtractor.prRngScenarios(leftNavModel,clinicsListDB);			
				clinicsViewList = FilterCriteriaExtractor.strRtgScenarios(leftNavModel,clinicsViewList);
				clinicsViewList = FilterCriteriaExtractor.hosAmnScenarios(leftNavModel,clinicsViewList);
			}else if(distanceRangeClick && starRatingClick && hospitalAmenitiesClick){
				clinicsViewList = FilterCriteriaExtractor.dstRngScenarios(leftNavModel,clinicsListDB);		
				clinicsViewList = FilterCriteriaExtractor.strRtgScenarios(leftNavModel,clinicsViewList);
				clinicsViewList = FilterCriteriaExtractor.hosAmnScenarios(leftNavModel,clinicsViewList);
			}else {

				if(priceRangeClick && distanceRangeClick){
					clinicsViewList = FilterCriteriaExtractor.prRngScenarios(leftNavModel,clinicsListDB);
					clinicsViewList = FilterCriteriaExtractor.dstRngScenarios(leftNavModel,clinicsViewList);
				}else if(priceRangeClick && starRatingClick) {
					clinicsViewList = FilterCriteriaExtractor.prRngScenarios(leftNavModel,clinicsListDB);
					clinicsViewList = FilterCriteriaExtractor.strRtgScenarios(leftNavModel,clinicsViewList);
				}else if(priceRangeClick && hospitalAmenitiesClick){	
					clinicsViewList = FilterCriteriaExtractor.prRngScenarios(leftNavModel,clinicsListDB);
					clinicsViewList = FilterCriteriaExtractor.hosAmnScenarios(leftNavModel,clinicsViewList);
				}else if(distanceRangeClick && starRatingClick){	
					clinicsViewList = FilterCriteriaExtractor.dstRngScenarios(leftNavModel,clinicsListDB);
					clinicsViewList = FilterCriteriaExtractor.strRtgScenarios(leftNavModel,clinicsViewList);
				}else if(distanceRangeClick && hospitalAmenitiesClick){	
					clinicsViewList = FilterCriteriaExtractor.dstRngScenarios(leftNavModel,clinicsListDB);
					clinicsViewList = FilterCriteriaExtractor.hosAmnScenarios(leftNavModel,clinicsViewList);
				}else if(starRatingClick && hospitalAmenitiesClick){	
					clinicsViewList = FilterCriteriaExtractor.strRtgScenarios(leftNavModel,clinicsListDB);
					clinicsViewList = FilterCriteriaExtractor.hosAmnScenarios(leftNavModel,clinicsViewList);
				}else{
					
					if(priceRangeClick) {
						clinicsViewList = FilterCriteriaExtractor.prRngScenarios(leftNavModel,clinicsListDB);
					}else if(distanceRangeClick){
						clinicsViewList = FilterCriteriaExtractor.dstRngScenarios(leftNavModel,clinicsListDB);
					}else if(starRatingClick){
						clinicsViewList = FilterCriteriaExtractor.strRtgScenarios(leftNavModel,clinicsListDB);
					}else if(hospitalAmenitiesClick){
						clinicsViewList = FilterCriteriaExtractor.hosAmnScenarios(leftNavModel,clinicsListDB);
					}
				}
				
			}	
			
		}
		
		// If no filter selected , when client uncheck all the filters then default search results will be send back
		if(IHMListHelper.isDefaultSearchResult(priceRangeClick,distanceRangeClick,starRatingClick,hospitalAmenitiesClick)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"No Clinic Found.Review Selection !!!", " ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			clinicsViewList.addAll(clinicsListDB);
		}
	
	}
	

	/*
	 * 
	 * Auto Complete Section
	 * 
	 * 
	 */
    @PostConstruct
    public void completeTest() {
        Set<String> testNoDuplicate = new HashSet<String>();

		GlobalController glbCtrlr = (GlobalController) getControllerObject("globalController",GlobalController.class);


		      for (String test : glbCtrlr.getTestNameList()) {
                     if(testNoDuplicate.add(test)) {
                         testNameList.add(test);
                     }
		         }
				 testNoDuplicate.clear();

				 for (String city : glbCtrlr.getCityList()) {
			            if(testNoDuplicate.add(city)) {
			                cityList.add(city);
			            }
			        }     

            }
	
	
    /**
     * this method for search locality by given value
     *
     * @param query - String
     * @return localityNames - List<String>
     */
	public List<String> completeLocality(String query) {
		List<String> localityNames = new ArrayList<String>();
	        GlobalController glbCtrlr = (GlobalController) getControllerObject("globalController", GlobalController.class);
			
			for (String locality : glbCtrlr.getLocalityList()) {
	            if (null != locality && locality.toLowerCase().startsWith(query.toLowerCase())) {
	                localityNames.add(locality);
	            }
	        }

		return localityNames;
	}

    /**
     * this method for by city select show locality
     *
     * @param  - SelectEvent
     */
    public void onItemSelect(AjaxBehaviorEvent event) {
		 	managerService = RepositoryContext.getBean(ManagerService.class);
			searchService = managerService.getSearchService();
            localityList = searchService.getAllLocalityByCity(city);

	 }
	 
	 
	 /*
	  * Compare Section
	  * 
	  */
	 
	public void addToCompareBox(AjaxBehaviorEvent event) {
		
		FacesMessage msg = null;
		Clinic clinicParam = (Clinic) event.getComponent().getAttributes().get("clinicParam");
		logger.info("<<< Clinic Added For Compare >>> "+ clinicParam.getClinicName());
		if(compareClinicList.size() <3){
			compareClinicList.add(SearchHelper.convertToCompareObject(clinicParam));
		}else{
			logger.info("<<< Compare Item Reach To Maximum Limit 3 >>>");
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"You may only compare up to three Clinic  !!!", " ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public void compareProducts(ActionEvent event) {
        isComparePage = true;
		logger.info("<<< [compareProducts()] Compare Processing start .. >>>");
		this.showCompareView = true;
		this.showLeftNav = false;
		this.showMapView = false;
		this.showSearchView = false;
		logger.info("<<< Total Compare Product : "+this.compareClinicList.size()+" >>>");
	}
	
	
	public void removeClinic(ActionEvent event) {
		
		logger.info("<<< removeClinic() >>>");
		logger.debug("<<< removeClinic() >>>");
		Map<String,Object> attributes = event.getComponent().getAttributes();
		CompareClinic compareParam = (CompareClinic) attributes.get("removeClinic");
		logger.info("<<< Clinic Remove From Compare >>> "+ compareParam.getClinicFullName());
		
		 Iterator<CompareClinic> ite = compareClinicList.iterator();
         while(ite.hasNext()) {
        	 CompareClinic clinic = ite.next();
			if (clinic.equals(compareParam)) {
				ite.remove();
				logger.info("<====> Successfully removed . ");
			}
         }
		
	}

	/*
	 * View More Click
	 */
	 public void onViewMoreClick(ActionEvent event){
		 
		 logger.info("<<< onViewMoreClick .. >>>");
		 Map<String,Object> attributes = event.getComponent().getAttributes();
		 String viewMore = (String) attributes.get("viewMore");
		 Clinic clinic = (Clinic) attributes.get("clinic");
		 if(!viewMore.isEmpty() && viewMore.equals("deals")){
			this.viewMoreDeals = clinic.getDealsTab();
		 }else if(!viewMore.isEmpty() && viewMore.equals("reviews")){
			 this.viewMoreReviews = clinic.getReviewsTab();
		 }
		 
	 }
	 
	 /*
	  * Appointment Place Area 
	  * 
	  */
	 public void onMakeAppointmentClick(ActionEvent event){
		 logger.info("<<< onMakeAppointmentClick .. >>>");
		 Map<String,Object> attributes = event.getComponent().getAttributes();

		 String makeAppointment = (String) attributes.get("event");
		 clinicAppointmentSelected = (Clinic) attributes.get("clinic");
         compareClinic = (CompareClinic) attributes.get("list");
		 eventModel = new DefaultScheduleModel();

         appointmentUpdateId = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("updateAppointmentId");
         clinicId = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("appointmentClinicId");
	 }
	 /* Srinivasulu */
	 public void onDistanceClick(ActionEvent event){
		 logger.info("<<< Distance Click .. >>>");
		 Map<String,Object> attributes = event.getComponent().getAttributes();
		 @SuppressWarnings("unused")
		 String makeAppointment = (String) attributes.get("event");
		 clinicAppointmentSelected = (Clinic) attributes.get("clinic");
		 eventModel = new DefaultScheduleModel();
	 }
	 /* Srinivasulu */
	public void onDateSelect(SelectEvent selectEvent) {
		ScheduleEvent event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
		String startDate = DateUtil.formatIHMDate(event.getStartDate(), "start");
		String endDate = DateUtil.formatIHMDate(event.getEndDate(),"end");
        String currentDate = DateUtil.formatIHMDate(new Date(), "start");

        managerService = RepositoryContext.getBean(ManagerService.class);
        calendarService = managerService.getCalendarService();

        if (event.getStartDate().after(new Date()) || currentDate.equals(startDate)) {
            if (appointmentUpdateId != null && clinicId != null) {
                appointmentList = calendarService.getClinicCalendar(clinicId.toString(), startDate, endDate);
                try {
                    SlrSeller slr = managerService.getSearchService().getClinicById(Long.parseLong(clinicId.toString()));
                    clinicAppointmentSelected = new Clinic();
                    clinicAppointmentSelected.setClinicEmail(slr.getEmailAddress());
                    clinicAppointmentSelected.setId(clinicAppointmentSelected.getId());
                    clinicAppointmentSelected.setClinicName(clinicAppointmentSelected.getClinicName());
                } catch (IHMCustomerException e) {
                    e.printStackTrace();
                }
            } else if(compareClinic == null) {
                List<Appointment> appointmentListDB = calendarService.getClinicCalendar(this.clinicAppointmentSelected.getId(), startDate, endDate);
                appointmentList.clear();
                for (Appointment appointment : appointmentListDB) {
                    appointmentList.add(appointment);
                }
            } else {
                List<Appointment> appointmentListDB = calendarService.getClinicCalendar(this.compareClinic.getId(), startDate, endDate);
                appointmentList.clear();
                for (Appointment appointment : appointmentListDB) {
                    appointmentList.add(appointment);
                }
            }
		} else {
			JsfUtil.addErrorMessage("Date isn't valid .Please choose date after current date");
			appointmentList.clear();
		}

	}
	 
	 /*
	  * Appointment BL Start
	  */
	 public String bookAppointmentSlot(Appointment makeAppointment,Clinic bookedClinic){
//		 ActionEvent event
		 logger.info("<<< bookAppointmentSlot .. >>>");
//		 Map<String,Object> attributes = event.getComponent().getAttributes();
//		 makeAppointment = (Appointment) this.getRequestParameterMap().get("selectedSlot");
//		 bookedClinic = (Clinic) this.getRequestParameterMap().get("selectedClinic");
		 logger.info(">>  clinicId "+bookedClinic.getId()+" Is Going To Booked!");
		 this.clinicAppointmentSelected = bookedClinic;
//		 this.getRequestParameterMap().get("selectedSlot");
//		 
		 return "/appointment/appointmentlogin.xhtml";
	 }
	 
	 
	 /*
	  * Clinics Map
	  */
	 public void showClinicsMap(AjaxBehaviorEvent event){
		 
		 logger.info("<<< showClinicsMap .. >>>");
		 if(clinicsViewList.size() > 0){
			 this.showMapView = true;
			 this.showSearchView = false;
			 this.showLeftNav = false;
		 }else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"No Clinic Available For Map!! Refine Search Result", " ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		 }
		 advancedModel = new DefaultMapModel();
		 LatLng latLang;
		 for(Clinic clinic:clinicsViewList){
			 float lattitude = clinic.getClinicAddress().getLattitude();
			 float longitude = clinic.getClinicAddress().getLongitude();
			 if(0.0 != lattitude && 0.0 != longitude) {
				 latLang = new LatLng(lattitude,longitude);
				 String Html = "<h4>"+clinic.getClinicName()+"</h4><p>"+clinic.getClinicAddress().getFullAddress()+"</p>";
				 advancedModel.addOverlay(new Marker(latLang, Html, "", "http://maps.google.com/mapfiles/ms/micons/pink-dot.png")); //islamabad.png
			 }
			 
		 }
	 }

    public String onBackSearchResult() {

        FacesMessage msg = null;
        isComparePage = true;
        managerService = RepositoryContext.getBean(ManagerService.class);
        searchService = managerService.getSearchService();
        this.isComparePage = false;
        this.showSearchView = true;
        this.showMapView = false;
        this.showLeftNav = true;
        this.showCompareView = false;
        String testNameParam = this.testName;
        this.searchCriteria = this.testName;
        String cityParam = this.city;
        String localityParam = this.locality;
        String zipParam = this.zip;
        String dateParam = DateUtil.formatIHMDate(this.date, "start");


        paging = new PagingBean();
        paging.initPageCountByDataCount(searchService.getSlrSellerCount(testNameParam, cityParam, localityParam, zipParam, dateParam));
        paging.initControlsRenderingCases();
        paging.initPageBeanList();


        return "searchresults";
    }
	 
	 public void onMarkerSelect(OverlaySelectEvent event) {
	        marker = (Marker) event.getOverlay();
	 }

	/**
	 * @return the paging
	 */
	public PagingBean getPaging() {
		return paging;
	}

	/**
	 * @param paging the paging to set
	 */
	public void setPaging(PagingBean paging) {
		this.paging = paging;
	}
	
	public List<Clinic> getClinicsViewList() {
		return clinicsViewList;
	}

	public void setClinicsViewList(List<Clinic> clinicsViewList) {
		this.clinicsViewList = clinicsViewList;
	}

	public List<Clinic> getClinicsListDB() {
		return clinicsListDB;
	}

	public void setClinicsListDB(List<Clinic> clinicsListDB) {
		this.clinicsListDB = clinicsListDB;
	}

	public List<CompareClinic> getCompareClinicList() {
		return compareClinicList;
	}

	public void setCompareClinicList(List<CompareClinic> compareClinicList) {
		this.compareClinicList = compareClinicList;
	}

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}

	public MapModel getAdvancedModel() {
		return advancedModel;
	}

	public void setAdvancedModel(MapModel advancedModel) {
		this.advancedModel = advancedModel;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public LeftNavModel getLeftNavModel() {
		return leftNavModel;
	}

	public void setLeftNavModel(LeftNavModel leftNavModel) {
		this.leftNavModel = leftNavModel;
	}

	public DealsTab getViewMoreDeals() {
		return viewMoreDeals;
	}

	public void setViewMoreDeals(DealsTab viewMoreDeals) {
		this.viewMoreDeals = viewMoreDeals;
	}

	public ReviewsTab getViewMoreReviews() {
		return viewMoreReviews;
	}

	public void setViewMoreReviews(ReviewsTab viewMoreReviews) {
		this.viewMoreReviews = viewMoreReviews;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public Clinic getClinicAppointmentSelected() {
		return clinicAppointmentSelected;
	}

	public void setClinicAppointmentSelected(Clinic clinicAppointmentSelected) {
		this.clinicAppointmentSelected = clinicAppointmentSelected;
	}

	public Integer getTotalItemsFound() {
		return totalItemsFound;
	}

	public boolean isShowSearchView() {
		return showSearchView;
	}

	public void setShowSearchView(boolean showSearchView) {
		this.showSearchView = showSearchView;
	}

	public boolean isShowAdvancedSearchView() {
		return showAdvancedSearchView;
	}

	public void setShowAdvancedSearchView(boolean showAdvancedSearchView) {
		this.showAdvancedSearchView = showAdvancedSearchView;
	}
	
	public boolean isShowMapView() {
		return showMapView;
	}

	public void setShowMapView(boolean showMapView) {
		this.showMapView = showMapView;
	}

	public boolean isShowLeftNav() {
		return showLeftNav;
	}

	public void setShowLeftNav(boolean showLeftNav) {
		this.showLeftNav = showLeftNav;
	}

	public boolean isShowCompareView() {
		return showCompareView;
	}

	public void setShowCompareView(boolean showCompareView) {
		this.showCompareView = showCompareView;
	}

	public void setTotalItemsFound(Integer totalItemsFound) {
		this.totalItemsFound = totalItemsFound;
	}

	public Integer getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(Integer activeIndex) {
		this.activeIndex = activeIndex;
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public Integer getItemsPerRow() {
		return itemsPerRow;
	}

	public void setItemsPerRow(Integer itemsPerRow) {
		this.itemsPerRow = itemsPerRow;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	public List<String> getAdvancedSearchCriteria() {
		return advancedSearchCriteria;
	}

	public void setAdvancedSearchCriteria(List<String> advancedSearchCriteria) {
		this.advancedSearchCriteria = advancedSearchCriteria;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getResultRange() {
		return resultRange;
	}

	public void setResultRange(String resultRange) {
		this.resultRange = resultRange;
	}

	public Integer getSortBy() {
		return sortBy;
	}

	public void setSortBy(Integer sortBy) {
		this.sortBy = sortBy;
	}

	public String getItemSelectErrorMessage() {
		return itemSelectErrorMessage;
	}

	public void setItemSelectErrorMessage(String itemSelectErrorMessage) {
		this.itemSelectErrorMessage = itemSelectErrorMessage;
	}
	
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	public List<String> getadvancedSearchTestNameList() {
		return advancedSearchTestNameList;
	}

	public void setadvancedSearchTestNameList(List<String> advancedSearchTestNameList) {
		this.advancedSearchTestNameList = advancedSearchTestNameList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


    public String getSelectedSortId() {
        return selectedSortId;
    }

    public void setSelectedSortId(String selectedSortId) {
        this.selectedSortId = selectedSortId;
    }

    public boolean isComparePage() {
        return isComparePage;
    }

    public void setComparePage(boolean isComparePage) {
        this.isComparePage = isComparePage;
    }

    public CompareClinic getCompareClinic() {
        return compareClinic;
    }

    public void setCompareClinic(CompareClinic compareClinic) {
        this.compareClinic = compareClinic;
    }

    public Object getClinicId() {
        return clinicId;
    }

    public void setClinicId(Object clinicId) {
        this.clinicId = clinicId;
    }

    public Object getAppointmentUpdateId() {
        return appointmentUpdateId;
    }

    public void setAppointmentUpdateId(Object appointmentUpdateId) {
        this.appointmentUpdateId = appointmentUpdateId;
    }
	public String getSourceDistance() {
		return sourceDistance;
	}

	public void setSourceDistance(String sourceDistance) {
		this.sourceDistance = sourceDistance;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getClientLocation() {
		return clientLocation;
	}

	public void setClientLocation(String clientLocation) {
		this.clientLocation = clientLocation;
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
    };
    public String getTxtSource() {
		return txtSource;
	}

	public void setTxtSource(String txtSource) {
		this.txtSource = txtSource;
	}


}