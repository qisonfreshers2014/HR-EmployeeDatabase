package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.EmployeeException;
import com.hred.exception.UserException;
import com.hred.model.Employee;
/**
 * jyothi ambepu
 * 
 */
public interface EmployeeDAO extends BaseDAO {

	public Employee getUserByEmail(String email) throws UserException;
	
	public List<Employee> getFilterEmployeeDetails(Employee employee) throws EmployeeException;
}
