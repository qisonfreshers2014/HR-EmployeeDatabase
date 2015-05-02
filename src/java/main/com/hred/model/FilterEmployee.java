package com.hred.model;


import java.sql.Timestamp;
public class FilterEmployee {
  
private String gender;
private int currentDesignation;
private Timestamp dateOfJoining;
private String from;
private String to;
private String highestQualification;


public FilterEmployee(String gender, int currentDesignation, Timestamp dateOfJoining,
		String from, String to, String highestQualification) {
	super();
	this.gender = gender;
	this.currentDesignation = currentDesignation;
	this.dateOfJoining = dateOfJoining;
	this.from = from;
	this.to = to;
	this.highestQualification = highestQualification;
}
public FilterEmployee() {
	super();
	// TODO Auto-generated constructor stub
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public int getCurrentDesignation() {
	return currentDesignation;
}
public void setCurrentDesignation(int currentDesignation) {
	this.currentDesignation = currentDesignation;
}
public Timestamp getDateOfJoining() {
	return this.dateOfJoining;
}
public void setDateOfJoining(Timestamp dOJ) {
	this.dateOfJoining = dOJ;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public String getHighestQualification() {
	return highestQualification;
}
public void setHighestQualification(String highestQualification) {
	this.highestQualification = highestQualification;
}




}
 

 