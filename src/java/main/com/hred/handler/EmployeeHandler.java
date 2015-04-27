package com.hred.handler;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.model.Employee;
import com.hred.model.SendNotificationHistory;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.dao.SendNotificationHistoryDAO;
import com.hred.service.descriptors.output.DisplayNotificationHome;
import com.hred.service.descriptors.output.NotificationHomeFilterInputDiscriptor;

public class EmployeeHandler extends AbstractHandler {


	private static EmployeeHandler INSTANCE = null;

	private EmployeeHandler() {
	}

	/**
	 * @return instance of EmployeeHandler
	 */
	public static EmployeeHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new EmployeeHandler();
		return INSTANCE;
	}

 
	
	public List<Employee> getFilterEmployeeDetails(Employee employee) throws EmployeeException {
		List<Employee> employees = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
		employees = (List<Employee>) empDAOImpl.getFilterEmployeeDetails(employee);
		return employees;
	}
	

	public Employee getEmployeeById(String id) throws EmployeeException {
		Employee employee = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getUserDAO();
		employee = empDAOImpl.getEmployeeById(id);

		return employee;
	}

	public List<Employee> getEmployees() throws EmployeeException {
		List<Employee> employees = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getUserDAO();
		employees = empDAOImpl.getEmployees();
		return employees;
	}

	public Employee save(Employee employee) {
		Employee empSaved = (Employee) DAOFactory.getInstance()
				.getEmployeeDAO().saveObject(employee);
		return empSaved;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getTodayBirthday() throws BusinessException {
		List<Employee> emp = null;
		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		emp = employeeDAOImpl.getBirthday();

		return emp;
	}


	public List<Employee> getWorkAniversary() throws BusinessException {
		// TODO Auto-generated method stub
		List<Employee> emp = null;
		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		emp = employeeDAOImpl.getWorkAniversary();

		return emp;
	}


	@SuppressWarnings("null")
	public List<DisplayNotificationHome> getAllEvents()
			throws BusinessException {
		List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
		// TODO Auto-generated method stub
		List<Employee> empBirthday = null;
		List<Employee> empWorkAniversay = null;
		
		//DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
		//List<SendNotificationHistory> notificationHistory = new ArrayList<SendNotificationHistory>();

		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		empBirthday = employeeDAOImpl.getBirthday();
		empWorkAniversay = employeeDAOImpl.getWorkAniversary();		
		

		SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
				.getInstance().getSendNotificationHistoryDAO();
		List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl.getHistorydata();
		Map<Integer, String> empidmapping = new HashedMap();					
		// Converting eachEmployeeHistory to map
		for (SendNotificationHistory eachEmployeeHistory : notificationHistory) {
			empidmapping.put(eachEmployeeHistory.getEmployeeId(),eachEmployeeHistory.getTemplateId());
		}
		// Entering Birthday Records To be displayed along with the notification
		// status
		
		
		for (Employee birthday : empBirthday) {
			DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
			displayNotificationHome.setEmployeeName(birthday.getEmployeeName());
			displayNotificationHome.setDate(birthday.getDOB());
			displayNotificationHome.setEmployeeEmail(birthday.getEmail());
			displayNotificationHome.setEvent("Birthday");
			if (empidmapping.containsKey(birthday.getEmployeeId())) {
				displayNotificationHome.setStatus("Send");
			} else {
				displayNotificationHome.setStatus("Not Send");
			}
			
			displayNotificationHomeList.add(displayNotificationHome);
		}
		// Entering Aniversary Records To be displayed along with the
		// notification status
		for (Employee aniversay : empWorkAniversay) {
			DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
			displayNotificationHome
					.setEmployeeName(aniversay.getEmployeeName());
			displayNotificationHome.setDate(aniversay.getDOJ());
			displayNotificationHome.setEmployeeEmail(aniversay.getEmail());
			displayNotificationHome.setEvent("Aniversay");
			if (empidmapping.containsKey(aniversay.getEmployeeId())) {
				displayNotificationHome.setStatus("Send");
			} else {
				displayNotificationHome.setStatus("Not Send");
			}
			displayNotificationHomeList.add(displayNotificationHome);
		}

		return displayNotificationHomeList;
	}

	public List<DisplayNotificationHome> getNotificationDisplayCriteria(
			NotificationHomeFilterInputDiscriptor filterCriteria) throws BusinessException {
		List<Employee> empBirthday = null;
		List<Employee> empWorkAniversay = null;
		List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
		
		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		empBirthday = employeeDAOImpl.getBirthday();
		empWorkAniversay = employeeDAOImpl.getWorkAniversary();
		
		// Taking History Data From the NotificationHistory Table
		SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory.getInstance().getSendNotificationHistoryDAO();
		List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl.getHistorydata();
		Map<Integer, SendNotificationHistory> empidmapping = new HashedMap();					
		// Converting eachEmployeeHistory to map
		for (SendNotificationHistory eachEmployeeHistory : notificationHistory) {
			empidmapping.put(eachEmployeeHistory.getEmployeeId(),eachEmployeeHistory);
		}
		
		
		 java.util.Date todate=filterCriteria.getTodate();
	 java.util.Date fromdate=filterCriteria.getFromdate();
		 String selectedEvent=filterCriteria.getSelectedEvent();
		String selectedState=filterCriteria.getSelectedState();
		
		if(selectedEvent.equalsIgnoreCase("birthDay"))
				{
			for (Employee birthday : empBirthday) {
				DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
				displayNotificationHome.setEmployeeName(birthday.getEmployeeName());
				displayNotificationHome.setEmployeeEmail(birthday.getEmail());
				displayNotificationHome.setDate(birthday.getDOB());
				displayNotificationHome.setEvent("Birthday");
				if (empidmapping.containsKey(birthday.getEmployeeId())) {
					displayNotificationHome.setStatus("Send");
				} else {
					displayNotificationHome.setStatus("Not Send");
				}
				
				displayNotificationHomeList.add(displayNotificationHome);
			}
				}
		else if(selectedEvent.equalsIgnoreCase("workAniversary"))
		{
			for (Employee aniversay : empWorkAniversay) {
				DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
				displayNotificationHome.setEmployeeName(aniversay.getEmployeeName());
				displayNotificationHome.setEmployeeEmail(aniversay.getEmail());
				displayNotificationHome.setDate(aniversay.getDOJ());
				displayNotificationHome.setEvent("Aniversay");
				if (empidmapping.containsKey(aniversay.getEmployeeId())) {
					displayNotificationHome.setStatus("Send");
				} else {
					displayNotificationHome.setStatus("Not Send");
				}
				displayNotificationHomeList.add(displayNotificationHome);
			}
		}
		
			
		
		return displayNotificationHomeList;
	}

}
