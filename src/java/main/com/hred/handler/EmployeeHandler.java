package com.hred.handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	List<Employee> empBirthday = null;
	List<Employee> empWorkAniversay = null;
	List<Employee> welcomeemp = null;
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

	@SuppressWarnings("null")
	// This method will return all the event of the company within one month
	public List<DisplayNotificationHome> getAllEvents()
			throws BusinessException {
		
		
		List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();

		SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
				.getInstance().getSendNotificationHistoryDAO();
		List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl
				.getHistorydata();
		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		empBirthday = employeeDAOImpl.getBirthday();
		empWorkAniversay = employeeDAOImpl.getWorkAniversary();

		// Converting eachEmployeeHistory to map
		Map<Integer, SendNotificationHistory> empidmapping = new HashedMap();
		for (SendNotificationHistory eachEmployeeHistory : notificationHistory) {
			empidmapping.put(eachEmployeeHistory.getEmployeeId(),
					eachEmployeeHistory);
		}
		
		
		welcomeemp = employeeDAOImpl.getWelcomeEmployee();
		for (Employee welEmp : welcomeemp) {
			DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
			displayNotificationHome.setEmployeeName(welEmp.getEmployeeName());
			displayNotificationHome.setDate(welEmp.getDateOfJoining());
			displayNotificationHome.setEmployeeEmail(welEmp.getEmail());
			displayNotificationHome.setEvent("Welcome");
			if (empidmapping.containsKey(welEmp.getEmployeeId())
					&& empidmapping.get(welEmp.getEmployeeId()).getTemplateId()
							.equalsIgnoreCase("02")) {
				displayNotificationHome.setStatus("Send");
			} else {
				displayNotificationHome.setStatus("Not Send");
			}
			displayNotificationHome.setEmployeeEmail(welEmp.getEmail());
			displayNotificationHomeList.add(displayNotificationHome);
		}
		
		// Entering Birthday Records To be displayed along with the notification
		// status

		for (Employee birthday : empBirthday) {
			DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
			displayNotificationHome.setEmployeeName(birthday.getEmployeeName());
			displayNotificationHome.setDate(birthday.getDateOfBirth());
			displayNotificationHome.setEmployeeEmail(birthday.getEmail());
			displayNotificationHome.setEvent("Birthday");
			if (empidmapping.containsKey(birthday.getEmployeeId())
					&& empidmapping.get(birthday.getEmployeeId())
							.getTemplateId().equalsIgnoreCase("01")) {
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
			displayNotificationHome.setDate(aniversay.getDateOfJoining());
			displayNotificationHome.setEmployeeEmail(aniversay.getEmail());
			displayNotificationHome.setEvent("Aniversary");
			if (empidmapping.containsKey(aniversay.getEmployeeId())
					&& empidmapping.get(aniversay.getEmployeeId())
							.getTemplateId().equalsIgnoreCase("02")) {
				displayNotificationHome.setStatus("Send");
			} else {
				displayNotificationHome.setStatus("Not Send");
			}
			displayNotificationHomeList.add(displayNotificationHome);
		}

	

		return displayNotificationHomeList;
	}


// This method will return the event which are satisfying the criteria conditions
public List<DisplayNotificationHome> getNotificationDisplayCriteria(NotificationHomeFilterInputDiscriptor filterCriteria)
		throws BusinessException {
	List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
	List<DisplayNotificationHome> displayNotificationHomeListAllevent = new ArrayList<DisplayNotificationHome>();
	List<Employee> empBirthdayWithDates = null;
	List<Employee> empWorkAniversayWithdates = null;
	String selectedEvent=filterCriteria.getSelectedEvent();
	EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
	if(filterCriteria.getFromdate() == null || filterCriteria.getTodate() == null)
	{
		empBirthdayWithDates=employeeDAOImpl.getBirthday();
		empWorkAniversayWithdates=employeeDAOImpl.getWorkAniversary();
	}
	else
	{
	
		//Retriving the Birthday from the database which lies within the the selected dates
		empBirthdayWithDates = employeeDAOImpl.getBirthdayWithindate(filterCriteria);
		//Retriving the Aniversaries from the database which lies within the the selected dates
		empWorkAniversayWithdates = employeeDAOImpl.getWorkAniversarywithdate(filterCriteria);
	}
	
	//getting the history Data from the database
	SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
			.getInstance().getSendNotificationHistoryDAO();
	List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl
			.getHistorydata();
	
	Map<Integer, SendNotificationHistory> empidmapping = new HashedMap();
	// Making a map with employee_id as key to check weather the nofification is send or not
	for (SendNotificationHistory eachEmployeeHistory : notificationHistory) {
		empidmapping.put(eachEmployeeHistory.getEmployeeId(),
				eachEmployeeHistory);
	}
	
		
	
		// Entering Birthdays Records To be displayed along with the
				// notification status
		if (selectedEvent.equalsIgnoreCase("birthDay")) {
		for (Employee birthday : empBirthdayWithDates) {
			DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
			displayNotificationHome.setEmployeeName(birthday.getEmployeeName());
			displayNotificationHome.setDate(birthday.getDateOfBirth());
			displayNotificationHome.setEmployeeEmail(birthday.getEmail());
			displayNotificationHome.setEvent("Birthday");
			if (empidmapping.containsKey(birthday.getEmployeeId())
					&& empidmapping.get(birthday.getEmployeeId())
							.getTemplateId().equalsIgnoreCase("01")) {
				displayNotificationHome.setStatus("Send");
			} else {
				displayNotificationHome.setStatus("Not Send");
			}

			displayNotificationHomeList.add(displayNotificationHome);
		}
		}
		// Entering Aniversary Records To be displayed along with the
		// notification status
		else if (selectedEvent.equalsIgnoreCase("workAniversary")){
		for (Employee aniversay : empWorkAniversayWithdates) {
			DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
			displayNotificationHome
					.setEmployeeName(aniversay.getEmployeeName());
			displayNotificationHome.setDate(aniversay.getDateOfJoining());
			displayNotificationHome.setEmployeeEmail(aniversay.getEmail());
			displayNotificationHome.setEvent("Aniversary");
			if (empidmapping.containsKey(aniversay.getEmployeeId())
					&& empidmapping.get(aniversay.getEmployeeId())
							.getTemplateId().equalsIgnoreCase("02")) {
				displayNotificationHome.setStatus("Send");
			} else {
				displayNotificationHome.setStatus("Not Send");
			}
			displayNotificationHomeList.add(displayNotificationHome);
		}
	
}
		
		else
		{
			for (Employee aniversay : empWorkAniversayWithdates) {
				DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
				displayNotificationHome
						.setEmployeeName(aniversay.getEmployeeName());
				displayNotificationHome.setDate(aniversay.getDateOfJoining());
				displayNotificationHome.setEmployeeEmail(aniversay.getEmail());
				displayNotificationHome.setEvent("Aniversary");
				if (empidmapping.containsKey(aniversay.getEmployeeId())
						&& empidmapping.get(aniversay.getEmployeeId())
								.getTemplateId().equalsIgnoreCase("02")) {
					displayNotificationHome.setStatus("Send");
				} else {
					displayNotificationHome.setStatus("Not Send");
				}
				displayNotificationHomeList.add(displayNotificationHome);
			}
			for (Employee birthday : empBirthdayWithDates) {
				DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
				displayNotificationHome.setEmployeeName(birthday.getEmployeeName());
				displayNotificationHome.setDate(birthday.getDateOfBirth());
				displayNotificationHome.setEmployeeEmail(birthday.getEmail());
				displayNotificationHome.setEvent("Birthday");
				if (empidmapping.containsKey(birthday.getEmployeeId())
						&& empidmapping.get(birthday.getEmployeeId())
								.getTemplateId().equalsIgnoreCase("01")) {
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
















































