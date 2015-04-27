package com.hred.persistence.dao;


import com.hred.model.Template;
import com.hred.persistence.daoimpl.EmployeeDAOimpl;
import com.hred.persistence.daoimpl.TemplateDAOimpl;
import com.hred.persistence.daoimpl.UserDAOImpl;


/**
 * DAO Factory. Handlers use this factory to get appropriate DAO.
 * 
 * @author vinay
 * 
 */
public class DAOFactory {
	private static DAOFactory INSTANCE = null;

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DAOFactory();
		}
		return INSTANCE;
	}



	
	public UserDAO getUserDAO() {
		return UserDAOImpl.getInstance();
	}

	public TemplateDAO getTemplateDAO() {
		return  TemplateDAOimpl.getInstance();
	}
	public EmployeeDAO getEmployeeDAO() {
		return  EmployeeDAOimpl.getInstance();
	}

}

