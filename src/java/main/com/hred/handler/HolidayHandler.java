package com.hred.handler;

import java.sql.Timestamp;
import java.util.List;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.HolidaysException;
import com.hred.exception.ObjectNotFoundException;
import com.hred.model.Holiday;
import com.hred.model.ObjectTypes;
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

	//getting Holiday By id
	public List<Holiday> getHolidayById(Holiday holiday) throws HolidaysException {
		List<Holiday> holidays = null;
		HolidayDAO HolidaysDAOImpl =(HolidayDAO) DAOFactory.getInstance().getHolidayDAO();
		holidays = (List<Holiday>)HolidaysDAOImpl.getHolidayById(holiday);

		return holidays;
	
	}
	
	//Getting list of holidays
	public List<Holiday> getHolidays()throws HolidaysException {
		
		List<Holiday> holidayslist = null;
		HolidayDAO HolidaysDAOImpl = (HolidayDAO) DAOFactory.getInstance().getHolidayDAO();
		holidayslist = (List<Holiday>)HolidaysDAOImpl.getHolidays();
		return holidayslist;
	
	}
	
	//save method for Holiday 
	public Holiday save(Holiday holidayInput)throws HolidaysException{
		
		List<Holiday> data = getHolidays();
		
		Timestamp fromDate = holidayInput.getFromDate();
		Timestamp toDate = holidayInput.getFromDate();
		String description = holidayInput.getDescription();
		String type = holidayInput.getType();
		
		holidayValidationFunc(data,fromDate,toDate,description,type,holidayInput);
		
		
		Holiday holidaysSaved = (Holiday) DAOFactory.getInstance().getHolidayDAO().saveObject(holidayInput);
		return holidaysSaved;
	
	}

	
	//validations before saving holidays data
	private void holidayValidationFunc(List<Holiday> data,Timestamp fromDate, Timestamp toDate,String description, String type,Holiday holidayInput) throws HolidaysException {
		
		if(fromDate.toString() == null){
			
			throw new HolidaysException(ExceptionCodes.HOLIDAY_DATE_DOES_NOT_EXIST,
			         ExceptionMessages.HOLIDAY_DATE_DOES_NOT_EXIST);			
		
		}else{
			
			for (int i = 0; i < data.size(); i++) {
								
				Timestamp dbdate = data.get(i).getFromDate();
			   	if(dbdate.compareTo(fromDate) == 0){
			   	 throw new HolidaysException(ExceptionCodes.HOLIDAY_DATE_ALREADY_EXISTS,
				         ExceptionMessages.HOLIDAY_DATE_ALREADY_EXISTS);
			   	 
				}				
			}
			
		}
		

		if (description == null || description.isEmpty() || description.trim().isEmpty()) {
			       throw new HolidaysException(ExceptionCodes.HOLIDAY_DESCRIPTION_NULL,
			         ExceptionMessages.HOLIDAY_DESCRIPTION_NULL);
			      }
			   
		if (type == null || type.isEmpty() || type.trim().isEmpty()) {
		       throw new HolidaysException(ExceptionCodes.HOLIDAY_TYPE_NULL,
		         ExceptionMessages.HOLIDAY_TYPE_NULL);
		      }
		   
		
	}

	//update method for Holiday
	public Holiday updateHoliday(Holiday holidayInput) throws ObjectNotFoundException, HolidaysException{
		
		List<Holiday> data = getHolidays();
		
		Timestamp fromDate = holidayInput.getFromDate();
		Timestamp toDate = holidayInput.getFromDate();
		String description = holidayInput.getDescription();
		String type = holidayInput.getType();
		long id = holidayInput.getId();
		
		holidayupdateValidationFunc(data,fromDate,toDate,description,type,id);
		
		Holiday holidayFromDB = (Holiday)DAOFactory.getInstance().getHolidayDAO().getObjectById(holidayInput.getId(), ObjectTypes.HOLIDAY);
		holidayFromDB.setFromDate(fromDate);
		holidayFromDB.setToDate(toDate);
		holidayFromDB.setDescription(description);
		holidayFromDB.setType(type);
		
		Holiday holidaysEdited = (Holiday) DAOFactory.getInstance().getHolidayDAO().update(holidayFromDB);
		return holidaysEdited;
	}

	
	//validations before update
	private void holidayupdateValidationFunc(List<Holiday> data,
			Timestamp fromDate, Timestamp toDate, String description,
			String type, long id) throws HolidaysException {
		
		for (int i = 0; i < data.size(); i++) {
			Timestamp dbdate = data.get(i).getFromDate();
			if(data.get(i).getId() != id){
					if(dbdate.compareTo(fromDate) == 0){
						
						throw new HolidaysException(ExceptionCodes.HOLIDAY_DATE_ALREADY_EXISTS,
						         ExceptionMessages.HOLIDAY_DATE_ALREADY_EXISTS);
						
					}
			}
			
			
		}
		if (description == null || description.isEmpty() || description.trim().isEmpty()) {
		       throw new HolidaysException(ExceptionCodes.HOLIDAY_DESCRIPTION_NULL,
		         ExceptionMessages.HOLIDAY_DESCRIPTION_NULL);
		      }
		   
		if (type == null || type.isEmpty() || type.trim().isEmpty()) {
	       throw new HolidaysException(ExceptionCodes.HOLIDAY_TYPE_NULL,
	         ExceptionMessages.HOLIDAY_TYPE_NULL);
	      }
		
	 }

	


}
