package com.hred.persistence.dao;

import org.hibernate.Session;

import com.hred.persistence.model.IdCounter;

/**
 * DAO to generate next Id in a collection
 * 
 * @author Vinay Thandra
 * 
 */
public interface IdGeneratorDAO {

	public long getNewId(String entityName, Session session);

	public IdCounter getObjectById(String entityName, Session session);
}
