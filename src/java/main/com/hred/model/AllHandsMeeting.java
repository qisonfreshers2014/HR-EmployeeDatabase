package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;

@Entity
@Table(name = "ALLHANDSMEETING")
@Increment
public class AllHandsMeeting extends AbstractObject{


	 @Column(name = "date")
	 private java.util.Date  date;
	 @Column(name = "employee_of_the_month")
	 private String employee;
	 @Column(name = "description")
	 private String description;

	 public java.util.Date getDate() {
		return date;
	}



	public void setDate(java.util.Date date) {
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






	@Override
	public int getObjectType() {
		// TODO Auto-generated method stub
		return ObjectTypes.ALL_HANDS_MEETING;
	}

}
