package com.hred.persistence.daoimpl;
import java.util.List;
/**
 * @author Bhargavi Uppoju
 *
 */


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hred.model.DesignationHistory;
import com.hred.model.DesignationType;
import com.hred.persistence.dao.DesignationHistoryDAO;
import com.hred.persistence.session.SessionFactoryUtil;
public class DesignationHistoryDAOImpl extends BaseDAOImpl implements DesignationHistoryDAO{
	
private static DesignationHistoryDAO INSTANCE = null;
	
	private DesignationHistoryDAOImpl(){
		
	}
	
	public static DesignationHistoryDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DesignationHistoryDAOImpl();
		}
		return INSTANCE;
	}

@SuppressWarnings("unchecked")
@Override
public List<DesignationHistory> getDesignationDetails(DesignationHistory designationHistory){
Session session = null;
List<DesignationHistory> list = null;
Transaction tx = null;
try {
session = getSession();
if (null == session) {
session = SessionFactoryUtil.getInstance().openSession();
tx = SessionFactoryUtil.getInstance().beginTransaction(session);
}
Criteria createCriteria = session.createCriteria(DesignationHistory.class);
createCriteria.add(Restrictions.eq("empId", designationHistory.getEmpId()));
createCriteria.add(Restrictions.eq("isDeleted",false));
list = (List<DesignationHistory>)createCriteria.list();
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
public List<DesignationHistory> getAllDesignationDetails(){
Session session = null;
List<DesignationHistory> list = null;
Transaction tx = null;
try {
session = getSession();
if (null == session) {
session = SessionFactoryUtil.getInstance().openSession();
tx = SessionFactoryUtil.getInstance().beginTransaction(session);
}
Criteria createCriteria = session.createCriteria(DesignationHistory.class);

//createCriteria.add(Restrictions.eq("isDeleted",false));
list = (List<DesignationHistory>)createCriteria.list();
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
public List<DesignationType> getDesignationName(DesignationType designationType){
Session session = null;
List<DesignationType> list = null;
Transaction tx = null;
try {
session = getSession();
if (null == session) {
session = SessionFactoryUtil.getInstance().openSession();
tx = SessionFactoryUtil.getInstance().beginTransaction(session);
}
String hql="from DesignationType where is_deleted =0";
Query query = session.createQuery(hql);
list = query.list();
//Criteria createCriteria = session.createCriteria(hql);

//createCriteria.add(Restrictions.eq("isDeleted",false));
 //list = (List<DesignationType>)createCriteria.list();
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

 