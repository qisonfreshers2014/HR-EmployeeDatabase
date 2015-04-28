package com.hred.handler;

import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.EmployeeDAO;

public class EmployeeHandler {

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

	public String getEmployeeName(long id) {
		  EmployeeDAO empDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		  String empFirstName = empDAOImpl.getEmployeeName(id);
		  return empFirstName;
		 }
	
}
