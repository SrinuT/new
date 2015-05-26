package com.ihm.customer.faces.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import com.ihm.customer.entites.*;
import com.ihm.customer.faces.bean.SessionBean;
import com.ihm.customer.service.*;
import org.apache.log4j.Logger;

import com.ihm.customer.exceptions.IHMCustomerException;
import com.ihm.customer.util.DateUtil;
import com.ihm.customer.util.IHMConstants;
import com.ihm.customer.util.RepositoryContext;
import com.ihm.customer.util.StringFunctions;
import com.ihm.frontend.search.dto.Appointment;
import com.ihm.frontend.search.dto.Clinic;
import com.ihm.frontend.search.dto.DealsTab;
import com.ihm.frontend.search.dto.GuestLogin;
import com.ihm.frontend.search.dto.ReviewsTab;
import com.ihm.jsf.JsfUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author SARDAR WAQAS AHMED
 * @email  architect_pakistan@hotmail.com
 * @since  17-Jan-2015
 * @version 1.0
 */
public class AppointmentController extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 977584857747794506L;
	private static Logger logger = Logger.getLogger(AppointmentController.class);
	
	private Integer activeIndex = 0;
	
	
	/*
	 * Appointment Page Rendering Options
	 */
	private boolean showAppointmentLogin;
	private boolean showAppointmenttMake;
	private boolean showAppointmentConfirm;
	private boolean showPanelTop;
	private boolean showPanelLast;
	
	
	/*
	 * Appointment Data
	 * 
	 */
	private String email;
	private String password;
	private String docName;
	private String refId;
	private String confirmationNo;
	
	/*
	 * Ajax Rendering
	 * 
	 */

	
	
	private boolean rememberMe;
	private boolean serviceCenterPayment;
	private boolean recievePromotionNews;
	
	private GuestLogin guestLogin;
	private Clinic bookedClinic;
	private Appointment makeAppointment;
	private boolean guestUser;
	/*
	 * ViewMore
	 */
	private DealsTab viewMoreDeals;
	private ReviewsTab viewMoreReviews;
	
	/*
	 * BL : Service Layer Objects
	 */
	private ManagerService managerService;
	private CalendarService calendarService;
	private CustomerAppointmentService appointmentService;
	private SearchService searchService;
	private UserService userService;
	private SMSService smsService;


    private String testName;

    private SlrSeller slrSeller;
	
	/*
	  * Appointment BL Start
	  */
	 public String bookAppointmentSlot(Appointment makeAppointment,Clinic bookedClinic, String testName){
		 logger.info("<<< bookAppointmentSlot .. >>>");
		 this.makeAppointment = makeAppointment;
		 this.bookedClinic = bookedClinic;
		 logger.info(">>  clinicId "+bookedClinic.getId()+" Is Going To Booked!");
         managerService = RepositoryContext.getBean(ManagerService.class);
         slrSeller = managerService.getSearchService().getSlrSellerById(bookedClinic.getSlrSellerId());
		 init();

         this.testName = testName;
         return "/appointment/appointment.xhtml?faces-redirect=true";


	 }
	
	/*
	  * Appointment BL Start
	  */
    public String bookAppointmentSlotUpdate(Appointment makeAppointment,Clinic bookedClinic, String testName, Long slrId){
		 logger.info("<<< bookAppointmentSlot .. >>>");
		 this.makeAppointment = makeAppointment;
		 this.bookedClinic = bookedClinic;
		 logger.info(">>  clinicId "+bookedClinic.getId()+" Is Going To Booked!");
        managerService = RepositoryContext.getBean(ManagerService.class);
        bookedClinic.setSlrSellerId(slrId);
        slrSeller = managerService.getSearchService().getSlrSellerById(slrId);
		 init();

        this.testName = testName;
         return "/appointment/appointment.xhtml?faces-redirect=true";


	 }
	 
	 public void onMakeAppointmentClick(ActionEvent actionEvent) {
		 
		 if(isGuestAppointmentValid()) {
            this.showPanelTop = true;
            this.showAppointmenttMake = true;
            this.showPanelLast = true;
            this.showAppointmentLogin = false;
            this.showAppointmentConfirm = false;
            this.guestUser = true;
		 }
	}
	 
	 public void onBackButtonClick(AjaxBehaviorEvent event) {
			logger.info("<<< [onBackButtonClick()]  >>>");			
			 this.showPanelTop = true;
			 this.showAppointmentLogin = true;
			 this.showPanelLast = true;
			 this.showAppointmenttMake = false;
			 this.showAppointmentConfirm = false;
	 }
	 
	 public String onConfirmedAppointmentClick() {		 
		 try {
			 logger.info("<<< confirm Started>>>>>");
			this.showAppointmentConfirm = true;

			managerService = RepositoryContext.getBean(ManagerService.class);
			calendarService = managerService.getCalendarService();
			appointmentService = managerService.getCustomerAppointmentService();
			searchService = managerService.getSearchService();
			userService = managerService.getUserService();
			logger.info("This is for testing message");

            SlrAppointmentBookings slotBookedDTO = null;

            Object id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("updateAppointmentId");
            if (id != null) {
                slotBookedDTO = appointmentService.getById(Long.parseLong(id.toString()));
                this.confirmationNo = slotBookedDTO.getConfirmationNumber();
                logger.info("This is for testing message IF Condition");
            } else {
                slotBookedDTO = new SlrAppointmentBookings();
                this.confirmationNo = StringFunctions.gen(IHMConstants.CONFIRMATION_STRING_LENGTH).toUpperCase();
                slotBookedDTO.setConfirmationNumber(this.confirmationNo);
                logger.info("This is for testing message Else Condition");
            }

            SlrSeller clinic = searchService.getClinicById(this.makeAppointment.getClinicIdFK());

            SlrProduct slrProduct = managerService.getProductService().getSlrProduct(testName, bookedClinic.getSlrSellerId());
            slotBookedDTO.setSlrProduct(slrProduct);
            logger.info("This is for testing message "+ slrProduct);


            SlrAppointmentSlots updateSlotDTO = calendarService.getSlotById(this.makeAppointment.getSlotIdPK());
			updateSlotDTO.setActive(false);
			boolean updateSlot = calendarService.updateAppointmentSlots(updateSlotDTO);
			logger.info("This is for testing message"+updateSlot);


			slotBookedDTO.setSlrAppointmentSlots(updateSlotDTO);
			slotBookedDTO.setSlrSeller(clinic);

             if (sessionBean.getPersonDto() == null) {
                 Long guestPersonId = guestPersonService.create(guestLogin);
                 slotBookedDTO.setGuestPerson(new GuestPerson());
                 slotBookedDTO.getGuestPerson().setId(guestPersonId);
             } else {
                 slotBookedDTO.setPerson(new Person());
                 slotBookedDTO.getPerson().setId(sessionBean.getPersonDto().getId());
                 
             }
			
			slotBookedDTO.setApptDate(makeAppointment.getStartDate());
			slotBookedDTO.setApptTime(makeAppointment.getSlotTime());
			slotBookedDTO.setBookedFor(guestLogin.getFirstName());
			slotBookedDTO.setBookedTime(new Date());
			slotBookedDTO.setBookingStatus(IHMConstants.BOOKING_STATUS);
			slotBookedDTO.setCreatedBy(IHMConstants.CUSTOMER_USER);
			slotBookedDTO.setCreatedOn(new Date());
			slotBookedDTO.setDocName(docName);
			slotBookedDTO.setPaymentOnServiceCenter(this.serviceCenterPayment);
			slotBookedDTO.setRecieveOffers(this.recievePromotionNews);
			slotBookedDTO.setRefId(refId);
			slotBookedDTO.setUpdatedBy(IHMConstants.CUSTOMER_USER);
			slotBookedDTO.setEmail(guestLogin.getEmail());
			slotBookedDTO.setUpdatedOn(new Date());
			
			String isSaved = appointmentService.bookedAppointmentForCustomer(slotBookedDTO);
						FacesMessage msg;
						logger.info("<<< confirm Started>>>>>" + isSaved);
						if(isSaved.equalsIgnoreCase(IHMConstants.SAVED)){
							if (guestLogin != null && guestLogin.getPrimaryNumber() != null && !guestLogin.getPrimaryNumber().trim().equals("")){
							logger.info("User information ======>"+"mobile :"+ guestLogin.getPrimaryNumber());
							managerService = RepositoryContext.getBean(ManagerService.class);
						 	smsService = managerService.getSmsService();
			
						 	String messageBody = IHMConstants.APPOINTMENTCONFSMS;
						 	messageBody = messageBody.replaceAll("%username%", guestLogin.getFirstName()+" "+guestLogin.getLastName());
						 	messageBody = messageBody.replaceAll("%confirmation number%",this.confirmationNo);
						 	messageBody = messageBody.replaceAll("%service name%", "");
						 	messageBody = messageBody.replaceAll("%Test Name%", testName);
						 	messageBody = messageBody.replaceAll("%Provider Name%",this.bookedClinic.getClinicName());
						 	messageBody = messageBody.replaceAll("%dd/mm/yyyy%", DateUtil.formatDateUK(makeAppointment.getStartDate()));
						 	messageBody = messageBody.replaceAll("%hh:mm%", DateUtil.formatTime(makeAppointment.getSlotTime()));
						 	messageBody = messageBody.replaceAll("%mobile no%", clinic.getMobileNumber());
						 	logger.info("<<< confirm Started before sending>>>>>");
						 					 		
						 	smsService.sendSMS(guestLogin.getPrimaryNumber().split("-")[1], messageBody, "");
						 	}
						 	
						}
//			if(isSaved.equalsIgnoreCase(IHMConstants.SAVED)){
//				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"You Successfully Book The Slot", " ");
//				//Added By Srinivasulu T for SMS
//				managerService = RepositoryContext.getBean(ManagerService.class);
//			 	smsService = managerService.getSmsService();
//
//			 	String messageBody = "Dear ";
//			 	messageBody+=guestLogin.getFirstName();
//			 	messageBody +=" Appointment Confirmation Number - "+ this.confirmationNo;
//			 	smsService.sendSMS(guestLogin.getPrimaryNumber(), messageBody, "pramotional");
//			}
//			else{
//				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Booking Failure", " ");
//			}
			//FacesContext.getCurrentInstance().addMessage(null, msg);
		 }catch (IHMCustomerException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		}
			
		return "/appointment/appointmentconfirm.xhtml?faces-redirect=true";
	 }

    public String onUpdateAppointment() {
        return "/appointment/appointmentconfirm.xhtml?faces-redirect=true";
    }

	/*
	 * View More Click
	 */
	 public void onViewMoreClick(ActionEvent event) {
		 Map<String,Object> attributes = event.getComponent().getAttributes();
		 String viewMore = (String) attributes.get("viewMore");
		 Clinic clinic = (Clinic) attributes.get("clinic");
		 if(!viewMore.isEmpty() && viewMore.equals("deals")){
			 this.viewMoreDeals = clinic.getDealsTab();
		 } else if(!viewMore.isEmpty() && viewMore.equals("reviews")){
			 this.viewMoreReviews = clinic.getReviewsTab();
		 }		 
	 }
	
	/**
	 * this method for checking is are equals email and confirm email
	 * 
	 * @param event - ComponentSystemEvent
	 */
	public void validateEmail(ComponentSystemEvent event) {		 
		FacesContext fc = FacesContext.getCurrentInstance();
	    UIComponent components = event.getComponent();
	    // get password
		UIInput uiInputPassword = (UIInput) components.findComponent("email");
		String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
		String passwordId = uiInputPassword.getClientId();
	 
		// get confirm email
		UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmEmail");
		String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? "" : uiInputConfirmPassword.getLocalValue().toString();
	 
		// Let required="true" do its job.
		if (password.isEmpty() || confirmPassword.isEmpty()) {
			return;
		}
	 
		if (!password.equals(confirmPassword)) {
			FacesMessage msg = new FacesMessage("Email must match confirm email");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(passwordId, msg);
			fc.renderResponse();	 
		}	 
	}
	 
	/*
	 * Reset properties
	 */
	public void init() {
        this.docName = "";
        this.refId = "";
        this.confirmationNo = "";
        this.rememberMe = false;
        if (sessionBean.getPersonDto() == null) {
            this.activeIndex = 0;
            this.showAppointmenttMake = false;
            this.showAppointmentConfirm = false;
            this.email = "";
            this.password = "";
            this.serviceCenterPayment = false;
            this.recievePromotionNews = false;
            this.guestLogin.init();
            this.showPanelTop = true;
            this.showAppointmentLogin = true;
            this.showPanelLast = true;
            this.guestUser = false;
        } else {
            guestUser = false;
            showAppointmenttMake = true;
            this.showAppointmentConfirm = true;
        }
    }
	 
	 
	 /**
	  * this method check appointments details is valid or no
	  */
	 private boolean isGuestAppointmentValid() {
         boolean isvalid = guestLogin.getEmail().equals(guestLogin.getConfirmEmail());
		 
		 if(!isvalid) {
			 JsfUtil.addErrorMessage("panelsForm" ,"Confirm email must be same as email");
		 }
		 
		 return isvalid;
	 }

	public AppointmentController() {
		this.showAppointmentLogin = true;
		this.showAppointmenttMake = false;
		this.showAppointmentConfirm = false;
		guestLogin = new GuestLogin();
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
		
		public Integer getActiveIndex() {
			return activeIndex;
		}

		public void setActiveIndex(Integer activeIndex) {
			this.activeIndex = activeIndex;
		}

		public boolean isShowAppointmentLogin() {
			return showAppointmentLogin;
		}

		public void setShowAppointmentLogin(boolean showAppointmentLogin) {
			this.showAppointmentLogin = showAppointmentLogin;
		}

		public boolean isRememberMe() {
			return rememberMe;
		}

		public void setRememberMe(boolean rememberMe) {
			this.rememberMe = rememberMe;
		}

		public boolean isServiceCenterPayment() {
			return serviceCenterPayment;
		}

		public void setServiceCenterPayment(boolean serviceCenterPayment) {
			this.serviceCenterPayment = serviceCenterPayment;
		}

		public boolean isRecievePromotionNews() {
			return recievePromotionNews;
		}

		public void setRecievePromotionNews(boolean recievePromotionNews) {
			this.recievePromotionNews = recievePromotionNews;
		}

		public boolean isShowAppointmenttMake() {
			return showAppointmenttMake;
		}

		public void setShowAppointmenttMake(boolean showAppointmenttMake) {
			this.showAppointmenttMake = showAppointmenttMake;
		}

		public boolean isShowAppointmentConfirm() {
			return showAppointmentConfirm;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getDocName() {
			return docName;
		}

		public void setDocName(String docName) {
			this.docName = docName;
		}

		public String getRefId() {
			return refId;
		}

		public void setRefId(String refId) {
			this.refId = refId;
		}

		public String getConfirmationNo() {
			return confirmationNo;
		}

		public void setConfirmationNo(String confirmationNo) {
			this.confirmationNo = confirmationNo;
		}

		public GuestLogin getGuestLogin() {
			return guestLogin;
		}

		public void setGuestLogin(GuestLogin guestLogin) {
			this.guestLogin = guestLogin;
		}

		public void setShowAppointmentConfirm(boolean showAppointmentConfirm) {
			this.showAppointmentConfirm = showAppointmentConfirm;
		}

		public boolean isShowPanelTop() {
			return showPanelTop;
		}

		public void setShowPanelTop(boolean showPanelTop) {
			this.showPanelTop = showPanelTop;
		}

		public boolean isShowPanelLast() {
			return showPanelLast;
		}

		public void setShowPanelLast(boolean showPanelLast) {
			this.showPanelLast = showPanelLast;
		}

		public Clinic getBookedClinic() {
			return bookedClinic;
		}

		public void setBookedClinic(Clinic bookedClinic) {
			this.bookedClinic = bookedClinic;
		}

		public Appointment getMakeAppointment() {
			return makeAppointment;
		}

		public void setMakeAppointment(Appointment makeAppointment) {
			this.makeAppointment = makeAppointment;
		}

		public boolean isGuestUser() {
			return guestUser;
		}

		public void setGuestUser(boolean guestUser) {
			this.guestUser = guestUser;
		} 
		
    public SlrSeller getSlrSeller() {
        return slrSeller;
    }

    public void setSlrSeller(SlrSeller slrSeller) {
        this.slrSeller = slrSeller;
    }
}