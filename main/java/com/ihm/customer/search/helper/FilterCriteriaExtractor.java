package com.ihm.customer.search.helper;

import com.ihm.customer.frontend.leftnav.HospitalAmenities;
import com.ihm.customer.frontend.leftnav.LeftNavModel;
import com.ihm.customer.frontend.leftnav.StarRating;
import com.ihm.frontend.search.dto.Clinic;
import com.ihm.frontend.search.dto.Facility;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sardar Waqas Ahmed
 * @ Date 09-Dec-2014
 *  1.0
 */
public abstract class FilterCriteriaExtractor {

	private static Logger logger = Logger.getLogger(FilterCriteriaExtractor.class);
	
	public static List<Clinic> extractByPriceRange(double startPrice,double endPrice,List<Clinic> clinicListDB) {
		List<Clinic> priceFilteredList = new ArrayList<Clinic>();
		try {

			
			for(Clinic clinic:clinicListDB){
				double testPrice = Double.parseDouble(clinic.getTestPrice().isEmpty()?"0.0":clinic.getTestPrice());
				if(isValueInBetween(testPrice,startPrice,endPrice)){
					priceFilteredList.add(clinic);
				}
			}
			
			
		} catch (NumberFormatException e) {
			logger.info("[FilterCriteriaProcessor NumberFormatException Occured] "+e.getMessage());
			throw e;
		} catch (Exception e) {
			throw e;
		}
		
		return priceFilteredList;
	}

	public static List<Clinic> extractByDistanceRange(double startDistance,double endDistance, List<Clinic> clinicListDB) {
		List<Clinic> distanceFilteredList = new ArrayList<Clinic>();
		try {




		} catch (NumberFormatException e) {
			logger.info("[FilterCriteriaProcessor Exception Occured] "+e.getMessage());
			throw e;
		} catch (Exception e) {
			throw e;
		}
		
		return distanceFilteredList;
	}
	

