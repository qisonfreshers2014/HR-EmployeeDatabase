package com.hred.service.descriptors.output;

import org.codehaus.jackson.annotate.JsonProperty;

public class SentMail {
	
	private String employeeName;
	private String subject;	
private String emailid;
@JsonProperty
public String getEmailid() {
	return emailid;
}
public void setEmailid(String emailid) {
	this.emailid = emailid;
}
@JsonProperty
public String getEmployeeName() {
	return employeeName;
}
public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
}
@JsonProperty
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}

}
