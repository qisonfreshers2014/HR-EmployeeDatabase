package com.hred.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;









import com.hred.common.Constants;
import com.hred.common.Utils;
import com.hred.common.cache.CacheManager;
import com.hred.common.cache.CacheRegionType;
import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.EncryptionException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.exception.UserException;
import com.hred.handler.annotations.AuthorizeEntity;
import com.hred.model.Employee;
import com.hred.model.FilterEmployee;
import com.hred.model.SendNotificationHistory;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.dao.SendNotificationHistoryDAO;
import com.hred.service.common.ServiceRequestContextHolder;
import com.hred.service.descriptors.input.EmployeeSearchInputDescriptor;
import com.hred.service.descriptors.output.DisplayNotificationHome;
import com.hred.service.descriptors.output.NotificationHomeFilterInputDiscriptor;

public class EmployeeHandler extends AbstractHandler {

	private static EmployeeHandler INSTANCE = null;
	List<Employee> empBirthday = null;
	List<Employee> empWorkAniversay = null;
	List<Employee> welcomeemp = null;

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

	 public Employee getEmployeeById(int id) throws EmployeeException {
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
	
	public Employee getEmployeeById(String id) throws EmployeeException {
		Employee employee = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getUserDAO();
		employee = empDAOImpl.getEmployeeById(id);

		return employee;
	}


	
	 public List<Employee> getEmployees(FilterEmployee employee) throws	 EmployeeException { 
		  List<Employee> employees = null; EmployeeDAO
	 empDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
	 employees = empDAOImpl.getEmployees(employee);
	 return employees;
	 
	 }
	/*
	  public List<Employee> getEmployees(FilterEmployee employee) throws
	  EmployeeException { List<Employee> employees = null; EmployeeDAO
	  empDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
	  employees = empDAOImpl.getEmployees(employee); return employees;
	  
	  }
	 */

	public List<Employee> getEmployees() throws EmployeeException {
		List<Employee> employees = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
		employees = empDAOImpl.getEmployees();
		return employees;
	}

	
	public List<Employee> getEmployee() throws EmployeeException{
		List<Employee> employeelist = null;
		EmployeeDAO EmployeeDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
		employeelist = (List<Employee>)EmployeeDAOImpl.getEmployee();
		return employeelist;
	
	}
	
	@AuthorizeEntity(roles={Constants.HR})
	public Employee saveAOP(Employee employee) throws EncryptionException, BusinessException {
	employee.setDeleted(false);
	employee.setPassword(Utils.encrypt(employee.getPassword()));
	String name = employee.getEmployeeName();
	long id = employee.getEmployeeId();
	String email = employee.getEmail();
	String password = employee.getPassword();
	long descid = employee.getCurrentDesignation();
	String qualification = employee.getHighestQualification();
	String salary = employee.getSalary();
	String blood = employee.getBloodGroup();
	long num = employee.getContactNo();
	Timestamp date = employee.getDateOfBirth();
	String fatherName = employee.getFathersName();
	String gender = employee.getGender();
	long contactNum = employee.getContactNo();
	String skypeid = employee.getSkype();
	Timestamp doj = employee.getDateOfJoining();
	String skill = employee.getSkill();
	String rating = employee.getRating();
	int YOE = employee.getYearsofexperience();
	long emercontnum = employee.getEmergencycontactnumber();
	String emercontname = employee.getEmergencyContactName();
	String currentaddr = employee.getCurrentAddress();
	String peraddr = employee.getPermanentAddress();

	validateEmp(name, id, email, password, descid, qualification, salary,
			blood, num, date, fatherName, gender, contactNum, skypeid, doj,
			skill, rating, YOE, emercontnum, emercontname, currentaddr,
			peraddr);

	Boolean isempidExist = DAOFactory.getInstance().getEmployeeDAO()
			.getEmployeeByEmpId(employee.getEmployeeId());

	Boolean isemailExist = DAOFactory.getInstance().getEmployeeDAO()
			.isEmployeeEmailExist(employee.getEmail());
	if (isempidExist) {
		throw new EmployeeException(
				ExceptionCodes.EMPLOYEE_EMPID_ALREADY_EXIST,
				ExceptionMessages.EMPLOYEE_EMPID_ALREADY_EXIST);
	}

	else if (isemailExist) {
		throw new EmployeeException(ExceptionCodes.EMPLOYEE_ALREADY_EXIST,
				ExceptionMessages.EMPLOYEE_ALREADY_EXIST);
	}
	Employee empSaved = (Employee) DAOFactory.getInstance()
			.getEmployeeDAO().saveObject(employee);
	return empSaved;
}

	private void validateEmp(String name, long id, String email,
			String password, long descid, String qualification, String salary,

			String blood, long num, Timestamp date, String fatherName, String gender,
			long contactNum, String skypeid, Timestamp doj, String skill, String rating, int YOE,
			long emercontnum, String emercontname, String currentaddr, String peraddr ) throws BusinessException {
		



		if (currentaddr == null || currentaddr.isEmpty()) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_CURRENTADDR_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_CURRENTADDR_NOT_EMPTY);
		}

