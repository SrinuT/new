package com.ihm.customer.search.helper;
/**
 * @author SARDAR WAQAS AHMED
 * @email  architect_pakistan@hotmail.com
 * @since  01 NOV, 2014
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.ihm.customer.entites.*;
import com.ihm.customer.service.CalendarService;
import com.ihm.customer.service.ManagerService;
import com.ihm.customer.util.DateUtil;
import com.ihm.customer.util.IHMConstants;
import com.ihm.frontend.search.dto.*;

@SuppressWarnings("all")
public class SearchHelper {

    private static Logger logger = Logger.getLogger(SearchHelper.class);

	public static List<Clinic> parseSearchResults(List<SlrSeller> serviceList,ManagerService managerService,Date searchDate,String testName,String clientLocation) {

        List<Clinic> clinicList = new ArrayList<Clinic>();
        Clinic clinic;
        int clinicUIIdCounter = 0;
        try {

            for(SlrSeller seller:serviceList) {
                DetailTab   detailTab = new DetailTab();
                SlrAddress address = seller.getSlrAddress();
                clinic = new Clinic();
                clinic.setId(seller.getId()+"");
                clinicUIIdCounter +=1;
                clinic.setClinicName(seller.getName());
                clinic.setTestName(testName);
                clinic.setClinicEmail(seller.getEmailAddress());
                clinic.setLandLineNumber(seller.getLandLineNumber());
                clinic.setSlrSellerId(seller.getId());
                clinic.setClinicUIId("tab-"+clinicUIIdCounter);
				clinic.setImageUrl1(seller.getImage1url());
				clinic.setImageUrl2(seller.getImage2url());
				clinic.setImageUrl3(seller.getImage3url());
				clinic.setImageUrl4(seller.getImage4url());
				clinic.setImageUrl5(seller.getImage5url());
                if(null != address) {
                    logger.info(":: Process Clinic Address >>>> ::");
                    logger.debug(":: Process Clinic Address >>>> ::");
					processAddress(address,clinic,clientLocation);
                }

                List<SlrProduct> clinicProductList = new ArrayList<SlrProduct>(seller.getSlrProducts());
                if(null != clinicProductList && clinicProductList.size() > 0 )  {
                    logger.info(":: Process Clinic Products >>>> ::  Total Products Found "+clinicProductList.size());
                    logger.debug(":: Process Clinic Products >>>> :: ");
                    // Ideally one exact search result should come against the search Query
					for (int i = 0; i < clinicProductList.size(); i++){
						logger.info(testName+"============================="+(clinicProductList.get(i).getName()));
						if(testName.equalsIgnoreCase(clinicProductList.get(i).getName())){
							clinic.setTestPrice(clinicProductList.get(i).getPrice()+"");
							/*int Price = Integer.valueOf(clinicProductList.get(i).getPrice().intValue());
							int Discount = Price - ((Price/100)*10);
							clinic.setTestDiscount(Discount+"");*/
							clinic.setPrimaryImage(clinicProductList.get(i).getPrimaryImageUrl());
							//clinic.setDistanceDifference(""+distance((double)clinicProductList.get(i).getSlrSeller().getSlrAddress().getLatitude(),0.00,0.00,0.00));
				
                    //processDiscountedProductPrice();
							/*List<SlrProductReview> customerReviews = new ArrayList<SlrProductReview>(clinicProductList.get(i).getSlrProductReviews());

                            if(null != customerReviews && customerReviews.size() > 0 ) {
                                logger.info(":: Process Customer Reviews Against Services >>>> ::  Total CustomerReviews Found "+customerReviews.size());
                                logger.debug(":: Process Customer Reviews >>>> :: ");
								///
                                processProductReviews(customerReviews,clinic);
							}*/
                        }
                    }
                }
				List<SlrSellerReview> customerReviews = new ArrayList<SlrSellerReview>(seller.getSlrSellerReviews());
				processProductReviews(customerReviews,clinic);

                List<SlrAmenities> facilitiesList = new ArrayList<SlrAmenities>(seller.getSlrAmenitieses());
                if(null != facilitiesList && facilitiesList.size() > 0) {
                    logger.info(":: Process Clinic Facilities >>>> ::  Total Facilities Found "+facilitiesList.size());
                    logger.debug(":: Process Clinic Facilities >>>> :: ");
                    processDetailsTab(facilitiesList, clinic, seller, detailTab);
                    // Iterate over facilities list and set avaialble faility For UI Icons
                    iterateAvailableFacilities(clinic.getDetailTab().getClinicFacilites(),clinic);
                }

                List<SlrCertification> certificationList = new ArrayList<SlrCertification>(seller.getSlrCertifications());
                if(null != certificationList && certificationList.size() > 0) {
                    logger.info(":: Process Clinic Certification >>>> ::  Total Certification Found "+facilitiesList.size());
                    logger.debug(":: Process Clinic Certification >>>> :: ");
                    processCertification(certificationList,clinic);
                }

                List<SlrAward> awardList = new ArrayList<SlrAward>(seller.getSlrAwards());
                if(null != awardList && awardList.size() > 0) {
                    logger.info(":: Process Clinic Award >>>> ::  Total Award Found "+awardList.size());
                    logger.debug(":: Process Clinic Award >>>> :: ");
                    processAward(awardList,clinic);
                }
				
				/*
				 * For Showing Current Day Operation Time On Search Page
				 */
                CalendarService calendarService = managerService.getCalendarService();
                String startDate = DateUtil.formatIHMDate(searchDate,"start");
                String endDate = DateUtil.formatIHMDate(searchDate,"end");
                // will change later this to start and endDate
				//List<Appointment> timeSlotList = calendarService.getClinicCalendar(seller.getId()+"","2015-01-01 00:00:00","2015-01-01 23:59:59");
				logger.info(startDate+"====================="+endDate+"<==========Search Date=============>"+searchDate);
				List<Appointment> timeSlotList = calendarService.getClinicCalendar(seller.getId()+"",startDate,endDate);
                logger.info("Time Slot Found From DB For Today Is : "+timeSlotList.size());
				processTimeSlots(timeSlotList,clinic);
				logger.info(timeSlotList);
                if(null != timeSlotList && timeSlotList.size() > 0) {
                    logger.info(":: Process Clinic Operational Timing ::");
                    logger.debug(":: Process Clinic Operational Timing >>>> :: ");
					if(searchDate == null){
						searchDate = new Date();
						clinic.setNexttimeslot(searchDate);
						logger.info("Search Date assign======"+searchDate);
					}
					else{
						clinic.setNexttimeslot(searchDate);
						logger.info("Search Date assingn Else====="+searchDate);
					}
                    processTimeSlots(timeSlotList,clinic);
                }
				else{
					logger.info("Time Slots List with out else condition--------------->"+seller.getId() +"Clinic Id" +clinic.getId()+ "clinic Name==>"+clinic.getClinicName());
					//Date nextAvailDate = 
					String starttimeofClinic = calendarService.getClinicCalendarforstartdate(clinic.getId());		
					String startDatedb = "";
					String endDatedb = "";
					if ( "" != starttimeofClinic){
					Date startdatefromdb = DateUtil.splitAndConvertStringDatenext(starttimeofClinic);
					clinic.setNexttimeslot(startdatefromdb);
					startDatedb = DateUtil.formatIHMDate(startdatefromdb,"start");
					endDatedb = DateUtil.formatIHMDate(startdatefromdb,"end");
					}
					else{
						if(searchDate == null){
							searchDate = new Date();
							clinic.setNexttimeslot(searchDate);
							logger.info("Search Date assign======"+searchDate);
						}
						else{
							//searchDate = new Date(); // Not Passing search Date assign Today date
							clinic.setNexttimeslot(searchDate);
							logger.info("Search Date assingn Else====="+searchDate);
						}
						startDatedb = startDate;
						endDatedb = endDate;
					}					
					logger.info("Start Date time for Clinic=======================>"+starttimeofClinic+"DAte converteser Start Date ==========="+startDatedb+"Enddate"+endDatedb);
					timeSlotList = calendarService.getClinicCalendarwithoutslot(seller.getId()+"",startDatedb,endDatedb);
					logger.info("Time Slots List with out --------------->"+ timeSlotList );
					processTimeSlots(timeSlotList,clinic);
				}
				
				/*
				 * Ideally one service will comes in search results against each clinic
				 * If 10 Clinic comes in search results then each clinic has 1 service i.e Neck Pain MRI 
				 * 
				 */
                List<SlrDeal> dealsList = new ArrayList<SlrDeal>(seller.getPrdDeals());
                if(null != dealsList && dealsList.size() > 0 )  {
                    logger.info(":: Process Service Deals ::");
                    logger.debug(":: Process Service Deals >>>> :: ");
						processDealsTab(dealsList,clinic,testName);			
                }
                if (!clinicProductList.isEmpty()) {
                    if (clinicProductList.get(0).getLongDescription() != null)
                        detailTab.setProductDescription(clinicProductList.get(0).getLongDescription());
                    if (clinicProductList.get(0).getName() != null)
                        detailTab.setProductName(clinicProductList.get(0).getName());
                }

                if(seller.getWebAddress() != null)detailTab.setWebAddress(seller.getWebAddress());
                if(seller.getMobileNumber() != null)detailTab.setMobileNumber(seller.getMobileNumber());
                if(seller.getEmailAddress() != null)detailTab.setEmailAddress(seller.getEmailAddress());
                if(seller.getSchedule() != null) detailTab.setOpeningHours(DateUtil.byGivenStringTakeTime(seller.getSchedule()));
                
				MapModel clinicMapModel = new DefaultMapModel();
			    LatLng coord1 = new LatLng(clinic.getClinicAddress().getLattitude(),clinic.getClinicAddress().getLongitude());
			    String Html = "<h4>"+clinic.getClinicName()+"</h4><p>"+clinic.getClinicAddress().getFullAddress()+"</p>";
			    clinicMapModel.addOverlay(new Marker(coord1,Html,"","http://maps.google.com/mapfiles/ms/micons/pink-dot.png"));
			    clinic.setClinicMap(clinicMapModel);
                clinicList.add(clinic);
            }
            Collections.sort(clinicList,new Clinic());
        }catch (Exception e) {
            logger.info("Error Occured While Parsing Search Results >>>> ");
            throw e;
        }
        return clinicList;

    }
	
	public static List<Clinic> parseAdvancedSearchResults(List<SlrSeller> serviceList,ManagerService managerService,Date searchDate,List<String> testNameList,String clientLocation) {
		String testName = "";
        List<Clinic> clinicList = new ArrayList<Clinic>();
        Clinic clinic;
        int clinicUIIdCounter = 0;
        try {
        	for(SlrSeller seller:serviceList) {
        		for(int i = 0; i<testNameList.size(); i++){
                    DetailTab   detailTab = new DetailTab();
                    SlrAddress address = seller.getSlrAddress();
                    clinic = new Clinic();
                    List<SlrProduct> clinicProductList = new ArrayList<SlrProduct>(seller.getSlrProducts());
                    if(null != clinicProductList && clinicProductList.size() > 0 )  {
                        // Ideally one exact search result should come against the search Query
    					logger.info(testNameList.get(i)+"============================="+(clinicProductList.get(0).getName()));
    					if(testNameList.get(i).equalsIgnoreCase(clinicProductList.get(0).getName())){
    							
		                    clinic.setId(seller.getId()+"");
		                    clinicUIIdCounter +=1;
		                    clinic.setClinicName(seller.getName());
		                    for (int j = 0; j < testNameList.size(); j++){
		                    	testName += testNameList.get(j)+",";
		                    }
		                    System.out.println(testName);
		                    testName = testName.substring(0,testName.length()-1);
		                    clinic.setTestName(testName);
		                    clinic.setClinicEmail(seller.getEmailAddress());
		                    clinic.setLandLineNumber(seller.getLandLineNumber());
		                    clinic.setClinicUIId("tab-"+clinicUIIdCounter);
		    				clinic.setImageUrl1(seller.getImage1url());
		    				clinic.setImageUrl2(seller.getImage2url());
		    				clinic.setImageUrl3(seller.getImage3url());
		    				clinic.setImageUrl4(seller.getImage4url());
		    				clinic.setImageUrl5(seller.getImage5url());
		                    if(null != address) {
		                        logger.info(":: Process Clinic Address >>>> ::");
		                        logger.debug(":: Process Clinic Address >>>> ::");
		    					processAddress(address,clinic,clientLocation);
		                    }
		                    double total = 0;
		                    Iterator<SlrProduct> it=clinicProductList.iterator();
		                    while(it.hasNext()){
		                      total += Double.parseDouble((it.next().getPrice()+""));	
		                      System.out.println("############ prices in helper "+total );
		                    }
		                    
		                    clinic.setTestPrice(total+"");
							System.out.println("Price====>"+total);
							
							clinic.setPrimaryImage(clinicProductList.get(0).getPrimaryImageUrl());
		    							
		    							
		    				List<SlrSellerReview> customerReviews = new ArrayList<SlrSellerReview>(seller.getSlrSellerReviews());
		    				processProductReviews(customerReviews,clinic);
		
		                    List<SlrAmenities> facilitiesList = new ArrayList<SlrAmenities>(seller.getSlrAmenitieses());
		                    if(null != facilitiesList && facilitiesList.size() > 0) {
		                        logger.info(":: Process Clinic Facilities >>>> ::  Total Facilities Found "+facilitiesList.size());
		                        logger.debug(":: Process Clinic Facilities >>>> :: ");
		                        processDetailsTab(facilitiesList, clinic, seller, detailTab);
		                        // Iterate over facilities list and set avaialble faility For UI Icons
		                        iterateAvailableFacilities(clinic.getDetailTab().getClinicFacilites(),clinic);
		                    }
		
		                    List<SlrCertification> certificationList = new ArrayList<SlrCertification>(seller.getSlrCertifications());
		                    if(null != certificationList && certificationList.size() > 0) {
		                        logger.info(":: Process Clinic Certification >>>> ::  Total Certification Found "+facilitiesList.size());
		                        logger.debug(":: Process Clinic Certification >>>> :: ");
		                        processCertification(certificationList,clinic);
		                    }
		
		                    List<SlrAward> awardList = new ArrayList<SlrAward>(seller.getSlrAwards());
		                    if(null != awardList && awardList.size() > 0) {
		                        logger.info(":: Process Clinic Award >>>> ::  Total Award Found "+awardList.size());
		                        logger.debug(":: Process Clinic Award >>>> :: ");
		                        processAward(awardList,clinic);
		                    }
		    				
		    				/*
		    				 * For Showing Current Day Operation Time On Search Page
		    				 */
		                    CalendarService calendarService = managerService.getCalendarService();
		                    String startDate = DateUtil.formatIHMDate(searchDate,"start");
		                    String endDate = DateUtil.formatIHMDate(searchDate,"end");
		                    // will change later this to start and endDate
		    				//List<Appointment> timeSlotList = calendarService.getClinicCalendar(seller.getId()+"","2015-01-01 00:00:00","2015-01-01 23:59:59");
		    				logger.info(startDate+"====================="+endDate+"<==========Search Date=============>"+searchDate);
		    				List<Appointment> timeSlotList = calendarService.getClinicCalendar(seller.getId()+"",startDate,endDate);
		                    logger.info("Time Slot Found From DB For Today Is : "+timeSlotList.size());
		    				processTimeSlots(timeSlotList,clinic);
		    				logger.info(timeSlotList);
		                    if(null != timeSlotList && timeSlotList.size() > 0) {
		                        logger.info(":: Process Clinic Operational Timing ::");
		                        logger.debug(":: Process Clinic Operational Timing >>>> :: ");
		    					if(searchDate == null){
		    						searchDate = new Date();
		    						clinic.setNexttimeslot(searchDate);
		    						logger.info("Search Date assign======"+searchDate);
		    					}
		    					else{
		    						clinic.setNexttimeslot(searchDate);
		    						logger.info("Search Date assingn Else====="+searchDate);
		    					}
		                        processTimeSlots(timeSlotList,clinic);
		                    }
		    				else{
		    					logger.info("Time Slots List with out else condition--------------->"+seller.getId() +"Clinic Id" +clinic.getId());
		    					//Date nextAvailDate = 
		    					String starttimeofClinic = calendarService.getClinicCalendarforstartdate(clinic.getId());		
		    					String startDatedb = "";
		    					String endDatedb = "";
		    					if ( "" != starttimeofClinic){
		    					Date startdatefromdb = DateUtil.splitAndConvertStringDate(starttimeofClinic);
		    					clinic.setNexttimeslot(startdatefromdb);
		    					startDatedb = DateUtil.formatIHMDate(startdatefromdb,"start");
		    					endDatedb = DateUtil.formatIHMDate(startdatefromdb,"end");
		    					}
		    					else{
		    						if(searchDate == null){
		    							searchDate = new Date();
		    							clinic.setNexttimeslot(searchDate);
		    							logger.info("Search Date assign======"+searchDate);
		    						}
		    						else{
		    							clinic.setNexttimeslot(searchDate);
		    							logger.info("Search Date assingn Else====="+searchDate);
		    						}
		    						startDatedb = startDate;
		    						endDatedb = endDate;
		    					}					
		    					logger.info("Start Date time for Clinic=======================>"+starttimeofClinic+"DAte converteser Start Date ==========="+startDatedb+"Enddate"+endDatedb);
		    					timeSlotList = calendarService.getClinicCalendarwithoutslot(seller.getId()+"",startDatedb,endDatedb);
		    					logger.info("Time Slots List with out --------------->" );
		    					processTimeSlots(timeSlotList,clinic);
		    				}
		    				
		    				/*
		    				 * Ideally one service will comes in search results against each clinic
		    				 * If 10 Clinic comes in search results then each clinic has 1 service i.e Neck Pain MRI 
		    				 * 
		    				 */
		                    List<SlrDeal> dealsList = new ArrayList<SlrDeal>(seller.getPrdDeals());
		                    if(null != dealsList && dealsList.size() > 0 )  {
		                        logger.info(":: Process Service Deals ::");
		                        logger.debug(":: Process Service Deals >>>> :: ");
		    						processDealsTab(dealsList,clinic,testNameList.get(i));			
		                    }
		                    if (!clinicProductList.isEmpty()) {
		                        if (clinicProductList.get(0).getLongDescription() != null)
		                            detailTab.setProductDescription(clinicProductList.get(0).getLongDescription());
		                        if (clinicProductList.get(0).getName() != null)
		                            detailTab.setProductName(clinicProductList.get(0).getName());
		                    }
		
		                    if(seller.getWebAddress() != null)detailTab.setWebAddress(seller.getWebAddress());
		                    if(seller.getMobileNumber() != null)detailTab.setMobileNumber(seller.getMobileNumber());
		                    if(seller.getEmailAddress() != null)detailTab.setEmailAddress(seller.getEmailAddress());
		                    if(seller.getSchedule() != null) detailTab.setOpeningHours(DateUtil.byGivenStringTakeTime(seller.getSchedule()));
		                    
		    				MapModel clinicMapModel = new DefaultMapModel();
		    			    LatLng coord1 = new LatLng(clinic.getClinicAddress().getLattitude(),clinic.getClinicAddress().getLongitude());
		    			    String Html = "<h4>"+clinic.getClinicName()+"</h4><p>"+clinic.getClinicAddress().getFullAddress()+"</p>";
		    			    clinicMapModel.addOverlay(new Marker(coord1,Html,"","http://maps.google.com/mapfiles/ms/micons/pink-dot.png"));
		    			    clinic.setClinicMap(clinicMapModel);
		    			    clinicList.add(clinic);
		    			    
                        }
                    }
        		}
        	}
            Collections.sort(clinicList,new Clinic());
        }catch (Exception e) {
            logger.info("Error Occured While Parsing Search Results >>>> ");
            throw e;
        }
        return clinicList;

    }

    private static void processAward(List<SlrAward> awardList, Clinic clinic) {
        List<Award> awards = new ArrayList<Award>();
        Award awardObject;
        for(SlrAward award:awardList){
            awardObject = new Award();
            awardObject.setAwardDate(award.getAwardYear()+"");
            awardObject.setAwardName(award.getName());
            awardObject.setDescription(award.getDescription());
//			awardObject.setImage(award.getAwardImagePath());
            awards.add(awardObject);
        }
        clinic.getDetailTab().setAwardList(awards);
    }

    private static void processCertification(List<SlrCertification> certificateList,Clinic clinic) {

        List<Certification> certifications = new ArrayList<Certification>();
        Certification certificateObject;
        for(SlrCertification certification:certificateList){
            certificateObject = new Certification();
            certificateObject.setCertificateDate(certification.getCertYear()+"");
            certificateObject.setCertificateName(certification.getName());
            certificateObject.setDescription(certification.getDescription());
//			certificateObject.setImage(certification.getCertificationImagePath());
            certifications.add(certificateObject);
        }
        clinic.getDetailTab().setCertificationList(certifications);
    }



    private static void iterateAvailableFacilities(List<Facility> clinicFacilites, Clinic clinic) {

        for(Facility facility:clinicFacilites) {
            if(null != facility.getFacilityHeading() && !facility.getFacilityHeading().isEmpty()) {
                if(facility.getFacilityHeading().equalsIgnoreCase(IHMConstants.REPORT_DELIVERY)|| facility.getFacilityHeading().equalsIgnoreCase(IHMConstants.CUSTOMER_WAITING_LOUNGE) || facility.getFacilityHeading().equalsIgnoreCase(IHMConstants.CAR_PARKING)) {
                    for(FacilityDocument doc:facility.getDocuments()) {
                        if(doc.getName().equalsIgnoreCase(IHMConstants.EMAIL) && doc.getStatus().equalsIgnoreCase("true")){
                            clinic.getFacilitesList().add(IHMConstants.EMAIL_ICON);
                            break;
                        }else if(doc.getName().equalsIgnoreCase(IHMConstants.WIFI) && doc.getStatus().equalsIgnoreCase("true")){
                            clinic.getFacilitesList().add(IHMConstants.WIFI_ICON);
                            break;
                        }else if(doc.getName().equalsIgnoreCase(IHMConstants.SELF) && doc.getStatus().equalsIgnoreCase("true")){
                            clinic.getFacilitesList().add(IHMConstants.SELF_ICON);
                            break;
                        }
                    }
                }
            }
        }

    }

	private static void processDealsTab(List<SlrDeal> dealsList, Clinic clinic,String testName) {

        if (dealsList != null && clinic != null && testName != null) {
        DealsTab dealTab = clinic.getDealsTab();
        List<ServiceDeals> serviceDealList = new ArrayList<ServiceDeals>();
        ServiceDeals deal = null;
		Double pricefinaldisamount = 0.00;
		Double pricedispercent = 0.00;
		
		
        for(SlrDeal slrDeal:dealsList) {
			Double pricedisamount = 0.00;
            deal = new ServiceDeals();
            deal.setDealName(slrDeal.getName());
            deal.setDealDescription(slrDeal.getDescription());
			deal.setDealDiscount(slrDeal.getDiscountAmount().toString());
			deal.setDealPercent(slrDeal.getDiscountPercentage().toString());
      /* for adding all deals for clinic, 
				comment this if you want to remove the not applicable deals and uncommnet the line 475 and 480
			*/
            serviceDealList.add(deal);
			Boolean productBaseoffertype = false;
			Boolean isProductApplicable = false;
			
			/* discount */
			/* Discount Amount Start*/
			 Double price = (Double.parseDouble(clinic.getTestPrice()));
			 Double dealAmount = 0.00;
			 // 
			 logger.info("Deal Id ==============>"+ slrDeal.getId());
			 if (slrDeal.getProductBase().trim().equals("ALL_PRODUCTS")){
			 
					if(Double.parseDouble(slrDeal.getDiscountAmount().toString())!=0.00){
						  dealAmount = Double.parseDouble(slrDeal.getDiscountAmount().toString());
						  logger.info("ALL_PRODUCTS>>>>>>>>>>>>> Amount"+ dealAmount);
						  clinic.setTestDiscountAmount(dealAmount.toString());
						  deal.setDealDiscount("Rs. "+dealAmount.toString());
					}else{
					     Double dealPercent = Double.parseDouble(slrDeal.getDiscountPercentage().toString());
					     dealAmount = (Double.parseDouble(clinic.getTestPrice())/100)*dealPercent;
					     logger.info("ALL_PRODUCTS>>>>>>>>>>>>> Percent"+ dealAmount);
					     deal.setDealDiscount("Rs. "+dealAmount.toString());
					 }
					productBaseoffertype = true;
			
			}
			 if (slrDeal.getProductBase().trim().equals("SELECT_PRODUCTS")){
				 Set<SlrProduct> productList = slrDeal.getSlrProducts();
				 for(SlrProduct product:productList) {
					 logger.info("product ================== "+testName);
					 logger.info("product ================== "+product.getName());
					 if(testName.trim().equals(product.getName())){
						 isProductApplicable = true;
					 }
				 }
				 if(isProductApplicable){
					
						if(Double.parseDouble(slrDeal.getDiscountAmount().toString())!=0.00){
							  dealAmount = Double.parseDouble(slrDeal.getDiscountAmount().toString());
							  logger.info("SELECT_PRODUCTS>>>>>>>>>>>>> Amount"+ dealAmount);
							  deal.setDealDiscount("Rs. "+dealAmount.toString());
						}else{
						     Double dealPercent = Double.parseDouble(slrDeal.getDiscountPercentage().toString());
						     dealAmount = (Double.parseDouble(clinic.getTestPrice())/100)*dealPercent;
						     logger.info("SELECT_PRODUCTS>>>>>>>>>>>>> Percent"+ dealAmount);
						     deal.setDealDiscount("Rs. "+dealAmount.toString());
						 }
						productBaseoffertype = true;
				 }else{
					 productBaseoffertype = false;
				 }
				 
			}
			 if (slrDeal.getProductBase().trim().equals("SELECT_CATEGORIES")){
				 	 Set<IhmCategories> categoriesList = slrDeal.getIhmCategorieses();
					 Set<SlrProduct> productList = slrDeal.getSlrProducts();
					 Boolean isCategorieApplicable = false;
					 for(SlrProduct product:productList) {
						 logger.info("product ================== "+testName);
						 logger.info("product ================== "+product.getName());
						 if(testName.trim().equals(product.getName())){
							//getIhmCategorieses
							 if(product.getIhmCategories()!=null){
								 logger.info("categeroy =================== "+product.getIhmCategories().getId());
								 for(IhmCategories categories:categoriesList) {
									 if(product.getIhmCategories().getId()==categories.getId()){
										 isCategorieApplicable = true;	
									 }
								 }
								 
							 }
						 }
						 
					 }
					 if(isCategorieApplicable){
						 if(Double.parseDouble(slrDeal.getDiscountAmount().toString())!=0.00){
							  dealAmount = Double.parseDouble(slrDeal.getDiscountAmount().toString());
							  logger.info("SELECT_CATEGORIES>>>>>>>>>>>>> Amount"+ dealAmount);
							  deal.setDealDiscount("Rs. "+dealAmount.toString());
						}
						if(Double.parseDouble(slrDeal.getDiscountPercentage().toString())!=0.00){
						     Double dealPercent = Double.parseDouble(slrDeal.getDiscountPercentage().toString());
						     dealAmount = (Double.parseDouble(clinic.getTestPrice())/100)*dealPercent;
						     logger.info("SELECT_CATEGORIES>>>>>>>>>>>>> Percent"+ dealAmount);
						     deal.setDealDiscount("Rs. "+dealAmount.toString());
						 }
						productBaseoffertype = true;
					 }else{
						 productBaseoffertype = false;
					 }
					
			}
				 //All Products SELECT_CATEGORIES
			if(productBaseoffertype == true){
					// Range
			    	if(slrDeal.getPriceBase().trim().equals("RANGE")){
				    		 if (slrDeal.getApplyStartAmount() != null && slrDeal.getApplyEndAmount() != null){
					    		 Double startAmountRange = Double.parseDouble(slrDeal.getApplyStartAmount().toString());
					    		 Double endAmountRange = Double.parseDouble(slrDeal.getApplyEndAmount().toString());
					    		logger.info(startAmountRange+" sreekanth "+endAmountRange);
						    	 if(price != 0.00 && price > dealAmount && dealAmount !=0.00){
						    		 if( price >= startAmountRange && price <= endAmountRange){
						    			 pricedisamount = dealAmount;
							    		 logger.info("Price Range if ==============>"+ pricedisamount);
						    		 }else{
										 pricedisamount = 0.00;
										 logger.info("RANGE Final else inside ===============>"+pricedisamount +" = " +dealAmount);
									 }
						    	 }else{
									 pricedisamount = 0.00;
									 logger.info("RANGE Final else inside ===============>"+pricedisamount +" = " +dealAmount);
								 }
				    		 }else{
								 pricedisamount = 0.00;
								 logger.info("RANGE Final Else Outside===============>"+pricedisamount +" = " +dealAmount);
							 }
			    	 	}else if(slrDeal.getPriceBase().trim().equals("GREATER_THAN")){
				    		 if (slrDeal.getApplyGreaterAmount() != null && slrDeal.getApplyGreaterAmount() != null){
				    			 Double greaterAmount = Double.parseDouble(slrDeal.getApplyGreaterAmount().toString());
					    	 		if (greaterAmount != 0.00){
						    	 		if ( price > greaterAmount){
						    	 			pricedisamount = dealAmount;
						    	 			logger.info("Price Greater_THan=========="+ pricedisamount + "greate"+ greaterAmount);
						    	 		}
						    	 		else{
						    	 			pricedisamount = 0.00;
						    	 		}
					    	 		}else if (price != 0.00 && price > dealAmount && dealAmount !=0.00){
										 pricedisamount = dealAmount;
										 logger.info("Deal all products Amount Normal===============>"+pricedisamount);
									 }else{
										 pricedisamount = 0.00;
										 logger.info("Deal all products Amount Normal===============>"+pricedisamount);
									 }
				    	 		}else if (price != 0.00 && price > dealAmount && dealAmount !=0.00){
									 pricedisamount = dealAmount;
									 logger.info("Deal all products Amount Normal===============>"+pricedisamount);
								 }else{
									 pricedisamount = 0.00;
									 logger.info("Deal all products Amount Normal===============>"+pricedisamount);
								 }
			    	 	}else if(slrDeal.getPriceBase().trim().equals("LESSER_THAN")){
				    		 if (slrDeal.getApplyLesserAmount() != null && slrDeal.getApplyLesserAmount() != null){
					    	 		Double lesserAmount = Double.parseDouble(slrDeal.getApplyLesserAmount().toString());
					    	 		if (lesserAmount != 0.00){
						    	 		if ( price < lesserAmount){
						    	 			pricedisamount = dealAmount;
						    	 		}
						    	 		else{
						    	 			pricedisamount = 0.00;
						    	 		}
					    	 		}else if (price != 0.00 && price > dealAmount && dealAmount !=0.00){
										 pricedisamount = dealAmount;
										 logger.info("Deal all products Amount Normal===============>"+pricedisamount);
									 }else{
										 pricedisamount = 0.00;
										 logger.info("Deal all products Amount Normal===============>"+pricedisamount);
									 }
				    		 	}else if (price != 0.00 && price > dealAmount && dealAmount !=0.00){
									 pricedisamount = dealAmount;
									 logger.info("Deal all products Amount Normal===============>"+pricedisamount);
								 }else{
									 pricedisamount = 0.00;
									 logger.info("Deal all products Amount Normal===============>"+pricedisamount);
								 }
			    	 	}
			    		if (slrDeal.getStartDate() != null && slrDeal.getEndDate() != null){
					    	 logger.info("clinic next Date==========0"+ slrDeal.getStartDate() +slrDeal.getEndDate() +clinic.getNexttimeslot());
					    	 if ((clinic.getNexttimeslot().compareTo(slrDeal.getStartDate()) >= 0 && clinic.getNexttimeslot().compareTo(slrDeal.getEndDate()) <= 0)||(slrDeal.getStartDate() == clinic.getNexttimeslot() && slrDeal.getEndDate() == clinic.getNexttimeslot())){
					    		 //serviceDealList.add(deal);
					    		 pricefinaldisamount +=pricedisamount;
					    		 logger.info("Comapare date if inside ========"+slrDeal.getStartDate() + "End date ===="+slrDeal.getEndDate());
					    		 logger.info("Finral if start and end date ========"+slrDeal.getStartDate()+ "Clinic next time sloft compare==    "+clinic.getNexttimeslot().compareTo(slrDeal.getStartDate())+"Clinic next tiem end date===="+clinic.getNexttimeslot().compareTo(slrDeal.getEndDate()));
					    	 }else{
					    		 pricefinaldisamount +=0.00;
					    		 logger.info("Comapare date else inside ========"+slrDeal.getStartDate() + "End date ===="+slrDeal.getEndDate());
					    		 logger.info("Finral else start and end date ========"+slrDeal.getStartDate()+ "Clinic next time sloft compare==    "+clinic.getNexttimeslot().compareTo(slrDeal.getStartDate())+"Clinic next tiem end date===="+clinic.getNexttimeslot().compareTo(slrDeal.getEndDate()));
					    	 }
					     } else{
					    	 //serviceDealList.add(deal);
					    	 pricefinaldisamount +=pricedisamount;
					    	 logger.info("Comapare date else Outside ========"+slrDeal.getStartDate() + "End date ===="+slrDeal.getEndDate());
					    	 logger.info("Finral else condition ========"+slrDeal.getStartDate() + "End date ===="+slrDeal.getEndDate() + "Total price ==="+pricefinaldisamount +"Price amdount----"+pricedisamount);
					     }
				 }else{
					 pricefinaldisamount +=0.00;
				 }
			
		    
				 
				logger.info("Amount price of Deals==================>"+ dealAmount.toString() + "total discount>>>"+ pricefinaldisamount);
			     //pricefinaldisamount +=pricedisamount;
			     
		}
			/* Discount Amount End*/
			
			logger.info("Final discount ====== pricefinaldisamount"+ pricefinaldisamount);
			        Double totalDiscountcombine = pricefinaldisamount;
					clinic.setTestDiscount(totalDiscountcombine.toString());
			   

		
        Collections.sort(serviceDealList, new ServiceDeals());
        dealTab.setServiceDealList(serviceDealList);
        clinic.setDealsTab(dealTab);
    }

   
    }

	private static void processTimeSlots(List<Appointment> timeSlotList,Clinic clinic) {

        List<Slot> timingSlot = new ArrayList<Slot>();
        Slot slot = null;
        for(Appointment appointment:timeSlotList) {
            slot = new Slot();
            slot.setSlotTime(appointment.getSlotTime());
            slot.setActive(appointment.isActive());
            timingSlot.add(slot);
        }
        Collections.sort(timingSlot, new Slot());
		clinic.setTimingSlot(timeSlotList);
		logger.info("timeSlotList :::::::::::"+ timeSlotList);
    }



    private static void processDetailsTab(List<SlrAmenities> amenitiesList,Clinic clinic,SlrSeller seller, DetailTab detailTab) {

        Facility facility = null;
        List<Facility> facilityList = new ArrayList<Facility>();
        for(SlrAmenities amenity:amenitiesList) {
            facility = new Facility();
            facility.setFacilityHeading(amenity.getName());
            facility.setDocuments(IHMListHelper.getAmenitiesList(amenity.getFeatures()));
            facilityList.add(facility);
        }
        Collections.sort(facilityList, new Facility());

        detailTab.setClinicFacilites(facilityList);
        detailTab.setClinicDescription(seller.getDescription());
        clinic.setDetailTab(detailTab);
    }

	private static void processProductReviews(List<SlrSellerReview> customerReviews,Clinic clinic) {
        ReviewsTab reviewsTab = clinic.getReviewsTab();
        List<CustomerReviews> customerReviewsList = new ArrayList<CustomerReviews>();
        CustomerReviews custReview ;
        float totalReviewsScore = 0;

		for(SlrSellerReview review:customerReviews) {

            custReview = new CustomerReviews();
            custReview.setCustomerReview(review.getReviews());
            custReview.setCustomerStarRating(review.getStarRating());
            totalReviewsScore += review.getStarRating();
            float percentProgress = (review.getStarRating()/10)*100;
            int ceildValue = (int) Math.ceil(percentProgress);
            custReview.setPercentProgress(ceildValue);
            customerReviewsList.add(custReview);

        }

        float averageStarRating = totalReviewsScore / customerReviews.size();
        float percentageProgress = (averageStarRating / 10)*100;
        int percent = (int)Math.ceil(percentageProgress);
        Collections.sort(customerReviewsList, new CustomerReviews());
        reviewsTab.setCustomerReviewsList(customerReviewsList);
        clinic.setScoredRating((int)Math.ceil(averageStarRating));
        clinic.setPercentageProgress(percent);
        clinic.setTotalCustomerRated(customerReviews.size()+"");
		
        clinic.setRatingImage(getServiceRatingImage((int)Math.ceil(averageStarRating)));

        clinic.setReviewsTab(reviewsTab);

		logger.info("Average Star Rating For "+clinic.getClinicName() +" = "+ (int)Math.ceil(averageStarRating));
        logger.info("Progress Bar Percentage For  "+clinic.getPercentageProgress() +" = "+ percentageProgress);
    }

    public static String getServiceRatingImage(int averageStarRating) {
        String starRatingImage = "";
        if(averageStarRating >= 9) {
            starRatingImage =  IHMConstants.SUPERIOR;
        } else if(averageStarRating >= 8 && averageStarRating < 9) {
            starRatingImage =  IHMConstants.ABOVE_AVERAGE;
        } else if(averageStarRating >= 6 && averageStarRating < 8) {
            starRatingImage =  IHMConstants.AVERAGE;
        } else if(averageStarRating >= 4 && averageStarRating < 6 ) {
            starRatingImage =  IHMConstants.BEL0W_AVERAGE;
        } else if(averageStarRating >= 1 && averageStarRating < 4 ) {
            starRatingImage =  IHMConstants.POOR;
        } else if(averageStarRating == 0 ) {
            starRatingImage =  IHMConstants.UNRATED;
        }
        return starRatingImage;
    }

	private static void processAddress(SlrAddress address,Clinic clinic,String clientLocation) {
        ClinicAddress clinicAddress = clinic.getClinicAddress();
        String state = "";
        String country = "";

        clinicAddress.setState(address.getState());
        clinicAddress.setCountry(address.getCountry());
        clinicAddress.setCity(address.getCity());
        clinicAddress.setZipCode(address.getZip());
        clinicAddress.setLattitude(address.getLatitude()!= null ? address.getLatitude():0);
        clinicAddress.setLongitude(address.getLongitude()!= null ? address.getLongitude():0);
		if(address.getLatitude()!=null&&address.getLongitude()!=null&&clientLocation!=null){
			String clinicLocation = address.getLatitude()+","+address.getLongitude();
			
			String distance = distanceCalculation(clientLocation,clinicLocation);
			clinic.setDistanceDifference(distance);
		}
        clinicAddress.setFullAddress(address.getAddress1()+", "+state + ", " + address.getCity() + " "+ address.getZip());
        clinic.setClinicAddress(clinicAddress);
    }

    public static CompareClinic convertToCompareObject(Clinic clinicParam) {
        CompareClinic compareObject = new CompareClinic();
        //compareObject.setClinicAppointmentContactNo(clinicParam.getAppointmentNo());
//		compareObject.setClinicDistance(clinicDistance);
        compareObject.setId(clinicParam.getId());
        compareObject.setClinicFullName(clinicParam.getClinicName());
        compareObject.setClinicRating(clinicParam.getTotalCustomerRated());
        compareObject.setClinicRatingImage(clinicParam.getRatingImage());
		compareObject.setTimeSlot(clinicParam.getTimingSlot());
//		compareObject.setHeading(clinicParam.)
        compareObject.setTestPrice(clinicParam.getTestPrice());
        if(clinicParam.getDetailTab().getClinicFacilites().size() > 0){
            for(Facility facility:clinicParam.getDetailTab().getClinicFacilites()){
                if(facility.getFacilityHeading().equals(IHMConstants.REPORT_DELIVERY)){
                    List<FacilityDocument> documents = facility.getDocuments();
                    compareObject.setReportDelivery(documents);
                }else if(facility.getFacilityHeading().equals(IHMConstants.DOORSTEP_SAMPLE_COLL)){
                    List<FacilityDocument> documents = facility.getDocuments();
                    compareObject.setDoorStepCollection(documents);
                }else if(facility.getFacilityHeading().equals(IHMConstants.CUSTOMER_WAITING_LOUNGE)){
                    List<FacilityDocument> documents = facility.getDocuments();
                    compareObject.setCustomerWaitingLounge(documents);
                }else if(facility.getFacilityHeading().equals(IHMConstants.ENTERTAINMENT)){
                    List<FacilityDocument> documents = facility.getDocuments();
                    compareObject.setEntertainment(documents);
                }else if(facility.getFacilityHeading().equals(IHMConstants.CAR_PARKING)){
                    List<FacilityDocument> documents = facility.getDocuments();
                    compareObject.setCarParking(documents);
                }else if(facility.getFacilityHeading().equals(IHMConstants.TWO_WHEELER_PARKING)){
                    List<FacilityDocument> documents = facility.getDocuments();
                    compareObject.setTwoWheelerParking(documents);
                }
            }

        }

        return compareObject;
    }
	public static String distanceCalculation(String clientLocation,String clinicLocation){
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=originCoordinates&destinations=destinationCoordinates&sensor=false";
	    
		String sourceSubstring = url.replaceAll("originCoordinates",URLEncoder.encode(clientLocation.toString()));
		String destSubstring = sourceSubstring.replaceAll(
		  "destinationCoordinates", URLEncoder.encode(clinicLocation.toString()));
		logger.info(destSubstring);
		BufferedReader in = null;
		StringBuilder builder = new StringBuilder();
		try {
		 in = new BufferedReader(new InputStreamReader(
		   new URL(destSubstring).openStream()));
		 for (String line = null; (line = in.readLine()) != null;) {
		  builder.append(line).append("\n");
		 }
		} catch (IOException e) {
		 // handle e
		}
		JSONParser parser=new JSONParser();
		JSONObject json;
		String distance = "0";
		try {
			logger.info("JSON========="+builder.toString());
			json = (JSONObject) parser.parse(builder.toString());
			if(json.get("status").toString().equalsIgnoreCase("ok")){
				JSONArray rows = (JSONArray) json.get("rows");
				JSONObject elements = (JSONObject) rows.get(0);
				JSONArray elemtnsArray = (JSONArray) elements.get("elements");
				JSONObject elemtsObjects = (JSONObject) elemtnsArray.get(0);
				JSONObject distanceOBject = (JSONObject) elemtsObjects.get("distance");
				
				if(distanceOBject!=null)
				distance = distanceOBject.get("text").toString();
				logger.info("DISTANCE==========++++++++++++++"+distance);
			}
			return distance;
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return distance;
		
	}

}
