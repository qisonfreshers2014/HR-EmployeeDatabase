package com.hred.handler;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.hred.common.ConfigReader;
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
import com.hred.model.DesignationHistory;
import com.hred.model.DesignationType;
import com.hred.model.Employee;
import com.hred.model.EmployeeOutFile;
import com.hred.model.File;
import com.hred.model.FilterEmployee;
import com.hred.model.SendNotificationHistory;
import com.hred.model.Skills;
import com.hred.model.output.CompareDates;
import com.hred.pagination.EmployeeListPaginationInput;
import com.hred.pagination.NotificationPaginationInput;
import com.hred.pagination.PaginationOutput;
import com.hred.pagination.Paginator;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.service.common.ServiceRequestContextHolder;
import com.hred.service.descriptors.input.ChangePassword;
import com.hred.service.descriptors.input.EmployeeSearchInputDescriptor;
import com.hred.service.descriptors.output.DisplayNotificationHome;
import com.hred.service.descriptors.output.NotificationHomeFilterInputDiscriptor;

public class EmployeeHandler extends AbstractHandler {

	private static final int NOT_BE_GRETER_THAN_TO_DOJ = 0;
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

	public Employee viewEmployee(int EmployeeId) throws NumberFormatException,
			BusinessException {
		Employee employee = null;
		DesignationType desgn = null;
		String path = null;
		String stage = null;
		try {
			Properties props = ConfigReader
					.getProperties(Constants.FILE_PATH_VARIABLES);
			stage = props.getProperty(Constants.STAGE_ENVIRONMENT);
			if (stage.equalsIgnoreCase("local")) {
				path = props.getProperty(Constants.LOCAL_PATH);
			} else if (stage.equalsIgnoreCase("stage")) {
				path = props.getProperty(Constants.STAGE_PATH);
			} else {
				path = props.getProperty(Constants.PRODUCTION_PATH);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employee = (Employee) empDAOImpl.viewEmployee(EmployeeId);
		EmployeeOutFile employeeout = new EmployeeOutFile(employee);
     
		
		
		if (employee.getFileId() != 0) {
			File file = FileHandler.getInstance().getFile(employee.getFileId());
			String image = "<img src='" + path + file.getFilePath()
					+ "' height='150' width='150'>";
			employeeout.setFilePath(image);
			
			System.out.println("file path is"+file.getFilePath());
		
		}
		List<Skills> skill = SkillsHandler.getInstance().getSkillsById(
				EmployeeId);
		String finalSkills = employee.getSkill();
		String finalRatings=employee.getRating();
		for (Skills sk : skill) {

			finalSkills += " , " + sk.getSkills();
			finalRatings +=" , " +sk.getRating();
		}
		

		List<DesignationType> desg = DesignationHistoryHandler.getInstance()
				.getDesignationNameAOP(desgn);

		String desgName = "";
		for (DesignationType dg : desg) {
			if (dg.getId() == employee.getCurrentDesignation()) {

				desgName += dg.getName();

			}
		}
   System.out.println(finalRatings);
		employeeout.setDesignationName(desgName);
		employeeout.setSkill(finalSkills);
		employeeout.setRating(finalRatings);
		return employeeout;

	}

	public Employee getEmployeeById(String id) throws EmployeeException {
		Employee employee = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getUserDAO();
		employee = empDAOImpl.getEmployeeById(id);

		return employee;
	}

	//@AuthorizeEntity(roles = { Constants.HR })
	public List<Employee> getEmployeesAOP(FilterEmployee employee)
			throws EmployeeException {
		List<Employee> employees = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employees = empDAOImpl.getEmployees(employee);
		
		
		if(employee.getGender()==null && employee.getDateOfJoiningFrom()==null && employee.getDateOfJoiningTo()==null && employee.getHighestQualification()==null && employee.getFilterEmployee()==0 && employee.getFrom()==null && employee.getTo()==null && employee.getCurrentDesignation()==0){
			
			throw new EmployeeException(
					ExceptionCodes.SELECT_ATLEAST_ONE_FIELD,
					ExceptionMessages.SELECT_ATLEAST_ONE_FIELD);
			
		}
		
		if(employee.getDateOfJoiningTo()!=null && employee.getDateOfJoiningFrom()!=null){
			
			 long FromDOJ=employee.getDateOfJoiningFrom().getTime();
			 
		       long ToDOJ=employee.getDateOfJoiningTo().getTime();
		
                if(FromDOJ>ToDOJ){
        	
        	throw new EmployeeException(
					ExceptionCodes.FROM_DOJ_CANNOT_BE_GRETER_THAN_TO_DOJ,
					ExceptionMessages.FROM_DOJ_CANNOT_BE_GRETER_THAN_TO_DOJ);
        	
        }
		
		}
		
		return employees;

	}

	/*
	 * public List<Employee> getEmployees(FilterEmployee employee) throws
	 * EmployeeException { List<Employee> employees = null; EmployeeDAO
	 * empDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
	 * employees = empDAOImpl.getEmployees(employee); return employees;
	 * 
	 * }
	 */

	public List<Employee> getEmployees() throws EmployeeException {
		List<Employee> employees = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employees = empDAOImpl.getEmployees();
		return employees;
	}

	public List<Employee> getEmployee() throws EmployeeException {
		List<Employee> employeelist = null;
		EmployeeDAO EmployeeDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employeelist = (List<Employee>) EmployeeDAOImpl.getEmployee();
		return employeelist;

	}

	@AuthorizeEntity(roles = { Constants.HR })
	public Employee saveAOP(Employee employee) throws EncryptionException,
			BusinessException {
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
		double YOE = employee.getYearsofexperience();
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

		String salarydes = employee.getSalary();
		if (salarydes == "") {
			salarydes = "0";
		}

		double salarydouble = Double.parseDouble(salarydes);
		String variable = employee.getVariableComponent();
		if (variable == "") {
			variable = "0";
		}
		/*
		 * double variabledouble = Double.parseDouble(variable);
		 * DesignationHistory designationHistory = new DesignationHistory();
		 * designationHistory.setEmpId(employee.getEmployeeId());
		 * designationHistory.setAppraisalDate(employee.getDateOfJoining());
		 * designationHistory
		 * .setDesignationId(employee.getCurrentDesignation());
		 * designationHistory.setSalary(salarydouble);
		 * designationHistory.setVariablePay(variabledouble);
		 * DesignationHistoryHandler.getInstance().saveAOP(designationHistory);
		 * // saving // in // designation // history
		 */
		Skills empskill = new Skills();
		empskill.setSkills(employee.getSkill());
		empskill.setRating(employee.getRating());
		empskill.setEmpId(employee.getEmployeeId());
		SkillsHandler.getInstance().saveAOP(empskill); // saving in skills table
		return empSaved;

	}

	private void validateEmp(String name, long id, String email,
			String password, long descid, String qualification, String salary,

			String blood, long num, Timestamp date, String fatherName,
			String gender, long contactNum, String skypeid, Timestamp doj,
			String skill, String rating, double YOE, long emercontnum,
			String emercontname, String currentaddr, String peraddr)
			throws BusinessException {

		if (currentaddr == null || currentaddr.isEmpty()) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_CURRENTADDR_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_CURRENTADDR_NOT_EMPTY);
		}

		/*if (emercontname == null || emercontname.isEmpty()) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_EMERNAME_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_EMERNAME_NOT_EMPTY);
		}
*/
		if (emercontnum == 0) {
			throw new EmployeeException(
					ExceptionCodes.EMPLOYEE_EMERNUM_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_EMERNUM_NOT_EMPTY);
		}

		/*
		 * if (YOE == 0) { throw new EmployeeException(
		 * ExceptionCodes.EMPLOYEE_RATING_NOT_EMPTY,
		 * ExceptionMessages.EMPLOYEE_RATING_NOT_EMPTY); }
		 */

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
		empFromDB.setSkype(employee.getSkype());
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employee = (Employee) empDAOImpl.update(empFromDB);
		return employee;
	}

