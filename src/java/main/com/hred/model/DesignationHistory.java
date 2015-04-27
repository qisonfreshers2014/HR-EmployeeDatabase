package com.hred.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="DESIGNATION_HISTORY") 
public class DesignationHistory extends AbstractObject{
	
	@Column(name="emp_id")
	private String empId;

/*	@Temporal(TemporalType.DATE)
	@Column(name="date")
	private java.util.Date appraisalDate;*/
	
/*	@Temporal(TemporalType.DATE)
	@Column(name="date")
	private java.util.Date date;*/
	
	@Column(name="appraisal_date")
	private Timestamp appraisalDate;
	
	@Column(name="designation_id")
	private int designationId;
	
	@Column(name="salary")
	private double salary;
	
	@Column(name="variable_pay")	
	private double variablePay;
	
	
	public String getEmpId() {
		return empId;
	}



	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getDesignationId() {
		return designationId;
	}



	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}



	public double getSalary() {
		return salary;
	}



	public void setSalary(double salary) {
		this.salary = salary;
	}



	public double getVariablePay() {
		return variablePay;
	}



	public void setVariablePay(double variablePay) {
		this.variablePay = variablePay;
	}



	public Timestamp getAppraisalDate() {
		return appraisalDate;
	}



	public void setAppraisalDate(Timestamp appraisalDate) {
		this.appraisalDate = appraisalDate;
	}



	@Override
	public int getObjectType() 
	{
		// TODO Auto-generated method stub
		return ObjectTypes.DESIGNATION_HISTORY;
	}
}
