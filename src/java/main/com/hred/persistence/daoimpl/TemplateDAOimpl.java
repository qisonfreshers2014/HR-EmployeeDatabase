 package com.hred.persistence.daoimpl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.TemplateException;
import com.hred.model.AllHandsMeeting;
import com.hred.model.Template;
import com.hred.pagination.PaginationInput;
import com.hred.pagination.Paginator;
import com.hred.persistence.dao.TemplateDAO;
import com.hred.persistence.session.SessionFactoryUtil;
import com.hred.service.descriptors.output.DisplayNotificationHome;

public class TemplateDAOimpl extends BaseDAOImpl implements TemplateDAO {
	private static TemplateDAO INSTANCE = null;
	private TemplateDAOimpl() {
	}
	public static TemplateDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TemplateDAOimpl();
		}
		return INSTANCE;
	}
 @SuppressWarnings("unchecked")
 public List<Template> getTemplateByName() {
  Session session = null;
  List<Template> list = null;
  Transaction tx = null;
  try {
  session = getSession();
  if (null == session) {
  session = SessionFactoryUtil.getInstance().openSession();
  tx = SessionFactoryUtil.getInstance().beginTransaction(session);
  }
  Criteria createCriteria = session.createCriteria(Template.class);
  list = (List<Template>)createCriteria.list();
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
  public List< Template> viewTemplate( long id) throws TemplateException{
  Session session = null;
  List<Template> list = null;
  Transaction tx = null;
  try {
  session = getSession();
  if (null == session) {
  session = SessionFactoryUtil.getInstance().openSession();
  tx = SessionFactoryUtil.getInstance().beginTransaction(session);
  }
  Criteria createCriteria = session.createCriteria(Template.class);
  createCriteria.add(Restrictions.eq("id",id));
  list = (List<Template>)createCriteria.list();
  if (list.size() == 0) {
   throw new TemplateException(ExceptionCodes.TEMPLATE_DOESNOT_EXIST, ExceptionMessages.TEMPLATE_DOES_NOT_EXIST);
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
  public Template getContentForMail(DisplayNotificationHome template)
	{
		Session session = null;
		Template list = null;
		Transaction tx = null;
		session = getSession();
		if (null == session) {
		session = SessionFactoryUtil.getInstance().openSession();
		tx = SessionFactoryUtil.getInstance().beginTransaction(session);
		     }
		Criteria createCriteria = session.createCriteria(Template.class);
		createCriteria.add(Restrictions.eq("name",template.getEvent()));
		list = (Template) createCriteria.uniqueResult();
	    return list;	
	}
	//This method to retrieve all the templates as a list object
	@SuppressWarnings("unchecked")
	@Override
	public List<Template> getTemplates() {
		Session session = null;
		List<Template> list = null;
		Transaction tx = null;
		try {
		session = getSession();
		if (null == session) {
		session = SessionFactoryUtil.getInstance().openSession();
		tx = SessionFactoryUtil.getInstance().beginTransaction(session);
		}
		String hql="select new Template(id,name) from Template";
		Query query = session.createQuery(hql);
		list =query.list();
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
	public Paginator<Template> getAllHandsSchedule(PaginationInput alltemplates) {
		 int pageNo = alltemplates.getPageNo();
		  int pageSize = alltemplates.getPageSize();
		  int skipCount = (pageNo - 1) * pageSize;  
		  Criteria criteria=createCustomCriteria(Template.class);
		           criteria.setProjection(Projections.projectionList()
		    	            .add(Projections.property("id"), "id")
		    	            .add(Projections.property("name"), "name"))
		    	            .setResultTransformer(Transformers.aliasToBean(Template.class));
		            criteria.setFirstResult(skipCount).setMaxResults(pageSize);
		  List<Template> consultantList=criteria.list();
		  Criteria countCriteria=createCustomCriteria(Template.class); 
		  Long totalCount = getRecordCount(countCriteria);
		  Paginator<Template> allhandsPaginator = new Paginator<>(consultantList, totalCount);
		  return allhandsPaginator;
	}
}
