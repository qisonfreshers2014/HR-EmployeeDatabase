package com.hred.persistence.daoimpl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.TemplateException;
import com.hred.model.Template;
import com.hred.persistence.dao.TemplateDAO;
import com.hred.persistence.session.SessionFactoryUtil;

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
   
  //createCriteria.add(Restrictions.eq("id", employee.getId()));
   
  //createCriteria.add(Restrictions.eq("isDeleted",false));
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
  public List< Template> viewTemplate( Template  template) throws TemplateException{
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
   
  createCriteria.add(Restrictions.eq("id",  template.getId()));
   
  //createCriteria.add(Restrictions.eq("isDeleted",false));
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

  
  

 public Template getContentForMail(Template template)
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
  createCriteria.add(Restrictions.eq("name",template.getSubject()));
 /* createCriteria.add(Restrictions.eq("name",  template.getSubject()));*/
  list = (Template) createCriteria.uniqueResult();
 
 return list; 
 
 }


 
}