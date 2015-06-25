package com.hred.model;


import java.sql.Timestamp;

import com.hred.pagination.PaginationInput;
public class FilterEmployee extends PaginationInput  {
  
private String gender;
private int currentDesignation;
private Timestamp dateOfJoining;
private Timestamp dateOfJoiningFrom;
private Timestamp dateOfJoiningTo;

private String from;
private String to;
private String highestQualification;
private boolean isDeleted;
private String filterEmployee;


public FilterEmployee(String gender, int currentDesignation, Timestamp dateOfJoining,
  String from, String to, String highestQualification, boolean isDeleted,String filterEmployee, Timestamp dateOfJoiningFrom, Timestamp dateOfJoiningTo ) {
 super();
 this.gender = gender;
 this.currentDesignation = currentDesignation;
 this.dateOfJoining = dateOfJoining;
 this.dateOfJoiningFrom = dateOfJoiningFrom;
 this.dateOfJoiningTo = dateOfJoiningTo;
 this.from = from;
 this.to = to;
 this.highestQualification = highestQualification;
 this.isDeleted=isDeleted;
 this.filterEmployee=filterEmployee;
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

public boolean getDeleted() {
   return isDeleted;
  }

 public void setDeleted(boolean deleted) {
   isDeleted = deleted;
  }
 
 
 public String getFilterEmployee() {
	return filterEmployee;
}
public void setFilterEmployee(String filterEmployee) {
	this.filterEmployee = filterEmployee;
}
public Timestamp getDateOfJoiningFrom() {
		return dateOfJoiningFrom;
	}
	public void setDateOfJoiningFrom(Timestamp dateOfJoiningFrom) {
		this.dateOfJoiningFrom = dateOfJoiningFrom;
	}
	public Timestamp getDateOfJoiningTo() {
		return dateOfJoiningTo;
	}
	public void setDateOfJoiningTo(Timestamp dateOfJoiningTo) {
		this.dateOfJoiningTo = dateOfJoiningTo;
	}

	public FilterEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FilterEmployee(int pageNo, int pageSize) {
		super(pageNo, pageSize);
		// TODO Auto-generated constructor stub
	}

}