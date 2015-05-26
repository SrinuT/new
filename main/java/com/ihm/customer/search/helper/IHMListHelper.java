package com.ihm.customer.search.helper;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ihm.customer.frontend.leftnav.Document;
import com.ihm.customer.frontend.leftnav.HospitalAmenities;
import com.ihm.customer.util.IHMConstants;
import com.ihm.frontend.search.dto.Clinic;
import com.ihm.frontend.search.dto.Facility;
import com.ihm.frontend.search.dto.FacilityDocument;

public class IHMListHelper {
	
	private static Logger logger = Logger.getLogger(IHMListHelper.class);
	
	public static List<FacilityDocument> getAmenitiesList(String features) {
		
		List<FacilityDocument> docList = new ArrayList<FacilityDocument>();
		FacilityDocument doc = null;
		String amenitesString[] = {};
		if(null != features && !features.isEmpty() && features.contains("|")) {
			amenitesString = features.split("\\|");	
			if(null != amenitesString && amenitesString.length > 0) {
				for(String key:amenitesString){
					key = key.trim();
					String status [] = key.split("-");
					String name = key.substring(0, key.length()-2);
					if(key.indexOf("-") != -1) {
						doc = new FacilityDocument(status[1].equalsIgnoreCase("y")?"true":"false",name , getFacilityImage(name));
						docList.add(doc);
					}
					if(key.equalsIgnoreCase("Available-Y") || key.equalsIgnoreCase("Available-N")){  // [For Doorstep Sample Collections] Only add 1. Ignore the other.
						break;
					}
				}
				
			}
		}else{
			logger.info(":: 1 Features Found For Amenities ::");
			String name = features.substring(0, features.length()-2);
			doc = new FacilityDocument(features.split("-")[1].equalsIgnoreCase("y")?"true":"false",name , getFacilityImage(name));
			docList.add(doc);
		}
		
		return docList;
	}
	
	public static String getFacilityImage(String facilityName) {
		
		String imagePath = "";
		if(facilityName.equalsIgnoreCase(IHMConstants.EMAIL)){
			imagePath = IHMConstants.EMAIL_ICON;
		}else if(facilityName.equalsIgnoreCase(IHMConstants.HOME)){
			imagePath = IHMConstants.HOME_ICON;
		}else if(facilityName.equalsIgnoreCase(IHMConstants.IHM_PHR_INTEGRATION)){
			imagePath = IHMConstants.IHM_PHR_INTEGRATION_ICON;
		}else if(facilityName.equalsIgnoreCase(IHMConstants.IHM_PHR_INTEGRATION)){
			imagePath = IHMConstants.IHM_PHR_INTEGRATION_ICON;
		}else if(facilityName.equalsIgnoreCase(IHMConstants.AC) || facilityName.equalsIgnoreCase(IHMConstants.NONAC)){
			imagePath = IHMConstants.AC_ICON;
		}else if(facilityName.equalsIgnoreCase(IHMConstants.WIFI)){
			imagePath = IHMConstants.WIFI_ICON;
		}else if(facilityName.equalsIgnoreCase(IHMConstants.AVAILABLE) || facilityName.equalsIgnoreCase(IHMConstants.NOT_AVAILABLE)){
			imagePath = IHMConstants.AVAILABLE_ICON;
		}else if(facilityName.equalsIgnoreCase(IHMConstants.TELEVISION)){
			imagePath = IHMConstants.TELEVISION_ICON;
		}else if(facilityName.equalsIgnoreCase(IHMConstants.NEWSPAPER)){
			imagePath = IHMConstants.NEWSPAPER_ICON;
		}else if(facilityName.equalsIgnoreCase(IHMConstants.MAGAZINE)){
			imagePath = IHMConstants.MAGAZINE_ICON;
		}else if(facilityName.equalsIgnoreCase(IHMConstants.SELF)){
			imagePath = IHMConstants.SELF_ICON;
		}else if(facilityName.equalsIgnoreCase(IHMConstants.SHARED)){
			imagePath = IHMConstants.SHARED_ICON;
		}else {
			imagePath = IHMConstants.NOIMAGE;
		}
		
		return imagePath;
	}
	
