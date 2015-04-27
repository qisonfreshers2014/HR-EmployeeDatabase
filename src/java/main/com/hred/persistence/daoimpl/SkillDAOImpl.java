package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.model.Employee;
import com.hred.model.Skills;
import com.hred.persistence.dao.SkillsDAO;
import com.hred.persistence.session.SessionFactoryUtil;
 

public class SkillDAOImpl extends BaseDAOImpl implements SkillsDAO{
	
	private static SkillDAOImpl INSTANCE = null;
	
	private SkillDAOImpl(){
		
	}
	public static SkillsDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SkillDAOImpl();
		}
		return INSTANCE;
	}

	 

}
