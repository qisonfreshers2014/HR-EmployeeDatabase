package com.hred.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.hred.common.Utils;
import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.EncryptionException;
import com.hred.exception.ObjectNotFoundException;
import com.hred.model.Employee;
import com.hred.model.SendNotificationHistory;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.dao.SendNotificationHistoryDAO;
import com.hred.service.descriptors.input.EmployeeSearchInputDescriptor;
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

	
	public List<Employee> getEmployee() throws EmployeeException{
		List<Employee> employeelist = null;
		EmployeeDAO EmployeeDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
		employeelist = (List<Employee>)EmployeeDAOImpl.getEmployee();
		return employeelist;
	
	}
	
	public List<Employee> searchEmployee(EmployeeSearchInputDescriptor employee) throws EmployeeException{
		List<Employee> employeelist = null;
		EmployeeDAO EmployeeDAOImpl = (EmployeeDAO) DAOFactory.getInstance().searchEmployeeDAO();
		employeelist = (List<Employee>)EmployeeDAOImpl.searchEmployee(employee);
		return employeelist;
	
	}
	
	public String getEmployeeName(long id) {
		  EmployeeDAO empDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		  String empFirstName = empDAOImpl.getEmployeeName(id);
		  return empFirstName;
		 }
	
	public Employee getEmployeeById(long id) throws EmployeeException {
		Employee employee = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employee = (Employee) empDAOImpl.getEmployeeById(id);

		return employee;
	}

	public List<Employee> viewEmployee(Employee employee) {
		List<Employee> employees = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employees = (List<Employee>) empDAOImpl.viewEmployee(employee);
		return employees;
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

	public Employee save(Employee employee) throws EncryptionException {
		employee.setDeleted(false);
		employee.setPassword(Utils.encrypt(employee.getPassword()));
		
		Employee empSaved = (Employee) DAOFactory.getInstance()
				.getEmployeeDAO().saveObject(employee);
		return empSaved;
	}


	@SuppressWarnings("unchecked")
	public List<Employee> getTodayBirthday() throws BusinessException {
		List<Employee> emp = null;
		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		emp = employeeDAOImpl.getTodaysBirthday();

		return emp;
	}

	public Employee updateEmployee(Employee employee) throws ObjectNotFoundException, EmployeeException, EncryptionException{
		
		Employee empFromDB = (Employee)DAOFactory.getInstance().getEmployeeDAO().getEmployeeById(employee.getId());
		empFromDB.setContactNo(employee.getContactNo());
		empFromDB.setEmail(employee.getEmail());
		empFromDB.setCurrentAddress(employee.getCurrentAddress());
		empFromDB.setPermanentAddress(employee.getPermanentAddress());
		empFromDB.setEmergencycontactnumber(employee.getEmergencycontactnumber());
		empFromDB.setEmergencyContactName(employee.getEmergencyContactName());
		empFromDB.setRelationWithEmergencyConatact(employee.getRelationWithEmergencyConatact());
		empFromDB.setPassword(Utils.encrypt(employee.getPassword()));
		empFromDB.setSkype(employee.getSkype());
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employee = (Employee) empDAOImpl.update(empFromDB);
		return employee;
	}
public Employee hrUpdateEmployee(Employee employee) throws ObjectNotFoundException, EmployeeException, EncryptionException{
		
		Employee empFromDB = (Employee)DAOFactory.getInstance().getEmployeeDAO().getEmployeeById(employee.getId());
		empFromDB.setContactNo(employee.getContactNo());
		empFromDB.setEmail(employee.getEmail());
		empFromDB.setCurrentAddress(employee.getCurrentAddress());
		empFromDB.setPermanentAddress(employee.getPermanentAddress());
		empFromDB.setEmergencycontactnumber(employee.getEmergencycontactnumber());
		empFromDB.setEmergencyContactName(employee.getEmergencyContactName());
		empFromDB.setRelationWithEmergencyConatact(employee.getRelationWithEmergencyConatact());
		empFromDB.setPassword(Utils.encrypt(employee.getPassword()));
		empFromDB.setSkype(employee.getSkype());
		empFromDB.setEmployeeId(employee.getEmployeeId());
		empFromDB.setEmployeeName(employee.getEmployeeName());
		empFromDB.setBankAccountNo(employee.getBankAccountNo());
		empFromDB.setBloodGroup(employee.getBankAccountNo());
		empFromDB.setDateOfJoining(employee.getDateOfBirth());
		empFromDB.setFathersName(employee.getFathersName());
		empFromDB.setHighestQualification(employee.getHighestQualification());
		empFromDB.setPan(employee.getPan());
		empFromDB.setPfNo(employee.getPfNo());
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employee = (Employee) empDAOImpl.update(empFromDB);
		return employee;
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
			displayNotificationHome.setDate(birthday.getDateOfBirth());
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
			displayNotificationHome.setDate(aniversay.getDateOfJoining());
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
				displayNotificationHome.setDate(birthday.getDateOfBirth());
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
				displayNotificationHome.setDate(aniversay.getDateOfJoining());
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
