package com.hred.pagination;

import java.sql.Timestamp;

public class EmployeeListPaginationInput extends PaginationInput {

	private int employeeId;
	private int currentDesignation;
	private Timestamp dateOfJoining;
	private String employeeName;
	private String pan;
	private boolean isDeleted;
	private int yearsofexperience;
	private Timestamp dateOfBirth;
	private Timestamp actualdateOfBirth;
	private long contactNo;
	private String email;
	private long emergencycontactnumber;
	 
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getCurrentDesignation() {
		return currentDesignation;
	}

	public void setCurrentDesignation(int currentDesignation) {
		this.currentDesignation = currentDesignation;
	}

	public Timestamp getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Timestamp dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getYearsofexperience() {
		return yearsofexperience;
	}

	public void setYearsofexperience(int yearsofexperience) {
		this.yearsofexperience = yearsofexperience;
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

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getEmergencycontactnumber() {
		return emergencycontactnumber;
	}

	public void setEmergencycontactnumber(long emergencycontactnumber) {
		this.emergencycontactnumber = emergencycontactnumber;
	}

	public EmployeeListPaginationInput(int employeeId, int currentDesignation,
			Timestamp dateOfJoining, String employeeName, String pan,
			boolean isDeleted, int yearsofexperience, Timestamp dateOfBirth,
			long contactNo, String email, long emergencycontactnumber, Timestamp actualdateOfBirth) {
		super();
		this.employeeId = employeeId;
		this.currentDesignation = currentDesignation;
		this.dateOfJoining = dateOfJoining;
		this.employeeName = employeeName;
		this.pan = pan;
		this.isDeleted = isDeleted;
		this.yearsofexperience = yearsofexperience;
		this.dateOfBirth = dateOfBirth;
		this.contactNo = contactNo;
		this.email = email;
		this.emergencycontactnumber = emergencycontactnumber;
		this.actualdateOfBirth=actualdateOfBirth;
	}
	
	public EmployeeListPaginationInput() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeListPaginationInput(int pageNo, int pageSize) {
		super(pageNo, pageSize);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
