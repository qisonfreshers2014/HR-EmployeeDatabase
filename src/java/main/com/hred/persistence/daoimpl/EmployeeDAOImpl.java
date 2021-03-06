package com.hred.persistence.daoimpl;

import java.util.ArrayList;
import java.util.Calendar;
  
 
 

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hred.common.Constants;
import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.UserException;
import com.hred.model.Employee;
import com.hred.model.FilterEmployee;
import com.hred.pagination.EmployeeListPaginationInput;
import com.hred.pagination.NotificationPaginationInput;
import com.hred.pagination.Paginator;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.session.SessionFactoryUtil;
import com.hred.service.descriptors.output.DisplayNotificationHome;
import com.hred.service.descriptors.input.EmployeeSearchInputDescriptor;
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
	 public Employee getEmployeeById(String id) throws EmployeeException {
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
	   createCriteria.add(Restrictions.eq("employeeId", id));
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
			createCriteria.add(Restrictions.eq("isDeleted", false));
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
			} catch (HibernateException e){
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
	   if(employee.getSearchKey().matches("[0-9]*")){
	    double emp = Double.parseDouble(employee.getSearchKey());
        int emp1=(int) emp;
	    Criterion id = Restrictions.eq("employeeId", emp1);
	    Criterion years = Restrictions.eq("yearsofexperience", emp);
	    Criterion active = Restrictions.eq("isDeleted",Boolean.FALSE);
	    Criterion search = Restrictions.and(Restrictions.or(id,years), active);
	    createCriteria.add(search);
	    list = (List<Employee>)createCriteria.list();
	   }else{
	   Criterion name = Restrictions.ilike("employeeName", employee.getSearchKey(),MatchMode.ANYWHERE);
	   Criterion email = Restrictions.ilike("email", employee.getSearchKey(), MatchMode.ANYWHERE);
	   Criterion active = Restrictions.eq("isDeleted",Boolean.FALSE);
	   Criterion search = Restrictions.and(Restrictions.or(name,email), active);
	   createCriteria.add(search);
	   list = (List<Employee>)createCriteria.list();
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
    String hql="from Employee where is_deleted =0 and month(actualdateOfBirth)=month(sysdate()) ORDER BY actualdateOfBirth ";//				
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
			String hql="from Employee where is_deleted=0 and  month(dateOfJoining)=month(sysdate()) and year(dateOfJoining)!=year(sysdate()) ORDER BY dateOfJoining ";				
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
		int today = todate.get(Calendar.DATE)+1;		
		Calendar fromdate = Calendar.getInstance();
		fromdate.setTime(filterCriteria.getFromdate());		
		int frommonth = fromdate.get(Calendar.MONTH)+1;
		int fromday = fromdate.get(Calendar.DATE);
		Session session = null;
		List<Employee> list = new ArrayList<Employee>();
		List<Employee> list1 = null;
		List<Employee> list2 = null;
		List<Employee> list3 = null;
		List<Employee> list4 = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			if(frommonth>tomonth)
			{
				String result1 ="from Employee where is_deleted=0 and  ((day(dateOfJoining) between "+fromday+" and 31 ) and (month(dateOfJoining) ="+frommonth+")) and year(dateOfJoining)!=year(sysdate()) ORDER BY dateOfJoining ";				
				org.hibernate.Query query1 = session.createQuery(result1);
				 list1  = query1.list();
				 String result2 ="from Employee where is_deleted=0 and  ((day(dateOfJoining) between 1 and 31 )  and (month(dateOfJoining) between "+(frommonth+1)+"  and 13)) and year(dateOfJoining)!=year(sysdate()) ORDER BY dateOfJoining ";				
					org.hibernate.Query query2 = session.createQuery(result2);
					 list2  = query2.list();
					 String result3 ="from Employee where is_deleted=0 and  ((day(dateOfJoining) between 1 and 31 )  and (month(dateOfJoining) between 1  and "+(tomonth-1)+")) and year(dateOfJoining)!=year(sysdate()) ORDER BY dateOfJoining ";				
						org.hibernate.Query query3= session.createQuery(result3);
						 list3  = query3.list();
					 String result4 ="from Employee where is_deleted=0 and  ((day(dateOfJoining) between 1 and "+today+"  ) and (month(dateOfJoining) ="+tomonth+")) and year(dateOfJoining)!=year(sysdate()) ORDER BY dateOfJoining ";	
						org.hibernate.Query query4 = session.createQuery(result4);
						 list4  = query4.list();
					 if(list1.size()!=0)
					 {
					 list.addAll(list1);
					 }
					 if(list2.size()!=0)
					 {
					 list.addAll(list2);
					 }
					 if(list3.size()!=0)
					 {
					 list.addAll(list3);
					 }
					 if(list4.size()!=0)
					 {
					 list.addAll(list4);
					 }
			}
			else if(frommonth==tomonth)
			{
				String result ="from Employee where is_deleted=0 and  ((day(dateOfJoining) between "+fromday+" and "+today+" ) and (month(dateOfJoining) = "+tomonth+")) and year(dateOfJoining)!=year(sysdate()) ORDER BY dateOfJoining ";	
				org.hibernate.Query query = session.createQuery(result);
				 list  = query.list();
			}
			else
			{
				String result1 ="from Employee where is_deleted=0 and  ((day(dateOfJoining) between "+fromday+" and 31 ) and (month(dateOfJoining) ="+frommonth+")) and year(dateOfJoining)!=year(sysdate()) ORDER BY dateOfJoining ";				
				org.hibernate.Query query1 = session.createQuery(result1);
				 list1  = query1.list();
				 String result2 ="from Employee where is_deleted=0 and  ((day(dateOfJoining) between 1 and 31 )  and (month(dateOfJoining) between "+(frommonth+1)+"  and "+(tomonth-1)+")) and year(dateOfJoining)!=year(sysdate()) ORDER BY dateOfJoining ";				
					org.hibernate.Query query2 = session.createQuery(result2);
					 list2  = query2.list();
					 String result3 ="from Employee where is_deleted=0 and  ((day(dateOfJoining) between 1 and "+today+"  ) and (month(dateOfJoining) ="+tomonth+")) and year(dateOfJoining)!=year(sysdate()) ORDER BY dateOfJoining ";	
						org.hibernate.Query query3 = session.createQuery(result3);
						 list3  = query3.list();
					 if(list1.size()!=0)
					 {
					 list.addAll(list1);
					 }
					 if(list2.size()!=0)
					 {
					 list.addAll(list2);
					 }
					 if(list3.size()!=0)
					 {
					 list.addAll(list3);
			}
			}
			if (list.size() == 0) {
				System.out.println(" No anivarsary");
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
		return list;
	}
	@Override
	public List<Employee> getBirthdayWithindate(NotificationHomeFilterInputDiscriptor filterCriteria) throws BusinessException{
		Calendar todate = Calendar.getInstance();
		todate.setTime(filterCriteria.getTodate());		
		int tomonth = todate.get(Calendar.MONTH)+1;
		int today = todate.get(Calendar.DATE)+1;		
		Calendar fromdate = Calendar.getInstance();
		fromdate.setTime(filterCriteria.getFromdate());		
		int frommonth = fromdate.get(Calendar.MONTH)+1;
		int fromday = fromdate.get(Calendar.DATE);
		Session session = null;
		List<Employee> list = new ArrayList<Employee>();
		List<Employee> list1 = null;
		List<Employee> list2 = null;
		List<Employee> list3 = null;
		List<Employee> list4 = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			if(frommonth>tomonth)
			{
				String result1 ="from Employee where is_deleted=0 and  ((day(actualdateOfBirth) between "+fromday+" and 31 ) and (month(actualdateOfBirth) ="+frommonth+")) ORDER BY actualdateOfBirth  ";				
				org.hibernate.Query query1 = session.createQuery(result1);
				 list1  = query1.list();
				 String result2 ="from Employee where is_deleted=0 and  ((day(actualdateOfBirth) between 1 and 31 )  and (month(actualdateOfBirth) between "+(frommonth+1)+" and 13)) ORDER BY actualdateOfBirth ";				
					org.hibernate.Query query2 = session.createQuery(result2);
					 list2  = query2.list();
					 String result3 ="from Employee where is_deleted=0 and  ((day(actualdateOfBirth) between 1 and 31 )  and (month(actualdateOfBirth) between 1  and "+(tomonth-1)+")) ORDER BY actualdateOfBirth  ";				
						org.hibernate.Query query3= session.createQuery(result3);
						 list3  = query3.list();
					 String result4 ="from Employee where is_deleted=0 and  ((day(actualdateOfBirth) between 1 and "+today+"  ) and (month(actualdateOfBirth) ="+tomonth+")) ORDER BY actualdateOfBirth ";	
						org.hibernate.Query query4 = session.createQuery(result4);
						 list4  = query4.list();
					 if(list1.size()!=0)
					 {
					 list.addAll(list1);
					 }
					 if(list2.size()!=0)
					 {
					 list.addAll(list2);
					 }
					 if(list3.size()!=0)
					 {
					 list.addAll(list3);
					 }
					 if(list4.size()!=0)
					 {
					 list.addAll(list4);
					 }
			}
			else if(frommonth==tomonth)
			{
				String result ="from Employee where is_deleted=0 and  ((day(actualdateOfBirth) between "+fromday+" and "+today+" ) and (month(actualdateOfBirth) = "+tomonth+")) ORDER BY actualdateOfBirth ";	
				org.hibernate.Query query = session.createQuery(result);
				 list  = query.list();
			}
			else
			{
				String result1 ="from Employee where is_deleted=0 and  ((day(actualdateOfBirth) between "+fromday+" and 31 ) and (month(actualdateOfBirth) ="+frommonth+")) ORDER BY actualdateOfBirth ";				
				org.hibernate.Query query1 = session.createQuery(result1);
				 list1  = query1.list();
				 String result2 ="from Employee where is_deleted=0 and  ((day(actualdateOfBirth) between 1 and 31 )  and (month(actualdateOfBirth) between "+(frommonth+1)+"  and "+(tomonth-1)+")) ORDER BY actualdateOfBirth ";				
					org.hibernate.Query query2 = session.createQuery(result2);
					 list2  = query2.list();
					 String result3 ="from Employee where is_deleted=0 and  ((day(actualdateOfBirth) between 1 and "+today+"  ) and (month(actualdateOfBirth) ="+tomonth+")) ORDER BY actualdateOfBirth ";	
						org.hibernate.Query query3 = session.createQuery(result3);
						 list3  = query3.list();
					 if(list1.size()!=0)
					 {
					 list.addAll(list1);
					 }
					 if(list2.size()!=0)
					 {
					 list.addAll(list2);
					 }
					 if(list3.size()!=0)
					 {
					 list.addAll(list3);
			}
			}
			if (list.size() == 0) {
				System.out.println(" No Birthday");		
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
    String hql="from Employee where is_deleted=0 and  day(actualdateOfBirth) = day(sysdate()) and month(actualdateOfBirth)=month(sysdate()) and employeeType = '"+Constants.FULLTIME+"'";		
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
    String hql="from Employee where is_deleted=0 and  day(dateOfJoining) = day(sysdate()) and month(dateOfJoining)=month(sysdate()) and year(dateOfJoining)!=year(sysdate()) and employeeType = '"+Constants.FULLTIME+"'";//			
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
	 @SuppressWarnings("unchecked")
	@Override
	 public List<Employee> getEmployees(FilterEmployee filter) {
	  Session session = null;
	  List<Employee> list = null;
	  Transaction tx = null;
	  try {
	   session = getSession();
	   if (null == session) {
	    session = SessionFactoryUtil.getInstance().openSession();
	    tx = SessionFactoryUtil.getInstance().beginTransaction(session);
	   }
	//when all fields are entered
	   if(!filter.getFilterEmployee().equalsIgnoreCase("Inactive")){
	   String query = "from Employee where is_Deleted=0";
	   if( filter.getFilterEmployee().equalsIgnoreCase("Fulltime")||filter.getFilterEmployee().equalsIgnoreCase("Contract")){
		    query = query + " and employeeType='"+filter.getFilterEmployee()+"'";                                       
		   }
	    if(filter.getCurrentDesignation() != 0){
	    query = query+" and currentDesignation="+filter.getCurrentDesignation();
	   }
	    if(filter.getDateOfJoiningFrom() !=null){
		    if(filter.getDateOfJoiningFrom().compareTo(filter.getDateOfJoiningTo())<=0){
		    	  query=query+"and DOJ between '"+filter.getDateOfJoiningFrom()+"' and '"+filter.getDateOfJoiningTo()+"' ";
		    	     }
		    }
	     if(filter.getFrom()!=null){
	    query = query + " and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)>="+filter.getFrom();
	   }
	    if(filter.getGender() != null){
	    query = query + " and gender='"+filter.getGender()+"'";                                       
	   }
	    if(filter.getHighestQualification() != null){
	    query = query + " and highestQualification='"+filter.getHighestQualification()+"'";
	   }
	     if(filter.getTo() !=null){
	    query = query + " and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)<="+filter.getTo();
	   }
	   Query hql1=session.createQuery(query);
	   list = (List<Employee>)hql1.list();
	   }
	   else if(filter.getFilterEmployee().equalsIgnoreCase("Inactive")){
	   String query1= "from Employee where is_Deleted=1";
	   if(filter.getCurrentDesignation() != 0){
	    query1 = query1+" and currentDesignation="+filter.getCurrentDesignation();
	   }
	   if(filter.getDateOfJoiningFrom() !=null){
		    if(filter.getDateOfJoiningFrom().compareTo(filter.getDateOfJoiningTo())<=0){
		    	  query1=query1+"and DOJ between '"+filter.getDateOfJoiningFrom()+"' and '"+filter.getDateOfJoiningTo()+"' ";  
		    	     }
		    }
	   if(filter.getFrom()!=null){
	    query1 = query1 + " and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)>="+filter.getFrom();
	   }
	   if(filter.getGender() != null){
	    query1 = query1+ " and gender='"+filter.getGender()+"'";
	   }
	   if(filter.getHighestQualification() != null){
	    query1 = query1 + " and highestQualification='"+filter.getHighestQualification()+"'";
	   }
	   if(filter.getTo() !=null){
	    query1 = query1 + " and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)<="+filter.getTo();
	   }
	   System.out.println("Query :\n"+ query1);
	   Query hql1=session.createQuery(query1); 
	   list = (List<Employee>)hql1.list();
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
	  return  list;  
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
			String hql="from Employee where is_deleted=0 and   date(dateOfJoining) between  date(sysdate())-6 and date(sysdate()) ORDER BY dateOfJoining ";				
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
	 @SuppressWarnings("unchecked")
		@Override
		public Boolean getEmployeeByEmpId(String empid) throws EmployeeException {
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
				createCriteria.add(Restrictions.eq("employeeId", empid));
				createCriteria.add(Restrictions.eq("isDeleted", Boolean.FALSE));
				list = createCriteria.list();
				if (list.size() == 0) {
					return false;
				  } else{
					  return true;
				  }
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
		}
	 @SuppressWarnings("unchecked")
		@Override
		public Boolean isEmployeeEmailExist(String email){
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
				createCriteria.add(Restrictions.eq("email", email));
				createCriteria.add(Restrictions.eq("isDeleted", Boolean.FALSE));
				list = createCriteria.list();
				if (list.size() == 0) {
					return false;
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
					return  true;
		}
	@SuppressWarnings("unchecked")
	  @Override
	  public Employee viewEmployee(String EmployeeId) throws EmployeeException {
     
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
	      createCriteria.add(Restrictions.eq("employeeId", EmployeeId));
              createCriteria.addOrder(Order.desc("id"));
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
	       return list.iterator().next();
	  }
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
			 String hql="from Employee where is_deleted =0 ORDER BY employeeId";
			 org.hibernate.Query query = session.createQuery(hql);
			    list = query.list();
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
	public Paginator<NotificationPaginationInput> getEmployeesPaginated(
			NotificationPaginationInput employee) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Employee getLoggedInUser(long userId) throws EmployeeException {
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
	      createCriteria.add(Restrictions.eq("id", userId));
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
	       return list.iterator().next();
	}
	@Override
	 public Paginator<Employee> getEmployeesListPaginated(
	   EmployeeListPaginationInput employee) {
	  int pageNo = employee.getPageNo();
	  int pageSize = employee.getPageSize();
	  int skipCount = (pageNo - 1) * pageSize;  
	  Long countfilter=(long) 0;
	 /* Criteria criteria=createCustomCriteria(Employee.class);
	  criteria.addOrder(Order.asc("id"));
	  criteria.setFirstResult(skipCount).setMaxResults(pageSize);
	  List<Employee> consultantList=criteria.list();
	  Criteria countCriteria=createCustomCriteria(Employee.class); 
	  Long totalCount = getRecordCount(countCriteria);
	  Paginator<Employee> employeePaginator = new Paginator<>(consultantList, totalCount);
	  return employeePaginator;*/
	  Session session = null;
		List<Employee> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			String query ="FROM Employee WHERE is_Deleted=0 ORDER BY LPAD('',employeeId,100)ASC";
			  Query hql1=session.createQuery(query);
			  Query count=session.createQuery("select count(*) FROM Employee WHERE is_Deleted=0");
			   countfilter = (Long)count.uniqueResult();
			   hql1.setFirstResult(skipCount);
			   hql1.setMaxResults(pageSize);
			   list = (List<Employee>)hql1.list();
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
		 Paginator<Employee> employeePaginator = new Paginator<>(list, countfilter);
		  return employeePaginator;
	 }
	@Override
	public Paginator<Employee> getSearchedEmployeesListPaginated(
			EmployeeSearchInputDescriptor employee) {
		long totalCount = 0;
		 Paginator<Employee> employeePaginator=null;
		 List<Employee> consultantList=null;
		 int pageNo = employee.getPageNo();
		  int pageSize = employee.getPageSize();
		  int skipCount = (pageNo - 1) * pageSize; 
		  Criteria criteria=createCustomCriteria(Employee.class);
		   System.out.println(employee.getSearchKey());
		   if(employee.getSearchKey().matches("[0-9.]*")){
		    double emp = Double.parseDouble(employee.getSearchKey());
	        int emp1=(int) emp;
		    Criterion id = Restrictions.ilike("employeeId", employee.getSearchKey(),MatchMode.EXACT);
		    Criterion years = Restrictions.eq("yearsofexperience", emp);
		    Criterion active = Restrictions.eq("isDeleted",Boolean.FALSE);
		    Criterion search = Restrictions.and(Restrictions.or(id,years), active);
		    criteria.add(search);
			totalCount = getRecordCount(criteria);
		    criteria.setFirstResult(skipCount).setMaxResults(pageSize);
		    consultantList=criteria.list();
		   }else{
		   Criterion id = Restrictions.ilike("employeeId", employee.getSearchKey(),MatchMode.ANYWHERE);
		   Criterion name = Restrictions.ilike("employeeName", employee.getSearchKey(),MatchMode.ANYWHERE);
		   Criterion email = Restrictions.ilike("email", employee.getSearchKey(), MatchMode.ANYWHERE);
		   Criterion active = Restrictions.eq("isDeleted",Boolean.FALSE);
		   Criterion search = Restrictions.and(Restrictions.or(id,name,email), active);
		   criteria.add(search);
			totalCount = getRecordCount(criteria);
		   criteria.setFirstResult(skipCount).setMaxResults(pageSize);
		 consultantList=criteria.list(); 
	   }         
	 employeePaginator = new Paginator<>(consultantList, totalCount);  
	 return employeePaginator;
}
	@Override
	public Paginator<Employee> getFilterEmployeesListPaginated(
			FilterEmployee filter) {
		  int pageNo = filter.getPageNo();
		  int pageSize = filter.getPageSize();
		  int skipCount = (pageNo - 1) * pageSize;  
		  Session session = null;
		  List<Employee> list = null;
		  Long countfilter=(long) 0;
		  Transaction tx = null;
		  try {
		   session = getSession();
		   if (null == session) {
		    session = SessionFactoryUtil.getInstance().openSession();
		    tx = SessionFactoryUtil.getInstance().beginTransaction(session);
		   }
		  if(!filter.getFilterEmployee().equalsIgnoreCase("Inactive")){
			   String query = "from Employee where is_Deleted=0";
			   if( filter.getFilterEmployee().equalsIgnoreCase("Fulltime")||filter.getFilterEmployee().equalsIgnoreCase("Contract")){
				    query = query + " and employeeType='"+filter.getFilterEmployee()+"'";                                       
				   }
			    if(filter.getCurrentDesignation() != 0){
			    query = query+" and currentDesignation="+filter.getCurrentDesignation();
			   }
			    if(filter.getDateOfJoiningFrom() !=null){
				    if(filter.getDateOfJoiningFrom().compareTo(filter.getDateOfJoiningTo())<=0){
				    	  query=query+"and DOJ between '"+filter.getDateOfJoiningFrom()+"' and '"+filter.getDateOfJoiningTo()+"' "; 
				    	     }
				    }
			     if(filter.getFrom()!=null){
			    query = query + " and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)>="+filter.getFrom();
			   }
			    if(filter.getGender() != null){
			    query = query + " and gender='"+filter.getGender()+"'";                                       
			   }
			   
			    if(filter.getHighestQualification() != null){
			    query = query + " and highestQualification='"+filter.getHighestQualification()+"'";
			   }
			     if(filter.getTo() !=null){
			    query = query + " and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)<="+filter.getTo();
			   }
			     query = query +"ORDER BY LPAD('',employeeId,100)ASC";
			   Query hql1=session.createQuery(query);
			   Query count=session.createQuery("select count(*)"+query);
			   countfilter = (Long)count.uniqueResult();
			   hql1.setFirstResult(skipCount);
			   hql1.setMaxResults(pageSize);
			   list = (List<Employee>)hql1.list();
			   }
			   else if(filter.getFilterEmployee().equalsIgnoreCase("Inactive")){
			   String query1= "from Employee where is_Deleted=1";
			   if(filter.getCurrentDesignation() != 0){
			    query1 = query1+" and currentDesignation="+filter.getCurrentDesignation();
			   }
			   if(filter.getDateOfJoiningFrom() !=null){
				    if(filter.getDateOfJoiningFrom().compareTo(filter.getDateOfJoiningTo())<=0){
				    	  query1=query1+"and DOJ between '"+filter.getDateOfJoiningFrom()+"' and '"+filter.getDateOfJoiningTo()+"' ";
				    	     }
				    }
			   if(filter.getFrom()!=null){
			    query1 = query1 + " and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)>="+filter.getFrom();
			   }
			   if(filter.getGender() != null){
			    query1 = query1+ " and gender='"+filter.getGender()+"'";
			   }
			   if(filter.getHighestQualification() != null){
			    query1 = query1 + " and highestQualification='"+filter.getHighestQualification()+"'";
			   }
			   if(filter.getTo() !=null){
			    query1 = query1 + " and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)<="+filter.getTo();
			   }
			   query1 = query1 +"ORDER BY LPAD('',employeeId,100)ASC";
			   Query hql1=session.createQuery(query1);
			   Query count=session.createQuery("select count(*)"+query1);
			   countfilter = (Long)count.uniqueResult();
			   hql1.setFirstResult(skipCount);
			   hql1.setMaxResults(pageSize);
			   list = (List<Employee>)hql1.list();
			   }
		  Paginator<Employee> employeePaginator = new Paginator<>(list, countfilter);
		  return employeePaginator;
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
}
	@Override
	public List getProfilePics() {
		Session session = null;
		List list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			Criteria createCriteria = session.createCriteria(Employee.class);
			createCriteria.add(Restrictions.eq("isDeleted", false));
			createCriteria.setProjection(Projections.property("fileId"));
			list = createCriteria.list();
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
				return  list;
	}
	@Override
	public Employee getEmployeeById(long id) throws EmployeeException {
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
}
