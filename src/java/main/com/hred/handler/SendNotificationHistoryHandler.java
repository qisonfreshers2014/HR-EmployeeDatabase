package com.hred.handler;
/**
 * *
 *
 * @author Rizwan
 *         
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.hred.common.ConfigReader;
import com.hred.common.Constants;
import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.UserException;
import com.hred.handler.annotations.AuthorizeEntity;
import com.hred.model.Employee;
import com.hred.model.SendNotificationHistory;
import com.hred.model.Template;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.dao.SendNotificationHistoryDAO;
import com.hred.persistence.dao.TemplateDAO;
import com.hred.service.descriptors.output.DisplayNotificationHome;

public class SendNotificationHistoryHandler extends AbstractHandler {

	private static SendNotificationHistoryHandler INSTANCE = null;
	
	
	private SendNotificationHistoryHandler() {
	}

	/**
	 * TemplateDAO
	 * 
	 * @return instance of UserHandler
	 */
	public static SendNotificationHistoryHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SendNotificationHistoryHandler();
		return INSTANCE;
	}
	
	@AuthorizeEntity(roles={Constants.HR})
	public SendNotificationHistory save(SendNotificationHistory entry) {
		SendNotificationHistory entrysaved = (SendNotificationHistory) DAOFactory
				.getInstance().getSendNotificationHistoryDAO()
				.saveObject(entry);
		return entrysaved;
	}
	@AuthorizeEntity(roles={Constants.HR})
	public List<SendNotificationHistory> getHistorydataAOP()
			throws BusinessException {
		// TODO Auto-generated method stub
		List<SendNotificationHistory> notificationHistory = null;
		SendNotificationHistoryDAO notificationHistoryDAOImpl = DAOFactory
				.getInstance().getSendNotificationHistoryDAO();
		notificationHistory = notificationHistoryDAOImpl.getHistorydata();

		return notificationHistory;
	}
	
	
