package com.hred.persistence.daoimpl;
import static com.hred.common.Utils.getDBSession;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.model.AbstractObject;
import com.hred.model.BaseObject;
import com.hred.model.Objects;
import com.hred.model.Template;
import com.hred.persistence.annotations.Increment;
import com.hred.persistence.dao.BaseDAO;
import com.hred.persistence.model.IdCounter;
import com.hred.persistence.session.SessionFactoryUtil;
import com.hred.service.common.ServiceRequestContextHolder;

public class BaseDAOImpl implements BaseDAO{

	public Session getSession() {
        Session session = ServiceRequestContextHolder.getContext().getSession();
        if (session == null) {
            session = SessionFactoryUtil.getInstance().openSession();
        }
        return session;
	}
	
	private Session checkSession() {
        return ServiceRequestContextHolder.getContext().getSession();
	}


    public Criteria createCriteria(Class inputClass) {
        return getSession().createCriteria(inputClass);
    }
    public Criteria createCustomCriteria(Class inputClass) {
        Criteria criteria = getDBSession().createCriteria(inputClass);
        criteria.add(Restrictions.eq(AbstractObject.LABEL_IS_DELETED, Boolean.FALSE));
        return criteria;
    }

    @Override
    public BaseObject saveObject(BaseObject persistentObject) {
     Session session = checkSession();
     Transaction tx = null;
     if (session == null) {
      session = SessionFactoryUtil.getInstance().getCurrentSession();
      tx = SessionFactoryUtil.getInstance().beginTransaction(session);
     }
     long id = 0;
     Increment incrementAnnotation = persistentObject.getClass()
       .getAnnotation(Increment.class);
     String tableName = persistentObject.getClass()
       .getAnnotation(Table.class).name();
     if (incrementAnnotation != null) {
      id = IdGeneratorDAOImpl.getInstance().getNewId(tableName, session);
      persistentObject.setId(id);
     }
     try {
      long userId = ServiceRequestContextHolder.getContext()
        .getUserSessionToken().getUserId();
      persistentObject.setCreatorId(userId);
      persistentObject.setModifierId(userId);
     } catch (Exception e) {
     }
     Timestamp timestamp = new Timestamp(com.hred.common.DateUtils
       .getCurrentTimeInGMT());
     persistentObject.setCts(timestamp);
     persistentObject.setMts(timestamp);
     timestamp = null;
     session.save(persistentObject);
     IdCounter counter = IdGeneratorDAOImpl.getInstance().getObjectById(tableName, session);
     counter.setCounter(id);
     session.update(counter);
     if (tx != null) {
      tx.commit();
     }
     return persistentObject;
    }
    
    @Override
    public BaseObject update(BaseObject persistentObject) {
     Session session = checkSession();
     Transaction tx = null;
     if (session == null) {
      session = SessionFactoryUtil.getInstance().getCurrentSession();
      tx = SessionFactoryUtil.getInstance().beginTransaction(session);
     }
     try {
      long userId = ServiceRequestContextHolder.getContext()
        .getUserSessionToken().getUserId();
      persistentObject.setModifierId(userId);
     } catch (Exception e) {
     }
     Timestamp timestamp = new Timestamp(com.hred.common.DateUtils
       .getCurrentTimeInGMT());
     persistentObject.setMts(timestamp);
     timestamp = null;
     session.update(persistentObject);
           if (tx != null) {
               tx.commit();
           }
     return persistentObject;
    }

    @Override
    public BaseObject getObjectById(long id, int objectType)
      throws ObjectNotFoundException {
     BaseObject persistentObject = null;
     Session session = checkSession();
     Transaction tx = null;
     if (session == null) {
      session = SessionFactoryUtil.getInstance().getCurrentSession();
      tx = SessionFactoryUtil.getInstance().beginTransaction(session);
     }
     Class dbname = Objects.getInstance().getObjectName(objectType);
     persistentObject = (BaseObject)session.get(dbname, id);//(BaseObject) criteria.uniqueResult();
     if (tx != null) {
      tx.commit();
     }
     if (persistentObject == null) {
      throw new ObjectNotFoundException(ExceptionCodes.OBJECT_NOT_FOUND,
        ExceptionMessages.OBJECT_NOT_FOUND);
     }
     return persistentObject;
    }


    @Override
    public List<BaseObject> save(List<BaseObject> objectList) {
     Session session = checkSession();
     if (null != objectList && objectList.size() > 0) {
      short count = 0;
      for (BaseObject object : objectList) {
       saveObject(object);
       count++;
       if (count == 1000) {// batch update for each 30 records
        session.flush();
        session.clear();
        count = 0;
       }
      }
     }
     return objectList;
    }
	
}
