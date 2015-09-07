package com.hred.model;

import java.sql.Timestamp;
import java.util.List;

public class EmployeeOutFile extends Employee {


 private Employee employee;
 private String filePath;
 private String designationName;
 private List<Skills> skills;

public EmployeeOutFile(String employeeId) {
  super(employeeId);
  // TODO Auto-generated constructor stub
 }
 public EmployeeOutFile(){
  
 }
 public EmployeeOutFile(Employee employee){
     super(employee);
     this.employee=employee;
 }
 


 public String getDesignationName() {
  return designationName;
 }
 public void setDesignationName(String designationName) {
  this.designationName = designationName;
 }
 public String getFilePath() {
  return filePath;
 }
 public void setFilePath(String filePath) {
  this.filePath = filePath;
 }
 
 public List<Skills> getSkills() {
	return skills;
}
public void setSkills(List<Skills> skills) {
	this.skills = skills;
}
public EmployeeOutFile(Employee employee,String filePath,String designationName,List<Skills> skills) {
     
  super(employee);
  this.filePath=filePath;
  this.designationName=designationName;
  this.skills=skills;
 }

 

}