	public static List<Clinic> extractByStarRating(List<StarRating> starRatingList, List<Clinic> clinicListDB) {
		List<Clinic> starRatingFilteredList = new ArrayList<Clinic>();
		try {
			for (Clinic clinic : clinicListDB) {
				for (StarRating star : starRatingList) {
					if (star.isSelected()) {
						int clinicRating = clinic.getScoredRating();
						String clinicImage = SearchHelper.getServiceRatingImage(clinicRating);
						String starRatingimage = star.getIconPath();
						if (starRatingimage.contains(clinicImage)) {
							// This clinic has selected filtered star rating.
							// Add in List
							starRatingFilteredList.add(clinic);
							break;  // Ignore others star in list. Bcz logically 1 clinic has single star rating
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return starRatingFilteredList;
	
	}
	
	public static List<Clinic> extractByHospitalAmenities(List<HospitalAmenities> checkedAmenitiesList, List<Clinic> clinicListDB) {
		int counter = 0;
		List<Clinic> hospitalAmenitiesFilteredList = new ArrayList<Clinic>();
		List<Facility> clinicFacilitesDB = null;
		for(Clinic clinic:clinicListDB) {
			counter++;
			logger.info(">>>>>>> Iterating Clinic "+counter);
			clinicFacilitesDB = IHMListHelper.extractAmenityByClinic(clinic);
			if(IHMListHelper.isHospitalAmenityApplicable(checkedAmenitiesList, clinicFacilitesDB)) {
				logger.info("::::::: Clinic Fullfill Filter Criteria . Going To Add In View List ... :::::::");
				hospitalAmenitiesFilteredList.add(clinic);
			}	
		}
//		for(HospitalAmenities parent:checkedAmenitiesList) {
//			logger.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
//			logger.info(":::::	Filtered Critera : "+parent.getHeading()+"            :::::");
//			logger.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
//			for(Document child:parent.getDocumnetList()){
//				
//				if(child.isStatus()) {
//					logger.info("			  "+child.getName()+"-"+child.isStatus()+"  			");
//					for(Clinic clinic:clinicListDB) {
//						counter++;
//						logger.info(">>>>>>> Iterating Clinic "+counter);
//						for(Facility facility:clinic.getDetailTab().getClinicFacilites()) {
//							
//							if(facility.getFacilityHeading().equalsIgnoreCase(parent.getHeading())) {
//								logger.info(">>>>> Client Selected Filter "+ parent.getHeading() +" Match With Clinic : "+facility.getFacilityHeading()+" <<<<");
//								logger.info(">>>>> Amenity Checking ... ");
//								for(FacilityDocument dbDocument:facility.getDocuments()) {
//									if(dbDocument.getStatus().equalsIgnoreCase("true") && dbDocument.getName().equalsIgnoreCase(child.getName())){
//										logger.info("::::::::: <<< Matched With DB Return Val >>> Filter Criteria Select By Client ....... "+parent.getHeading()+" ...... CheckBox Value ----> "+child.getName()+" CheckBox Status ----> "+child.isStatus()+" :::::::::");
//										logger.info(">>>>>>> Going To Add Clinic In Filtered List ");
//										hospitalAmenitiesFilteredList.add(clinic);
//									}else{
//										logger.info("::::::::: <<< Not Matched With DB Return Val >>> Filter Criteria Select By Client ....... "+parent.getHeading()+" ...... CheckBox Value ----> "+dbDocument.getName()+" CheckBox Status ----> "+dbDocument.getStatus()+" :::::::::");
//									}
//								}
//							}else{
//								logger.info("!!!!!! Client Selected Filter "+ parent.getHeading() +" Didnot Match With Clinic : "+facility.getFacilityHeading()+" !!!!!!");
//							}
//						}
//					}					
//				}else{
//					logger.info("			  "+child.getName()+"-"+child.isStatus()+"  			");
//				}
//			}
//		}
		logger.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		
		return hospitalAmenitiesFilteredList;  
	}
	

	public static boolean isValueInBetween(double value,double start,double end) {
		
		if(value >= start && value <= end)
			return true;
		else
			return false;
	}
	
	
	
	/*
	 * 
	 * Different Scenarios Handled
	 * 
	 */
	

	public static List<Clinic> prRngScenarios(LeftNavModel leftNavModel,List<Clinic> clinicsListDB) {
	
		List<Clinic> clinicsViewList = null;
		
		double startPrice = leftNavModel.getPriceStart();
		double endPrice = leftNavModel.getPriceEnd();
		logger.info("Backing Bean Value On Price Slider >>> Start Price >>> "+startPrice+" End Price >>> "+endPrice);
		
		clinicsViewList = extractByPriceRange(startPrice, endPrice,clinicsListDB);
		
		return clinicsViewList;
	}
	
	public static List<Clinic> dstRngScenarios(LeftNavModel leftNavModel,List<Clinic> clinicsListDB) {
		
		List<Clinic> clinicsViewList = null;

		double startDistance = leftNavModel.getDistanceStart();
		double endDistance = leftNavModel.getDistanceEnd();
		logger.info("Backing Bean Value On Distance Slider >>> Start Distance >>> "+startDistance+" End Distance >>> "+endDistance);
		
		clinicsViewList = extractByDistanceRange(startDistance, endDistance,clinicsListDB);
		
		return clinicsViewList;
	}

	public static List<Clinic> strRtgScenarios(LeftNavModel leftNavModel,List<Clinic> clinicsListDB) {
		
		List<Clinic> clinicsViewList = null;
		
		clinicsViewList = extractByStarRating(leftNavModel.getStarRatingList(),clinicsListDB);
		
		return clinicsViewList;
	}

	public static List<Clinic> hosAmnScenarios(LeftNavModel leftNavModel,List<Clinic> clinicsListDB) {
		
		List<Clinic> clinicsViewList = null;
		List<HospitalAmenities> checkedAmenities = IHMListHelper.getSelectedAmenitiesByUser(leftNavModel.getHosAmenitiesList());
		clinicsViewList = extractByHospitalAmenities(checkedAmenities,clinicsListDB);
		
		return clinicsViewList;
	}
}

