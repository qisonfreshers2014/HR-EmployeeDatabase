package com.hred.persistence.daoimpl;

import java.util.Calendar;
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
import com.hred.service.descriptors.output.NotificationHomeFilterInputDiscriptor;

public class EmployeeDAOImpl extends BaseDAOImpl implements EmployeeDAO {

	private static EmployeeDAO INSTANCE = null;

	public static EmployeeDAO getInstance()
	{
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
				return  (Employee) list.iterator().next();
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
	
	public String getEmployeeName(long id) {
		Session session = null;
		session = getSession();
		Criteria createCriteria = session.createCriteria(Employee.class);
		createCriteria.add(Restrictions.eq("id", id));
		List<Employee> list = createCriteria.list();
		return list.iterator().next().getEmployeeName();
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
    String hql="from Employee where is_deleted =0 and month(dateOfBirth)=month(sysdate())";//				
			org.hibernate.Query query = session.createQuery(hql);
			 results = query.list();
			 
			 
			 
			if (results.size() == 0) {
				System.out.println("No Birthday");
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
			
			String hql="from Employee where is_deleted=0 and  month(dateOfJoining)=month(sysdate()) and year(dateOfJoining)!=year(sysdate())";				
			org.hibernate.Query query = session.createQuery(hql);
			 list  = query.list();
			
			if (list.size() == 0) {
				System.out.println("No work Anivarsary");
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
	@Override
	public List<Employee> getWorkAniversarywithdate(NotificationHomeFilterInputDiscriptor filterCriteria) throws BusinessException{
		Calendar todate = Calendar.getInstance();
		todate.setTime(filterCriteria.getTodate());
		int tomonth = todate.get(Calendar.MONTH)+1;
		int today = todate.get(Calendar.DATE);		
		Calendar fromdate = Calendar.getInstance();
		fromdate.setTime(filterCriteria.getFromdate());		
		int frommonth = fromdate.get(Calendar.MONTH)+1;
		int fromday = fromdate.get(Calendar.DATE);
		Session session = null;
		List<Employee> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			
			String result ="from Employee where is_deleted=0 and  (day(dateOfJoining) between "+fromday+" and "+today+") and (month(dateOfJoining) between "+frommonth+" and "+tomonth+")";				
			org.hibernate.Query query = session.createQuery(result);
			 list  = query.list();
			
			if (list.size() == 0) {
			
				throw new UserException(ExceptionCodes.NO_EMPLOYEE_JOINED_TODAY, ExceptionMessages.NO_EMPLOYEE_JOINED_TODAY);
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
	
	@Override
	public List<Employee> getBirthdayWithindate(NotificationHomeFilterInputDiscriptor filterCriteria) throws BusinessException{
		
		
		Calendar todate = Calendar.getInstance();
		todate.setTime(filterCriteria.getTodate());
		int tomonth = todate.get(Calendar.MONTH)+1;
		int today = todate.get(Calendar.DATE);		
		Calendar fromdate = Calendar.getInstance();
		fromdate.setTime(filterCriteria.getFromdate());		
		int frommonth = fromdate.get(Calendar.MONTH)+1;
		int fromday = fromdate.get(Calendar.DATE);
		
		Session session = null;
		List<Employee> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			
			String result ="from Employee where is_deleted=0 and   (day(dateOfBirth) between "+fromday+" and "+today+") and (month(dateOfBirth) between "+frommonth+" and "+tomonth+")";	
			org.hibernate.Query query = session.createQuery(result);
			 list  = query.list();
			
			if (list.size() == 0) {
				throw new UserException(ExceptionCodes.NO_BIRTHDAY_FOUND, ExceptionMessages.NO_BIRTHDAY_FOUND);
						
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
			
	
    String hql="from Employee where is_deleted=0 and  day(dateOfBirth) = day(sysdate()) and month(dateOfBirth)=month(sysdate())";			
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
    String hql="from Employee where is_deleted=0 and  day(dateOfJoining) = day(sysdate()) and month(dateOfJoining)=month(sysdate()) and year(dateOfJoining)!=year(sysdate())";//				
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
	public List<Employee> getWelcomeEmployee() throws BusinessException {
		Session session = null;
		List<Employee> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			
			String hql="from Employee where is_deleted=0 and   date(dateOfJoining) between date(sysdate())-6 and date(sysdate())+6";				
			org.hibernate.Query query = session.createQuery(hql);
			 list  = query.list();
			
			if (list.size() == 0) {
				System.out.println(" No employee Joined today");
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
			createCriteria.add(Restrictions.eq("id", employee.getId()));
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
	public Employee getEmployeeById(String id) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

}
