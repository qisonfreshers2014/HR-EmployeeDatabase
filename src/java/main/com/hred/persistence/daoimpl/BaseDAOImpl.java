package com.hred.persistence.daoimpl;
import static com.hred.common.Utils.getDBSession;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hred.exception.ObjectNotFoundException;
import com.hred.model.BaseObject;
import com.hred.persistence.dao.BaseDAO;
import com.hred.persistence.session.SessionFactoryUtil;
import com.hred.service.common.ServiceRequestContextHolder;

public class BaseDAOImpl implements BaseDAO{
	
	public Session getSession(){
		return getDBSession();
	}

	@Override
	public BaseObject saveObject(BaseObject persistentObject) {
		Session session = getSession();
		Transaction tx = null;
        if (session == null) { 
        	session = SessionFactoryUtil.getInstance().getCurrentSession();
            tx = SessionFactoryUtil.getInstance().beginTransaction(session);
        }
        try {
            long userId = ServiceRequestContextHolder.getContext().getUserSessionToken().getUserId();
            persistentObject.setCreatorId(userId);
            persistentObject.setModifierId(userId);
        } catch (Exception e) {
        }
		persistentObject.setCts(com.hred.common.DateUtils.getCurrentTimeInGMT());
		persistentObject.setMts(com.hred.common.DateUtils.getCurrentTimeInGMT());
	    session.save(persistentObject);
        if (tx != null) {
            tx.commit();
        }
		return persistentObject;
	}
	@Override
	 public  BaseObject getObjectById(long id)
	   throws ObjectNotFoundException{
		return null;
	}
	 @Override
	 public BaseObject update(BaseObject persistentObject) {
		 Session session = getSession();
		 Transaction tx = null;
	        if (session == null) { 
	        	session = SessionFactoryUtil.getInstance().getCurrentSession();
	            tx = SessionFactoryUtil.getInstance().beginTransaction(session);
	        }
	        try {
	            long userId = ServiceRequestContextHolder.getContext().getUserSessionToken().getUserId();
	            persistentObject.setModifierId(userId);
	        } catch (Exception e) {
	        }
		 persistentObject.setMts(com.hred.common.DateUtils.getCurrentTimeInGMT());
	  session.update(persistentObject);
	  return persistentObject;
	 }

	 @Override
	 public List<BaseObject>save(List<BaseObject> objectList) {
		Session  session = getSession();
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
