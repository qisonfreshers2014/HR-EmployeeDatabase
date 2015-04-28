package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.UserException;
import com.hred.model.Employee;


/**
 * Venkatesh Chitla
 * 
 */

public interface EmployeeDAO extends BaseDAO{
	
	public Employee getEmployeeById(long id) throws EmployeeException;

	public List<Employee> getEmployees();

	public List<Employee> getTodaysBirthday() throws BusinessException;
}
