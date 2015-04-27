package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="DESIGNATION_TYPE")
public class DesignationType extends AbstractObject{
	

	@Column(name="name")
	private String name;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public DesignationType() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DesignationType(AbstractObject abstractObject) {
		super(abstractObject);
		// TODO Auto-generated constructor stub
	}


	public DesignationType(String name) {
		super();
		this.name = name;
	}


	@Override
	public int getObjectType() {
		// TODO Auto-generated method stub
		return ObjectTypes.DESIGNATION_TYPE;
	}
	
}
