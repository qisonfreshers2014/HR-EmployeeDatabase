package com.hred.handler;


import java.util.List;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.SkillsException;
 
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
	
 private void validationFunc( String skillName,boolean trainingAttended,int empId, String rating,List<Skills> skill )  throws SkillsException{
     
    // List<AllHandsMeeting> data=save();
      for (int i = 0; i < skill.size(); i++) {
       
          String dbSkill = skill.get(i).getSkills();
          int dbEmpid = skill.get(i).getEmpId();
             if((dbSkill.compareTo(skillName) == 0) && (dbEmpid ==empId)){
              throw new SkillsException(ExceptionCodes.SKILLNAME_ALREADY_EXISTS,
                   ExceptionMessages.SKILLNAME_ALREADY_EXISTS);
              
          }    
      }   
       
      if (skillName == null || skillName.isEmpty()|| skillName.trim().isEmpty()) {
  		throw new SkillsException(ExceptionCodes.Skills_DOESNOT_EXIST,ExceptionMessages.Skills_DOESNOT_EXIST);
  	}
   
      if (empId==0) {
			throw new SkillsException(ExceptionCodes.Skills_DOESNOT_EXIST,ExceptionMessages.Skills_DOESNOT_EXIST);
      }
	 if (rating == null || rating.isEmpty()|| rating.trim().isEmpty()) {
				throw new SkillsException(ExceptionCodes.Skills_DOESNOT_EXIST,ExceptionMessages.Skills_DOESNOT_EXIST);
			}
      
      }

 
	
	public Skills save(Skills skills) throws SkillsException{
	List<Skills> skill = getSkillsDetails();
	//List<Skills> skill = getSkillsDetails(skills);
		  String skillName = skills.getSkills();
		  boolean trainingAttended = skills.getTrainingAttended();
		  int empId = skills.getEmpId();
		  String rating =skills.getRating();
		  /*validateSkills(skillName);
		 // validateTraining(trainingAttended);
		  validateEmpId(empId);
		  validateRating(rating);*/
		validationFunc(skillName,trainingAttended,empId,rating,skill);
		Skills skillssaved=(Skills) DAOFactory.getInstance().getSkillDAO().saveObject(skills);
		return skillssaved;
	}
	/*public  void validateSkills(String skillName) throws SkillsException {
		if (skillName == null || skillName.isEmpty()|| skillName.trim().isEmpty()) {
		throw new SkillsException(ExceptionCodes.Skills_DOESNOT_EXIST,ExceptionMessages.Skills_DOESNOT_EXIST);
	}
		
	}
	 private void validateTraining(boolean trainingAttended) {
		if (trainingAttended == null) {
			   throw new SkillsException(ExceptionCodes.HRPolicy_DOESNOT_EXIST,
			     ExceptionMessages.HRPolicy_DOESNOT_EXIST);
		
		
	}
   public void validateRating(String rating) throws SkillsException {
		if (rating == null || rating.isEmpty()|| rating.trim().isEmpty()) {
			throw new SkillsException(ExceptionCodes.Skills_DOESNOT_EXIST,ExceptionMessages.Skills_DOESNOT_EXIST);
		}
		
	}
	public void validateEmpId(int empId) throws SkillsException {
		if (empId==0) {
			throw new SkillsException(ExceptionCodes.Skills_DOESNOT_EXIST,ExceptionMessages.Skills_DOESNOT_EXIST);
					
		}
	}*/

	public List<Skills> getEditSkills(Skills skills) throws SkillsException {
		List<Skills> skill = null;
		SkillsDAO skillsDAOImpl = (SkillsDAO) DAOFactory.getInstance().getSkillDAO();
		skill = (List<Skills>) skillsDAOImpl.getEditSkills(skills);
		return skill;
	}

	 

}
