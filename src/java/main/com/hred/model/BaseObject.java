package com.hred.model;

import java.sql.Timestamp;

/**
 * 
 * Defines all the common attributes across all model objects
 * @author Vinay Thandra
 *
 * */
public interface BaseObject {

	long getId();

	void setId(long id);
	
	/**
	 * CTS --> creation time stamp
	 * @return
	 */
	Timestamp getCts();

	void setCts(Timestamp timestamp);

	long getCreatorId();

	void setCreatorId(long creatorId);

	/**
	 * MTS --> last modified time stamp
	 * @return
	 */	
	Timestamp getMts();

	void setMts(Timestamp mts);

	long getModifierId();

	void setModifierId(long modifierId);

	/**
	 * Gets the type of object. The references for this is found in 
	 * Object types are defined in ObjectType interface
	 * @return
	 */
	int getObjectType();

}
