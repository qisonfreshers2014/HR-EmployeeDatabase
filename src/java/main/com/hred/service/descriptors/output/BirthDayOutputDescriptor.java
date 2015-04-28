package com.hred.service.descriptors.output;

import org.codehaus.jackson.annotate.JsonProperty;

import com.hred.service.descriptors.JSONSerializationDescriptor;

public interface BirthDayOutputDescriptor extends JSONSerializationDescriptor {
	@JsonProperty
	public int getEmployeeId();
	
	@JsonProperty
	public String getEmployeeName();
	
	@JsonProperty
	public String getDOB();
}
