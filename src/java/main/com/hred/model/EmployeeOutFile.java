package com.hred.model;

import java.sql.Timestamp;

public class EmployeeOutFile extends Employee {


 private Employee employee;

public EmployeeOutFile(int employeeId) {
  super(employeeId);
  // TODO Auto-generated constructor stub
 }
 public EmployeeOutFile(){
  
 }
 public EmployeeOutFile(Employee employee){
     super(employee);
     this.employee=employee;
 }
 
 private String filePath;
 private String designationName;

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
 public EmployeeOutFile(Employee employee,String filePath,String designationName) {
     
  super(employee);
  this.filePath=filePath;
  this.designationName=designationName;
 }

 

}