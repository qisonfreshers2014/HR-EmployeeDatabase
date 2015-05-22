package com.hred.persistence.daoimpl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.HolidaysException;
import com.hred.model.Holiday;
import com.hred.persistence.dao.HolidayDAO;
import com.hred.persistence.session.SessionFactoryUtil;

/**
 * @author saisudha
 *
 */
public class HolidayDAOImpl extends BaseDAOImpl implements HolidayDAO {

	private static HolidayDAO INSTANCE = null;

	private HolidayDAOImpl() {
	}

	public static HolidayDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HolidayDAOImpl();
		}
		return INSTANCE;
	}
	
	/* */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Holiday> getHolidayById(Holiday holiday) throws HolidaysException {
		Session session = null;
		List<Holiday> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			Criteria createCriteria = session.createCriteria(Holiday.class);
			createCriteria.add(Restrictions.eq("id", holiday.getId()));
			createCriteria.add(Restrictions.eq("isDeleted", false));
			list = (List<Holiday>)createCriteria.list();
			  if (list.size() == 0) {
				    throw new HolidaysException(ExceptionCodes.HOLIDAY_DOESNOT_EXIST, ExceptionMessages.HOLIDAY_DOESNOT_EXIST);
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
	public List<Holiday> getHolidays() {
		Session session = null;
		List<Holiday> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			
			/*String hql = "SELECT fromDate, toDate, description FROM Holiday WHERE type ="+ holiday.getType();
			Query query = session.createQuery(hql);
			list = query.list();
			
			if(list.size()==0){
				try {
					throw new BusinessException(ExceptionCodes.HOLIDAYSID_DOESNOT_EXIST,ExceptionMessages.HOLIDAYSID_DOESNOT_EXIST);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			Criteria createCriteria = session.createCriteria(Holiday.class);
			createCriteria.add(Restrictions.eq("isDeleted", false));
			createCriteria.addOrder(Order.asc("fromDate"));
	
			list = (List<Holiday>)createCriteria.list();
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
	public Holiday getHolidayByDate(Holiday holiday) {

		Session session = null;
		List<Holiday> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			Criteria createCriteria = session.createCriteria(Holiday.class);
			createCriteria.add(Restrictions.eq("fromDate",holiday.getFromDate()));
			createCriteria.add(Restrictions.eq("isDeleted", false));
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

		return  (Holiday)list.iterator().next();
	}

	
	
	}
