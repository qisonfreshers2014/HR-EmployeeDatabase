package com.hred.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;
@Entity
@Table(name="appraisal_history")
@Increment
public class AppraisalHistory {
	public AppraisalHistory()
	{
		
	}
	@Column(name="id")
	private int id;
	
	@Column(name="emp_id")
	private String designation_name;
	
	@Column(name="date")
	private String date;
	
	@Column(name="designation_id")
	private int designation_id;
	
	@Column(name="salary")
	private double salary;
	
	@Column(name="variable_pay")
	private double variable_pay;
	
}
