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


 public AuthenticationOutput(String sessionToken, int authStatus, Employee emp) {
	// TODO Auto-generated constructor stub
	 this.sessionToken = sessionToken;
	  this.authStatus = authStatus;
	  this.emp = emp;
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