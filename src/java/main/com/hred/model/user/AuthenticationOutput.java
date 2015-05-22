package com.hred.model.user;

import org.codehaus.jackson.annotate.JsonProperty;

 




import com.hred.model.Employee;
import com.hred.model.User;
import com.hred.service.annotations.SerializationDescriptor;
import com.hred.service.descriptors.output.EmployeeDetails;

/**
 * @author RAMMOHAN
 * 
 */
public class AuthenticationOutput {

 private String sessionToken;
 private int authStatus;
 private EmployeeDetails empDetails;
 private Boolean roleHr;

public AuthenticationOutput(String sessionToken, int authStatus, EmployeeDetails empDetails, Boolean roleHr) {
	// TODO Auto-generated constructor stub
	 this.sessionToken = sessionToken;
	  this.authStatus = authStatus;
	  this.empDetails = empDetails;
	  this.roleHr=roleHr;
}

@JsonProperty
public Boolean getRoleHr() {
	return roleHr;
}

public void setRoleHr(Boolean roleHr) {
	this.roleHr = roleHr;
}

@JsonProperty
 public String getSessionToken() {
  return sessionToken;
 }

 @JsonProperty
 public int getAuthStatus() {
  return authStatus;
 }
 
 @SerializationDescriptor(value = User.class)
    @JsonProperty
 public EmployeeDetails getemployeeDetails() {
  return empDetails;
 }
}