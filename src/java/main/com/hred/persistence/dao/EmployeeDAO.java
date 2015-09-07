package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.UserException;
import com.hred.model.Employee;
import com.hred.model.FilterEmployee;
import com.hred.pagination.EmployeeListPaginationInput;
import com.hred.pagination.NotificationPaginationInput;
import com.hred.pagination.Paginator;
import com.hred.service.descriptors.input.EmployeeSearchInputDescriptor;
import com.hred.service.descriptors.output.DisplayNotificationHome;
import com.hred.service.descriptors.output.NotificationHomeFilterInputDiscriptor;

/**
 * jyothi ambepu
 * Venkatesh Chitla
 * Rizwan Khan
 */
public interface EmployeeDAO extends BaseDAO {
	
	public Employee viewEmployee(String string) throws EmployeeException;

	public Employee getUserByEmail(String email) throws UserException;


	public String getEmployeeName(long id);
 
	
	public Employee getEmployeeById(String id) throws EmployeeException;

	public List<Employee> getEmployees();

	//public Employee getEmployeeById(int id) throws EmployeeException;
	 public List<Employee> getWelcomeEmployee() throws BusinessException;

	 public List<Employee> getTodaysBirthday() throws BusinessException;

	 public List<Employee> getTodayWorkAniversary() throws BusinessException;

	 public List<Employee> getBirthdayWithindate(
	   NotificationHomeFilterInputDiscriptor filterCriteria)
	   throws BusinessException;

	 public List<Employee> getWorkAniversarywithdate(
	   NotificationHomeFilterInputDiscriptor filterCriteria)
	   throws BusinessException;

	 List<Employee> getWorkAniversary() throws BusinessException;

	 List<Employee> getBirthday() throws BusinessException;


	public List<Employee> getEmployees(FilterEmployee filter);


	public List<Employee> searchEmployee(EmployeeSearchInputDescriptor employee);



	public List<Employee> getEmployee();

 public	Boolean getEmployeeByEmpId(String string) throws EmployeeException;

 public Boolean isEmployeeEmailExist(String email);

Paginator<NotificationPaginationInput> getEmployeesPaginated(NotificationPaginationInput employee);
public Employee getLoggedInUser(long userId) throws EmployeeException;

public Paginator<Employee> getEmployeesListPaginated(EmployeeListPaginationInput employee);

public Paginator<Employee> getFilterEmployeesListPaginated(
		FilterEmployee employee);

public Paginator<Employee> getSearchedEmployeesListPaginated(
		EmployeeSearchInputDescriptor employee);
public List getProfilePics();

public Employee getEmployeeById(long id) throws EmployeeException;
	
	
}
