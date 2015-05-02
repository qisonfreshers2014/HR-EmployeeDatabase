package com.hred.service.descriptors.Input;
 


import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonProperty;

public class EmployeeOutput {
	private int employeeId;
	private String employeeName;
	private String gender;
	private Timestamp DOB;
	private Timestamp DOJ;
	private String email;
	private String fatherName;
	private String currentDesignation;
	private String highestQualification;
	private String skypeId;
	private  long emergencyContactNo;

	
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
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	@JsonProperty
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@JsonProperty
	public Timestamp getDOJ() {
		  return DOJ;
		 }

		 public void setDOJ(Timestamp dOJ) {
		  DOB = dOJ;
		 }
	@JsonProperty
	public Timestamp getDOB() {
		  return DOB;
		 }

		 public void setDOB(Timestamp dOB) {
		  DOB = dOB;
		 }
		 @JsonProperty
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email=email;
	}
	@JsonProperty
	public String getFatherName(){
		return fatherName;
	}
	
	public void setFatherName(String fatherName){
		this.fatherName=fatherName;
	}
	@JsonProperty
	public String getcurrentDesignation(){
		return currentDesignation;
	}
	public void setcurrentDesignation(String currentDesignation){
		this.currentDesignation=currentDesignation;
	}
	@JsonProperty
	public String getHighestQualification(){
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification){
		this.highestQualification=highestQualification;
	}
	@JsonProperty
	public String getskypeId(){
		return skypeId;
	}
	public void setskypeId(String skypeId){
		this.skypeId=skypeId;
	}
	@JsonProperty
	public long getEmergencyContactDetails(){
		return emergencyContactNo;
	}
	public void setEmergencyContactDetails(int emergencyContactNo){
		this.emergencyContactNo=emergencyContactNo;
	}
	

}
