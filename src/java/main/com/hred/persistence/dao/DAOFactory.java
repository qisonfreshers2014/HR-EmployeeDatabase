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
	

	/*public TemplateDAO getTemplateDAO() {
		  return  TemplateDAOimpl.getInstance();
		 }*/
	
	public HolidayDAO getHolidayDAO() {
		return HolidayDAOImpl.getInstance();
	}




	public EmployeeDAO getEmployeeDAO() {
		return EmployeeDAOImpl.getInstance();
	}
	
	public SkillsDAO getSkillDAO(){
		return SkillDAOImpl.getInstance();
	}


	public TemplateDAO getTemplateDAO() {

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

