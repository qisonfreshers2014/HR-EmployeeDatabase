package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;

@Entity
@Table(name = "NOTIFICATIONHISTORY")
@Increment
public class SendNotificationHistory extends AbstractObject {

	@Column(name = "employee_email")
	private String employeeEmail;
	@Column(name = "employee_name")
	private String employeeName;
	@Column(name = "employee_id")
	private int employeeId;
	@Column(name = "event_name")
	private String eventName;
	@Column(name = "template_id")
	private String templateId;
	
/*	@Column(name = "entered_time") 
	private java.util.Date enteredTime;
	*/
	

	

	public SendNotificationHistory() {
		super();
		// TODO Auto-generated constructor stub
	}





	public SendNotificationHistory(AbstractObject abstractObject) {
		super(abstractObject);
		// TODO Auto-generated constructor stub
	}









	public SendNotificationHistory(String employeeEmail, String employeeName,
			int employeeId, String eventName, String templateId) {
		super();
		this.employeeEmail = employeeEmail;
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.eventName = eventName;
		this.templateId = templateId;
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





	public int getEmployeeId() {
		return employeeId;
	}





	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}








	public String getEventName() {
		return eventName;
	}





	public void setEventName(String eventName) {
		this.eventName = eventName;
	}





	public String getTemplateId() {
		return templateId;
	}





	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}









	@Override
	public int getObjectType() {

		return ObjectTypes.SENDNOTIFICATIONHISTORY;
	}





	
}
