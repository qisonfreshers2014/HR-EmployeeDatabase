package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hred.common.DateUtils;
import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
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

	@SuppressWarnings("unchecked")
	@Override
	public Employee getEmployeeById(long id) throws EmployeeException {
		// TODO Auto-generated method stub
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
			/*String hql="from Employee where id="+id+"";		
			org.hibernate.Query query = session.createQuery(hql);
			 list  = query.list();*/
			createCriteria.add(Restrictions.eq("id", id));
			list = createCriteria.list();
			if (list.size() == 0) {
				throw new EmployeeException(ExceptionCodes.EMPLOYEE_DOESNOT_EXIST, ExceptionMessages.EMPLOYEE_DOESNOT_EXIST);
			}

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
		return  (Employee) list.iterator().next();
	

	}
	@Override
	public List<Employee> getEmployees() {
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
			list = createCriteria.list();
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
	public List<Employee> getTodaysBirthday() throws BusinessException {

	Session session = null;
	List<Employee> list = null;
	Transaction tx = null;
	try {
	session = getSession();
	if (null == session) {
	session = SessionFactoryUtil.getInstance().openSession();
	tx = SessionFactoryUtil.getInstance().beginTransaction(session);
	}


	Long date = DateUtils.getCurrentTime();//Calendar.getInstance().DAY_OF_MONTH;
	String dob = date.toString();
	Criteria result = session.createCriteria(Employee.class).add(Restrictions.eq("DOB", dob));
	list = result.list();

	if (list.size() == 0) {
		throw new BusinessException(ExceptionCodes.NO_BIRTHDAY_TODAY, ExceptionMessages.NO_BIRTHDAY_TODAY);
	}

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
	return list;
	}
	



}
