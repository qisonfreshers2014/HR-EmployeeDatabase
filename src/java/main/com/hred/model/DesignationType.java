package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;
@Entity
@Table(name="designation_type")
@Increment
public class DesignationType extends AbstractObject{

	public DesignationType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DesignationType(String name) {
		super();
		this.name = name;
	}
	
	@Column(name="name")
	private String name;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}




	public DesignationType(AbstractObject abstractObject) {
		super(abstractObject);
		// TODO Auto-generated constructor stub
	}





	@Override
	public int getObjectType() {
		// TODO Auto-generated method stub
		return ObjectTypes.DESIGNATION_TYPE;
	}
	
}
