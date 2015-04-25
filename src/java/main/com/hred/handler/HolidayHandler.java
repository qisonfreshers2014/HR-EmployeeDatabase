/**
 * 
 */
package com.hred.handler;

import java.util.List;

import com.hred.exception.HolidaysException;
import com.hred.model.Holiday;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.HolidayDAO;

/**
 * @author saisudha
 *
 */
public class HolidayHandler extends AbstractHandler {
	private static HolidayHandler INSTANCE = null;

	private HolidayHandler() {
	}

	/**
	 * @return instance of UserHandler
	 */
	public static HolidayHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new HolidayHandler();
		return INSTANCE;
	}


	public Holiday getHolidaysById(int id) throws HolidaysException {
		Holiday holidays = null;
		HolidayDAO HolidaysDAOImpl = DAOFactory.getInstance().getHolidayDAO();
		holidays = HolidaysDAOImpl.getHolidaysById(id);

		return holidays;
	}
	
	/*
	 * public List<Employee> getFilterEmployeeDetails(Employee employee) throws EmployeeException {
List<Employee> employees = null;
EmployeeDAO empDAOImpl = (EmployeeDAO) DAOFactory.getInstance().getEmployeeDAO();
employees = (List<Employee>) empDAOImpl.getFilterEmployeeDetails(employee);
return employees;
}*/
	public List<Holiday> getHolidays(Holiday holiday)throws HolidaysException {
		List<Holiday> holidayslist = null;
		HolidayDAO HolidaysDAOImpl = (HolidayDAO) DAOFactory.getInstance().getHolidayDAO();
		holidayslist = (List<Holiday>)HolidaysDAOImpl.getHolidays(holiday);
		return holidayslist;
	
	}
	
	public Holiday save(Holiday holidayInput){
		//Holiday holidayTobeSaved = new Holiday(holidayInput);
		Holiday holidaysSaved = (Holiday) DAOFactory.getInstance().getHolidayDAO().saveObject(holidayInput);
		return holidaysSaved;
	}

	public Holiday editHoliday(Holiday holidayInput){
		//Holiday holidayTobeSaved = new Holiday(holidayInput);
		Holiday holidaysEdited = (Holiday) DAOFactory.getInstance().getHolidayDAO().update(holidayInput);
		return holidaysEdited;
	}


}