		if (emercontname == null || emercontname.isEmpty()) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_EMERNAME_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_EMERNAME_NOT_EMPTY);
		}

		if (emercontnum == 0) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_EMERNUM_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_EMERNUM_NOT_EMPTY);
		}

		if (YOE == 0) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_RATING_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_RATING_NOT_EMPTY);
		}

		if (rating == null || rating.isEmpty()) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_RATING_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_RATING_NOT_EMPTY);
		}

		if (skill == null || skill.isEmpty()) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_SKILL_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_SKILL_NOT_EMPTY);
		}

		if (doj == null) {
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_DOJ_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_DOJ_NOT_EMPTY);

		}

		if (skypeid == null || skypeid.isEmpty()) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_SKYPEID_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_SKYPEID_NOT_EMPTY);
		}

		if (contactNum == 0) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_CONTACTNUM_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_CONTACTNUM_NOT_EMPTY);
		}

		if (fatherName == null || fatherName.isEmpty()) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_FATHERNAME_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_FATHERNAME_NOT_EMPTY);
		}

		if (gender == "" || gender.isEmpty()) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_GENDER_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_GENDER_NOT_EMPTY);
		}

		if (name == null || name.isEmpty() || name.trim().isEmpty()) {
			throw new EmployeeException(ExceptionCodes.INVALID_NAME,
					ExceptionMessages.INVALID_NAME);
		}

		if (id == 0) {
			throw new EmployeeException(ExceptionCodes.INVALID_ROLE_ID,
					ExceptionMessages.INVALID_ROLE_ID);
		}

		if (email == null || email.isEmpty() || email.trim().isEmpty()) {
			throw new BusinessException(ExceptionCodes.EMAIL_CANNOT_BE_EMPTY,
					ExceptionMessages.EMAIL_CANNOT_BE_EMPTY);
		}
		/*
		 * boolean isEmailPatternValid = Pattern.compile(Utils.EMAIL_PATTERN)
		 * .matcher(email).matches(); if (!isEmailPatternValid) { throw new
		 * BusinessException(ExceptionCodes.INVALID_EMAIL_PATTERN,
		 * ExceptionMessages.INVALID_EMAIL_PATTERN); }
		 */
		if (password == null || password.isEmpty() || password.trim().isEmpty()) {
			throw new BusinessException(
					ExceptionCodes.PASSWORD_CANNOT_BE_EMPTY,
					ExceptionMessages.PASSWORD_CANNOT_BE_EMPTY);
		}
		boolean isPasswordStrengthValid = password.trim().length() >= Utils.MIN_PASSWORD_LENGTH;
		if (!isPasswordStrengthValid) {
			throw new BusinessException(ExceptionCodes.WEAK_PASSWORD,
					ExceptionMessages.WEAK_PASSWORD);
		}
		if (descid == 0) {
			throw new EmployeeException(
					ExceptionCodes.DESIGNATION_DOESNOT_EXIST,
					ExceptionMessages.DESIGNATION_DOESNOT_EXIST);
		}
		if (qualification == null || qualification.isEmpty()) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_QUALIFICATION_NOT_EXIST,
					ExceptionMessages.EMPLOYEE_QUALIFICATION_NOT_EXIST);
		}

		if (salary == null || salary.isEmpty()) {
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_SALARY,
					ExceptionMessages.EMPLOYEE_SALARY);
		}

		if (blood == null || blood.isEmpty()) {
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_BLOOD_GROUP,
					ExceptionMessages.EMPLOYEE_BLOOD_GROUP);
		}

		if (num == 0) {
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_NUMBER,
					ExceptionMessages.EMPLOYEE_NUMBER);
		}
		if (date == null) {
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_DOB_NULL,
					ExceptionMessages.EMPLOYEE_DOB_NULL);
		}
	}
	
	// updating the details of employee
	public Employee updateEmployee(Employee employee)
			   throws ObjectNotFoundException, EmployeeException,
			   EncryptionException {

			  Employee empFromDB = (Employee) DAOFactory.getInstance()
			    .getEmployeeDAO().getEmployeeById(employee.getEmployeeId());
			  empFromDB.setContactNo(employee.getContactNo());
			  empFromDB.setEmail(employee.getEmail());
			  empFromDB.setCurrentAddress(employee.getCurrentAddress());
			  empFromDB.setPermanentAddress(employee.getPermanentAddress());
			  empFromDB.setEmergencycontactnumber(employee
			    .getEmergencycontactnumber());
			  empFromDB.setEmergencyContactName(employee.getEmergencyContactName());
			  empFromDB.setRelationWithEmergencyConatact(employee
			    .getRelationWithEmergencyConatact());
			  empFromDB.setPassword(Utils.encrypt(employee.getPassword()));
			  empFromDB.setSkype(employee.getSkype());
			  EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
			    .getEmployeeDAO();
			  employee = (Employee) empDAOImpl.update(empFromDB);
			  return employee;
			 }


	// updating the details of employee by hr
			 public Employee hrUpdateEmployee(Employee employee)
			   throws ObjectNotFoundException, EmployeeException,
			   EncryptionException {

			  Employee empFromDB = (Employee) DAOFactory.getInstance()
			    .getEmployeeDAO().getEmployeeById(employee.getEmployeeId());
			  empFromDB.setContactNo(employee.getContactNo());
			  empFromDB.setEmail(employee.getEmail());
			  empFromDB.setCurrentAddress(employee.getCurrentAddress());
			  empFromDB.setPermanentAddress(employee.getPermanentAddress());
			  empFromDB.setEmergencycontactnumber(employee
			    .getEmergencycontactnumber());
			  empFromDB.setEmergencyContactName(employee.getEmergencyContactName());
			  empFromDB.setRelationWithEmergencyConatact(employee
			    .getRelationWithEmergencyConatact());
			  empFromDB.setPassword(Utils.encrypt(employee.getPassword()));
			  empFromDB.setSkype(employee.getSkype());
			  empFromDB.setBankAccountNo(employee.getBankAccountNo());
			  empFromDB.setFathersName(employee.getFathersName());
			  empFromDB.setHighestQualification(employee.getHighestQualification());
			  empFromDB.setPan(employee.getPan());
			  empFromDB.setPfNo(employee.getPfNo());
			  empFromDB.setSalary(employee.getSalary());
			  empFromDB.setGender(employee.getGender());
			  empFromDB.setBloodGroup(employee.getBloodGroup());
			//  empFromDB.setEmployeeId(employee.getEmployeeId());
			  empFromDB.setEmployeeName(employee.getEmployeeName());
			  empFromDB.setVariableComponent(employee.getVariableComponent());
			  EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
			    .getEmployeeDAO();
			  employee = (Employee) empDAOImpl.update(empFromDB);
			  return employee;
			 }

	
