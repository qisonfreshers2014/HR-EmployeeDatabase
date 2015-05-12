package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

 
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.SkillsException;
 
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
	@Override
	public List<Skills> getSkillsDetails() throws SkillsException{
	Session session = null;
	List<Skills> list = null;
	Transaction tx = null;
	try {
		session = getSession();
		if (null == session) {
			session = SessionFactoryUtil.getInstance().openSession();
			tx = SessionFactoryUtil.getInstance().beginTransaction(session);
		}
		Criteria createCriteria = session.createCriteria(Skills.class);
		 
		
		list = (List<Skills>)createCriteria.list();
	  } finally {
			try {
				if (tx != null) {
					tx.commit();
					if (session.isConnected())
						session.close();
				}
			} catch (HibernateException e) {

				e.printStackTrace();
			}
	  }
	return  list;
}
	
	 
	@Override
	public List<Skills> getEditSkills(Skills skills) throws SkillsException {
		Session session = null;
		List<Skills> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			Criteria createCriteria = session.createCriteria(Skills.class);
			
			 createCriteria.add(Restrictions.eq("id",skills.getId()));
			list = (List<Skills>)createCriteria.list();
			
		  } finally {
				try {
					if (tx != null) {
						tx.commit();
						if (session.isConnected())
							session.close();
					}
				} catch (HibernateException e) {

					e.printStackTrace();
				}
		  }
		return  list;
	}
	 
 
	@Override
	 public List<Skills> getSkillsById(int empId) {
	  Session session = null;
	  List<Skills> list = null;
	  Transaction tx = null;
	  try {
	   session = getSession();
	   if (null == session) {
	    session = SessionFactoryUtil.getInstance().openSession();
	    tx = SessionFactoryUtil.getInstance().beginTransaction(session);
	   }
	   Criteria createCriteria = session.createCriteria(Skills.class);
	   
	    createCriteria.add(Restrictions.eq("empId",empId));
	   list = (List<Skills>)createCriteria.list();
	   
	    } finally {
	    try {
	     if (tx != null) {
	      tx.commit();
	      if (session.isConnected())
	       session.close();
	     }
	    } catch (HibernateException e) {

	     e.printStackTrace();
	    }
	    }
	  return  list;
	 }	 
	 
	 
	 
	 

}
