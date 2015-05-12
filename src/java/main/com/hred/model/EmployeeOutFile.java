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
	public EmployeeOutFile(AbstractObject abstractObject, int employeeId,
			String employeeName, String gender, Timestamp dateOfBirth,
			Timestamp dateOfJoining, int yearsofexperience, long contactNo,
			String currentAddress, String permanentAddress, String email,
			String password, long emergencycontactnumber, String fathersName,
			String emergencyContactName, String relationWithEmergencyConatact,
			String bloodGroup, int currentDesignation,
			String highestQualification, String pan, String pfNo,
			String bankAccountNo, String skype, String skill, String rating,
			String variableComponent, String salary, int fileId) {
		super(abstractObject, employeeId, employeeName, gender, dateOfBirth,
				dateOfJoining, yearsofexperience, contactNo, currentAddress,
				permanentAddress, email, password, emergencycontactnumber, fathersName,
				emergencyContactName, relationWithEmergencyConatact, bloodGroup,
				currentDesignation, highestQualification, pan, pfNo, bankAccountNo,
				skype, skill, rating, variableComponent, salary, fileId);
		// TODO Auto-generated constructor stub
	}

	

}
