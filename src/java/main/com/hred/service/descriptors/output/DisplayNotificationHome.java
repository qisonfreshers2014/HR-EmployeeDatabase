package com.hred.service.descriptors.output;

import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonProperty;

public class DisplayNotificationHome {
	private String status;
	private String event;
	private Timestamp date;
	private String employeeEmail;
	private String employeeName;
	private String modifiedContent;
	



	@JsonProperty
	public String getModifiedContent() {
		return modifiedContent;
	}

	public void setModifiedContent(String modifiedContent) {
		this.modifiedContent = modifiedContent;
	}

	@JsonProperty
	public String getEmployeeEmail() {
		return employeeEmail;
	}

	@JsonProperty
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}


	
	@JsonProperty
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	@JsonProperty
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	@JsonProperty
	public String getEvent() {
		return event;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
	

	
	
	
	
}
