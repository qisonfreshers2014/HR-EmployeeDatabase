package com.hred.handler;


import com.hred.model.Skills;
import com.hred.persistence.dao.DAOFactory;


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
	
	public Skills save(Skills skills){
		Skills skillssaved=(Skills) DAOFactory.getInstance().getSkillDAO().saveObject(skills);
		return skillssaved;
	}


}