	// updating the details of employee by hr
	@AuthorizeEntity(roles = { Constants.HR })
	public Employee hrUpdateEmployeeAOP(Employee employee)
			throws ObjectNotFoundException, EmployeeException,
			EncryptionException {

		Employee empFromDB = (Employee) DAOFactory.getInstance()
				.getEmployeeDAO().getEmployeeById(employee.getEmployeeId());
		empFromDB.setContactNo(employee.getContactNo());
		// empFromDB.setEmail(employee.getEmail());
		empFromDB.setCurrentAddress(employee.getCurrentAddress());
		empFromDB.setPermanentAddress(employee.getPermanentAddress());
		empFromDB.setEmergencycontactnumber(employee
				.getEmergencycontactnumber());
		empFromDB.setEmergencyContactName(employee.getEmergencyContactName());
		empFromDB.setRelationWithEmergencyConatact(employee
				.getRelationWithEmergencyConatact());
		empFromDB.setDateOfBirth(employee.getDateOfBirth());
		// empFromDB.setPassword(Utils.encrypt(employee.getPassword()));
		empFromDB.setFileId(employee.getFileId());
		empFromDB.setSkype(employee.getSkype());
		empFromDB.setBankAccountNo(employee.getBankAccountNo());
		empFromDB.setFathersName(employee.getFathersName());
		empFromDB.setHighestQualification(employee.getHighestQualification());
		empFromDB.setPan(employee.getPan());
		empFromDB.setPfNo(employee.getPfNo());
		empFromDB.setSalary(employee.getSalary());
		// empFromDB.setGender(employee.getGender());
		empFromDB.setBloodGroup(employee.getBloodGroup());
		// empFromDB.setEmployeeId(employee.getEmployeeId());
		empFromDB.setEmployeeName(employee.getEmployeeName());
		empFromDB.setVariableComponent(employee.getVariableComponent());
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employee = (Employee) empDAOImpl.update(empFromDB);
		return employee;
	}

