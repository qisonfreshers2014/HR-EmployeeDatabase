package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Saisudha
 *
 */

@Entity
@Table(name = "holiday_master")

public class holiday_master extends AbstractObject{

	@Column(name = "id")
	 private int id;
	 @Column(name = "type")
	 private String type; 
	
	public holiday_master(int id, String type){
		this.id = id;
		this.type = type;
	}
	 
	public long getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public int getObjectType() {
		
		return ObjectTypes.holiday_master;
	}

}
