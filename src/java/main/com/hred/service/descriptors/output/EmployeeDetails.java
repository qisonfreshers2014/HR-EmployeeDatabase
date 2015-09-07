package com.hred.service.descriptors.output;

import org.codehaus.jackson.annotate.JsonProperty;

public class EmployeeDetails {

 private String employeeId;
 private String employeeName;
 private String gender;
 private long contactNo;
 private Boolean isDeleted;
 private long id;

 
 @JsonProperty
 public String getEmployeeId() {
  return employeeId;
 }
 
 public void setEmployeeId(String employeeId2) {
  this.employeeId = employeeId2;
 }
 
 @JsonProperty
 public String getEmployeeName() {
  return employeeName;
 }
 public void setEmployeeName(String employee_name) {
  this.employeeName = employee_name;
 }
 @JsonProperty
 public String getGender() {
  return gender;
 }
 public void setGender(String gender) {
  this.gender = gender;
 }
 
 @JsonProperty
 public long getContactNo() {
  return contactNo;
 }
 public void setContactNo(long contactNo) {
  this.contactNo = contactNo;
 }
 @JsonProperty
 public Boolean getIsDeleted() {
  return isDeleted;
 }
 public void setIsDeleted(Boolean isDeleted) {
  this.isDeleted = isDeleted;
 }
 @JsonProperty
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

}