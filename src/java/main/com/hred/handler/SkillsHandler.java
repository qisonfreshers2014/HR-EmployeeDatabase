package com.hred.handler;


import java.util.ArrayList;
import java.util.List;

import com.hred.common.Constants;
import com.hred.exception.EmployeeException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.exception.SkillsException;
import com.hred.handler.annotations.AuthorizeEntity;
import com.hred.model.Employee;
import com.hred.model.ObjectTypes;
import com.hred.model.Skills;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.SkillsDAO;


public class SkillsHandler extends AbstractHandler {
	private static SkillsHandler INSTANCE = null;

	private SkillsHandler() {
	}

	/**
	 * @return instance of EmployeeHandler
	 */
	public static SkillsHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SkillsHandler();
		return INSTANCE;
	}
	
	 
		
	public List<Skills> getSkillsDetails() throws SkillsException {
		List<Skills> skill = null;
		SkillsDAO skillsDAOImpl = (SkillsDAO) DAOFactory.getInstance().getSkillDAO();
		skill = (List<Skills>) skillsDAOImpl.getSkillsDetails();
		return skill;
	}
	
	 public List<com.hred.model.Skills> getSkillsById(String string) throws SkillsException {
		  List<Skills> skill = null;
		  SkillsDAO skillsDAOImpl = (SkillsDAO) DAOFactory.getInstance().getSkillDAO();
		  skill = (List<Skills>) skillsDAOImpl.getSkillsById(string);
		  
		  return skill;
		 }
	
 private void validationFunc( String skillName,String empId, String rating,List<Skills> skill )  throws SkillsException{
     
	 	   
      if(skillName == null || skillName.isEmpty()|| skillName.trim().isEmpty()) {
  		throw new SkillsException(ExceptionCodes.Skills_DOESNOT_EXIST,ExceptionMessages.Skills_DOESNOT_EXIST);
  		}
   
      if (empId=="" || empId.isEmpty()){
			throw new SkillsException(ExceptionCodes.Skills_DOESNOT_EXIST,ExceptionMessages.Skills_DOESNOT_EXIST);
      	}
      
      if (rating == null || rating.isEmpty()|| rating.trim().isEmpty()){
				throw new SkillsException(ExceptionCodes.Skills_DOESNOT_EXIST,ExceptionMessages.Skills_DOESNOT_EXIST);
		}
      
      }
	
	public Skills saveAOP(Skills skills) throws SkillsException, EmployeeException{
	List<Skills> skill = getSkillsDetails();
	Employee emp=new Employee();
	EmployeeHandler emphandler=EmployeeHandler.getInstance();
	emp=emphandler.getEmployeeById(skills.getEmpId());
	if(emp==null)
	{
		throw new EmployeeException(ExceptionCodes. EMPLOYEE_DOESNOT_EXIST,ExceptionMessages. EMPLOYEE_DOESNOT_EXIST);
	}
	else
	{
		 String skillName = skills.getSkills();
		  String empId = skills.getEmpId();
		  String rating =skills.getRating();
		validationFunc(skillName,empId,rating,skill);
		Skills skillssaved=(Skills) DAOFactory.getInstance().getSkillDAO().saveObject(skills);
		return skillssaved;
	}
	 
	}
	
	//@AuthorizeEntity(roles={Constants.HR})
	public Skills update(Skills skills) throws ObjectNotFoundException, SkillsException {
		List<Skills> skill = getSkillsDetails();
		String skillName = skills.getSkills();
		  String empId = skills.getEmpId();
		  String rating =skills.getRating();
		  long id=skills.getId();
		  
		   
		//validationFunc(skillName,trainingAttended,empId,rating,skill);
		
		Skills skillsfromdb = (Skills)DAOFactory.getInstance().getSkillDAO().getObjectById(id, ObjectTypes.SKILLS);
		skillsfromdb.setEmpId(skillsfromdb.getEmpId());
		skillsfromdb.setSkills(skills.getSkills());
		skillsfromdb.setRating(skills.getRating());
	
		Skills skillsEdited = (Skills) DAOFactory.getInstance().getSkillDAO().update(skillsfromdb);
		return skillsEdited;
	
}

	 

	public List<Skills> getEditSkills(Skills skills) throws SkillsException {
		List<Skills> skill = null;
		SkillsDAO skillsDAOImpl = (SkillsDAO) DAOFactory.getInstance().getSkillDAO();
		skill = (List<Skills>) skillsDAOImpl.getEditSkills(skills);
		return skill;
	}

	public Skills deleteSkillsById(long id) throws ObjectNotFoundException {
		Skills skillsfromdb = (Skills)DAOFactory.getInstance().getSkillDAO().getObjectById(id, ObjectTypes.SKILLS);
		skillsfromdb.setDeleted(true);
		Skills skillsEdited = (Skills) DAOFactory.getInstance().getSkillDAO().update(skillsfromdb);
		return skillsEdited;
	}

	
	
	
}