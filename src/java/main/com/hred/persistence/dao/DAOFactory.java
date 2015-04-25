package com.hred.persistence.dao;


import com.hred.persistence.daoimpl.HolidayDAOImpl;
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

	
	public HolidayDAO getHolidayDAO() {
		return HolidayDAOImpl.getInstance();
	}


}

