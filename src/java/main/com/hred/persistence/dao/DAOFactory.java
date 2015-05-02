package com.hred.persistence.dao;
import com.hred.persistence.daoimpl.AllHandsMeetingDAOImpl;
import com.hred.persistence.daoimpl.DesignationHistoryDAOImpl;
import com.hred.persistence.daoimpl.EmployeeDAOImpl;
import com.hred.persistence.daoimpl.EmployeeDAOImpl;
import com.hred.persistence.daoimpl.FileDAOImpl;
import com.hred.persistence.daoimpl.HRPolicyDAOImpl;
import com.hred.persistence.daoimpl.HolidayDAOImpl;
import com.hred.persistence.daoimpl.SendNotificationHistoryDAOImpl;
import com.hred.persistence.daoimpl.SkillDAOImpl;
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
	
	public EmployeeDAO getEmployeeDAO() {
		 
		  return EmployeeDAOImpl.getInstance();
	}
	 
	public HolidayDAO getHolidayDAO() {
		return HolidayDAOImpl.getInstance();
	}


	public HRPolicyDAO getHrPolicyDAO() {
		return HRPolicyDAOImpl.getInstance();
	}

	public EmployeeDAO searchEmployeeDAO() {
		return EmployeeDAOImpl.getInstance();
	}


	 public SkillsDAO getSkillDAO(){
		return SkillDAOImpl.getInstance();
	}

	public TemplateDAO getTemplateDAO(){
		return TemplateDAOimpl.getInstance();
	}
	
	public FileDAO getFileDAO() {
		return FileDAOImpl.getInstance();
	}

	public AllHandsMeetingDAO getAllHandsMeetingDAO() {
	
		return AllHandsMeetingDAOImpl.getInstance();
	}

	
	public DesignationHistoryDAO getDesignationHistoryDAO(){
			return DesignationHistoryDAOImpl.getInstance();
	}





	public HRPolicyDAO getHRPolicyDAO(){
		return HRPolicyDAOImpl.getInstance();
	}
	
	public SendNotificationHistoryDAO getSendNotificationHistoryDAO()
	{
		return SendNotificationHistoryDAOImpl.getInstance();
	}

	
}

