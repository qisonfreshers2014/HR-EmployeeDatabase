package com.hred.service.descriptors.input;

import org.codehaus.jackson.annotate.JsonProperty;

public class EmployeeOutput {
	
	

	private int employee_id;
	private String employee_name;
	private String gender;
	private String DOB;
	private String DOJ;
	
	@JsonProperty
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	@JsonProperty
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
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
		DOJ = dOJ;
	}

}
