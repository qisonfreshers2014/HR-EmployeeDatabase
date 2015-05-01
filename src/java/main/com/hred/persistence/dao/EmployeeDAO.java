package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.SkillsException;
import com.hred.model.Employee;
import com.hred.model.FilterEmployee;
import com.hred.model.Skills;
import com.hred.model.User;

 
public interface EmployeeDAO extends BaseDAO{

	List<Employee> getEmployees();

	Employee getEmployeeById(String id);

	Employee getUserByEmail(String email);
	

	//List<Skills> getSkillsDetails(Skills skills) throws SkillsException;

	 

	List<Employee> getEmployees(FilterEmployee filter);

	
}
