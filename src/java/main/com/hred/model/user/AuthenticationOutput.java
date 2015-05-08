package com.hred.model.user;

import org.codehaus.jackson.annotate.JsonProperty;

 



import com.hred.model.Employee;
import com.hred.model.User;
import com.hred.service.annotations.SerializationDescriptor;

/**
 * @author RAMMOHAN
 * 
 */
public class AuthenticationOutput {

 private String sessionToken;
 private int authStatus;
 private Employee emp;
 private Boolean roleHr;

public AuthenticationOutput(String sessionToken, int authStatus, Employee emp, Boolean roleHr) {
	// TODO Auto-generated constructor stub
	 this.sessionToken = sessionToken;
	  this.authStatus = authStatus;
	  this.emp = emp;
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
 public Employee getEmployee() {
  return emp;
 }
}