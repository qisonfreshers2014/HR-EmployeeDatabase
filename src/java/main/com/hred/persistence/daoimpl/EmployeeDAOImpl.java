/**
 * 
 */
package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.hred.model.Employee;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.session.SessionFactoryUtil;
import com.hred.service.descriptors.Input.EmployeeSearchInputDescriptor;
/**
 * @author saisudha
 *
 */
public class EmployeeDAOImpl  extends BaseDAOImpl implements EmployeeDAO{

	private static EmployeeDAO INSTANCE = null;
		
	private EmployeeDAOImpl(){
		
	}
	
	public static EmployeeDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new EmployeeDAOImpl();
		}
		return INSTANCE;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployee() {
		Session session = null;
		List<Employee> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			Criteria createCriteria = session.createCriteria(Employee.class);
			//createCriteria.add(Restrictions.eq("id", employee.getId()));
			list = (List<Employee>)createCriteria.list();
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

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> searchEmployee(EmployeeSearchInputDescriptor employee) {
		Session session = null;
		List<Employee> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			Criteria createCriteria = session.createCriteria(Employee.class);
			//Criterion empid = Restrictions.eq("employeeId", employee.getSearchKey());
			Criterion name = Restrictions.ilike("employeeName",employee.getSearchKey(), MatchMode.ANYWHERE);
			Criterion email = Restrictions.ilike("email", employee.getSearchKey(), MatchMode.ANYWHERE);
			//Criterion gender = Restrictions.eq("gender", employee.getSearchKey());
		   //Criterion experience = Restrictions.eq("yearsofexperience", employee.getSearchKey());
			Disjunction disjunction = Restrictions.disjunction();
			//disjunction.add(empid);
			disjunction.add(name);
			disjunction.add(email);
		//	disjunction.add(gender);
		//	disjunction.add(experience);
			createCriteria.add(disjunction);
			
			list = (List<Employee>)createCriteria.list();
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
