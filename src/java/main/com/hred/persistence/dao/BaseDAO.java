
package com.hred.persistence.dao;

/**
 * @author vthandra
 */


import java.util.List;

import org.hibernate.Criteria;

import com.hred.exception.ObjectNotFoundException;
import com.hred.model.BaseObject;
import com.hred.model.Employee;
import com.hred.model.FilterEmployee;



/*
 * These are the methods we want to expose to business handlers for direct use. So they can call e.g. saveObject method
 * on the corresponding DAOs
 */
public interface BaseDAO {

	 public BaseObject saveObject(BaseObject persistentObject);

	 public BaseObject update(BaseObject persistentObject);

	 public List<BaseObject> save(List<BaseObject> persistentObjects);

	 public BaseObject getObjectById(long id,int objectType) throws ObjectNotFoundException;

	Long getRecordCount(Criteria countCriteria);


	
 

}