@SuppressWarnings("null")
// This method will return all the event of the company within one month
@AuthorizeEntity(roles={Constants.HR})
public List<DisplayNotificationHome> getAllEventsAOP()
  throws BusinessException {

 List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
 List<SendNotificationHistory> notificationHistory = new ArrayList<SendNotificationHistory>();

 SendNotificationHistoryHandler sendnotificationHandler= SendNotificationHistoryHandler.getInstance();
 notificationHistory = sendnotificationHandler.getHistorydata();
 
/* SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
   .getInstance().getSendNotificationHistoryDAO();
 notificationHistory = SendNotificationHistoryDAOImpl.getHistorydata();*/
 EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
 empBirthday = employeeDAOImpl.getBirthday();
 empWorkAniversay = employeeDAOImpl.getWorkAniversary();

 // Converting eachEmployeeHistory to map

 displayNotificationHomeList.addAll(getAnivarsaryList(empWorkAniversay));
 displayNotificationHomeList.addAll(getBirthdaysList(empBirthday));
 displayNotificationHomeList.addAll(getWelcomeEmployeeList());

 return displayNotificationHomeList;
}

// This method will return the event which are satisfying the criteria
// conditions
@AuthorizeEntity(roles={Constants.HR})
public List<DisplayNotificationHome> getNotificationDisplayCriteriaAOP(
  NotificationHomeFilterInputDiscriptor filterCriteria)
  throws BusinessException {
 List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();

 List<Employee> empBirthdayWithDates = null;
 List<Employee> empWorkAniversayWithdates = null;
 String selectedEvent = filterCriteria.getSelectedEvent();
 System.out.println(selectedEvent);
 EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
 if (filterCriteria.getFromdate() == null
   || filterCriteria.getTodate() == null) {
  empBirthdayWithDates = employeeDAOImpl.getBirthday();
  empWorkAniversayWithdates = employeeDAOImpl.getWorkAniversary();
  if (selectedEvent.equalsIgnoreCase("Welcome")) {
	  displayNotificationHomeList=getWelcomeEmployeeList();
  }
  }
  
 else {

  // Retriving the Birthday from the database which lies within the
  // the selected dates
  empBirthdayWithDates = employeeDAOImpl
    .getBirthdayWithindate(filterCriteria);
  // Retriving the Aniversaries from the database which lies within
  // the the selected dates
  empWorkAniversayWithdates = employeeDAOImpl
    .getWorkAniversarywithdate(filterCriteria);
 }


 // Entering Birthdays Records To be displayed along with the
 // notification status
 if (selectedEvent.equalsIgnoreCase("birthDay")) {
  displayNotificationHomeList = getBirthdaysList(empBirthdayWithDates);
  if(displayNotificationHomeList.size() ==0)
  {
	  throw new UserException(ExceptionCodes.NO_BIRTHDAY_FOUND, ExceptionMessages.NO_BIRTHDAY_FOUND);
  }
 }
 
 
 else if (selectedEvent.equalsIgnoreCase("Welcome"))
 {
	 if(displayNotificationHomeList.size()==0)
	  {
	 throw new UserException(ExceptionCodes.NO_WELCOME_EMPLOYEE, ExceptionMessages.NO_WELCOME_EMPLOYEE);
	  }
 }
 
 
 // Entering Aniversary Records To be displayed along with the
 // notification status
 else if (selectedEvent.equalsIgnoreCase("workAniversary")) {

  displayNotificationHomeList = getAnivarsaryList(empWorkAniversayWithdates);
	
  if(displayNotificationHomeList.size() ==0)
  {
	  throw new UserException(ExceptionCodes.NO_EMPLOYEE_JOINED_TODAY, ExceptionMessages.NO_EMPLOYEE_JOINED_TODAY);
  }
 }
 // If no date criteria is selected this else will return the events in
 // the current month
 else {
  displayNotificationHomeList
    .addAll(getAnivarsaryList(empWorkAniversayWithdates));
  displayNotificationHomeList
    .addAll(getBirthdaysList(empBirthdayWithDates));
  if(displayNotificationHomeList.size()==0)
  {
	  throw new UserException(ExceptionCodes.NO_EVENT_FOUND, ExceptionMessages.NO_EVENT_FOUND); 
  }

 }

 return displayNotificationHomeList;

}


