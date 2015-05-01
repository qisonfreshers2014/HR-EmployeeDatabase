package com.hred.service.descriptors.outputDescriptors;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.hred.service.annotations.SerializationDescriptor;
import com.hred.service.descriptors.output.DisplayNotificationHome;

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
