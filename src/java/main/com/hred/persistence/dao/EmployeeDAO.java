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
 * 
 */
public interface EmployeeDAO extends BaseDAO {

	public Employee getUserByEmail(String email) throws UserException;
	
/*	public List<Employee> getFilterEmployeeDetails(Employee employee) throws EmployeeException;*/
	
	
	public Employee getEmployeeById(String id) throws EmployeeException;

	public List<Employee> getEmployees();

	public List<Employee> getBirthday() throws BusinessException;


	public List<Employee> getWorkAniversary() throws BusinessException;

	public List<Employee> getWelcomeEmployee() throws BusinessException;

	

	public List<Employee> getTodaysBirthday() throws BusinessException ;
	public List<Employee> getTodayWorkAniversary() throws BusinessException;

	public List<Employee> getBirthdayWithindate(NotificationHomeFilterInputDiscriptor filterCriteria) throws BusinessException;

	public List<Employee> getWorkAniversarywithdate(NotificationHomeFilterInputDiscriptor filterCriteria) throws BusinessException;


}
