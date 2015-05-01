package com.hred.service.descriptors.output;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.hred.service.annotations.SerializationDescriptor;

public class VeiwHRPolicyDiscriptor {
	List<VeiwHRPolicies> displayData;

	@SerializationDescriptor(VeiwHRPolicies.class)
	@JsonProperty
	public List<VeiwHRPolicies> getData() {
		return displayData;
	}

	public void setData(List<VeiwHRPolicies> displayData) {
		this.displayData = displayData;
	}
}
