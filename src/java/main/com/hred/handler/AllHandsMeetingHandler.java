package com.hred.handler;

import java.sql.Timestamp;
import java.util.List;

import com.hred.exception.AllHandsMeetingException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.exception.TemplateException;
import com.hred.model.AllHandsMeeting;
import com.hred.model.ObjectTypes;
import com.hred.model.Template;
import com.hred.persistence.dao.AllHandsMeetingDAO;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.TemplateDAO;
import com.hred.persistence.daoimpl.AllHandsMeetingDAOImpl;

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
	

	public List<AllHandsMeeting> getAllHandsMeeting()throws AllHandsMeetingException {
		List<AllHandsMeeting> allhandsmeetinglist = null;
		AllHandsMeetingDAO AllHandsMeetingDAOImpl = (AllHandsMeetingDAO) DAOFactory.getInstance().getAllHandsMeetingDAO();
		allhandsmeetinglist = (List<AllHandsMeeting>)AllHandsMeetingDAOImpl.getAllHandsMeeting();
		return allhandsmeetinglist;
	
	}
	 private void validationFunc(Timestamp date, String employee, String description,List<AllHandsMeeting> allhandsmeeting)  throws AllHandsMeetingException{
		   
		  // List<AllHandsMeeting> data=save();
		    for (int i = 0; i < allhandsmeeting.size(); i++) {
		    	 // Timestamp dbdate = data.get(i).getFromDate(); 
		        Timestamp dbdate = allhandsmeeting.get(i).getDate();
		           if(dbdate.compareTo(date) == 0){
		            throw new AllHandsMeetingException(ExceptionCodes.ALLHANDSMEETING_DATE_ALREADY_EXISTS,
		                 ExceptionMessages.ALLHANDSMEETING_DATE_ALREADY_EXISTS);
		            
		        }    
		    }   
		 
		/* 
		 if (date == null)
		    {
		       throw new AllHandsMeetingException(ExceptionCodes.EVERY_FIELD_IS_MANDATORY,
		         ExceptionMessages.EVERY_FIELD_IS_MANDATORY);
		      }*/
		    if (employee == null || employee.isEmpty()
		        || employee.trim().isEmpty()) {
		       throw new AllHandsMeetingException(ExceptionCodes.EVERY_FIELD_IS_MANDATORY,
		         ExceptionMessages.EVERY_FIELD_IS_MANDATORY);
		      }
		    if (description == null || description.isEmpty()) {
		       throw new AllHandsMeetingException(ExceptionCodes.EVERY_FIELD_IS_MANDATORY,
		         ExceptionMessages.EVERY_FIELD_IS_MANDATORY);
		      }
		 
	 }
	public AllHandsMeeting save(AllHandsMeeting allhandsmeetingInput) throws AllHandsMeetingException{
		List<AllHandsMeeting> allhands = getAllHandsMeeting();
		  
		Timestamp date=allhandsmeetingInput.getDate();
		String employee=allhandsmeetingInput.getEmployee();
		String description=allhandsmeetingInput.getDescription();
		
		validationFunc(date,employee,description,allhands);
		AllHandsMeeting allhandsmeetingSaved = (AllHandsMeeting) DAOFactory.getInstance().getAllHandsMeetingDAO().saveObject(allhandsmeetingInput);
		return allhandsmeetingSaved;
	}

	
	public List<AllHandsMeeting> getAllHandsMeetingById(AllHandsMeeting allhandsmeeting) throws AllHandsMeetingException{
		List<AllHandsMeeting> allhandsmeetings = null;
		AllHandsMeetingDAO allhandsmeetingDAOImpl = (AllHandsMeetingDAO) DAOFactory.getInstance().getAllHandsMeetingDAO();
		allhandsmeetings = (List<AllHandsMeeting>)allhandsmeetingDAOImpl.getAllHandsMeetingById(allhandsmeeting);
		return allhandsmeetings;
		
	}
	
	
	public AllHandsMeeting update(AllHandsMeeting allhandsmeeting) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		
		AllHandsMeeting allhandsmeetingFromDB = (AllHandsMeeting)DAOFactory.getInstance().getAllHandsMeetingDAO().getObjectById(allhandsmeeting.getId(), ObjectTypes.ALL_HANDS_MEETING);
		allhandsmeetingFromDB.setDate(allhandsmeeting.getDate());
		allhandsmeetingFromDB.setEmployee(allhandsmeeting.getEmployee());
		allhandsmeetingFromDB.setDescription(allhandsmeeting.getDescription());
		AllHandsMeeting allhandsmeetingEdited = (AllHandsMeeting) DAOFactory.getInstance().getAllHandsMeetingDAO().update(allhandsmeetingFromDB);
		return allhandsmeetingEdited;
	}

	
}
