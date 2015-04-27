package com.hred.persistence.dao;


import java.util.List;

import com.hred.exception.EmployeeException;
import com.hred.model.Employee;



public interface EmployeeDAO extends BaseDAO{

	List<Employee> viewEmployee(Employee employee);

}
