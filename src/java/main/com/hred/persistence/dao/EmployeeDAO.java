package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.EmployeeException;
import com.hred.model.Employee;


/**
 * jyothi ambepu
 * 
 */

public interface EmployeeDAO extends BaseDAO{
	
	 

	 
	
	public List<Employee> getFilterEmployeeDetails(Employee employee) throws EmployeeException;

	 

}
