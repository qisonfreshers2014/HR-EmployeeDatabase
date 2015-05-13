package com.hred.pagination;

import java.sql.Timestamp;

public class NotificationPaginationInput extends PaginationInput {

	 private String status;
	 private String event;
	 private Timestamp date;
	 private String employeeEmail;
	 private String employeeName;
	 private String modifiedContent;

	
	
	 public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getModifiedContent() {
		return modifiedContent;
	}

	public void setModifiedContent(String modifiedContent) {
		this.modifiedContent = modifiedContent;
	}

	public NotificationPaginationInput()
	 {
		super();
	 }

			public NotificationPaginationInput(String event, Timestamp date,
				String employeeEmail, String employeeName) {		
			this.event = event;
			this.date = date;
			this.employeeEmail = employeeEmail;
			this.employeeName = employeeName;
			
		}

	public NotificationPaginationInput(int pageNo, int pageSize) {
		super(pageNo, pageSize);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
