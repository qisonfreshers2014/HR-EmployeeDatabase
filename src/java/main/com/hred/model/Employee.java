package com.hred.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;



/**
 * @author Venkatesh Chitla
 *
 */

@Entity
@Table(name = "EMPLOYEE")
@Increment
public class Employee extends AbstractObject {
	

 

	@Column(name = "employee_id")
	private int employeeId;
	@Column(name = "name")
	private String employeeName;
	@Column(name = "gender")
	private String gender;
	//@Temporal(TemporalType.DATE)
	@Column(name = "DOB")
	private Timestamp dateOfBirth;
	//@Temporal(TemporalType.DATE)
	@Column(name = "DOJ")
	private Timestamp dateOfJoining;

	@Column(name = "years_of_experience")
	private int yearsofexperience;
	@Column(name = "contact_number")
	private long contactNo;
	@Column(name = "current_address")
	private String currentAddress;
	@Column(name = "permanent_address")
	private String permanentAddress;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "emergency_contact_num")
	private long emergencycontactnumber;
	@Column(name = "fathers_name")
	private String fathersName;
	@Column(name = "emergency_conatact_name")
	private String emergencyContactName;
	@Column(name = "relation_with_contact")
	private String relationWithEmergencyConatact;
	@Column(name = "bloog_group")
	private String bloodGroup;
	@Column(name = "current_designation")
	private int currentDesignation;
	@Column(name = "highest_qualification")
	private String highestQualification;
	@Column(name = "pan")
	private String pan;
	@Column(name = "pf_num")
	private String pfNo;
	@Column(name = "bank_account_num")
	private String bankAccountNo;
	@Column(name = "skype")
	private String skype;
	@Column(name = "Skill")
	private int skill;
	@Column(name = "Rating")
	private int rating;
	@Column(name="variable_component")
	private String variableComponent;
 public static final String AUTH_TYPE_REGULAR = "REGULAR";
 public static final int AUTH_STATUS_EXISTING = 0;
 public static final int AUTH_STATUS_NEW = 1;
 public static final int AUTH_STATUS_NONE = 2;
	
 @Column(name = "employee_id")
 private int employeeId;
 @Column(name = "name")
 private String employeeName;
 @Column(name = "gender")
 private String gender;
 //@Temporal(TemporalType.DATE)
 @Column(name = "DOB")
 private Timestamp dateOfBirth;
 //@Temporal(TemporalType.DATE)
 @Column(name = "DOJ")
 private Timestamp dateOfJoining;
		

 @Column(name = "years_of_experience")
 private int yearsofexperience;
 @Column(name = "contact_number")
 private long contactNo;
 @Column(name = "current_address")
 private String currentAddress;
 @Column(name = "permanent_address")
 private String permanentAddress;
 @Column(name = "email")
 private String email;
 @Column(name = "password")
 private String password;
 @Column(name = "emergency_contact_num")
 private long emergencycontactnumber;
 @Column(name = "fathers_name")
 private String fathersName;
 @Column(name = "emergency_conatact_name")
 private String emergencyContactName;
 @Column(name = "relation_with_contact")
 private String relationWithEmergencyConatact;
 @Column(name = "bloog_group")
 private String bloodGroup;
 @Column(name = "current_designation")
 private int currentDesignation;
 @Column(name = "highest_qualification")
 private String highestQualification;
 @Column(name = "pan")
 private String pan;
 @Column(name = "pf_num")
 private String pfNo;
 @Column(name = "bank_account_num")
 private String bankAccountNo;
 @Column(name = "skype")
 private String skype;
 @Column(name = "Skill")
 private String skill;
 @Column(name = "Rating")
 private int rating;
 @Column(name="variable_component")
 private String variableComponent;
 @Column(name="Salary")
 private String salary;
 
 public String getSkill() {
  return skill;
 }

	public Employee(int employeeId) {
		this.employeeId = employeeId;
		//this.employeeName = employeeName;
	}
 public void setSkill(String skill) {
  this.skill = skill;
 }

	
	public int getEmployeeId() {
		return employeeId;
	}
 public String getSalary() {
  return salary;
 }

 public void setSalary(String salary) {
  this.salary = salary;
 }

 public Employee() {
  

 }

 public Employee(int employeeId) {
  this.employeeId = employeeId;
  //this.employeeName = employeeName;
 }

 
 public int getEmployeeId() {
  return employeeId;
 }


	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}
 public void setEmployeeId(int employeeId) {
  this.employeeId = employeeId;
 }

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
 public String getEmployeeName() {
  return employeeName;
 }

	public Timestamp getDateOfJoining() {
		return dateOfJoining;
	}
 public void setEmployeeName(String employeeName) {
  this.employeeName = employeeName;
 }

	public void setDateOfJoining(Timestamp dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
 public String getGender() {
  return gender;
 }

 public void setGender(String gender) {
  this.gender = gender;
 }


 public Timestamp getDateOfBirth() {
  return dateOfBirth;
 }

 public void setDateOfBirth(Timestamp dateOfBirth) {
  this.dateOfBirth = dateOfBirth;
 }

 public Timestamp getDateOfJoining() {
  return dateOfJoining;
 }

 public void setDateOfJoining(Timestamp dateOfJoining) {
  this.dateOfJoining = dateOfJoining;
 }

 public int getYearsofexperience() {
  return yearsofexperience;
 }

 public void setYearsofexperience(int yearsofexperience) {
  this.yearsofexperience = yearsofexperience;
 }

 public long getContactNo() {
  return contactNo;
 }

 public void setContactNo(long contactNo) {
  this.contactNo = contactNo;
 }

 public String getCurrentAddress() {
  return currentAddress;
 }

 public void setCurrentAddress(String currentAddress) {
  this.currentAddress = currentAddress;
 }

 public String getPermanentAddress() {
  return permanentAddress;
 }

 public void setPermanentAddress(String permanentAddress) {
  this.permanentAddress = permanentAddress;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public long getEmergencycontactnumber() {
  return emergencycontactnumber;
 }

 public void setEmergencycontactnumber(long emergencycontactnumber) {
  this.emergencycontactnumber = emergencycontactnumber;
 }

 public String getFathersName() {
  return fathersName;
 }

 public void setFathersName(String fathersName) {
  this.fathersName = fathersName;
 }

 public String getEmergencyContactName() {
  return emergencyContactName;
 }

 public void setEmergencyContactName(String emergencyContactName) {
  this.emergencyContactName = emergencyContactName;
 }

 public String getRelationWithEmergencyConatact() {
  return relationWithEmergencyConatact;
 }

 public void setRelationWithEmergencyConatact(
   String relationWithEmergencyConatact) {
  this.relationWithEmergencyConatact = relationWithEmergencyConatact;
 }

 public String getBloodGroup() {
  return bloodGroup;
 }

 public void setBloodGroup(String bloodGroup) {
  this.bloodGroup = bloodGroup;
 }

 public int getCurrentDesignation() {
  return currentDesignation;
 }

 public void setCurrentDesignation(int currentDesignation) {
  this.currentDesignation = currentDesignation;
 }

 public String getHighestQualification() {
  return highestQualification;
 }

 public void setHighestQualification(String highestQualification) {
  this.highestQualification = highestQualification;
 }

 public String getPan() {
  return pan;
 }

 public void setPan(String pan) {
  this.pan = pan;
 }

 public String getPfNo() {
  return pfNo;
 }

 public void setPfNo(String pfNo) {
  this.pfNo = pfNo;
 }

 public String getBankAccountNo() {
  return bankAccountNo;
 }

 public void setBankAccountNo(String bankAccountNo) {
  this.bankAccountNo = bankAccountNo;
 }

 public String getSkype() {
  return skype;
 }

 public void setSkype(String skype) {
  this.skype = skype;
 }

 
 public int getRating() {
  return rating;
 }

 public void setRating(int rating) {
  this.rating = rating;
 }

 public String getVariableComponent() {
  return variableComponent;
 }

 public void setVariableComponent(String variableComponent) {
  this.variableComponent = variableComponent;
 }

 @Override
 public int getObjectType() {
  return ObjectTypes.EMPLOYEE;
 }

}

