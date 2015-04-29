package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;

@Entity
@Table(name = "Emp_Skills")
@Increment
public class Skills extends AbstractObject{

	 @Column(name = "emp_id")
	 private int empId; 
	 @Column(name = "skills")
	 private String skills;
	 @Column(name = "rating")
	 private String rating;
	 @Column(name = "training_attended",columnDefinition= "bit")
	 private boolean trainingAttended;
	
	 public Skills() {
	  	 }
	 /**
	  * 
	  * @param id
	  * @param emp_id
	  * @param skills
	  * @param rating
	  * @param trainingAttended
	  */
	 
	 public static final String AUTH_TYPE_REGULAR = "REGULAR";
	 public static final int AUTH_STATUS_EXISTING = 0;
	 public static final int AUTH_STATUS_NEW = 1; 
	 public static final int AUTH_STATUS_NONE = 2;
	 
	 public Skills(int empId, String skills,
	   String rating,boolean trainingAttended)
	   {
	  this.empId = empId;
	  this.skills = skills;
	  this.rating = rating;
	  this.trainingAttended = trainingAttended;
	    }


	 /**
	  * @return the empId
	  */
	 public int getEmpId() {
	  return empId;
	 }

	 /**
	  * @param EmpId the empId to set
	  */
	 public void setEmpId(int empId) {
	  this.empId = empId;
	 }

	 /**
	  * @return the skills
	  */
	 public String getSkills() {
	  return skills;
	 }

	 /**
	  * @param skills the skills to set
	  */
	 public void setSkills(String skills) {
	  this.skills = skills;
	 }

	 /**
	  * @return the rating
	  */
	 public String getRating() {
	  return rating;
	 }

	 /**
	  * @param rating the rating to set
	  */
	 public void setRating(String rating) { 
	  this.rating = rating;
	 }

	 /**
	  * @return the trainingAttended
	  */
	 public boolean getTrainingAttended() {
		  return trainingAttended;
		 }

		 /**
		  * @param training attended the training attended  to set
		  */
		 public void setTrainingAttended(boolean trainingAttended) {
		  this.trainingAttended = trainingAttended;
		 }
 
   @Override
	public int getObjectType() {
	return ObjectTypes.USER;
}
}
 