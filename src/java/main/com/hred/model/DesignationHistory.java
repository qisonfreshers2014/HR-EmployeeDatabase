package com.hred.model;

import java.beans.Transient;
import java.sql.Timestamp;
/**
 * @author Bhargavi Uppoju
 *
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;
@Entity
@Table(name=" designation_history") 
@Increment
public class DesignationHistory extends AbstractObject{
	
	@Column(name="emp_id")
	private int empId;

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
	
	@javax.persistence.Transient
	private int currentdesgId;


	public int getEmpId() {
		return empId;
	}



	public void setEmpId(int empId) {
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



	public int getCurrentdesgId() {
		return currentdesgId;
	}



	public void setCurrentdesgId(int currentdesgId) {
		this.currentdesgId = currentdesgId;
	}



	@Override
	public int getObjectType() 
	{
		// TODO Auto-generated method stub
		return ObjectTypes.DESIGNATION_HISTORY;
	}
}
