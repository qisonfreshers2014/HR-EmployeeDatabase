package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hred.exception.BusinessException;
import com.hred.exception.EmployeeException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.model.Employee;
import com.hred.model.SendNotificationHistory;
import com.hred.persistence.dao.SendNotificationHistoryDAO;
import com.hred.persistence.session.SessionFactoryUtil;

public class SendNotificationHistoryDAOImpl extends BaseDAOImpl implements SendNotificationHistoryDAO{

	
	private static SendNotificationHistoryDAO INSTANCE = null;

	private SendNotificationHistoryDAOImpl() {

	}

	public static SendNotificationHistoryDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SendNotificationHistoryDAOImpl();
		}
		return INSTANCE;
	}

	
	public List<SendNotificationHistory> getHistorydata() {
		Session session = null;
		List<SendNotificationHistory> historydata = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			
			String hql="from SendNotificationHistory";				
			org.hibernate.Query query = session.createQuery(hql);
			historydata  = query.list();
			
		

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
		return historydata;
	}

	
	
	
}
