package com.hred.persistence.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hred.persistence.dao.IdGeneratorDAO;
import com.hred.persistence.model.IdCounter;

public class IdGeneratorDAOImpl implements IdGeneratorDAO {// dont change it to
															// BaseDAO else it
															// may be in a
	// recursive loop when
	// we try to generate id for the entity object
	// 'IdCounter'

	private static IdGeneratorDAO INSTANCE = null;

	public IdGeneratorDAOImpl() {
		
	}


	public static IdGeneratorDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new IdGeneratorDAOImpl();
		}
		return INSTANCE;
	}

	public long getNewId(String entityName, Session session) {
		Criteria criteria = session.createCriteria(IdCounter.class);
		criteria.add(Restrictions.eq(IdCounter.NAME, entityName));
		if (criteria.list().isEmpty()) {
			IdCounter counterObj = new IdCounter(entityName, 0);
			session.save(counterObj);
		}
		IdCounter counterObj = (IdCounter) criteria.list().get(0);
		long newCount = counterObj.getCounter() + 1;
		counterObj = null;
		return newCount;
	}

	public IdCounter getObjectById(String entityName, Session session) {
		//Session session = getDBSession();
		Criteria criteria = session.createCriteria(IdCounter.class);
		criteria.add(Restrictions.eq(IdCounter.NAME, entityName));
		IdCounter counterObj = (IdCounter) criteria.list().get(0);

		return counterObj;
	}

}
