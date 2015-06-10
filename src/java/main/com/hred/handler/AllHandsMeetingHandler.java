package com.hred.handler;

import java.sql.Timestamp;
import java.util.List;

import com.hred.common.Constants;
import com.hred.exception.AllHandsMeetingException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.handler.annotations.AuthorizeEntity;
import com.hred.model.AllHandsMeeting;
import com.hred.model.ObjectTypes;
import com.hred.pagination.AllhandsmeetingInput;
import com.hred.pagination.PaginationOutput;
import com.hred.pagination.Paginator;
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
	
	//@AuthorizeEntity(roles={Constants.HR})
	public List<AllHandsMeeting> getAllHandsMeeting()throws AllHandsMeetingException {
		List<AllHandsMeeting> allhandsmeetinglist = null;
		AllHandsMeetingDAO AllHandsMeetingDAOImpl = (AllHandsMeetingDAO) DAOFactory.getInstance().getAllHandsMeetingDAO();
		allhandsmeetinglist = (List<AllHandsMeeting>)AllHandsMeetingDAOImpl.getAllHandsMeeting();
		return allhandsmeetinglist;
	
	}
	 private void validationFunc(Timestamp date, String employee, String description,List<AllHandsMeeting> allhandsmeeting)  throws AllHandsMeetingException{
		   
/*		if(date.compareTo()==0){
			
		}
		 else{*/
		    for (int i = 0; i < allhandsmeeting.size(); i++) {
		    
		        Timestamp dbdate = allhandsmeeting.get(i).getDate();
		           if(dbdate.compareTo(date) == 0){
		            throw new AllHandsMeetingException(ExceptionCodes.ALLHANDSMEETING_DATE_ALREADY_EXISTS,
		                 ExceptionMessages.ALLHANDSMEETING_DATE_ALREADY_EXISTS);
		            
		        }    
		    }   
		//}
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
	 @AuthorizeEntity(roles={Constants.HR})
	public AllHandsMeeting saveAOP(AllHandsMeeting allhandsmeetingInput) throws AllHandsMeetingException{
		List<AllHandsMeeting> allhands = getAllHandsMeeting();
		  
		Timestamp date=allhandsmeetingInput.getDate();
		String employee=allhandsmeetingInput.getEmployee();
		String description=allhandsmeetingInput.getDescription();
		
		validationFunc(date,employee,description,allhands);
		AllHandsMeeting allhandsmeetingSaved = (AllHandsMeeting) DAOFactory.getInstance().getAllHandsMeetingDAO().saveObject(allhandsmeetingInput);
		return allhandsmeetingSaved;
	}

	
	@AuthorizeEntity(roles={Constants.HR})
	public List<AllHandsMeeting> getAllHandsMeetingByIdAOP(AllHandsMeeting allhandsmeeting) throws AllHandsMeetingException{
		List<AllHandsMeeting> allhandsmeetings = null;
		AllHandsMeetingDAO allhandsmeetingDAOImpl = (AllHandsMeetingDAO) DAOFactory.getInstance().getAllHandsMeetingDAO();
		allhandsmeetings = (List<AllHandsMeeting>)allhandsmeetingDAOImpl.getAllHandsMeetingById(allhandsmeeting);
		return allhandsmeetings;
		
	}
	
	@AuthorizeEntity(roles={Constants.HR})
	public AllHandsMeeting updateAOP(AllHandsMeeting allhandsmeeting) throws ObjectNotFoundException, AllHandsMeetingException {
		// TODO Auto-generated method stub
		List<AllHandsMeeting> allhands = getAllHandsMeeting();
		Timestamp date=allhandsmeeting.getDate();
		String employee=allhandsmeeting.getEmployee();
		String description=allhandsmeeting.getDescription();
		long id=allhandsmeeting.getId();
		validationFuncupdate(id,date,employee,description,allhands);
			
		
				
		AllHandsMeeting allhandsmeetingFromDB = (AllHandsMeeting)DAOFactory.getInstance().getAllHandsMeetingDAO().getObjectById(allhandsmeeting.getId(), ObjectTypes.ALL_HANDS_MEETING);
	allhandsmeetingFromDB.setDate(allhandsmeeting.getDate());
		allhandsmeetingFromDB.setEmployee(allhandsmeeting.getEmployee());
		allhandsmeetingFromDB.setDescription(allhandsmeeting.getDescription());
	
		AllHandsMeeting allhandsmeetingEdited = (AllHandsMeeting) DAOFactory.getInstance().getAllHandsMeetingDAO().update(allhandsmeetingFromDB);
		return allhandsmeetingEdited;
	}

	private void validationFuncupdate(long id,Timestamp date, String employee,String description, List<AllHandsMeeting> allhands) throws AllHandsMeetingException {
		 
		  for (int i = 0; i < allhands.size(); i++) {
			  Timestamp dbdate = allhands.get(i).getDate();
		   if(allhands.get(i).getId() != id){
		     if(dbdate.compareTo(date) == 0){
		      
		    	 throw new AllHandsMeetingException(ExceptionCodes.ALLHANDSMEETING_DATE_ALREADY_EXISTS,
		                 ExceptionMessages.ALLHANDSMEETING_DATE_ALREADY_EXISTS);
		      
		     }
		   }
		   
		   
		  }
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
	
	
	public PaginationOutput<AllHandsMeeting> getAllhandsSchedule(AllhandsmeetingInput allhands) {
		 Paginator<AllHandsMeeting> paginator = new Paginator<>();
		 paginator = DAOFactory.getInstance().getAllHandsMeetingDAO().getAllHandsSchedule(allhands);
		 
		 PaginationOutput<AllHandsMeeting> allHandsOutput = new PaginationOutput<>(paginator, allhands.getPageNo(), allhands.getPageSize());
		 return allHandsOutput;
	}



	
}

