package com.hred.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;

@Entity
@Table(name = "employee")
@Increment
public class Employee extends AbstractObject {
 public static final String AUTH_TYPE_REGULAR = "REGULAR";
 public static final int AUTH_STATUS_EXISTING = 0;
 public static final int AUTH_STATUS_NEW = 1;
 public static final int AUTH_STATUS_NONE = 2;

 @Column(name = "employee_id")
 private String employeeId;
 @Column(name = "name")
 private String employeeName;
 @Column(name = "gender")
 private String gender;
 //@Temporal(TemporalType.DATE)
 @Column(name = "DOB")
 private Timestamp dateOfBirth;
 //@Temporal(TemporalType.DATE)
 @Column(name = "Actual_DOB")
 private Timestamp actualdateOfBirth;
@Column(name = "DOJ")
 private Timestamp dateOfJoining;
 @Column(name = "years_of_experience")
 private double yearsofexperience;
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
 private String rating;
 @Column(name="variable_component")
 private String variableComponent;
 @Column(name="Salary")
 private String salary;
 @Column(name="file_id")
 private long fileId;
 @Column(name="employee_Type")
 private String employeeType;
 @Column(name="university")
 private String university;
 @Column(name="hobbies")
 private String hobbies;
 @Column(name = "Last_Working_day")
 private Timestamp lastWorkingDay;

public long getFileId() {
	return fileId;
}

public void setFileId(long fileId) {
	this.fileId =fileId;
}

public String getSkill() {
  return skill;
 }

 public void setSkill(String skill) {
  this.skill = skill;
 }

 public String getSalary() {
  return salary;
 }

 public void setSalary(String salary) {
  this.salary = salary;
 }

 public Employee() {
  

 }

 public Employee(String employeeId) {
  this.employeeId = employeeId;
  //this.employeeName = employeeName;
 }

 
 public String getEmployeeId() {
  return employeeId;
 }

 public void setEmployeeId(String employeeId) {
  this.employeeId = employeeId;
 }

 public String getEmployeeName() {
  return employeeName;
 }

 public void setEmployeeName(String employeeName) {
  this.employeeName = employeeName;
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
 public Timestamp getActualdateOfBirth() {
		return actualdateOfBirth;
	}

	public void setActualdateOfBirth(Timestamp actualdateOfBirth) {
		this.actualdateOfBirth = actualdateOfBirth;
	}
 public Timestamp getDateOfJoining() {
  return dateOfJoining;
 }

 public void setDateOfJoining(Timestamp dateOfJoining) {
  this.dateOfJoining = dateOfJoining;
 }

 public double getYearsofexperience() {
  return yearsofexperience;
 }

 public void setYearsofexperience(double yearsofexperience) {
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
 
 public String getRating() {
  return rating;
 }

 public void setRating(String rating) {
  this.rating = rating;
 }

 public String getVariableComponent() {
  return variableComponent;
 }

 public void setVariableComponent(String variableComponent) {
  this.variableComponent = variableComponent;
 }
 

 public String getEmployeeType() {
	return employeeType;
}

public void setEmployeeType(String employeeType) {
	this.employeeType = employeeType;
}

public String getUniversity() {
	return university;
}

public void setUniversity(String university) {
	this.university = university;
}

public String getHobbies() {
	return hobbies;
}

public void setHobbies(String hobbies) {
	this.hobbies = hobbies;
}

public Timestamp getLastWorkingDay() {
	return lastWorkingDay;
}

public void setLastWorkingDay(Timestamp lastWorkingDay) {
	this.lastWorkingDay = lastWorkingDay;
}

public Employee(AbstractObject abstractObject, String employeeId,
		String employeeName, String gender, Timestamp dateOfBirth,
		Timestamp dateOfJoining, int yearsofexperience, long contactNo,
		String currentAddress, String permanentAddress, String email,
		String password, long emergencycontactnumber, String fathersName,
		String emergencyContactName, String relationWithEmergencyConatact,
		String bloodGroup, int currentDesignation, String highestQualification,
		String pan, String pfNo, String bankAccountNo, String skype,
		String skill, String rating, String variableComponent, String salary,
		int fileId, Timestamp actualdateOfBirth, String employeeType, String university, String hobbies,Timestamp lastWorkingDay) {
	super(abstractObject);
	this.employeeId = employeeId;
	this.employeeName = employeeName;
	this.actualdateOfBirth=actualdateOfBirth;
	this.gender = gender;
	this.dateOfBirth = dateOfBirth;
	this.dateOfJoining = dateOfJoining;
	this.yearsofexperience = yearsofexperience;
	this.contactNo = contactNo;
	this.currentAddress = currentAddress;
	this.permanentAddress = permanentAddress;
	this.email = email;
	this.password = password;
	this.emergencycontactnumber = emergencycontactnumber;
	this.fathersName = fathersName;
	this.emergencyContactName = emergencyContactName;
	this.relationWithEmergencyConatact = relationWithEmergencyConatact;
	this.bloodGroup = bloodGroup;
	this.currentDesignation = currentDesignation;
	this.highestQualification = highestQualification;
	this.pan = pan;
	this.pfNo = pfNo;
	this.bankAccountNo = bankAccountNo;
	this.skype = skype;
	this.skill = skill;
	this.rating = rating;
	this.variableComponent = variableComponent;
	this.salary = salary;
	this.fileId = fileId;
	this.employeeType=employeeType;
	this.hobbies=hobbies;
	this.university=university;
	this.lastWorkingDay=lastWorkingDay;
}

public Employee(Employee employee) {
	
	super(employee);
	this.employeeId = employee.getEmployeeId();
	this.employeeName = employee.getEmployeeName();
	this.gender = employee.getGender();
	this.dateOfBirth = employee.getDateOfBirth();
	this.actualdateOfBirth=employee.getActualdateOfBirth();
	this.dateOfJoining = employee.getDateOfJoining();
	this.yearsofexperience = employee.getYearsofexperience();
	this.contactNo = employee.getContactNo();
	this.currentAddress = employee.getCurrentAddress();
	this.permanentAddress = employee.getPermanentAddress();
	this.email = employee.getEmail();
	this.emergencycontactnumber = employee.getEmergencycontactnumber();
	this.fathersName = employee.getFathersName();
	this.emergencyContactName = employee.getEmergencyContactName();
	this.relationWithEmergencyConatact = employee.getRelationWithEmergencyConatact();
	this.bloodGroup = employee.getBloodGroup();
	this.currentDesignation = employee.getCurrentDesignation();
	this.highestQualification = employee.getHighestQualification();
	this.pan = employee.getPan();
	this.pfNo = employee.getPfNo();
	this.bankAccountNo = employee.getBankAccountNo();
	this.skype = employee.getSkype();
	this.skill = employee.getSkill();
	this.rating = employee.getRating();
	this.variableComponent = employee.getVariableComponent();
	this.salary = employee.getSalary();
	this.fileId = employee.getFileId();
	this.employeeType=employee.getEmployeeType();
	this.hobbies=employee.getHobbies();
	this.university=employee.getUniversity();
	this.lastWorkingDay=employee.getLastWorkingDay();
}

@Override
 public int getObjectType() {
  return ObjectTypes.EMPLOYEE;
 }

}