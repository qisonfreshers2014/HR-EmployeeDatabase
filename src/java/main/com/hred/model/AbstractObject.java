package com.hred.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * Abstract Object is the implementation for common attributes of all objects
 * in the system. And also all model objects in the system will inherit this class.
 * @author Vinay Thandra
 * 
 **/
@MappedSuperclass
public abstract class AbstractObject implements BaseObject{

	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "cts")
	private Timestamp cts;
	
	@Column(name = "mts")
	private Timestamp mts;
	
	@Column(name = "created_by")
	private long creatorId;
	
	@Column(name = "modified_by")
	private long modifierId;

	
	@Column(name = "is_deleted")

	private boolean isDeleted;

	public static final String LABEL_ID = "id";
	public static final String LABEL_CREATED_TIME = "cts";
	public static final String LABEL_CREATOR_ID = "creatorId";
	public static final String LABEL_MODIFIED_TIME = "mts";
	public static final String LABEL_MODIFIER_ID = "modifierId";
	public static final String LABEL_IS_DELETED = "isDeleted";

	public AbstractObject() {
		super();
	}

	public AbstractObject(AbstractObject abstractObject) {
		this.id = abstractObject.id;
		this.cts = abstractObject.cts;
		this.creatorId = abstractObject.creatorId;
		this.modifierId = abstractObject.modifierId;
		this.mts = abstractObject.mts;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Timestamp getCts() {
		return cts;
	}

	@Override
	public void setCts(Timestamp cts) {
		this.cts = cts;
	}

	@Override
	public long getCreatorId() {
		return creatorId;
	}

	@Override
	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	@Override
	public Timestamp getMts() {
		return mts;
	}

	@Override
	public void setMts(Timestamp mts) {
		this.mts = mts;
	}

	@Override
	public long getModifierId() {
		return modifierId;
	}

	@Override
	public void setModifierId(long modifierId) {
		this.modifierId = modifierId;
	}

	public boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}
}