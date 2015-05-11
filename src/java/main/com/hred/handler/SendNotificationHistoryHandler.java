package com.hred.handler;
/**
 * *
 *
 * @author Rizwan
 *         
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.hred.common.Constants;
import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
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

	public List<SendNotificationHistory> getHistorydata()
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
		DisplayNotificationHome requiredContent = new DisplayNotificationHome();
		 Template template = new Template();
		TemplateDAO tempDAOImpl = (TemplateDAO) DAOFactory.getInstance()
				.getTemplateDAO();
		EmployeeHandler employeeHandler=EmployeeHandler.getInstance();

		
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
					Email email = new SimpleEmail();
					email.setHostName("smtp.gmail.com");
					email.setSmtpPort(465);
					email.setAuthenticator(new DefaultAuthenticator(
							"hrmsqison@gmail.com", "Qison123"));
					email.setSSLOnConnect(true);
					email.setFrom("hrmsqison@gmail.com");
					email.setSubject("Happy Birth Day "+birthday.getEmployeeName());
					email.addTo(birthday.getEmail());
					email.setContent(body, "text/html");
					for(Employee addcctomail:AllEmployeesData)
					{
					email.addBcc(addcctomail.getEmail());
					}
					email.send();
					
						// send message
										entry.setTemplateId("01");
					entry.setEmployeeEmail(birthday.getEmail());
					entry.setEmployeeName(birthday.getEmployeeName());
					entry.setDeleted(false);
					for (Employee allemployee : AllEmployeesData) {
						if (allemployee.getEmail().equals(birthday.getEmail())) {
							entry.setEmployeeId(allemployee.getEmployeeId());
						}
					}
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
					Email email = new SimpleEmail();
					email.setHostName("smtp.gmail.com");
					email.setSmtpPort(465);
					email.setAuthenticator(new DefaultAuthenticator(
							"hrmsqison@gmail.com", "Qison123"));
					email.setSSLOnConnect(true);
					email.setFrom("hrmsqison@gmail.com");
					email.setSubject("Happy Work Anniversary  "+aniversary.getEmployeeName());
					email.addTo(aniversary.getEmail());
					for(Employee addcctomail:AllEmployeesData)
					{
					email.addBcc(addcctomail.getEmail());
					}
					email.setContent(body, "text/html");
					email.send();
					
					entry.setEmployeeEmail(aniversary.getEmail());
					entry.setEmployeeName(aniversary.getEmployeeName());
					entry.setDeleted(false);
					entry.setTemplateId("02");
					for (Employee allemployee : AllEmployeesData) {
						if (allemployee.getEmail()
								.equals(aniversary.getEmail())) {
							entry.setEmployeeId(allemployee.getEmployeeId());
						}
					}
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
			throws EmailException {
		{
			
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

			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(
					"hrmsqison@gmail.com", "Qison123"));
			email.setSSLOnConnect(true);
			email.setFrom("hrmsqison@gmail.com");
			String subjectMail = sentMailToEmployee.getEvent() + " "
					+ sentMailToEmployee.getEmployeeName();
			for(Employee addcctomail:AllEmployeesData)
			{
			email.addBcc(addcctomail.getEmail());
			}
			
			try {
				email.setSubject(subjectMail);
				email.addTo(sentMailToEmployee.getEmployeeEmail());
				if (sentMailToEmployee.getEvent().equalsIgnoreCase("birthday")) {
					entry.setTemplateId("01");
								
				
				} else if (sentMailToEmployee.getEvent().equalsIgnoreCase("Anniversary")) {
					entry.setTemplateId("02");
					
							}
				else
				{
					entry.setTemplateId("03");
				
				
				}
			
				email.setContent(body, "text/html");
								email.send();
				entry.setEmployeeName(sentMailToEmployee.getEmployeeName());
				entry.setEmployeeEmail(sentMailToEmployee.getEmployeeEmail());
				
				for (Employee allemployee : AllEmployeesData) {
					if (allemployee.getEmail().equals(
							sentMailToEmployee.getEmployeeEmail())) {
						entry.setEmployeeId(allemployee.getEmployeeId());
					}
				}
				SendNotificationHistory entrysaved = (SendNotificationHistory) DAOFactory
						.getInstance().getSendNotificationHistoryDAO()
						.saveObject(entry);
			} catch (EmailException e) {
				System.out.println("Unable to Send Msg");
			}
			return "{\"status\": \"SUCCESS\", \"payload\": \"Mail Send\"}";
		}
	}
}