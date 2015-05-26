/**
 * Service to send the SMS to Users
 */
package com.ihm.customer.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.ihm.customer.faces.controller.AppointmentController;
import com.ihm.customer.service.SMSService;

/**
 * @author Srinivasulu T
 *
 */
@Service
public class SMSServiceImpl implements SMSService,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2893980655912778386L;
	private static Logger logger = Logger.getLogger(AppointmentController.class);
	
	
	@Override
	public String pramotionalSMS(String userName, String password,
			String messageTo, String messageBody, String senderId,
			String flashFlag) {
			/*response string intialization*/
			String response="";
			
				try {
					// Construct The Post Data
					String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
					data += "&" + URLEncoder.encode("pwd", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
					data += "&" + URLEncoder.encode("to", "UTF-8") + "=" + URLEncoder.encode(messageTo, "UTF-8");
					data += "&" + URLEncoder.encode("msg", "UTF-8") + "=" + URLEncoder.encode(messageBody, "UTF-8");
					data += "&" + URLEncoder.encode("sid", "UTF-8") + "=" + URLEncoder.encode(senderId, "UTF-8");
					data += "&" + URLEncoder.encode("fl", "UTF-8") + "=" + URLEncoder.encode(flashFlag, "UTF-8");
					logger.info(data);
					//Push the HTTP Request
					URL url = new URL("http://login.smsgatewayhub.com/smsapi/pushsms.aspx?");
					URLConnection conn = url.openConnection();
					conn.setDoOutput(true);

					OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
					wr.write(data);
					wr.flush();

					//Read The Response
					BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line;
					while ((line = rd.readLine()) != null) {
						// Process line…
						response += line;
					}
					logger.info("response===>"+response);
					wr.close();
					rd.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return response;
	}

	@Override
	public String transactionlSMS(String userName, String password,
			String messageTo, String messageBody, String senderId,
			String flashFlag, String gwid) {
		/*response string intialization*/
		String response="";
		
			try {
				// Construct The Post Data
				String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
				data += "&" + URLEncoder.encode("pwd", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
				data += "&" + URLEncoder.encode("to", "UTF-8") + "=" + URLEncoder.encode(messageTo, "UTF-8");
				data += "&" + URLEncoder.encode("msg", "UTF-8") + "=" + URLEncoder.encode(messageBody, "UTF-8");
				data += "&" + URLEncoder.encode("sid", "UTF-8") + "=" + URLEncoder.encode(senderId, "UTF-8");
				data += "&" + URLEncoder.encode("fl", "UTF-8") + "=" + URLEncoder.encode(flashFlag, "UTF-8");
				data += "&" + URLEncoder.encode("gwid", "UTF-8") + "=" + URLEncoder.encode(gwid, "UTF-8");
				logger.info(data);
				//Push the HTTP Request
				URL url = new URL("http://login.smsgatewayhub.com/smsapi/pushsms.aspx?");
				URLConnection conn = url.openConnection();
				conn.setDoOutput(true);

				OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(data);
				wr.flush();

				//Read The Response
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = rd.readLine()) != null) {
					// Process line…
					response += line;
				}
				logger.info("response===>"+response);
				wr.close();
				rd.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return response;
	}

	@Override
	public String sendSMS(String mobileNumbers, String Message, String type) {
		String response = "failed";
		try {
			
			Properties props = PropertiesLoaderUtils.loadAllProperties("ihm.properties");
			String username = props.getProperty("ihm.sms.username");
			String password = props.getProperty("ihm.sms.password");
			//String url = props.getProperty("ihm.sms.url");
			String senderID = props.getProperty("ihm.sms.senderID");
			if(type.equalsIgnoreCase("pramotional")){
				response = pramotionalSMS(username, password, mobileNumbers, Message, senderID, "0");
			}else{
				response = transactionlSMS(username, password, mobileNumbers, Message, senderID, "0","2");
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}



	

}
