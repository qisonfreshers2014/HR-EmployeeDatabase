/**
 * 
 */
package com.hred.persistence.dao;

import java.util.List;

import com.hred.model.Employee;
import com.hred.service.descriptors.Input.EmployeeSearchInputDescriptor;

/**
 * @author saisudha
 *
 */
public interface EmployeeDAO  extends BaseDAO{

	 public List<Employee> getEmployee();

	 public List<Employee> searchEmployee(EmployeeSearchInputDescriptor employee);

	 
}
