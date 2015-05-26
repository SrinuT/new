package com.ihm.customer.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * The DateUtil class holds utility operations related to datetime
 * 
 * @author Sardar Waqas Ahmed
 * @since Mar 8, 2012
 * 
 */
@SuppressWarnings("all")
public class DateUtil {

	private DateUtil(){
		
	}
	
	/**
	 * Default date pattern for formating
	 */
	static String datePattern = "yyyy-MM-dd HH:mm:ss";
	/**
	 * Default output format for oracle to_date function
	 */
	static String oracleOutputDatePattern = "mm/dd/yyyy HH24:MI:SS";
	
	static final private SimpleDateFormat standardFormat = new SimpleDateFormat("MM/dd/yyyy k:mm:ss");
    static final private SimpleDateFormat ukFormat = new SimpleDateFormat("yyyy-MM-dd");
    static final private SimpleDateFormat usFormat = new SimpleDateFormat("MM/dd/yyyy");
    static final private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm z");
    static final private SimpleDateFormat hoursOnly = new SimpleDateFormat("hh");
    static final private SimpleDateFormat minOnly = new SimpleDateFormat("mm");


    /**
     * this is for opening houers
     */
    static final private String ALL_DAY = "8:00 AM - 8:00 PM" ;
    static final private String MORNING = "8:00 AM - 11:00 AM" ;
    static final private String AFTERNOON = "1:00 PM - 3:00 PM" ;
    static final private String EVENING = "3:00 PM - 6:00 PM" ;
    static final private String NIGHT = "8:00 PM - 10:00 PM" ;


    static {
        standardFormat.setLenient(false);
        ukFormat.setLenient(false);
        usFormat.setLenient(false);
    }

