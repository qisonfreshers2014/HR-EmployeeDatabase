package com.hred.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;


@Entity
@Table(name = "allhandsmeeting")
@Increment
public class AllHandsMeeting extends AbstractObject{


	 @Column(name = "date")
	 private Timestamp date;
	 @Column(name = "employee_of_the_month")
	 private String employee;
	 @Column(name = "description")
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


	@Override
	public int getObjectType() {
		
		return ObjectTypes.ALL_HANDS_MEETING;
	}

}
