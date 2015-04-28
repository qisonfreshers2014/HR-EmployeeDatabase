package com.hred.handler;

import java.util.List;
import java.util.regex.Pattern;

import com.hred.common.Utils;
import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.EncryptionException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.model.Employee;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.EmployeeDAO;

public class EmployeeHandler extends AbstractHandler {
	private static EmployeeHandler INSTANCE = null;
	 public static final String EMAIL_PATTERN = "^[_A-Za-z]+[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	    public static final String NAME_PATTERN = "^[A-Za-z0-9\\s]*$";
	    public static final String USER_NAME_PATTERN = "^[A-Za-z\\s]*$";
	    public static final String DESIGNATION = "^([A-Z]+)$";
	    public static final String GENDER = "^(?:male|Male|female|Female)$";//----//^M(ale)?$|^F(emale)?$
	    public static final String EMPLOYEE_ID_PATTERN = "^[A-Za-z0-9]*$";
	    public static final int MIN_PASSWORD_LENGTH = 6;
	    public static final int ARTICLE_BRIEF_DESCRIPTION_LENGTH = 190;
	    public static final int BRIEF_DESCRIPTION_START_INDEX = 0;
		public static final String LOCATION_PATTERN = "^[A-Za-z\\s]*$";
		public static final String DATE_PATTTERN="^(0[1-9]|1[012])([-/])(0[1-9]|[12][0-9]|3[01])\\2([23]0)\\d\\d$";
		

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

	public List<Employee> getEmployees() throws EmployeeException {
		List<Employee> employees = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getUserDAO();
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

}