// This Method will return the birthday list with a selected criteria
public List<DisplayNotificationHome> getBirthdaysList(
		List<Employee> employeeBirthday) throws BusinessException {
	/*SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
			.getInstance().getSendNotificationHistoryDAO();
	List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl
			.getHistorydata();*/
	 List<SendNotificationHistory> notificationHistory = new ArrayList<SendNotificationHistory>();
	 SendNotificationHistoryHandler sendnotificationHandler= SendNotificationHistoryHandler.getInstance();
	 notificationHistory = sendnotificationHandler.getHistorydata();
	List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();

	for (Employee birthday : employeeBirthday) {
		DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome(
				"Birthday", birthday.getDateOfBirth(), birthday.getEmail(),
				birthday.getEmployeeName());

		if (notificationHistory.size() != 0) {
			for (SendNotificationHistory checkingHistory : notificationHistory) {
				if (checkingHistory.getEmployeeId() == birthday
						.getEmployeeId()
						&& checkingHistory.getTemplateId().equals("01")) {
					displayNotificationHome.setStatus("Sent");
					break;
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

// This Method will return the Anivarsary list with a selected criteria
public List<DisplayNotificationHome> getAnivarsaryList(
		List<Employee> employeeAniversary) throws BusinessException {
	/*SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
			.getInstance().getSendNotificationHistoryDAO();
	List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl
			.getHistorydata();*/
	 List<SendNotificationHistory> notificationHistory = new ArrayList<SendNotificationHistory>();
	 SendNotificationHistoryHandler sendnotificationHandler= SendNotificationHistoryHandler.getInstance();
	 notificationHistory = sendnotificationHandler.getHistorydata();
	List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();

	for (Employee anivarsary : employeeAniversary) {
		DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome(
				"Anniversary", anivarsary.getDateOfJoining(),
				anivarsary.getEmail(), anivarsary.getEmployeeName());

		if (notificationHistory.size() != 0) {
			for (SendNotificationHistory checkingHistory : notificationHistory) {
				if (checkingHistory.getEmployeeId() == anivarsary
						.getEmployeeId()
						&& checkingHistory.getTemplateId().equals("02")) {
					displayNotificationHome.setStatus("Sent");
					break;
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

// This Method will return the birthday list with a selected criteria
public List<DisplayNotificationHome> getWelcomeEmployeeList()
		throws BusinessException

{
	EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
/*	SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
			.getInstance().getSendNotificationHistoryDAO();
	List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl
			.getHistorydata();*/
	 List<SendNotificationHistory> notificationHistory = new ArrayList<SendNotificationHistory>();
	 SendNotificationHistoryHandler sendnotificationHandler= SendNotificationHistoryHandler.getInstance();
	 notificationHistory = sendnotificationHandler.getHistorydata();
	List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
	welcomeemp = employeeDAOImpl.getWelcomeEmployee();
	for (Employee welEmp : welcomeemp) {
		DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome(
				"WelCome", welEmp.getDateOfJoining(),
				welEmp.getEmail(), welEmp.getEmployeeName());

		if (notificationHistory.size() != 0) {
			for (SendNotificationHistory checkingHistory : notificationHistory) {
				if (checkingHistory.getEmployeeId() == welEmp
						.getEmployeeId()
						&& checkingHistory.getTemplateId().equals("03")) {
					displayNotificationHome.setStatus("Sent");
					break;
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
public List<Employee> searchEmployee(EmployeeSearchInputDescriptor employee) {

	List<Employee> employeelist = null;
	EmployeeDAO EmployeeDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
	employeelist = (List<Employee>)EmployeeDAOImpl.searchEmployee(employee);
	return employeelist;
}

//hr delete operation

public Employee deleteEmployee(Employee employee) throws EmployeeException {
	Employee empFromDB = (Employee) DAOFactory.getInstance()
		    .getEmployeeDAO().getEmployeeById(employee.getEmployeeId());
		  empFromDB.setDeleted(true);
		  EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
		    .getEmployeeDAO();
		  employee = (Employee) empDAOImpl.update(empFromDB);
		  return employee;
}
public boolean logout(){
	boolean isLogout = false;
	String userSessionId = ServiceRequestContextHolder.getContext()
			.getUserSessionToken().getUserSessionId();
	
	isLogout = CacheManager.getInstance().getCache(CacheRegionType.USER_SESSION_CACHE).remove(userSessionId);
	return isLogout;
}

}
