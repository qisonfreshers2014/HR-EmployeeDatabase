package com.hred.model.user;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

import com.hred.model.User;
import com.hred.service.annotations.SerializationDescriptor;

/**
 * @author RAMMOHAN
 * 
 */
public class AuthenticationOutput {

 private String sessionToken;
 private int authStatus;
 private User user;
 ///////////////////////////////


 
 /////////////////////////////
 public AuthenticationOutput(String sessionToken, int authStatus, User user) {
  this.sessionToken = sessionToken;
  this.authStatus = authStatus;
  this.user = user;

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
 public User getUser() {
  return user;
 }
}