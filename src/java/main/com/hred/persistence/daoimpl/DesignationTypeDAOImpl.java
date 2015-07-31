package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hred.exception.DesignationTypeException;
import com.hred.exception.EmployeeException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.model.DesignationType;
import com.hred.model.Employee;
import com.hred.persistence.dao.DesignationTypeDAO;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.dao.TemplateDAO;
import com.hred.persistence.session.SessionFactoryUtil;

public class DesignationTypeDAOImpl extends BaseDAOImpl implements DesignationTypeDAO {

	
	private static DesignationTypeDAO INSTANCE = null;

	 private DesignationTypeDAOImpl() {
	 }

	 public static DesignationTypeDAO getInstance() {
	  if (INSTANCE == null) {
	   INSTANCE = new DesignationTypeDAOImpl();
	  }
	  return INSTANCE;
	 }
	
	public DesignationType getDesignationByID(Employee getDesignation)
	{    
		DesignationType getDesignationDetails= new DesignationType();
		Session session = null;
		List<Employee> results = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			System.out.println(getDesignation.getCurrentDesignation());
    String hql="from DesignationType where is_deleted =0 and id="+getDesignation.getCurrentDesignation();//				
			org.hibernate.Query query = session.createQuery(hql);
			getDesignationDetails = (DesignationType) query.uniqueResult();
		}
		 finally {

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
		return getDesignationDetails;
		
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDesignationTypes() {
		List<String> odesignationTypes= null;
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			
    String hql="from DesignationType where is_deleted =0";			
			org.hibernate.Query query = session.createQuery(hql);
			odesignationTypes = (List<String>)query.list();
		}
		 finally {

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
		return odesignationTypes;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public DesignationType getDesignationTypeById(long id) throws DesignationTypeException {
		List<DesignationType> list=null;
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			
			Criteria createCriteria = session.createCriteria(DesignationType.class);
			  
			   createCriteria.add(Restrictions.eq("id", id));
			   list = createCriteria.list();
			   if (list.size() == 0) {
			    throw new DesignationTypeException(ExceptionCodes.DESIGNATION_TYPE_DOES_NOT_EXIST,ExceptionMessages.DESIGNATION_TYPE_DOES_NOT_EXIST);
			     } 
			  }finally {
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
			    return  (DesignationType)list.iterator().next();
	
	}
	
}
