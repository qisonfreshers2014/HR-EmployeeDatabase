package com.hred.handler;

import java.util.List;

import com.hred.exception.EmployeeException;
import com.hred.model.Employee;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.EmployeeDAO;

public class EmployeeHandler extends AbstractHandler {
	private static EmployeeHandler INSTANCE = null;

	private EmployeeHandler() {
	}

	/**
	 * @return instance of EmployeeHandler
	 */
	public static EmployeeHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new EmployeeHandler();
		return INSTANCE;
	}

 
	
	public List<Employee> getFilterEmployeeDetails(Employee employee) throws EmployeeException {
		List<Employee> employees = null;
		EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
		employees = (List<Employee>) empDAOImpl.getFilterEmployeeDetails(employee);
		return employees;
	}
	
	public Employee save(Employee employee){
		Employee empSaved=(Employee) DAOFactory.getInstance().getUserDAO().saveObject(employee);
		return empSaved;
	}


}
