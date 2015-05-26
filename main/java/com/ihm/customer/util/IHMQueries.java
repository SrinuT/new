package com.ihm.customer.util;

import java.util.Date;

/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/

public class IHMQueries {
	
	public static final String SELECT_ALL_TEST = "SELECT product.name FROM SlrProduct As product WHERE product.name like '%<CODE></CODE>%' ORDER BY product.name ASC";
	
	public static final String SELECT_APPOINTMENT = "SELECT SAS.id AS slotIdPK , SAS.slrSeller.id AS clinicIdFK,SAS.slrAppointment.id AS appointmentIdFK,SAS.active AS STATUS, AP.duration AS Duration,AP.serviceDay AS ServiceDay,AP.startTime AS Start_DATE ,AP.endTime AS END_TIME,SAS.slotTime AS SLOT " 
													+" FROM SlrAppointment AP LEFT JOIN AP.slrAppointmentSlotses SAS "
													+" WHERE 1 = 1 AND AP.startTime BETWEEN <START_DATE> AND <END_DATE> "
													+" AND SAS.slrSeller.id = <SELLER_ID> ORDER BY AP.startTime , SAS.slotTime  ASC";
	
	public static final String SELECT_APPOINTMENT_SLOT ="SELECT SAS.id AS slotIdPK , SAS.slrSeller.id AS clinicIdFK,SAS.slrAppointment.id AS appointmentIdFK,SAS.active AS STATUS, AP.duration AS Duration,AP.serviceDay AS ServiceDay,AP.startTime AS Start_DATE ,AP.endTime AS END_TIME,SAS.slotTime AS SLOT " 
			+" FROM SlrAppointment AP LEFT JOIN AP.slrAppointmentSlotses SAS "
			+" WHERE 1 = 1 AND AP.startTime BETWEEN <START_DATE> AND <END_DATE> "
			+" AND SAS.slrSeller.id = <SELLER_ID> ORDER BY AP.startTime , SAS.slotTime  ASC";
	
	public static final String SELECT_APPOINTMENT_SLOT_DATE ="SELECT distinct AP.startTime FROM SlrAppointment AS AP WHERE AP.startTime >= CURDATE() AND AP.slrSeller.id =<SELLER_ID> ORDER BY AP.startTime ASC LIMIT 0,1";
			 //"SELECT AP.startTime FROM SlrAppointment AS AP WHERE AP.startTime >= CURDATE() AND AP.slrSeller.id =<SELLER_ID> ORDER BY AP.startTime ASC";
			//"SELECT distinct AP.startTime FROM SlrAppointment AS AP WHERE AP.startTime > NOW() AND AP.slrSeller.id =<SELLER_ID> ORDER BY AP.startTime ASC LIMIT 0,1";
		   //"SELECT * FROM slr_appointment WHERE start_time  ORDER BY start_time LIMIT 1;" 
	
}
