package com.hred.persistence.daoimpl;

import java.util.List;

 
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hred.model.Employee;
import com.hred.model.FilterEmployee;
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
//Criteria createCriteria = session.createCriteria(FilterEmployee.class);
			
//when all fields are entered
			String query = "from Employee where 1=1";
			if(filter.getCurrentDesignation() != 0){
				query = query+" and currentDesignation="+filter.getCurrentDesignation();
			}
			if(filter.getDateOfJoining() != null){
				query = query + " and DOJ='"+filter.getDateOfJoining()+"'";
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
			System.out.println("Quiery :\n"+ query);
			Query hql1=session.createQuery(query);
			list = (List<Employee>)hql1.list();
			
/*if(filter.getGender()!=null && filter.getCurrentDesignation()!=0 && filter.getDateOfJoining()!=null && filter.getFrom()>0 &&filter.getTo()<30 && filter.getHighestQualification()!=null) {
String query = "from Employee where (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) >="+filter.getFrom()+" and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) "
+ "<="+ filter.getTo() +" and  gender=\'"+filter.getGender()+"\' and DOJ=\'" +filter.getDateOfJoining()+"\' and  currentDesignation=\'"+filter.getCurrentDesignation()+" \'and highestQualification=\'"+filter.getHighestQualification()+"\'"; 
		Query hql1=session.createQuery(query);
		list = (List<Employee>)hql1.list();
		}

//gender and designation are empty(remaining fields are filled)
else if(filter.getGender() == null && filter.getCurrentDesignation()==0 && filter.getDateOfJoining()!=null && filter.getFrom()>0 &&filter.getTo()<30 && filter.getHighestQualification()!=null){
String query=	"from Employee where (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) >="+filter.getFrom()+" and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)"
+ "<="+ filter.getTo() +" and DOJ=\'" +filter.getDateOfJoining()+"\' and highestQualification=\'"+filter.getHighestQualification()+"\'";
		Query hql2=session.createQuery(query);
		list = (List<Employee>)hql2.list();
}		 

// if designation is empty(remaining fields are filled)
else if(filter.getGender()!= null  && filter.getCurrentDesignation()==0 && filter.getDateOfJoining()!=null && filter.getFrom()>=0 &&filter.getTo()<30 && filter.getHighestQualification()!=null){
String query=	"from Employee where (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) >="+filter.getFrom()+" and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)"
+ "<="+ filter.getTo() +" and  gender=\'"+filter.getGender()+"\' and DOJ=\'" +filter.getDateOfJoining()+"\' and highestQualification=\'"+filter.getHighestQualification()+"\'";
		Query hql3=session.createQuery(query);
		list = (List<Employee>)hql3.list();
}

 //when gender,highestQualification,designation is not null(remaining are null)
	else if (filter.getGender()!=null && filter.getCurrentDesignation()!=0 && filter.getHighestQualification()!=null && filter.getDateOfJoining()==null){
	String query="from Employee where  gender=\'"+filter.getGender()+"\' and  currentDesignation=\'"+filter.getCurrentDesignation()+" \'and highestQualification=\'"+filter.getHighestQualification()+"\'"; 
	Query hql4=session.createQuery(query);
	list = (List<Employee>)hql4.list();
	}

//gender and highest qualification is  empty
	else if(filter.getGender() == null && filter.getHighestQualification()==null && filter.getCurrentDesignation()!=0 && filter.getDateOfJoining()!=null && filter.getFrom()>0 &&filter.getTo()<30){
		String query = "from Employee where (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) >="+filter.getFrom()+" and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) "
		+ "<="+ filter.getTo() +" and DOJ=\'" +filter.getDateOfJoining()+"\' and  currentDesignation=\'"+filter.getCurrentDesignation()+"\'";
		Query hql5=session.createQuery(query);
		list = (List<Employee>)hql5.list();
		}

//When Gender is empty
else if(filter.getGender() == null && filter.getCurrentDesignation()!=0 && filter.getDateOfJoining()!=null && filter.getFrom()>=0 &&filter.getTo()<30 && filter.getHighestQualification()!=null){
String query=	"from Employee where (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) >="+filter.getFrom()+" and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)"
+ "<="+ filter.getTo() +" and  currentDesignation=\'"+filter.getCurrentDesignation()+"\' and DOJ=\'" +filter.getDateOfJoining()+"\' and highestQualification=\'"+filter.getHighestQualification()+"\'";
		Query hql6=session.createQuery(query);
		list = (List<Employee>)hql6.list();
}


//current designation is 0 and highest qualification is  empty
else if(filter.getGender() != null && filter.getHighestQualification()==null && filter.getCurrentDesignation()==0 && filter.getDateOfJoining()!=null && filter.getFrom()>0 &&filter.getTo()<30){
	String query = "from Employee where (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) >="+filter.getFrom()+" and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) "
	+ "<="+ filter.getTo() +" and DOJ=\'" +filter.getDateOfJoining()+"\' and   gender=\'"+filter.getGender()+"\'";
	Query hql9=session.createQuery(query);
	list = (List<Employee>)hql9.list();
	}


//as of now above  are working properly


//when  gender is null and currentDesignation is 0 and highestQUalificatio is null
	else if (filter.getGender() == null && filter.getCurrentDesignation() == 0 && filter.getHighestQualification()==null && filter.getDateOfJoining()!=null){
	String query="from Employee where (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) >="+filter.getFrom()+" and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)"
	+ "<="+ filter.getTo()+"";	
		Query hql7=session.createQuery(query);
		list = (List<Employee>)hql7.list();
}


	 
//when designation is not null
	else if(filter.getGender()==null && filter.getCurrentDesignation()!= 0 && filter.getDateOfJoining()==null && filter.getHighestQualification()==null) {
String query = "from Employee where (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) >="+filter.getFrom()+" and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0)"
+ "<="+ filter.getTo() +" and DOJ=\'" +filter.getDateOfJoining()+"\' and  gender=\'"+filter.getGender()+" \' and highestQualification=\'"+filter.getHighestQualification()+"\'"; 
	Query hql8=session.createQuery(query);
	list = (List<Employee>)hql8.list();
		}




 

	else{
		 
	}*/
 

		//list = createCriteria.list();
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
	public Employee getEmployeeById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Employee getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	/*if(filter.getGender()!=null){
		query = query+"gender="+ filter.getGender();
	}  
	 
	else if(filter.getDateOfJoining()!=null && filter.getGender()!=null){
		query = query+"dateOfJoining="+filter.getDateOfJoining()+ "and gender= "+ filter.getGender();
	}
	
	else if(filter.getCurrentDesignation()!=0 && filter.getGender()!=null){
		query=query+"currentDesignation="+filter.getCurrentDesignation()+" and gender="+filter.getGender();
	}
	else if(filter.getDateOfJoining()!=null && (filter.getFrom()!=0 && filter.getTo()!=0)){
		
	}*/
	




/*
String query1= "from Employee where (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) >="+filter.getFrom()+" and (years_of_experience + timestampdiff(MONTH,DOJ, sysdate())/12.0) "
		+ "<="+ filter.getTo() +" and gender=\'"+filter.getGender()+"\'";
Query hql1 = session.createQuery(query1);*/



//list = (List<Employee>)hql1.list();

}
