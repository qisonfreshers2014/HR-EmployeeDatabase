package com.hred.service.descriptors.outputDescriptors;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.hred.model.DesignationHistory;
import com.hred.service.annotations.SerializationDescriptor;
import com.hred.service.descriptors.JSONSerializationDescriptor;
import com.hred.service.descriptors.input.DesignationHistoryOutput;

public class DesignationOutputDescriptor implements JSONSerializationDescriptor {
	List<DesignationHistory> designations;
	
	@SerializationDescriptor(DesignationHistoryOutput.class)
	@JsonProperty
	public List<DesignationHistory> getDesignations() {
		return designations;
	}

	public void setDesignations(List<DesignationHistory> designations) 
	{
		this.designations = designations;
	}
}