	@SuppressWarnings("null")
	// This method will return all the event of the company within one month
	@AuthorizeEntity(roles = { Constants.HR })
	public List<DisplayNotificationHome> getAllEventsAOP()
			throws BusinessException {

		List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
		List<SendNotificationHistory> notificationHistory = new ArrayList<SendNotificationHistory>();

		SendNotificationHistoryHandler sendnotificationHandler = SendNotificationHistoryHandler
				.getInstance();
		notificationHistory = sendnotificationHandler.getHistorydataAOP();

		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		empBirthday = employeeDAOImpl.getBirthday();
		empWorkAniversay = employeeDAOImpl.getWorkAniversary();

		// Converting eachEmployeeHistory to map

		displayNotificationHomeList
				.addAll(getAnivarsaryListAOP(empWorkAniversay));
		displayNotificationHomeList.addAll(getBirthdaysListAOP(empBirthday));
		displayNotificationHomeList.addAll(getWelcomeEmployeeListAOP());
		// Comparing the dates irrespective of year to sort the list
		Collections.sort(displayNotificationHomeList, new CompareDates());

		return displayNotificationHomeList;
	}

	// This method will return the event which are satisfying the criteria
	// conditions
	@AuthorizeEntity(roles = { Constants.HR })
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
				displayNotificationHomeList = getWelcomeEmployeeListAOP();
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
			displayNotificationHomeList = getBirthdaysListAOP(empBirthdayWithDates);
			if (displayNotificationHomeList.size() == 0) {
				throw new UserException(ExceptionCodes.NO_BIRTHDAY_FOUND,
						ExceptionMessages.NO_BIRTHDAY_FOUND);
			}
		}

		else if (selectedEvent.equalsIgnoreCase("Welcome")) {
			if (displayNotificationHomeList.size() == 0) {
				throw new UserException(ExceptionCodes.NO_WELCOME_EMPLOYEE,
						ExceptionMessages.NO_WELCOME_EMPLOYEE);
			}
		}

		// Entering Aniversary Records To be displayed along with the
		// notification status
		else if (selectedEvent.equalsIgnoreCase("workAniversary")) {

			displayNotificationHomeList = getAnivarsaryListAOP(empWorkAniversayWithdates);

			if (displayNotificationHomeList.size() == 0) {
				throw new UserException(
						ExceptionCodes.NO_EMPLOYEE_JOINED_TODAY,
						ExceptionMessages.NO_EMPLOYEE_JOINED_TODAY);
			}
		}
		// If no date criteria is selected this else will return the events in
		// the current month
		else {
			displayNotificationHomeList
					.addAll(getAnivarsaryListAOP(empWorkAniversayWithdates));
			displayNotificationHomeList
					.addAll(getBirthdaysListAOP(empBirthdayWithDates));

			if (displayNotificationHomeList.size() == 0) {
				throw new UserException(ExceptionCodes.NO_EVENT_FOUND,
						ExceptionMessages.NO_EVENT_FOUND);
			}

		}
		// Comparing the dates irrespective of year to sort the list
		Collections.sort(displayNotificationHomeList, new CompareDates());
		return displayNotificationHomeList;

	}

	// This Method will return the birthday list with a selected criteria
	@AuthorizeEntity(roles = { Constants.HR })
	public List<DisplayNotificationHome> getBirthdaysListAOP(
			List<Employee> employeeBirthday) throws BusinessException {

		List<SendNotificationHistory> notificationHistory = new ArrayList<SendNotificationHistory>();
		SendNotificationHistoryHandler sendnotificationHandler = SendNotificationHistoryHandler
				.getInstance();
		notificationHistory = sendnotificationHandler.getHistorydataAOP();
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
	@AuthorizeEntity(roles = { Constants.HR })
	public List<DisplayNotificationHome> getAnivarsaryListAOP(
			List<Employee> employeeAniversary) throws BusinessException {

		List<SendNotificationHistory> notificationHistory = new ArrayList<SendNotificationHistory>();
		SendNotificationHistoryHandler sendnotificationHandler = SendNotificationHistoryHandler
				.getInstance();
		notificationHistory = sendnotificationHandler.getHistorydataAOP();
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
	@AuthorizeEntity(roles = { Constants.HR })
	public List<DisplayNotificationHome> getWelcomeEmployeeListAOP()
			throws BusinessException

	{
		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();

		List<SendNotificationHistory> notificationHistory = new ArrayList<SendNotificationHistory>();
		SendNotificationHistoryHandler sendnotificationHandler = SendNotificationHistoryHandler
				.getInstance();
		notificationHistory = sendnotificationHandler.getHistorydataAOP();
		List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();
		welcomeemp = employeeDAOImpl.getWelcomeEmployee();
		for (Employee welEmp : welcomeemp) {
			DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome(
					"Welcome", welEmp.getDateOfJoining(), welEmp.getEmail(),
					welEmp.getEmployeeName());

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

	@AuthorizeEntity(roles = { Constants.HR })
	public List<Employee> searchEmployeeAOP(
			EmployeeSearchInputDescriptor employee) {

		List<Employee> employeelist = null;
		EmployeeDAO EmployeeDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employeelist = (List<Employee>) EmployeeDAOImpl
				.searchEmployee(employee);
		return employeelist;
	}

	// hr delete operation
	@AuthorizeEntity(roles = { Constants.HR })
	public Employee deleteEmployeeAOP(Employee employee)
			throws EmployeeException {
		Employee empFromDB = (Employee) DAOFactory.getInstance()
				.getEmployeeDAO().getEmployeeById(employee.getEmployeeId());
		empFromDB.setDeleted(true);
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employee = (Employee) empDAOImpl.update(empFromDB);
		return employee;
	}

	public void changePassword(ChangePassword changePasswordEmployee)
			throws EncryptionException, BusinessException {

		Employee empFromDB = (Employee) DAOFactory.getInstance()
				.getEmployeeDAO()
				.getEmployeeById(changePasswordEmployee.getId());
		String passwordFromDB = empFromDB.getPassword();
		String oldpassword = changePasswordEmployee.getOldPassword();
		String newPassword = changePasswordEmployee.getNewPassword();
		String confirmNewPassword = changePasswordEmployee
				.getConfirmNewPassword();

		String encryptedOldPassword = Utils.encrypt(oldpassword.trim());
		System.out.println(changePasswordEmployee.getId());

		if (newPassword == null || newPassword.isEmpty()
				|| newPassword.trim().isEmpty() || confirmNewPassword == null
				|| confirmNewPassword.isEmpty()
				|| confirmNewPassword.trim().isEmpty()) {
			throw new BusinessException(
					ExceptionCodes.PASSWORD_CANNOT_BE_EMPTY,
					ExceptionMessages.PASSWORD_CANNOT_BE_EMPTY);
		}

		boolean passwordValidity = passwordFromDB.equals(encryptedOldPassword);
		boolean newPasswordValidity = newPassword.equals(confirmNewPassword);
		if (passwordValidity && newPasswordValidity) {
			empFromDB.setPassword(Utils.encrypt(newPassword));
			updateEmployee(empFromDB);
		} else if (!passwordValidity) {
			throw new UserException(ExceptionCodes.INVALID_OLD_PASSWORD,
					ExceptionMessages.INVALID_OLD_PASSWORD);
		} else if (!newPasswordValidity) {
			throw new UserException(ExceptionCodes.INVALID_NEW_PASSWORD,
					ExceptionMessages.INVALID_NEW_PASSWORD);
		}
	}

	public boolean logout() {
		boolean isLogout = false;
		String userSessionId = ServiceRequestContextHolder.getContext()
				.getUserSessionToken().getUserSessionId();

		isLogout = CacheManager.getInstance()
				.getCache(CacheRegionType.USER_SESSION_CACHE)
				.remove(userSessionId);
		return isLogout;
	}

	public List<Employee> getTodaysBirthday() throws BusinessException {
		List<Employee> employeeTodaysBirthday = null;
		EmployeeDAO employeeDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employeeTodaysBirthday = employeeDAOImpl.getTodaysBirthday();
		return employeeTodaysBirthday;
	}

	public List<Employee> getTodayWorkAniversary() throws BusinessException {
		List<Employee> employeeTodayWorkAniversary = null;
		EmployeeDAO employeeDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employeeTodayWorkAniversary = employeeDAOImpl.getTodayWorkAniversary();
		return employeeTodayWorkAniversary;
	}

	// updating designation details
	@AuthorizeEntity(roles = { Constants.HR })
	public Employee updateDesigantionDetailsAOP(DesignationHistory desHistory)
			throws ObjectNotFoundException, EmployeeException,
			EncryptionException {

		Employee empFromDB = (Employee) DAOFactory.getInstance()
				.getEmployeeDAO().getEmployeeById(desHistory.getEmpId());
		String salary = "" + desHistory.getSalary();
		String variablePay = "" + desHistory.getVariablePay();

		empFromDB.setSalary(salary);
		empFromDB.setCurrentDesignation(desHistory.getDesignationId());
		empFromDB.setVariableComponent(variablePay);
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		Employee employee = (Employee) empDAOImpl.update(empFromDB);
		return employee;
	}

	public PaginationOutput<NotificationPaginationInput> getEmployeesPaginated(
			NotificationPaginationInput employee) {

		Paginator<NotificationPaginationInput> paginator = new Paginator<>();

		paginator = DAOFactory.getInstance().getEmployeeDAO()
				.getEmployeesPaginated(employee);

		PaginationOutput<NotificationPaginationInput> empPaginationOutput = new PaginationOutput<NotificationPaginationInput>(
				paginator, employee.getPageNo(), employee.getPageSize());

		return empPaginationOutput;
	}

	public Employee getLoggedInUser(long userId) throws EmployeeException {
		Employee employee = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getEmployeeDAO();
		employee = (Employee) empDAOImpl.getLoggedInUser(userId);

		return employee;
	}

	public PaginationOutput<Employee> getEmployeesListPaginated(
			  EmployeeListPaginationInput employee) {
			 
			 Paginator<Employee> paginator = new Paginator<>();
			 paginator = DAOFactory.getInstance().getEmployeeDAO().getEmployeesListPaginated(employee);
			 
			 PaginationOutput<Employee> empPaginationOutput = new PaginationOutput<>(paginator, employee.getPageNo(), employee.getPageSize());
			 return empPaginationOutput;
			 
			}
}
