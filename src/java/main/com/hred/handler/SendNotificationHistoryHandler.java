package com.hred.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.hred.exception.BusinessException;
import com.hred.model.Employee;
import com.hred.model.SendNotificationHistory;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.dao.SendNotificationHistoryDAO;
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

	public String SentAutomatedMailMail() throws BusinessException {
		
			EmployeeDAO employeeDAOImpl = DAOFactory.getInstance()
					.getEmployeeDAO();
			List<Employee> todaysBithday = employeeDAOImpl.getTodaysBirthday();
			List<Employee> todaysWorkAniversay = employeeDAOImpl
					.getTodayWorkAniversary();
			List<Employee> AllEmployeesData = new ArrayList<Employee>();
			SendNotificationHistory entry = new SendNotificationHistory();
			EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
					.getEmployeeDAO();
			AllEmployeesData = empDAOImpl.getEmployees();
			String from = "rizzkhan02@gmail.com";// change accordingly
			String host = "192.168.10.4";// or IP address

			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"rizzkhan02@gmail.com", "Rizzkhan02@@");// change
																			// accordingly
						}
					});

			if (todaysBithday.size() != 0) {
				for (Employee birthday : todaysBithday) {

					try {
						String to = birthday.getEmail();
						MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress(
								"rizzkhan02@gmail.com"));// change accordingly
						message.addRecipient(Message.RecipientType.TO,
								new InternetAddress(to));
						message.setSubject("Happy BirthDay "
								+ birthday.getEmployeeName());
						message.setText("Testing.......");

						// send message
						Transport.send(message);
						entry.setTemplateId("01");
						entry.setEmployeeEmail(birthday.getEmail());
						entry.setEmployeeName(birthday.getEmployeeName());
						entry.setDeleted(false);
						for (Employee allemployee : AllEmployeesData) {
							if (allemployee.getEmail().equals(
									birthday.getEmail())) {
								entry.setEmployeeId(allemployee.getEmployeeId());
							}
						}
						save(entry);

					} catch (MessagingException e) {
						throw new RuntimeException(e);
					}

				}
			}
			if (todaysWorkAniversay.size() != 0) {
				for (Employee aniversary : todaysWorkAniversay) {

					try {
						String to = aniversary.getEmail();
						MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress(
								"rizzkhan02@gmail.com"));// change accordingly
						message.addRecipient(Message.RecipientType.TO,
								new InternetAddress(to));
						message.setSubject("Happy BirthDay "
								+ aniversary.getEmployeeName());
						message.setText("Testing.......");

						// send message
						Transport.send(message);
						entry.setEmployeeEmail(aniversary.getEmail());
						entry.setEmployeeName(aniversary.getEmployeeName());
						entry.setDeleted(false);
						entry.setTemplateId("02");
						for (Employee allemployee : AllEmployeesData) {
							if (allemployee.getEmail().equals(
									aniversary.getEmail())) {
								entry.setEmployeeId(allemployee.getEmployeeId());
							}
						}
						save(entry);

					} catch (MessagingException e) {
						throw new RuntimeException(e);
					}

				}

			}
		

		return "{\"status\": \"SUCCESS\", \"payload\": \"Mail Send\"}";

	}

	public String SentMail(DisplayNotificationHome sentMailToEmployee)
			throws EmailException {
		{

			String body = "Hi " + sentMailToEmployee.getEmployeeName() + "/n";
			SendNotificationHistory entry = new SendNotificationHistory();
			List<Employee> AllEmployeesData = new ArrayList<Employee>();
			EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
					.getEmployeeDAO();
			AllEmployeesData = empDAOImpl.getEmployees();

			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(
					"rizzkhan02@gmail.com", "Rizzkhan02@@"));
			email.setSSLOnConnect(true);
			email.setFrom("rizzkhan02@gmail.com");
			try {
				email.setSubject(sentMailToEmployee.getEvent());
				email.setMsg(body);
     
				email.addTo(sentMailToEmployee.getEmployeeEmail());
				email.send();
				 if(sentMailToEmployee.getEvent().equalsIgnoreCase("birthday"))
			      {
			    	  entry.setTemplateId("01");
			      }
			      else
			      {
			    	  entry.setTemplateId("02");
			      }
				 
				entry.setEmployeeName(sentMailToEmployee.getEmployeeName());
				entry.setEmployeeEmail(sentMailToEmployee.getEmployeeEmail());
				entry.setTemplateId("01");
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
