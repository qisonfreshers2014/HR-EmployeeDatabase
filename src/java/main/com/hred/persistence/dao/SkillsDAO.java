package com.hred.persistence.dao;

import java.util.List;

 

import com.hred.exception.SkillsException;
import com.hred.model.Skills;


 

 
public interface SkillsDAO extends BaseDAO{
	
	 
	public List<Skills> getSkillsById(String string);
	public List<Skills> getEditSkills(Skills skills) throws SkillsException;

	 

	public List<Skills> getSkillsDetails() throws SkillsException;
 
	 

}