	/**
	 * 
	 * The formatDate() method
	 * 
	 * @param date
	 * @param datePattern
	 * @return
	 */
	public static String formatDate(java.util.Date date, String datePattern) {
		SimpleDateFormat customFormat = new SimpleDateFormat(datePattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * 
	 * The formatDate() method formats the given according pattern "MM/dd/yyyy k:mm:ss"
	 * 
	 * @param date the given java.util.Date instance to be formated
	 * @return returns the formated date in string form
	 */
	public static String formatDate(java.util.Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(datePattern);
		customFormat.setLenient(false);
		return (null == date?"":customFormat.format(date));
	}

	/**
	 * 
	 * The formatDate() method formats the current date according pattern "MM/dd/yyyy k:mm:ss"
	 * 
	 * @return returns the formated date in string form
	 */
	public static String getFormatedDate() {
		SimpleDateFormat customFormat = new SimpleDateFormat(datePattern);
		customFormat.setLenient(false);
		return customFormat.format(new Date());
	}

	/**
	 * 
	 * The getOracleToDateFunction() method returns the to_date function in the
	 * default sanmar format. For example, to_date('03/08/2010 11:07:07',
	 * 'mm/dd/yyyy HH24:MI:SS')
	 * 
	 * @param date
	 * @return
	 */
	public static String getOracleToDateFunction(java.util.Date date) {
		String toDate = "to_date('" + formatDate(date, datePattern) + "', '" + oracleOutputDatePattern + "')";
		return toDate;
	}

	/**
	 * 
	 * The getOracleToDateFunction() method returns the to_date oracle function
	 * with the specfied format
	 * 
	 * @param date the given java.util.Date instance to be formatted
	 * @param datePattern the given pattern for formatting
	 * @return
	 */
	public static String getOracleToDateFunction(java.util.Date date, String datePattern) {
		String toDate = "to_date('" + formatDate(date, datePattern) + "', '" + datePattern + "')";
		return toDate;
	}

	/**
	 * 
	 * The getTimestamp() method converts java.util.Date to java.sql.Timestamp
	 *
	 * @param date the given java.util.Date instance for converting to java.sql.Timestamp
	 * @return returns java.sql.Timestamp after converting the given java.util.Date instance 
	 */
	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 
	 * The getTimestamp() method converts the current java.util.Date to java.sql.Timestamp
	 *
	 * @return returns java.sql.Timestamp after converting the current java.util.Date instance 
	 */
	public static Timestamp getTimestamp() {
		return getTimestamp(new Date());
	}
	
	public static Date getDateTimeFromMillis(long millis){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		
		return calendar.getTime();
	}
	
	public static Timestamp getTimestamp(long millis) {
		return getTimestamp(getDateTimeFromMillis(millis));
	}
	
	public static String getCurrentDate() {		  
		Date date = new Date();
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("DD");
		return sdf.format(date);
	}
	
	public static String getCurrentMonth() {		  
		Date date = new Date();
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("MM");
		return sdf.format(date);
	}
	
	public static String getCurrentYear() {		  
		Date date = new Date();
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}
	/**
	 * 
	 * The monthsBetween() method returns the months between two dates
	 * 
	 * @param dateOne
	 *            specifies first date
	 * @param dateTwo
	 *            specifies second date
	 * @return returns the number of months between two dates
	 */
	public static int monthsBetween(Date dateOne, Date dateTwo) {
		Calendar presentCalendar = Calendar.getInstance();
		Calendar pastCalendar = Calendar.getInstance();

		if (dateOne.after(dateTwo)) {
			presentCalendar.setTime(dateOne);
			pastCalendar.setTime(dateTwo);
		} else {
			presentCalendar.setTime(dateTwo);
			pastCalendar.setTime(dateOne);
		}

		int yearsBetween = presentCalendar.get(Calendar.YEAR) - pastCalendar.get(Calendar.YEAR);
		int monthsBetween = presentCalendar.get(Calendar.MONTH) - pastCalendar.get(Calendar.MONTH);

		return (yearsBetween * 12) + monthsBetween;
	}
	
	/**
	 * The lessThan() method compares the first date argument with second date.
	 * It returns true if the first date is less than the second date. Otherwise it
	 * returns false.
	 * 
	 * @param firstDate
	 *            Specifies the first date argument.
	 * @param secondDate
	 *            Specifies the second date argument.
	 * @return Returns true if the first date is less than the second date.
	 *         Otherwise it returns false.
	 */
	public static boolean lessThan(Date firstDate, Date secondDate){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(firstDate);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(secondDate);
		
		return calendar1.before(calendar2);
	}
	
	/**
	 * The greaterThan() method compares the first date argument with second date.
	 * It returns true if the first date is greater than the second date. Otherwise it
	 * returns false.
	 * 
	 * @param firstDate
	 *            Specifies the first date argument.
	 * @param secondDate
	 *            Specifies the second date argument.
	 * @return Returns true if the first date is greater than the second date.
	 *         Otherwise it returns false.
	 */
	public static boolean greaterThan(Date firstDate, Date secondDate){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(firstDate);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(secondDate);
		
		return calendar1.after(calendar2);
	}
	
	/**
	 * The equals() method compares the first date argument with second date.
	 * It true if the first date is equal to the second date. Otherwise it
	 * returns false.
	 * 
	 * @param firstDate
	 *            Specifies the first date argument.
	 * @param secondDate
	 *            Specifies the second date argument.
	 * @return Returns true if the first date is equal to the second date.
	 *         Otherwise it returns false.
	 */
	public static boolean equals(Date firstDate, Date secondDate){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(firstDate);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(secondDate);
		
		return calendar1.equals(calendar2);
	}
	
	public static boolean isDateBetweenRange(Date date, Date fromDate, Date toDate){
		
		if(equals(date, fromDate) || greaterThan(date, fromDate) && (equals(date, toDate) || lessThan(date, toDate))){
			return true;
		}
		
		return false;
	}
	
	 /**
     * The getDateFromDateAndTime() method creates a date object from the given date and time.
     * @param date Specifies the date object.
     * @param time Specifies the time string containing hour, minute and second values concatenated by full colon .eg. 11:50:40.
     * @param isAM Specifies whether the given time string is in AM or PM period.
     * @return
     */
    public static Date getDateFromDateAndTime(Date date, String time, boolean isAM){
            Calendar dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(date);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
            calendar.set(Calendar.DATE, dateCalendar.get(Calendar.DATE));
            
            String[] timeParts = time.split(":");
            calendar.set(Calendar.HOUR, Integer.parseInt(timeParts[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
            calendar.set(Calendar.SECOND, Integer.parseInt(timeParts[2]));
            if(isAM){
                    calendar.set(Calendar.AM, 0);
            }else{
                    calendar.set(Calendar.PM, 1);
            }
            
            return calendar.getTime();
    }
    
    /**
     * The getDateFromDateHourAndMinue() method creates a date object from the given date and time.
     * @param date Specifies the date object.
     * @param time Specifies the time string containing hour and minute values concatenated by full colon .eg. 11:50.
     * @param isAM Specifies whether the given time string is in AM or PM period.
     * @return
     */
    public static Date getDateFromDateHourAndMinue(Date date, String time, boolean isAM){
            Calendar dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(date);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
            calendar.set(Calendar.DATE, dateCalendar.get(Calendar.DATE));
            
            String[] timeParts = time.split(":");
            calendar.set(Calendar.HOUR, Integer.parseInt(timeParts[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
            if(isAM){
                    calendar.set(Calendar.AM_PM, Calendar.AM);
            }else{
                    calendar.set(Calendar.AM_PM, Calendar.PM);
            }
            
            return calendar.getTime();
    }
    
    /**
     * The getDateHourAndMinuteOnly() method creates a date object from the given date with date, hour and minute only.
     * @param date Specifies the date object.
     * @return Returns the date object.
     */
    public static Date getDateHourAndMinuteOnly(Date date){
            Calendar dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(date);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
            calendar.set(Calendar.DATE, dateCalendar.get(Calendar.DATE));
            calendar.set(Calendar.HOUR, dateCalendar.get(Calendar.HOUR));
            calendar.set(Calendar.MINUTE, dateCalendar.get(Calendar.MINUTE));
            calendar.set(Calendar.AM_PM, dateCalendar.get(Calendar.AM_PM));
            
            return calendar.getTime();
    }

    public static int getYear(){
    	Calendar calendar = Calendar.getInstance();

    	 return calendar.get(Calendar.YEAR);
    }
	public static Date getSixMonthsInPast() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -6);
		return c.getTime();
    }

	public static Date getOneYearInPast() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);
		return c.getTime();
    }

	public static String formatMinuteOnly(java.util.Date date) {
        return minOnly.format(date);
    }
    
	public static String formatHourOnly(java.util.Date date) {
        return hoursOnly.format(date);
    }
    
	public static String formatDateUS(java.util.Date date) {
        return usFormat.format(date);
    }
    
	public static java.util.Date parseDateUS(String dateString) throws ParseException {
        return usFormat.parse(dateString);
    }
    
	public static String formatDateUK(java.util.Date date) {
        return ukFormat.format(date);
    }

	public static java.util.Date parseDateUK(String dateString) throws ParseException {
        return ukFormat.parse(dateString);
    }
	public static String parseDateUKNew(String dateString) throws ParseException {
        return  ukFormat.format(dateString);
    }
	public static String formatDateMySQL(java.util.Date date) {
        return standardFormat.format(date);
    }

	public static java.util.Date parseDateMySQL(String dateString) throws ParseException {
        return ukFormat.parse(dateString);
    }
    
	public static String formatTime(java.util.Date date) {
        return timeFormat.format(date);
    }

	public static java.util.Date parseDate(String dateString) throws ParseException {
        return standardFormat.parse(dateString);
    }
    
	public static String formatDate(String datePattern, java.util.Date date) {
        SimpleDateFormat customFormat = new SimpleDateFormat(datePattern);
        customFormat.setLenient(false);
        return customFormat.format(date);
    }

	public static java.util.Date parseDate(String datePattern, String dateString) throws ParseException {
        SimpleDateFormat customFormat = new SimpleDateFormat(datePattern);
        customFormat.setLenient(false);
        return customFormat.parse(dateString);
    }

	public static void main(String[] args) {
		System.out.println("DateUtil class: Testing started....");
//		String date="25/12/2013";
//		System.out.println("Before Conversion "+date);
		try {
//			Date newDate = new Date();
//			newDate = DateUtil.parseDate(date);
//		System.out.println("After Conversion "+newDate);
			System.out.println("Year Month Day "+DateUtil.getCurrentYear()+"-"+DateUtil.getCurrentMonth()+"-"+DateUtil.getCurrentDate());
			System.out.println("Current Date "+DateUtil.getCurrentDate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// testMonthsBetween();
		// testGetTimestamp();
		// testGetOracleToDateFunction();
		// testFormatDate();
		System.out.println("DateUtil class: Testing ended.");
	}

	private static void testMonthsBetween() {
		System.out.println("Testing DateUtil.monthsBetween() method....");
		Calendar presentCalendar = Calendar.getInstance();
		presentCalendar.setTime(new Date());

		Calendar pastCalendar = Calendar.getInstance();
		pastCalendar.setTime(new Date());
		pastCalendar.add(Calendar.YEAR, -1);
		pastCalendar.add(Calendar.MONTH, -1);

		System.out.println("present: " + presentCalendar.getTime());
		System.out.println("past: " + pastCalendar.getTime());
		System.out.println("Months Between: " + monthsBetween(presentCalendar.getTime(), pastCalendar.getTime()));
	}

	private static void testGetTimestamp() {
		System.out.println("Testing DateUtil.getTimestamp() method....");
		Date date = new Date();
		System.out.println(getTimestamp());
	}

	private static void testGetOracleToDateFunction() {
		System.out.println("Testing DateUtil.getOracleToDateFunction() method....");
		Date date = new Date();
		System.out.println(getOracleToDateFunction(date));
		// System.out.println(getOracleToDateFunction(date, "dd-MM-yy"));
	}

	private static void testFormatDate() {
		System.out.println("Testing DateUtil.gformatDate() method....");
		Date date = new Date();
		String dateString = formatDate(date, "dd/mm/yy");
		System.out.println(dateString);
	}
	
	/**
	 * 
	 * The getCurrentdateWithoutTime() method returns current/system date only after removing time.
	 *
	 * @return returns import java.sql.Timestamp; after removing time 
	 */
	
	public static Timestamp getCurrentdateWithoutTime() {
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return new Timestamp(calendar.getTime().getTime());
	}

	public static String getStartDateOfCurrentWeek() {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		System.out.println("***********************************");
		System.out.print("Start Date : " + c.getTime() + ", ");
		Date firstDayOfWeek= c.getTime();
		return standardFormat.format(firstDayOfWeek);  
	}

	public static Date getEndDateOfCurrentWeek() {

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_WEEK, 6);
		return c.getTime();

	}
	
	public static String getStartOfCurrentMonth() {		  
	        Date today = new Date();  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(today);  
	        calendar.set(Calendar.DAY_OF_MONTH, 1);  
	        Date firstDayOfMonth = calendar.getTime();  
	        return standardFormat.format(firstDayOfMonth);  
	    
	}
	public static String getEndOfCurrentMonth() {		  
        Date today = new Date();  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(today);  
        calendar.set(Calendar.DAY_OF_MONTH, 30);  
        Date firstDayOfMonth = calendar.getTime();  
        return standardFormat.format(firstDayOfMonth);  
    
	}
	public static String getStartOfCurrentYear() {		  
        Date today = new Date();  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(today);  
        calendar.set(Calendar.DAY_OF_YEAR, 1);  
        Date firstDayOfMonth = calendar.getTime();  
        return standardFormat.format(firstDayOfMonth);  
    
	}
	
	public static String convertMonth(String number){
		
		String month = "";
		switch(Integer.parseInt(number)){
		case 01:
			month = "January";
			break;
		case 02:
			month = "Feburary";
			break;
		case 03:
			month = "March";
			break;
		case 04:
			month = "April";
			break;
		case 05:
			month = "May";
			break;
		case 06:
			month = "June";
			break;
		case 07:
			month = "July";
			break;
		case 18:
			month = "August";
			break;
		case 19:
			month = "September";
			break;
		case 10:
			month = "October";
			break;
		case 11:
			month = "November";
			break;
		case 12:
			month = "December";
			break;
		}
		
		return month;
	}
	
	public static Date splitAndConvertStringDate(String date){
		if (date != null && date.isEmpty()) {
		Date dateConverted = null;
		//String dateArray[] = date.split("/");
		//java.sql.Date dateConverted = new java.sql.Date(Long.parseLong(date));
		//dateConverted.setDate(Integer.parseInt(dateArray[0]));
		//dateConverted.setMonth(Integer.parseInt(dateArray[1]));
		//dateConverted.setYear(Integer.parseInt(dateArray[2]));
		//System.out.println("Date After Conversion In Date Variable :"+dateConverted.toString());
		//String da = DateUtil.formatDateMySQL(dateConverted);
		//System.out.println(da);
		try {
			dateConverted = DateUtil.parseDateMySQL(date);
			System.out.println(dateConverted.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateConverted;
	}
	
		return null;
	}
	public static Date splitAndConvertStringDatenext(String date){
		
		System.out.println("Date Before Conversion In String Variable :"+date);
		Date dateConverted = null;
		//String dateArray[] = date.split("/");
		//java.sql.Date dateConverted = new java.sql.Date(Long.parseLong(date));
		//dateConverted.setDate(Integer.parseInt(dateArray[0]));
		//dateConverted.setMonth(Integer.parseInt(dateArray[1]));
		//dateConverted.setYear(Integer.parseInt(dateArray[2]));
		//System.out.println("Date After Conversion In Date Variable :"+dateConverted.toString());
		//String da = DateUtil.formatDateMySQL(dateConverted);
		//System.out.println(da);
		try {
			dateConverted = DateUtil.parseDateMySQL(date);
			System.out.println(dateConverted.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateConverted;
	}
	
	public static String formatIHMDate(Date date, String flag) {
		if (null != date) {
			String ihmDate = formatDateUK(date);
			if (flag.equals("start")) {
				ihmDate = ihmDate + " 00:00:00";
			} else {
				ihmDate = ihmDate + " 23:59:59";
			}
			System.out.println("enddate format function==============>"+ihmDate);
			return ihmDate;
			
		  
		}else{
			return "";
		}
	}

    /**
     * this method for define opening times by given string
     *
     * @param timeString - String
     * @return openingTimes - String
     */
    public static String byGivenStringTakeTime(String timeString) {
        StringBuilder openingTime = new StringBuilder();

        int second = timeString.indexOf("_", 1);
        int threid = timeString.indexOf("_", second + 1);
        int fourth = timeString.indexOf("_", threid + 1);
        int fivth = timeString.indexOf("_", fourth + 1);

        boolean allDay = timeString.substring(0, second).contains("-Y");
        boolean morning = timeString.substring(second, threid).contains("-Y");
        boolean afternoon = timeString.substring(threid, fourth).contains("-Y");
        boolean evening = timeString.substring(fourth, fivth).contains("-Y");
        boolean night = timeString.substring(fivth, timeString.lastIndexOf("_")).contains("-Y");

        if (allDay) openingTime.append(" ").append(ALL_DAY);
        if (morning) openingTime.append(" ").append(MORNING);
        if (afternoon) openingTime.append(" ").append(AFTERNOON);
        if (evening) openingTime.append(" ").append(EVENING);
        if (night) openingTime.append(" ").append(NIGHT);

        return openingTime.toString();
    }
}
