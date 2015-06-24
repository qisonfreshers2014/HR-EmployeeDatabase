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
import java.util.ArrayList;
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
				String body="Hi "+birthday.getEmployeeName()+"</br>"+birthdayTemplate.getContent();

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
					email.addBcc(bcc);
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
			requiredContent.setEvent("Anniversary");
			Template anivarsaryTemplate=tempDAOImpl.getContentForMail(requiredContent);
			
			for (Employee aniversary : todaysWorkAniversay) {
				String body="Hi "+aniversary.getEmployeeName()+"</br>"+anivarsaryTemplate.getContent();
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
					//email.addBcc(bcc);
					email.setContent(body, "text/html");
					email.send();
					
					entry.setEmployeeEmail(aniversary.getEmail());
					entry.setEmployeeName(aniversary.getEmployeeName());
					entry.setEventName("Anniversary");
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
								
				
				} else if (sentMailToEmployee.getEvent().equalsIgnoreCase("Anniversary")) {
					entry.setTemplateId("02");
					entry.setEventName("Anniversary");
					
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
	               
	                String subjectMail = sentMailToEmployee.getEvent() + " "
	                        + sentMailToEmployee.getEmployeeName();
	               	               
	                email.setSubject(sentMailToEmployee.getEvent());
	                email.addTo(sentMailToEmployee.getEmployeeEmail());
	                email.setContent(body, "text/html");
	                email.send();
	                return "{\"status\": \"SUCCESS\", \"payload\": \"Mail Send\"}";
	               
	        }
	 
	  public String sendRegistrationMail(String employeeEmail,String employeeName,String randomPassword) throws EmailException{
		  

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
          
          //Templete templte=TemplateHandler.getInstance().viewTemplateAOP(template.getId());
          String content="Hi"+employeeName+"You have been added to HRMS You can check it by login into HRMS site with username:"+employeeEmail+"Password:"+randomPassword+"";
		  
		  MultiPartEmail email = new MultiPartEmail();
          email.setHostName(hostName);
          email.setSmtpPort(465);
          email.setAuthenticator(new DefaultAuthenticator(
                  authenticatorMail, authenticatorPassword));
          email.setSSLOnConnect(true);
          email.setFrom(from);
         
         // String subjectMail = sentMailToEmployee.getEvent() + " "
                 // + sentMailToEmployee.getEmployeeName();
         	               
          email.setSubject("HRMS registration");
          email.addTo(employeeEmail);
          email.setContent(content,"text/html");
          email.send();
          return "{\"status\": \"SUCCESS\", \"payload\": \"Mail Send\"}";
		  
		  
	  }
	 
}