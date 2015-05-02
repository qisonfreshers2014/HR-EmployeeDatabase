package com.hred.handler;

import java.util.ArrayList;
import java.util.List;




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

	

@SuppressWarnings("null")
// This method will return all the event of the company within one month
public List<DisplayNotificationHome> getAllEvents()
  throws BusinessException {
 
 
 List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
 List<SendNotificationHistory> notificationHistory = new ArrayList<SendNotificationHistory>();

 SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
   .getInstance().getSendNotificationHistoryDAO();
 notificationHistory = SendNotificationHistoryDAOImpl
   .getHistorydata();
 EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
 empBirthday = employeeDAOImpl.getBirthday();
 empWorkAniversay = employeeDAOImpl.getWorkAniversary();

 // Converting eachEmployeeHistory to map
 
 displayNotificationHomeList.addAll(getAnivarsaryList(empWorkAniversay));
 displayNotificationHomeList.addAll(getBirthdaysList(empBirthday));
 displayNotificationHomeList.addAll(getWelcomeEmployeeList());


 return displayNotificationHomeList;
}


//This method will return the event which are satisfying the criteria conditions
public List<DisplayNotificationHome> getNotificationDisplayCriteria(
  NotificationHomeFilterInputDiscriptor filterCriteria)
  throws BusinessException {
 List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
 
 List<Employee> empBirthdayWithDates = null;
 List<Employee> empWorkAniversayWithdates = null;
 String selectedEvent = filterCriteria.getSelectedEvent();  
 EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
 if (filterCriteria.getFromdate() == null
   || filterCriteria.getTodate() == null) {
  empBirthdayWithDates = employeeDAOImpl.getBirthday();
  empWorkAniversayWithdates = employeeDAOImpl.getWorkAniversary();
 } else {

  // Retriving the Birthday from the database which lies within the
  // the selected dates
  empBirthdayWithDates = employeeDAOImpl
    .getBirthdayWithindate(filterCriteria);
  // Retriving the Aniversaries from the database which lies within
  // the the selected dates
  empWorkAniversayWithdates = employeeDAOImpl
    .getWorkAniversarywithdate(filterCriteria);
 }

 // getting the history Data from the database
 SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
   .getInstance().getSendNotificationHistoryDAO();
 List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl
   .getHistorydata();


 // Entering Birthdays Records To be displayed along with the
 // notification status
 if (selectedEvent.equalsIgnoreCase("birthDay")) {
  displayNotificationHomeList=getBirthdaysList(empBirthdayWithDates);
 }
 // Entering Aniversary Records To be displayed along with the
 // notification status
 else if (selectedEvent.equalsIgnoreCase("workAniversary")) {
 
  displayNotificationHomeList=getAnivarsaryList(empBirthdayWithDates);
 }
//If no date criteria is selected this else will return the events in the current month 
 else {
 displayNotificationHomeList.addAll(getAnivarsaryList(empBirthdayWithDates));
 displayNotificationHomeList.addAll(getBirthdaysList(empBirthdayWithDates));
   
 }

 return displayNotificationHomeList;

}

//This Method will return the birthday list with a selected criteria
public List<DisplayNotificationHome> getBirthdaysList(List<Employee> employeeBirthday )
{
 SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
   .getInstance().getSendNotificationHistoryDAO();
 List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl
   .getHistorydata();
 List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
 
 for (Employee birthday : employeeBirthday) {
  DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome(
    "Birthday", birthday.getDateOfBirth(),
    birthday.getEmail(), birthday.getEmployeeName());

  if (notificationHistory.size() != 0) {
   for (SendNotificationHistory checkingHistory : notificationHistory) {
    if (checkingHistory.getEmployeeId() == birthday
      .getEmployeeId()
      && checkingHistory.getTemplateId().equals("01")) {
     displayNotificationHome.setStatus("Send");
    } else {
     displayNotificationHome.setStatus("Not Send");
    }
   }
  } else {
   displayNotificationHome.setStatus("Not Send");
  }

  displayNotificationHomeList.add(displayNotificationHome);
 }
 return displayNotificationHomeList;
}

//This Method will return the Anivarsary list with a selected criteria
public List<DisplayNotificationHome> getAnivarsaryList(List<Employee> employeeAnivarsary )
{
 SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
   .getInstance().getSendNotificationHistoryDAO();
 List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl
   .getHistorydata();
 List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
 
 for (Employee birthday : employeeAnivarsary) {
  DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome(
    "Anivarsary", birthday.getDateOfBirth(),
    birthday.getEmail(), birthday.getEmployeeName());

  if (notificationHistory.size() != 0) {
   for (SendNotificationHistory checkingHistory : notificationHistory) {
    if (checkingHistory.getEmployeeId() == birthday
      .getEmployeeId()
      && checkingHistory.getTemplateId().equals("02")) {
     displayNotificationHome.setStatus("Sent");
    } else {
     displayNotificationHome.setStatus("Not Sent");
    }
   }
  } else {
   displayNotificationHome.setStatus("Not Sent");
  }

  displayNotificationHomeList.add(displayNotificationHome);
 }
 return displayNotificationHomeList;
}


//This Method will return the birthday list with a selected criteria
public List<DisplayNotificationHome> getWelcomeEmployeeList() throws BusinessException

{
 EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
 SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
.getInstance().getSendNotificationHistoryDAO();
List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl
.getHistorydata();
List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
 welcomeemp = employeeDAOImpl.getWelcomeEmployee();
 for (Employee welEmp : welcomeemp) {
  DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome(
    "Wel Come", welEmp.getDateOfBirth(), welEmp.getEmail(),
    welEmp.getEmployeeName());
  for (SendNotificationHistory checkinghistory : notificationHistory) {
   if (checkinghistory.getEmployeeEmail()
     .equals(welEmp.getEmail())
     && checkinghistory.getTemplateId().equalsIgnoreCase(
       "03")) {
    displayNotificationHome.setStatus("Send");
    break;
   } else {
    displayNotificationHome.setStatus("Not Send");
   }
  }

  displayNotificationHomeList.add(displayNotificationHome);
 }
 return displayNotificationHomeList;
}


}
