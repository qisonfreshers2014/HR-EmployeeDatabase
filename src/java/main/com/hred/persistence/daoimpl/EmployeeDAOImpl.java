package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.UserException;
import com.hred.model.Employee;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.session.SessionFactoryUtil;
import com.hred.service.descriptors.input.EmployeeSearchInputDescriptor;
import com.hred.service.descriptors.output.DisplayNotificationHome;
import com.hred.service.descriptors.output.NotificationHomeFilterInputDiscriptor;

public class EmployeeDAOImpl extends BaseDAOImpl implements EmployeeDAO {

	private static EmployeeDAO INSTANCE = null;

	private EmployeeDAOImpl() {
	}

	public static EmployeeDAO getInstance()
	{
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
		
		createCriteria.add(Restrictions.eq("DOJ",employee.getDateOfJoining()));
				 
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

	@Override
	public Employee getUserByEmail(String email) throws UserException {
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
			 

			createCriteria.add(Restrictions.eq("email", email));
			//createCriteria.add(Restrictions.eq("isDeleted", false));
			list = createCriteria.list();
			if (list.size() == 0) {
				throw new UserException(ExceptionCodes.USER_DOESNOT_EXIST, ExceptionMessages.USER_DOESNOT_EXIST);
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
	 
		return list.iterator().next();
		
	}
	
	@Override
	public Employee getEmployeeById(String id) throws EmployeeException {
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
			// createCriteria.add(Restrictions.eq("id", id));
			// createCriteria.add(Restrictions.eq("isDeleted", false));
			list = createCriteria.list();
			if (list.size() == 0) {
				throw new EmployeeException(
						ExceptionCodes.EMPLOYEE_DOESNOT_EXIST,
						ExceptionMessages.EMPLOYEE_DOESNOT_EXIST);
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
		return list.iterator().next();

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
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getBirthday() throws BusinessException {

		Session session = null;
		List<Employee> results = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
    String hql="from Employee where month(DOB)=month(sysdate())";//				
			org.hibernate.Query query = session.createQuery(hql);
			 results = query.list();
			 
			 
			 
			if (results.size() == 0) {
				throw new BusinessException(ExceptionCodes.NO_BIRTHDAY_TODAY,
						ExceptionMessages.NO_BIRTHDAY_TODAY);
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
		return results;
	}
	
	@Override
	public List<Employee> getWorkAniversary() throws BusinessException {
		Session session = null;
		List<Employee> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			
			String hql="from Employee where month(DOJ)=month(sysdate())";				
			org.hibernate.Query query = session.createQuery(hql);
			 list  = query.list();
			
			if (list.size() == 0) {
				throw new BusinessException(ExceptionCodes.NO_WORK_ANNIVERSARY_TODAY,
						ExceptionMessages.NO_WORK_ANNIVERSARY_TODAY);
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
	



	@SuppressWarnings("deprecation")
public List<DisplayNotificationHome> getEventWithinDate(NotificationHomeFilterInputDiscriptor filterCriteria) {
	Session session = null;
	List<DisplayNotificationHome> list = null;
	Transaction tx = null;
	

	try {
		session = getSession();
		if (null == session) {
			session = SessionFactoryUtil.getInstance().openSession();
			tx = SessionFactoryUtil.getInstance().beginTransaction(session);
		}
		
		/*String hql="from Employee where day(DOB) between "+fromDate.getDay()+" and "+toDate.getDay()+" and month(DOB) between "+fromDate.getMonth()+" and "+toDate.getMonth()+" or  day(DOJ) between "+fromDate.getDay()+" and "+toDate.getDay()+" and month(DOJ) between "+fromDate.getMonth()+" and "+toDate.getMonth();			
		org.hibernate.Query query = session.createQuery(hql);
		list  = query.list();*/
		
	
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
	
	public List<Employee> getTodaysBirthday()  {

		Session session = null;
		List<Employee> results = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			
	
    String hql="from Employee where day(DOB) = day(sysdate()) and month(DOB)=month(sysdate())";			
			org.hibernate.Query query = session.createQuery(hql);
			 results = query.list();
						 
			 
				if (results.size() == 0) {
					System.out.println("No Aniversary");
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
		return results;
	}
	public List<Employee> getTodayWorkAniversary() {

		Session session = null;
		List<Employee> results = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
    String hql="from Employee where day(DOJ) = day(sysdate()) and month(DOJ)=month(sysdate())";//				
			org.hibernate.Query query = session.createQuery(hql);
			 results = query.list();
			 
			 
			 
			if (results.size() == 0) {
				System.out.println("No Aniversary");
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
		return results;
	}

	@Override
	public List<Employee> viewEmployee(Employee employee) {
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
			createCriteria.add(Restrictions.eq("employeeId", employee.getEmployeeId()));
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
		return list;
	}

	@Override
	public List<Employee> getEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchEmployee(EmployeeSearchInputDescriptor employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmployeeName(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeById(long id) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}


	

	


}
