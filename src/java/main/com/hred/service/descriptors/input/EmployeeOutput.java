package com.hred.service.descriptors.input;
 


import org.codehaus.jackson.annotate.JsonProperty;

public class EmployeeOutput {
	private int employeeId;
	private String employeeName;
	private String gender;
	private String DOB;
	private String DOJ;
	private String email;
	private String fatherName;
	private String designation;
	private String highestQualification;
	private String skypeId;
	private  long emergency_Contact_No;

	
	@JsonProperty
	public int getEmployee_id() {
		return employeeId;
	}
	public void setEmployee_id(int employeeId) {
		this.employeeId = employeeId;
	}
	@JsonProperty
	public String getEmployee_name() {
		return employeeName;
	}
	public void setEmployee_name(String employeeName) {
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
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	@JsonProperty
	public String getDOJ() {
		return DOJ;
	}
	public void setDOJ(String dOJ) {
		this.DOJ = dOJ;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getFatherName(){
		return fatherName;
	}
	public void setFatherName(String fatherName){
		this.fatherName=fatherName;
	}
	public String getDesignation(){
		return designation;
	}
	public void setDesignation(String designation){
		this.designation=designation;
	}
	public String getHighestQualification(){
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification){
		this.highestQualification=highestQualification;
	}
	public String getskypeId(){
		return skypeId;
	}
	public void setskypeId(String skypeId){
		this.skypeId=skypeId;
	}
	public long getEmergencyContactDetails(){
		return emergency_Contact_No;
	}
	public void setEmergencyContactDetails(int emergency_Contact_No){
		this.emergency_Contact_No=emergency_Contact_No;
	}
	

}
