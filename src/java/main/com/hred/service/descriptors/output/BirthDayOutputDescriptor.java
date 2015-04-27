package com.hred.service.descriptors.output;

import org.codehaus.jackson.annotate.JsonProperty;

import com.hred.service.descriptors.JSONSerializationDescriptor;

public class BirthDayOutputDescriptor implements JSONSerializationDescriptor {

	private int employee_id;
	private String employee_name;

	private java.util.Date DOB;
	
	
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
	public java.util.Date getDOB() {
		return DOB;
	}

	public void setDOB(java.util.Date dOB) {
		DOB = dOB;
	}
}
