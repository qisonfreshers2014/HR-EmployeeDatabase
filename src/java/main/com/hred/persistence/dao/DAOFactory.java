package com.hred.persistence.dao;

import com.hred.persistence.daoimpl.*;


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
	public EmployeeDAO getEmployeeDAO() {
		// TODO Auto-generated method stub
		return EmployeeDAOImpl.getInstance();
	}

	
	public HolidayDAO getHolidayDAO() {
		return HolidayDAOImpl.getInstance();
	}

	public TemplateDAO getTemplateDAO() {
		return TemplateDAOimpl.getInstance();
	}

	public AllHandsMeetingDAO getAllHandsMeetingDAO() {
	
		return AllHandsMeetingDAOImpl.getInstance();
	}

	
	

}

