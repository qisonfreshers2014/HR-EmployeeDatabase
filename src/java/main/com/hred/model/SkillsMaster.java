package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;


@Entity
@Table(name = "skills_master")
@Increment
public class SkillsMaster extends AbstractObject {
	@Column(name = "id")
	 private int id;
	 @Column(name = "emp_name")
	 private String empName;  
	 public SkillsMaster() {
	  	 }
	 /**
	  * 
	  * @param id
	  * @param emp_name
	  */
	 public static final String AUTH_TYPE_REGULAR = "REGULAR";
	 public static final int AUTH_STATUS_EXISTING = 0;
	 public static final int AUTH_STATUS_NEW = 1; 
	 public static final int AUTH_STATUS_NONE = 2;
	 
	 public SkillsMaster(int id,String empName)
	 {
	  this.id = id;
	  this.empName = empName;
	  }

	 /**
	  * @return the id
	  */
 
	 public int getID(){
	  return id;
	 }

	 
	 public void setID(int id){
	  this.id=id;
	 }

	 
	 public String getEmpName() {
	  return empName;
	 }

	 /**
	  * @param EmpName the EmpName to set
	  */
	 public void setEmpName(String empName) {
	  this.empName = empName;
	 }
 
	 @Override
		public int getObjectType() {
		return ObjectTypes.USER;
}
}