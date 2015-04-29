/**
 * 
 */
package com.hred.handler;

import java.util.List;

import com.hred.exception.EmployeeException;
import com.hred.model.Employee;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.service.descriptors.Input.EmployeeSearchInputDescriptor;

/**
 * @author saisudha
 *
 */
public class EmployeeHandler extends AbstractHandler {
	
	private static EmployeeHandler INSTANCE = null;

	private EmployeeHandler() {
	}
	
		public static EmployeeHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new EmployeeHandler();
		return INSTANCE;
	}

	
	public List<Employee> getEmployee() throws EmployeeException{
		List<Employee> employeelist = null;
		EmployeeDAO EmployeeDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
		employeelist = (List<Employee>)EmployeeDAOImpl.getEmployee();
		return employeelist;
	
	}
	
	public List<Employee> searchEmployee(EmployeeSearchInputDescriptor employee) throws EmployeeException{
		List<Employee> employeelist = null;
		EmployeeDAO EmployeeDAOImpl = (EmployeeDAO) DAOFactory.getInstance().searchEmployeeDAO();
		employeelist = (List<Employee>)EmployeeDAOImpl.searchEmployee(employee);
		return employeelist;
	
	}
	
}
