package com.hred.pagination;

import java.sql.Timestamp;

import javax.persistence.Column;

public class AllhandsmeetingInput extends PaginationInput {


	 private Timestamp date;

	 private String employee;

	 private String description;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AllhandsmeetingInput(Timestamp date, String employee,
			String description) {
		super();
		this.date = date;
		this.employee = employee;
		this.description = description;
	}

	public AllhandsmeetingInput() {
		super();
		
	}

	public AllhandsmeetingInput(int pageNo, int pageSize) {
		super(pageNo, pageSize);
		
	}
	
	 
}
