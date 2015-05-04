 package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;

@Entity
@Table(name = "EMPLOYEESKILLS")
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




   public int getEmpId() {
  return empId;
 }




 public void setEmpId(int empId) {
  this.empId = empId;
 }




 public String getSkills() {
  return skills;
 }




 public void setSkills(String skills) {
  this.skills = skills;
 }




 public String getRating() {
  return rating;
 }




 public void setRating(String rating) {
  this.rating = rating;
 }




 public boolean isTrainingAttended() {
  return trainingAttended;
 }




 public void setTrainingAttended(boolean trainingAttended) {
  this.trainingAttended = trainingAttended;
 }




@Override
 public int getObjectType() {
 return ObjectTypes.USER;
}
}