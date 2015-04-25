package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hred.exception.EmployeeException;
import com.hred.model.Employee;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.session.SessionFactoryUtil;

public class EmployeeDAOImpl extends BaseDAOImpl implements EmployeeDAO{
	
	private static EmployeeDAO INSTANCE = null;
	
	private EmployeeDAOImpl(){
		
	}
	public static EmployeeDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new EmployeeDAOImpl();
		}
		return INSTANCE;
	}
 
	

	@Override
	public List<Employee> getFilterEmployeeDetails(Employee employee) throws EmployeeException{
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
			 
			createCriteria.add(Restrictions.eq("gender",employee.getGender()));
			createCriteria.add(Restrictions.eq("currentDesignation",employee.getCurrentDesignation()));
			
			createCriteria.add(Restrictions.eq("DOJ",employee.getDOJ()));
					 
		 	/*//createCriteria.add(Restrictions.gt("yearsofexperience",employee.getYearsofexperience()));
			createCriteria.add(Restrictions.eq("highestQualification",employee.getHighestQualification()));
			 
				//createCriteria.add(Restrictions.eq("isDeleted",false));
*/			
			//createCriteria.add(Restrictions.eq("employeeName",employee.getEmployeeName()));
			
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
