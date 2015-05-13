package com.hred.service.descriptors.output;

import org.codehaus.jackson.annotate.JsonProperty;

public class EmployeeDetails {

 private int employeeId;
 private String employeeName;
 private String gender;
 

 
 @JsonProperty
 public int getEmployeeId() {
  return employeeId;
 }
 
 public void setEmployeeId(int employeeId) {
  this.employeeId = employeeId;
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
 private long contactNo;
 private Boolean isDeleted;
}