package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.UserException;
import com.hred.model.Employee;
import com.hred.service.descriptors.output.DisplayNotificationHome;
import com.hred.service.descriptors.output.NotificationHomeFilterInputDiscriptor;

/**
 * jyothi ambepu
 * Venkatesh Chitla
 * 
 */
public interface EmployeeDAO extends BaseDAO {
	
	List<Employee> viewEmployee(Employee employee);

	public Employee getUserByEmail(String email) throws UserException;


	public String getEmployeeName(long id);

	
	public List<Employee> getFilterEmployeeDetails(Employee employee) throws EmployeeException;
	
	
	public Employee getEmployeeById(String id) throws EmployeeException;

	public List<Employee> getEmployees();

	public List<Employee> getBirthday() throws BusinessException;


	public List<Employee> getWorkAniversary() throws BusinessException;



	public List<DisplayNotificationHome> getEventWithinDate(NotificationHomeFilterInputDiscriptor filterCriteria);

	public List<Employee> getTodayWorkAniversary();
	
	public Employee getEmployeeById(long id) throws EmployeeException;


	public List<Employee> getTodaysBirthday() throws BusinessException;
}