//This Method will be used for sending the automated mail by the server
	
	public String sentAutomatedMailMail() throws EmailException {
		System.out.println("Comming to the SNH method");
		System.out.println("mail sent Time**************  "+Calendar.getInstance().getTime());
		String hostName=null;
		String smtpPort=null;
		String authenticatorMail=null;
		String authenticatorPassword=null;
		String from=null;
		String bcc=null;
		
		DisplayNotificationHome requiredContent = new DisplayNotificationHome();
		 Template template = new Template();
		TemplateDAO tempDAOImpl = (TemplateDAO) DAOFactory.getInstance()
				.getTemplateDAO();
		EmployeeHandler employeeHandler=EmployeeHandler.getInstance();
		
		try
		{
			Properties props = ConfigReader.getProperties(Constants.MAIL_CONFIGURATION_SETTING);
			hostName=props.getProperty(Constants.HOST_NAME);
		 smtpPort=props.getProperty(Constants.HOST_NAME);
		 authenticatorMail=props.getProperty(Constants.AUTHENTICATOR_MAIL);
		 authenticatorPassword=props.getProperty(Constants.AUTHENTICATOR_PASSWORD);
		 from=props.getProperty(Constants.SEND_FROM);
		 bcc=props.getProperty(Constants.SEND_BCC);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		List<Employee> todaysBithday=null;;
		List<Employee> todaysWorkAniversay=null;
		try {
			todaysBithday = employeeHandler.getTodaysBirthday();
			 todaysWorkAniversay = employeeHandler.getTodayWorkAniversary();
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			System.out.println("No event found");
		}
		
		List<Employee> AllEmployeesData = new ArrayList<Employee>();
		SendNotificationHistory entry = new SendNotificationHistory();
	
		try {
			AllEmployeesData = employeeHandler.getEmployees();
		} catch (EmployeeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
	
		
		
		if (todaysBithday.size() != 0) {
			requiredContent.setEvent("Birthday");
			Template birthdayTemplate=tempDAOImpl.getContentForMail(requiredContent);
			
			for (Employee birthday : todaysBithday) {
				String body="Hi "+birthday.getEmployeeName()+"</br></br>"+birthdayTemplate.getContent();

				try {	
					Email email = new MultiPartEmail();
					email.setHostName(hostName);
					email.setSmtpPort(465);
					email.setAuthenticator(new DefaultAuthenticator(
							authenticatorMail, authenticatorPassword));
					email.setSSLOnConnect(true);
					email.setFrom(from);
					email.setSubject("Happy Birth Day "+birthday.getEmployeeName());
					email.addTo(birthday.getEmail());
					email.setContent(body, "text/html");
					email.addCc(bcc);
					email.send();
					
					
						// send message
										entry.setTemplateId("01");
										entry.setEventName("Birthday");
					entry.setEmployeeEmail(birthday.getEmail());
					entry.setEmployeeName(birthday.getEmployeeName());
					entry.setDeleted(false);
					entry.setEmployeeId(birthday.getEmployeeId());
					save(entry);

				} catch (EmailException e) {
				e.printStackTrace();	//System.out.println("Unable to send Mail");
				}

			}
		}
		if (todaysWorkAniversay.size() != 0) {
			requiredContent.setEvent("Work Anniversary");
			Template anivarsaryTemplate=tempDAOImpl.getContentForMail(requiredContent);
			
			for (Employee aniversary : todaysWorkAniversay) {
				Calendar now = Calendar.getInstance();   // Gets the current date and time
				int year = now.get(Calendar.YEAR); 
				Timestamp doj =aniversary.getDateOfJoining();
				now.setTime(doj);
				int dojYear =now.get(Calendar.YEAR);
				int diff=year-dojYear;
				String body="<b>Hi "+aniversary.getEmployeeName();
				   if(diff>1){
						body+="<br/><br/>Congratulations on completing " +diff+" years with Qison Software.<br/><br/></b>"+anivarsaryTemplate.getContent();
				   } else{
					    body+="<br/><br/>Congratulations on completing " +diff+" year with Qison Software.<br/><br/></b>"+anivarsaryTemplate.getContent();
				   }
				String aniversarytext = null;
				try {
					Email email = new MultiPartEmail();
					email.setHostName(hostName);
					email.setSmtpPort(465);
					email.setAuthenticator(new DefaultAuthenticator(
							authenticatorMail, authenticatorPassword));
					email.setSSLOnConnect(true);
					email.setFrom(from);
					email.setSubject("Happy Work Anniversary  "+aniversary.getEmployeeName());
					email.addTo(aniversary.getEmail());
					email.addCc(bcc);
					email.setContent(body, "text/html");
					email.send();
					entry.setEmployeeEmail(aniversary.getEmail());
					entry.setEmployeeName(aniversary.getEmployeeName());
					entry.setEventName("Work Anniversary");
					entry.setDeleted(false);
					entry.setTemplateId("02");
					entry.setEmployeeId(aniversary.getEmployeeId());
					save(entry);

				} catch (EmailException e) {
					System.out.println("Unable to send Mail");
				}

			}

		}

		return "{\"status\": \"SUCCESS\", \"payload\": \"Mail Send\"}";

	}

			

	// This function will be used to send the mail Mail from the Notification Page
	@AuthorizeEntity(roles={Constants.HR})
	public String sentMailAOP(DisplayNotificationHome sentMailToEmployee)
			throws EmailException, UserException {
		{
			String hostName=null;
			String smtpPort=null;
			String authenticatorMail=null;
			String authenticatorPassword=null;
			String from=null;
			String bcc=null;
			long file_id;
			
			try
			{
				Properties props = ConfigReader.getProperties(Constants.MAIL_CONFIGURATION_SETTING);
				hostName=props.getProperty(Constants.HOST_NAME);
			 smtpPort=props.getProperty(Constants.HOST_NAME);
			 authenticatorMail=props.getProperty(Constants.AUTHENTICATOR_MAIL);
			 authenticatorPassword=props.getProperty(Constants.AUTHENTICATOR_PASSWORD);
			 from=props.getProperty(Constants.SEND_FROM);
			 bcc=props.getProperty(Constants.SEND_BCC);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
				String body=sentMailToEmployee.getModifiedContent();

			SendNotificationHistory entry = new SendNotificationHistory();
			List<Employee> AllEmployeesData = new ArrayList<Employee>();
			EmployeeHandler employeeHandler=EmployeeHandler.getInstance();
			try {
				AllEmployeesData = employeeHandler.getEmployees();
			} catch (EmployeeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	

			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(hostName);
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(
					authenticatorMail, authenticatorPassword));
			email.setSSLOnConnect(true);
			email.setFrom(from);
			String subjectMail = sentMailToEmployee.getEvent() + " "
					+ sentMailToEmployee.getEmployeeName();
			email.addCc(bcc);
			
			try {
				email.setSubject(subjectMail);
				email.addTo(sentMailToEmployee.getEmployeeEmail());
				if (sentMailToEmployee.getEvent().equalsIgnoreCase("Birthday")) {
					entry.setTemplateId("01");
					entry.setEventName("Birthday");
								
				
				} else if (sentMailToEmployee.getEvent().equalsIgnoreCase("Work Anniversary")) {
					entry.setTemplateId("02");
					entry.setEventName("Work Anniversary");
					
							}
				else
				{
					entry.setTemplateId("03");
					entry.setEventName("Welcome");
				
				
				}
			
				email.setContent(body, "text/html");
				email.send();
				entry.setEmployeeName(sentMailToEmployee.getEmployeeName());
				entry.setEmployeeEmail(sentMailToEmployee.getEmployeeEmail());
				
				Employee emp=DAOFactory.getInstance().getEmployeeDAO().getUserByEmail(sentMailToEmployee.getEmployeeEmail());
				entry.setEmployeeId(emp.getEmployeeId());
						
				
				 DAOFactory.getInstance().getSendNotificationHistoryDAO()
						.saveObject(entry);
			} catch (EmailException e) {
				//System.out.println("Unable to Send Msg");
				e.printStackTrace();
				 throw new UserException(ExceptionCodes.UNABLE_TO_SEND_MAIL, ExceptionMessages.UNABLE_TO_SEND_MAIL);
			}
			return "{\"status\": \"SUCCESS\", \"payload\": \"Mail Send\"}";
		}
	}
	
	 @AuthorizeEntity(roles={Constants.HR})
	    public String sentMailManualAOP(DisplayNotificationHome sentMailToEmployee)
	            throws EmailException, UserException {
	       
	            String hostName=null;
	            String smtpPort=null;
	            String authenticatorMail=null;
	            String authenticatorPassword=null;
	            String from=null;
	            String bcc=null;
	            long file_id;
	           
	            try
	            {
	                Properties props = ConfigReader.getProperties(Constants.MAIL_CONFIGURATION_SETTING);
	                hostName=props.getProperty(Constants.HOST_NAME);
	             smtpPort=props.getProperty(Constants.HOST_NAME);
	             authenticatorMail=props.getProperty(Constants.AUTHENTICATOR_MAIL);
	             authenticatorPassword=props.getProperty(Constants.AUTHENTICATOR_PASSWORD);
	             from=props.getProperty(Constants.SEND_FROM);
	             bcc=props.getProperty(Constants.SEND_BCC);
	            }
	            catch(IOException e)
	            {
	                e.printStackTrace();
	            }
	           
	                String body=sentMailToEmployee.getModifiedContent();

	                MultiPartEmail email = new MultiPartEmail();
	                email.setHostName(hostName);
	                email.setSmtpPort(465);
	                email.setAuthenticator(new DefaultAuthenticator(
	                        authenticatorMail, authenticatorPassword));
	                email.setSSLOnConnect(true);
	                email.setFrom(from);
	                email.setBounceAddress("rahul.shelke@qison.com");
	                String subjectMail = sentMailToEmployee.getEvent() + " "
	                        + sentMailToEmployee.getEmployeeName();
	               	               
	                email.setSubject(sentMailToEmployee.getEvent());
	                System.out.println(sentMailToEmployee.getEmployeeEmail());
	                email.addTo(sentMailToEmployee.getEmployeeEmail());
	                email.setContent(body, "text/html");
	                email.send();
	                return "{\"status\": \"SUCCESS\", \"payload\": \"Mail Send\"}";
	               
	        }
	 
	 
		@AuthorizeEntity(roles={Constants.HR})
	  public String sendRegistrationMail(String employeeEmail,String employeeName,String randomPassword) throws EmailException{
		  

          String hostName=null;
          String smtpPort=null;
          String authenticatorMail=null;
          String authenticatorPassword=null;
          String from=null;
          String bcc=null;
          String URL=null;
          long file_id;
         
          try
          {
              Properties props = ConfigReader.getProperties(Constants.MAIL_CONFIGURATION_SETTING);
              hostName=props.getProperty(Constants.HOST_NAME);
           smtpPort=props.getProperty(Constants.HOST_NAME);                                                               
           authenticatorMail=props.getProperty(Constants.AUTHENTICATOR_MAIL);
           authenticatorPassword=props.getProperty(Constants.AUTHENTICATOR_PASSWORD);
           from=props.getProperty(Constants.SEND_FROM);
           bcc=props.getProperty(Constants.SEND_BCC);
           URL=props.getProperty(Constants.URL);                                                            
          }
          catch(IOException e)
          {
              e.printStackTrace();
          }
          
          //Templete templte=TemplateHandler.getInstance().viewTemplateAOP(template.getId());
          String content="";
          
          content +="Hi ,    <b>"+employeeName+"</b><br/><br/>";
        		  
        content += "Welcome to <b>Qi People.</b><br/><br/>";
        		   
        content	+= "You have been registered on the system. You can login to the system with following credentials:<br/><br/>";

        content += "<b>URL:</b>"+URL+"<br/><br/>";
        		   
        content += "<b>Username:</b>"+employeeEmail+"<br/><br/>";
        content += "<b>Password:</b>"+randomPassword+"<br/><br/>";
        		   
        content += "Please change your password once you login to the system.<br/><br/>";
        		   
        content += "<b> Features:</b><br/>";
        		content +=  " -          Qi People lets you view some of your basic information<br/>";
        				content +=   "-          Allows you to edit some information and upgrade your skill set<br/>";
        				content += 	 " -          You can view the yearly Holiday list<br/>";
        				content +=  "-          One stop for all HR policies<br/>";
        				content +=  "-          And All Hands data  too<br/><br/><br/>";
        		   
        				content +=  " <b>Thanks,</b><br/><br/>";
        				content +=  "<b>Team HR.</b>";
        		   
        		

		  
		  MultiPartEmail email = new MultiPartEmail();
          email.setHostName(hostName);
          email.setSmtpPort(465);
          email.setAuthenticator(new DefaultAuthenticator(
                  authenticatorMail, authenticatorPassword));
          email.setSSLOnConnect(true);
          email.setFrom(from);
          email.setBounceAddress("rahul.shelke@qison.com");
         
         // String subjectMail = sentMailToEmployee.getEvent() + " "
                 // + sentMailToEmployee.getEmployeeName();
         	               
          email.setSubject("HRMS registration");
          email.addTo(employeeEmail);
          email.setContent(content,"text/html");
          email.send();
          return "{\"status\": \"SUCCESS\", \"payload\": \"Mail Send\"}";
		  
		  
	  }

		public String sendForgotPasswordMail(String email, String employeeName,String randomPassword) throws EmailException {
			 String hostName=null;
	          String smtpPort=null;
	          String authenticatorMail=null;
	          String authenticatorPassword=null;
	          String from=null;
	          String bcc=null;
	          String URL=null;
	          long file_id;
	         
	          try
	          {
	              Properties props = ConfigReader.getProperties(Constants.MAIL_CONFIGURATION_SETTING);
	              hostName=props.getProperty(Constants.HOST_NAME);
	           smtpPort=props.getProperty(Constants.HOST_NAME);                                                               
	           authenticatorMail=props.getProperty(Constants.AUTHENTICATOR_MAIL);
	           authenticatorPassword=props.getProperty(Constants.AUTHENTICATOR_PASSWORD);
	           from=props.getProperty(Constants.SEND_FROM);
	           bcc=props.getProperty(Constants.SEND_BCC);
	           URL=props.getProperty(Constants.URL);                                                            
	          }
	          catch(IOException e)
	          {
	              e.printStackTrace();
	          }
	          
	          //Templete templte=TemplateHandler.getInstance().viewTemplateAOP(template.getId());
	          String content="";
	          
	          content +="Hi ,    <b>"+employeeName+"</b><br/><br/>";
	          
	        content	+= "You can Login by using following credentials<br/><br/>";
		   
	        content += "<b>Username:</b>"+email+"<br/><br/>";
	        content += "<b>Password:</b>"+randomPassword+"<br/><br/>";
	        		   
	        content += "Please change your password once you login to the system.<br/><br/>";

			  MultiPartEmail mulEmail = new MultiPartEmail();
			  mulEmail.setHostName(hostName);
			  mulEmail.setSmtpPort(465);
			  mulEmail.setAuthenticator(new DefaultAuthenticator(
	                  authenticatorMail, authenticatorPassword));
			  mulEmail.setSSLOnConnect(true);
			  mulEmail.setFrom(from);
	         
	         // String subjectMail = sentMailToEmployee.getEvent() + " "
	                 // + sentMailToEmployee.getEmployeeName();
	         	               
			  mulEmail.setSubject("Forgot Password");
			  mulEmail.addTo(email);
			  mulEmail.setContent(content,"text/html");
			  mulEmail.setBounceAddress("rahul.shelke@qison.com");
			  mulEmail.send();
	          return "{\"status\": \"SUCCESS\", \"payload\": \"Mail Send\"}";
			  
		}
	 
}
