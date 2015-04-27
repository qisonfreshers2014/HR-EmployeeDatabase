package com.hred.handler;

import java.util.List;

import com.hred.exception.AllHandsMeetingException;
import com.hred.model.AllHandsMeeting;
import com.hred.persistence.dao.AllHandsMeetingDAO;
import com.hred.persistence.dao.DAOFactory;

/**
 * @author saisudha
 *
 */
public class AllHandsMeetingHandler extends AbstractHandler {
	private static AllHandsMeetingHandler INSTANCE = null;

	private AllHandsMeetingHandler() {
	}

	/**
	 * @return instance of UserHandler
	 */
	public static AllHandsMeetingHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new AllHandsMeetingHandler();
		return INSTANCE;
	}


	public AllHandsMeeting getHolidaysById(int id) throws AllHandsMeetingException {
		AllHandsMeeting allhandsmeetings = null;
		AllHandsMeetingDAO AllHandsMeetingDAOImpl = DAOFactory.getInstance().getAllHandsMeetingDAO();
		allhandsmeetings = AllHandsMeetingDAOImpl.getAllHandsMeetingById(id);

		return allhandsmeetings;
	}
	
	/*
	 * public List<Employee> getFilterEmployeeDetails(Employee employee) throws EmployeeException {
List<Employee> employees = null;
EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
employees = (List<Employee>) empDAOImpl.getFilterEmployeeDetails(employee);
return employees;
}*/
	public List<AllHandsMeeting> getAllHandsMeeting(AllHandsMeeting allhandsmeeting)throws AllHandsMeetingException {
		List<AllHandsMeeting> allhandsmeetinglist = null;
		AllHandsMeetingDAO AllHandsMeetingDAOImpl = (AllHandsMeetingDAO) DAOFactory.getInstance().getAllHandsMeetingDAO();
		allhandsmeetinglist = (List<AllHandsMeeting>)AllHandsMeetingDAOImpl.getAllHandsMeeting(allhandsmeeting);
		return allhandsmeetinglist;
	
	}
	
	public AllHandsMeeting save(AllHandsMeeting allhandsmeetingInput){
		//Holiday holidayTobeSaved = new Holiday(holidayInput);
		AllHandsMeeting allhandsmeetingSaved = (AllHandsMeeting) DAOFactory.getInstance().getAllHandsMeetingDAO().saveObject(allhandsmeetingInput);
		return allhandsmeetingSaved;
	}

	public AllHandsMeeting edit(AllHandsMeeting allhandsmeetingInput){
		//Holiday holidayTobeSaved = new Holiday(holidayInput);
		AllHandsMeeting allhandsmeetingEdited = (AllHandsMeeting) DAOFactory.getInstance().getAllHandsMeetingDAO().saveObject(allhandsmeetingInput);
		return allhandsmeetingEdited;
	}


}
