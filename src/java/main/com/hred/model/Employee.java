package com.hred.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "EMPLOYEE")
public class Employee extends AbstractObject {
	@Column(name = "employee_id")
	private int employeeId;

	@Column(name = "name")
	private String employeeName;
	@Column(name = "gender")
	private String gender;
	@Column(name = "DOB")
	private String DOB;
	@Column(name = "DOJ")
	private String DOJ;
	@Column(name = "years_of_experience")
	private String yearsofexperience;
	@Column(name = "contact_number")
	private String contactNo;
	@Column(name = "current_address")
	private String currentAddress;
	@Column(name = "permanent_address")
	private String permanentAddress;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "emergency_contact_num")
	private String emergencycontactnumber;
	@Column(name = "fathers_name")
	private String fathersName;
	@Column(name = "emergency_conatact_name")
	private String emergencyContactName;
	@Column(name = "relation_with_contact")
	private String relationWithEmergencyConatact;
	@Column(name = "bloog_group")
	private String bloodGroup;
	@Column(name = "current_designation")
	private String currentDesignation;
	@Column(name = "reporting_manager")
	private String reportinManagerId;
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
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
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
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getDOJ() {
		return DOJ;
	}
	public void setDOJ(String dOJ) {
		DOJ = dOJ;
	}
	public String getYearsofexperience() {
		return yearsofexperience;
	}
	public void setYearsofexperience(String yearsofexperience) {
		this.yearsofexperience = yearsofexperience;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
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
	public String getEmergencycontactnumber() {
		return emergencycontactnumber;
	}
	public void setEmergencycontactnumber(String emergencycontactnumber) {
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
	public String getCurrentDesignation() {
		return currentDesignation;
	}
	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
	}
	public String getReportinManagerId() {
		return reportinManagerId;
	}
	public void setReportinManagerId(String reportinManagerId) {
		this.reportinManagerId = reportinManagerId;
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


	
public int getObjectType() {
		
		return ObjectTypes.EMPLOYEE;
	}
}
