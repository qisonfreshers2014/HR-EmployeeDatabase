/**
 * 
 */
package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hred.model.HRPolicy;
import com.hred.persistence.dao.HrPolicyDAO;
import com.hred.persistence.session.SessionFactoryUtil;

/**
 * @author saisudha
 *
 */
public class HrPolicyDAOImpl extends BaseDAOImpl implements HrPolicyDAO{
	
	private static HrPolicyDAO INSTANCE = null;
	
	private HrPolicyDAOImpl() {
	}

	public static HrPolicyDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HrPolicyDAOImpl();
		}
		return INSTANCE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HRPolicy> getPolicy() {
		Session session = null;
		List<HRPolicy> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			Criteria createCriteria = session.createCriteria(HRPolicy.class);
			//createCriteria.add(Restrictions.eq("id", employee.getId()));
			list = (List<HRPolicy>)createCriteria.list();
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