	public static List<Document> getLeftNavAmenitiesList(String features) {
		
		List<Document> docList = new ArrayList<Document>();
		Document doc = null;
//		boolean flag = false;
		String amenitesString[] = {};
//		if(features.equals(IHMConstants.DOORSTEP_SAMPLE_COLL_FEATURES))
//			flag = true;
		if(null != features && !features.isEmpty() && features.contains("|")) {
			amenitesString = features.split("\\|");	
			if(null != amenitesString && amenitesString.length > 0) {
				for(String key:amenitesString){
					key = key.trim();
					doc = new Document(false,key , getFacilityImage(key));
					docList.add(doc);
				}
				
			}
		}else{
			logger.info(":: 1 Amenitiy Found ::");
			doc = new Document(false,features , getFacilityImage(features));
			docList.add(doc);
		}
//		if(flag){
//			doc = new Document(false,"Not Available" , getFacilityImage(features));
//			docList.add(doc);
//		}
		return docList;
	}
	
	public static List<HospitalAmenities> getSelectedAmenitiesByUser(List<HospitalAmenities> allLeftNavAmenities){
		
		List<HospitalAmenities> checkedAmenities = new ArrayList<HospitalAmenities>();
		HospitalAmenities object;
		for(HospitalAmenities hosAmenity:allLeftNavAmenities) {
			object = new HospitalAmenities();
			object.setHeading(hosAmenity.getHeading());
			for(Document doc:hosAmenity.getDocumnetList()){
				if(doc.isStatus()) {
					if(doc.getName().equalsIgnoreCase(IHMConstants.NONAC)) { // Not handled in DB Level
						doc.setName(IHMConstants.AC);
						doc.setStatus(false);
						object.getDocumnetList().add(doc);
					}else{
						object.getDocumnetList().add(doc);
					}
				}
			}
			if(object.getDocumnetList().size() > 0) {
				checkedAmenities.add(object);
			}
		}
		logger.info(">>> Total Hospital Amenities Checked On [LeftNavFilter] By User Is "+ checkedAmenities.size()+" <<<");
		return checkedAmenities;
	}
	
	public static List<Facility> extractAmenityByClinic(Clinic clinic) {
		return clinic.getDetailTab().getClinicFacilites()!=null?clinic.getDetailTab().getClinicFacilites():new ArrayList<Facility>();
	}
	
	public static boolean isHospitalAmenityApplicable(List<HospitalAmenities> checkedAmenitiesList,List<Facility> clinicFacilitesDB) {
		
		boolean childAmenityApplicable = true;
		boolean headingApplicable = false;
		for (HospitalAmenities checkedAmenity : checkedAmenitiesList) {
			if (childAmenityApplicable) {
				for (Facility facilityDb : clinicFacilitesDB) {
					if(childAmenityApplicable) {
						if (checkedAmenity.getHeading().equalsIgnoreCase(facilityDb.getFacilityHeading())) {
							headingApplicable = true;
							for (Document docUI : checkedAmenity.getDocumnetList()) {
								if(childAmenityApplicable) {
									for (FacilityDocument docDB : facilityDb.getDocuments()) {
											if (docUI.getName().equalsIgnoreCase(docDB.getName()) && docUI.isStatus() && docDB.getStatus().equalsIgnoreCase("false")) { // If any single amenity is not true then this is not success criteria. Checking for false because client selected criteria will always true
												childAmenityApplicable = false;
												break;
											}else if(docUI.getName().equalsIgnoreCase(docDB.getName()) && !docUI.isStatus()  && docDB.getStatus().equalsIgnoreCase("true")){
												childAmenityApplicable = false;
												break;
											}
										}
								}else{
									break;
								}
							}
						}
					}else{
						break;
					}
				}
			}
		}
		if(!headingApplicable){ // This means this particular clinic has 1 Amenity heading missing and that Amenity is not available from start so dont 
								// in clinic view list
			childAmenityApplicable = false;
		}
		return childAmenityApplicable;
	}
	
	public static boolean isDefaultSearchResult(boolean priceRangeClick,boolean distanceRangeClick, boolean starRatingClick,boolean hospitalAmenitiesClick) {
		return (!priceRangeClick && !distanceRangeClick && !starRatingClick && !hospitalAmenitiesClick)?true:false;
	}
}
