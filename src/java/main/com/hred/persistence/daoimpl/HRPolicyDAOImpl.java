package com.hred.persistence.daoimpl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hred.model.HRPolicy;
import com.hred.persistence.dao.HRPolicyDAO;
import com.hred.persistence.session.SessionFactoryUtil;

/**
 * @author Bhargavi Uppoju
 *
 */

public class HRPolicyDAOImpl  extends BaseDAOImpl implements HRPolicyDAO{
	
private static HRPolicyDAO INSTANCE = null;
	
	private HRPolicyDAOImpl(){
		
	}
	public static HRPolicyDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HRPolicyDAOImpl();
		}
		return INSTANCE;
	}

//view
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