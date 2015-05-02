package com.hred.handler;

 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.hred.common.Utils;
import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.EncryptionException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.model.Employee;
import com.hred.model.FilterEmployee;
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

	 

	public Employee getEmployeeById(String id) throws EmployeeException {
		Employee employee = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getUserDAO(); 
		employee = empDAOImpl.getEmployeeById(id);

		return employee;
	}
 
	
	public List<Employee> getEmployees(FilterEmployee employee) throws EmployeeException {
		List<Employee> employees = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
		employees = empDAOImpl.getEmployees(employee);
		return employees;
 
	}
	
	public List<Employee> getEmployees() throws EmployeeException {
		List<Employee> employees = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance()
				.getUserDAO();
		employees = empDAOImpl.getEmployees();
 		return employees;
	}

	public Employee save(Employee employee) throws EncryptionException,BusinessException {
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
				blood, num, date, fatherName, gender, contactNum, skypeid, doj, skill, rating, YOE, 
				emercontnum, emercontname, currentaddr, peraddr);
		
		
		Boolean isempidExist = DAOFactory.getInstance().getEmployeeDAO()
				.getEmployeeByEmpId(employee.getEmployeeId());

		Boolean isemailExist = DAOFactory.getInstance().getEmployeeDAO()
				.getEmployeeByEmail(employee.getEmail());
		if (isempidExist) {
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_EMPID_ALREADY_EXIST,
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
		
		if(peraddr == null || peraddr.isEmpty()){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_PERADDR_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_PERADDR_NOT_EMPTY);
		}

		
		
		if(currentaddr == null || currentaddr.isEmpty()){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_CURRENTADDR_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_CURRENTADDR_NOT_EMPTY);
		}

		if(emercontname == null || emercontname.isEmpty()){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_EMERNAME_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_EMERNAME_NOT_EMPTY);
		}
		
		if(emercontnum == 0){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_EMERNUM_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_EMERNUM_NOT_EMPTY);
		}
		
		if(YOE == 0){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_RATING_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_RATING_NOT_EMPTY);
		}
		
		if(rating == null || rating.isEmpty()){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_RATING_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_RATING_NOT_EMPTY);
		}
		
		if(skill == null || skill.isEmpty()){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_SKILL_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_SKILL_NOT_EMPTY);
		}
		
		if(doj == null){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_DOJ_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_DOJ_NOT_EMPTY);
			
		}
		
		if(skypeid == null || skypeid.isEmpty()){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_SKYPEID_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_SKYPEID_NOT_EMPTY);
		}
		
		if(contactNum == 0){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_CONTACTNUM_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_CONTACTNUM_NOT_EMPTY);
		}
		
		if(fatherName == null || fatherName.isEmpty()){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_FATHERNAME_NOT_EMPTY,
					ExceptionMessages.EMPLOYEE_FATHERNAME_NOT_EMPTY);
		}
		
		if(gender == "" || gender.isEmpty()){
			throw new EmployeeException(ExceptionCodes.EMPLOYEE_GENDER_NOT_EMPTY,
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

	@SuppressWarnings("unchecked")
	public List<Employee> getTodayBirthday() throws BusinessException {
		List<Employee> emp = null;
		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		emp = employeeDAOImpl.getTodaysBirthday();

		return emp;
	}

	public Employee updateEmployee(Employee employee)
			throws ObjectNotFoundException, EmployeeException,
			EncryptionException {

		Employee empFromDB = (Employee) DAOFactory.getInstance()
				.getEmployeeDAO().getEmployeeById(employee.getId());
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

	public Employee hrUpdateEmployee(Employee employee)
			throws ObjectNotFoundException, EmployeeException,
			EncryptionException {

		Employee empFromDB = (Employee) DAOFactory.getInstance()
				.getEmployeeDAO().getEmployeeById(employee.getId());
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
		empFromDB.setEmployeeId(employee.getEmployeeId());
		empFromDB.setEmployeeName(employee.getEmployeeName());
		empFromDB.setVariableComponent(employee.getVariableComponent());
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

		// DisplayNotificationHome displayNotificationHome = new
		// DisplayNotificationHome();
		// List<SendNotificationHistory> notificationHistory = new
		// ArrayList<SendNotificationHistory>();

		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		empBirthday = employeeDAOImpl.getBirthday();
		empWorkAniversay = employeeDAOImpl.getWorkAniversary();

		SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
				.getInstance().getSendNotificationHistoryDAO();
		List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl
				.getHistorydata();
		Map<Integer, String> empidmapping = new HashedMap();
		// Converting eachEmployeeHistory to map
		for (SendNotificationHistory eachEmployeeHistory : notificationHistory) {
			empidmapping.put(eachEmployeeHistory.getEmployeeId(),
					eachEmployeeHistory.getTemplateId());
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
			NotificationHomeFilterInputDiscriptor filterCriteria)
			throws BusinessException {
		List<Employee> empBirthday = null;
		List<Employee> empWorkAniversay = null;
		List<DisplayNotificationHome> displayNotificationHomeList = new ArrayList<DisplayNotificationHome>();

		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		empBirthday = employeeDAOImpl.getBirthday();
		empWorkAniversay = employeeDAOImpl.getWorkAniversary();

		// Taking History Data From the NotificationHistory Table
		SendNotificationHistoryDAO SendNotificationHistoryDAOImpl = DAOFactory
				.getInstance().getSendNotificationHistoryDAO();
		List<SendNotificationHistory> notificationHistory = SendNotificationHistoryDAOImpl
				.getHistorydata();
		Map<Integer, SendNotificationHistory> empidmapping = new HashedMap();
		// Converting eachEmployeeHistory to map
		for (SendNotificationHistory eachEmployeeHistory : notificationHistory) {
			empidmapping.put(eachEmployeeHistory.getEmployeeId(),
					eachEmployeeHistory);
		}

		java.util.Date todate = filterCriteria.getTodate();
		java.util.Date fromdate = filterCriteria.getFromdate();
		String selectedEvent = filterCriteria.getSelectedEvent();
		//String selectedState = filterCriteria.getSelectedState();

		if (selectedEvent.equalsIgnoreCase("birthDay")) {
			for (Employee birthday : empBirthday) {
				DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
				displayNotificationHome.setEmployeeName(birthday
						.getEmployeeName());
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
		} else if (selectedEvent.equalsIgnoreCase("workAniversary")) {
			for (Employee aniversay : empWorkAniversay) {
				DisplayNotificationHome displayNotificationHome = new DisplayNotificationHome();
				displayNotificationHome.setEmployeeName(aniversay
						.getEmployeeName());
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
