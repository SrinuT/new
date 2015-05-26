/**
@author sardarwaqasahmed
@Date	Oct 28, 2013
*/
package com.ihm.customer.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ihm.customer.entites.SlrAppointment;
import com.ihm.customer.entites.SlrSeller;
import com.ihm.customer.faces.controller.SearchController;
import com.ihm.customer.frontend.leftnav.HospitalAmenities;
import com.ihm.customer.search.helper.IHMListHelper;
import com.ihm.customer.search.helper.SearchHelper;
import com.ihm.customer.service.AmenitiesService;
import com.ihm.customer.service.CalendarService;
import com.ihm.customer.service.ManagerService;
import com.ihm.customer.service.SearchService;
import com.ihm.customer.util.DateUtil;
import com.ihm.customer.util.IHMConstants;
import com.ihm.customer.util.PropertiesUtil;
import com.ihm.customer.util.RepositoryContext;
import com.ihm.frontend.search.dto.Appointment;
import com.ihm.frontend.search.dto.Clinic;
import com.ihm.frontend.search.dto.CustomerReviews;
import com.ihm.frontend.search.dto.Facility;
import com.ihm.frontend.search.dto.FacilityDocument;
import com.ihm.frontend.search.dto.ServiceDeals;
import com.ihm.frontend.search.dto.Slot;
@SuppressWarnings("all")
public class IHMantraFunctionalTest {
//
//	private static Logger logger = Logger.getLogger(IHMantraFunctionalTest.class);
//
//	private ManagerService managerService;
//	private SearchService searchService;
//	private AmenitiesService amenitiesService;
//	private CalendarService calendarService;
//
//	@Test(enabled = false)
//	public void testSearchClinic() {
//
//		logger.info("<><><>  testSearchClinic Start <><><>");
//		managerService = RepositoryContext.getBean(ManagerService.class);
//		searchService = managerService.getSearchService();
//
//		String testName = "Bllod Test";
//		String city = "CA";
//		String locality = "localitytest";
//		String zip = "94087";
//		Date date = new Date(2014, 12,02, 00, 00);
//		String dateParam = " " +date;
//		dateParam = DateUtil.formatDate(date);
//		try {
//
//
//			List<SlrSeller> clinicList = searchService.simpleSearchSeller(testName, city, locality, zip, "",0,5);
//			Assert.assertEquals((clinicList.size() > 0),true);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}
//	@Test(enabled = false)
//	public void testAmenitiesMap() {
//
//		logger.info("<><><>  testAmenitiesMap Start <><><>");
//		try {
//
//			String features = "Email-Y";
//			List<FacilityDocument> list = IHMListHelper.getAmenitiesList(features);
//			for (FacilityDocument doc : list) {
//				   logger.info("Iterating Over Features");
//				   logger.info(  doc.getName()+" "+doc.getStatus()+" "+doc.getIconPath());
//			}
//			Assert.assertEquals((list.size() > 0),true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Test(enabled = false)
//	public void testSearchHelper() {
//
//		logger.info("<><><>  testSearchHelper Start <><><>");
//		managerService = RepositoryContext.getBean(ManagerService.class);
//		searchService = managerService.getSearchService();
//
//		String testName = "Bllod Test";
//		String city = "CA";
//		String locality = "localitytest";
//		String zip = "94087";
//		Date date = new Date();
//
//		try {
//
//			Clinic clinic;
//			List<Clinic> clinicList = new ArrayList<Clinic>();
//
//			List<SlrSeller> serviceList = searchService.simpleSearchSeller(null, null, null, null, null,0,5);
//			if(null != serviceList && serviceList.size() > 0) {
//				logger.info("Total "+ serviceList.size() + " Clinic Found >>>>");
//				logger.debug("Total "+ serviceList.size() + " Clinic Found >>>>");
//				clinicList = SearchHelper.parseSearchResults(serviceList,managerService,date,testName);
//				logger.info("Total Clinic Found -:- "+clinicList.size() );
//				for(Clinic labClinic:clinicList) {
//					logger.info("-----------------------------------------------");
//					logger.info(labClinic.getClinicName() + " Clinic Description "+ labClinic.getDetailTab().getClinicDescription());
//					logger.info(labClinic.getClinicName() + " Clinic Address "+ labClinic.getClinicAddress().getFullAddress() );
//					logger.info("Total Customer Rating Found -:- "+labClinic.getReviewsTab().getCustomerReviewsList().size()+" Clinic Star Rating >>> "+ labClinic.getScoredRating());
//					for(CustomerReviews reviews:labClinic.getReviewsTab().getCustomerReviewsList()) {
//						logger.info(">>>> "+reviews.getCustomerReview()+ " Rating "+reviews.getCustomerStarRating());
//					}
//					logger.info("Total TimeSlot Found -:- "+labClinic.getTimingSlot().size() );
//					for(Slot slot:labClinic.getTimingSlot()){
//						logger.info(">>>> "+ slot.getSlotTime());
//					}
//					logger.info("Total Amenities Found -:- "+labClinic.getDetailTab().getClinicFacilites().size() );
//					for(Facility facility:labClinic.getDetailTab().getClinicFacilites()){
//						logger.info(">>>"+facility.getFacilityHeading());
//					}
//					logger.info("Total Deals Found -:- "+labClinic.getDealsTab().getServiceDealList().size() );
//					for(ServiceDeals deal:labClinic.getDealsTab().getServiceDealList()){
//						logger.info(">>>> "+deal.getDealName());
//					}
////					logger.info("-----------------------------------------------");
//				}
//				Assert.assertEquals((clinicList.size() > 0),true);
//			}else {
//				logger.info(">>>>> No Search Results Found from Database ");
//				logger.debug(">>>>> No Search Results Found from Database ");
//			}
//
//		} catch (Exception e) {
//			logger.info(":: Controller Layer Error Caught :: ");
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Test(enabled = false)
//	public void testStarRatingFilter() {
//		logger.info("<><><>  testSearchHelper Start <><><>");
//		managerService = RepositoryContext.getBean(ManagerService.class);
//		searchService = managerService.getSearchService();
//		SearchController ctr = new SearchController();
//		String testName = "Bllod Test";
//		String city = "CA";
//		String locality = "localitytest";
//		String zip = "94087";
//		Date date = new Date();
//		String dateParam = DateUtil.formatIHMDate(date, "start");
//
//		try {
//
//			Clinic clinic;
//			List<Clinic> clinicList = new ArrayList<Clinic>();
//			List<SlrSeller> serviceList = searchService.simpleSearchSeller(testName, city, locality, zip, dateParam,0,5);
//			if(null != serviceList && serviceList.size() > 0) {
//				logger.info("Total "+ serviceList.size() + " Clinic Found >>>>");
//				logger.debug("Total "+ serviceList.size() + " Clinic Found >>>>");
//				clinicList = SearchHelper.parseSearchResults(serviceList,managerService,date,testName);
//				ctr.generateStarRatingLeftNav(clinicList);
//				Assert.assertEquals((clinicList.size() > 0),true);
//			}else {
//				logger.info(">>>>> No Search Results Found from Database ");
//				logger.debug(">>>>> No Search Results Found from Database ");
//			}
//
//		} catch (Exception e) {
//			logger.info(":: Controller Layer Error Caught :: ");
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Test(enabled = false)
//	public void testLoadProperties() {
//		logger.info("::      Property Read >>> "+PropertiesUtil.getProperty("ihm.report.delivery"));
//	}
//
//	@Test(enabled = false)
//	public void testDistinctAmenitiesFromDB() {
//
//		logger.info("<><><>  testDistinctAmenitiesFromDB Start <><><>");
//		managerService = RepositoryContext.getBean(ManagerService.class);
//		amenitiesService = managerService.getAmenitiesService();
//
//		List<String> amenitiesListDB = amenitiesService.getDistinctAmenitiesName();
//		logger.info("Distinct Amenities Found From DB : "+amenitiesListDB.size());
//		HospitalAmenities hospitalAmenity = null;
//		List<HospitalAmenities> hosAmenitiesList  = new ArrayList<HospitalAmenities>();
//		for(String amenity:amenitiesListDB) {
//
//			hospitalAmenity = new HospitalAmenities();
//			hospitalAmenity.setHeading(amenity);
//			if(amenity.equals(IHMConstants.REPORT_DELIVERY)) {
//				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.REPORT_DELIVERY_FEATURES));
//			}else if(amenity.equals(IHMConstants.DOORSTEP_SAMPLE_COLL)){
//				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.DOORSTEP_SAMPLE_COLL_FEATURES));
//			}else if(amenity.equals(IHMConstants.CUSTOMER_WAITING_LOUNGE)){
//				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.CUSTOMER_WAITING_LOUNGE_FEATURES));
//			}else  if(amenity.equals(IHMConstants.ENTERTAINMENT)){
//				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.ENTERTAINMENT_FEATURES));
//			}else  if(amenity.equals(IHMConstants.CAR_PARKING)){
//				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.CAR_PARKING_FEATURES));
//			}else  if(amenity.equals(IHMConstants.TWO_WHEELER_PARKING)){
//				hospitalAmenity.setDocumnetList(IHMListHelper.getLeftNavAmenitiesList(IHMConstants.TWO_WHEELER_PARKING_FEATURES));
//			}
//
//			hosAmenitiesList.add(hospitalAmenity);
//
//		}
//
//		logger.info("Total Amenities Added In LeftNavModel : "+hosAmenitiesList.size());
//	}
//
//	@Test(enabled = false)
//	public void testClinicCalendar() {
//
//		try{
//
//		logger.info("<><><>  testClinicCalendar Start <><><>");
//		managerService = RepositoryContext.getBean(ManagerService.class);
//		calendarService = managerService.getCalendarService();
//		 String year = DateUtil.getCurrentYear();
//		 String month = DateUtil.getCurrentMonth();
//		 String startDay = "01";
//		 String endDay = "31";
//		 String startDate = 2015+"-"+01+"-"+startDay+" 00:00:00";
//		 String endDate = 2015+"-"+01+"-"+endDay+" 23:59:59";
//
//		 List<Appointment> slotsListDB = calendarService.getClinicCalendar("1",startDate,endDate);
//		logger.info("Time Slot Found From DB : "+slotsListDB.size());
//		for(Appointment appointmnet:slotsListDB){
//			logger.info(" :: Appointment >>>> :: "+appointmnet.toString());
//		}
//		} catch (Exception e) {
//			logger.info(":: Controller Layer Error Caught :: ");
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Test(enabled = false)
//	public void testAutoCompleteList() {
//		try {
//			logger.info("<><><>  testAutoCompleteList Start <><><>");
//			managerService = RepositoryContext.getBean(ManagerService.class);
//			searchService = managerService.getSearchService();
//			List<String> testName = searchService.getAllTestByClinic("b");
//			logger.info("Total AutoComplete Test Found :-  "+testName.size());
//		} catch (Exception e) {
//			logger.info(":: Controller Layer Error Caught :: ");
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Test(enabled = false)
//	public void testDateFormat(){
//		 String year = DateUtil.getCurrentYear();
//		 String month = DateUtil.getCurrentMonth();
//		 String startDay = "01";
//		 String endDay = "31";
//		 String startDate = "'"+year+"-"+month+"-"+startDay+" 00:00:00"+"'";
//		 String endDate = "'"+year+"-"+month+"-"+endDay+" 23:59:59"+"'";
//		logger.info("StartDate >> "+startDate+" End Date >>> "+endDate);
//	}
}