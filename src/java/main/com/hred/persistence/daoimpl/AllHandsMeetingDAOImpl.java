package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hred.exception.AllHandsMeetingException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.model.AllHandsMeeting;
import com.hred.pagination.AllhandsmeetingInput;
import com.hred.pagination.PaginationInput;
import com.hred.pagination.Paginator;
import com.hred.persistence.dao.AllHandsMeetingDAO;
import com.hred.persistence.session.SessionFactoryUtil;

public class AllHandsMeetingDAOImpl extends BaseDAOImpl implements AllHandsMeetingDAO {
	private static AllHandsMeetingDAO INSTANCE = null;

	private AllHandsMeetingDAOImpl() {
	}

	public static AllHandsMeetingDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AllHandsMeetingDAOImpl();
		}
		return INSTANCE;
	}
	
	/* */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public  List<AllHandsMeeting> getAllHandsMeetingById(AllHandsMeeting allhandsmeeting) throws AllHandsMeetingException {
		Session session = null;
		List<AllHandsMeeting> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			Criteria createCriteria = session.createCriteria(AllHandsMeeting.class);
			createCriteria.add(Restrictions.eq("id", allhandsmeeting.getId()));
			//createCriteria.add(Restrictions.eq("isDeleted", false));
			list = (List<AllHandsMeeting>)createCriteria.list();
			if (list.size() == 0) {
				throw new AllHandsMeetingException(ExceptionCodes.AllHANDSMEETINGID_DOESNOT_EXIST, ExceptionMessages.AllHANDSMEETINGID_DOESNOT_EXIST);
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
	
	
		
	@SuppressWarnings("unchecked")
	@Override
	public List<AllHandsMeeting> getAllHandsMeeting() {
		Session session = null;
		List<AllHandsMeeting> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			
			
			Criteria createCriteria = session.createCriteria(AllHandsMeeting.class);
			createCriteria.addOrder(Order.desc("date"));
			list = (List<AllHandsMeeting>)createCriteria.list();
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
	public Paginator<AllHandsMeeting> getAllHandsSchedule(PaginationInput allhands) {
		 int pageNo = allhands.getPageNo();
		  int pageSize = allhands.getPageSize();
		  
		  int skipCount = (pageNo - 1) * pageSize;  
		  Criteria criteria=createCustomCriteria(AllHandsMeeting.class);
		  criteria.addOrder(Order.desc("date"));
		  

		        criteria.setFirstResult(skipCount).setMaxResults(pageSize);
		  List<AllHandsMeeting> consultantList=criteria.list();
		  
		  Criteria countCriteria=createCustomCriteria(AllHandsMeeting.class); 
		
		  Long totalCount = getRecordCount(countCriteria);

		  Paginator<AllHandsMeeting> allhandsPaginator = new Paginator<>(consultantList, totalCount);
		  return allhandsPaginator;
	}

	


	
}
