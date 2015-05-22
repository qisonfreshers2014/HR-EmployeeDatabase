package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.UserException;
import com.hred.model.User;
import com.hred.persistence.dao.UserDAO;
import com.hred.persistence.session.SessionFactoryUtil;

public class UserDAOImpl extends BaseDAOImpl implements UserDAO {

	private static UserDAO INSTANCE = null;

	private UserDAOImpl() {
	}

	public static UserDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UserDAOImpl();
		}
		return INSTANCE;
	}
	@Override
	public User getUserByEmail(String email) throws UserException {
		Session session = null;
		List<User> list = null;
		Transaction tx = null;
		try {
			session = getSession();
			if (null == session) {
				session = SessionFactoryUtil.getInstance().openSession();
				tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			}
			Criteria createCriteria = session.createCriteria(User.class);
			createCriteria.add(Restrictions.eq("email", email));
			createCriteria.add(Restrictions.eq("isDeleted", false));
			list = createCriteria.list();
			if (list.size() == 0) {
				throw new UserException(ExceptionCodes.USER_DOESNOT_EXIST, ExceptionMessages.USER_DOESNOT_EXIST);
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
		return list.iterator().next();

	}
}
