package com.hred.service.descriptors.output;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.hred.model.Template;
import com.hred.service.annotations.SerializationDescriptor;
import com.hred.service.descriptors.JSONSerializationDescriptor;

public interface TemplateOutputDescriptor extends JSONSerializationDescriptor {
	
	@JsonProperty
	public int getStart();

	@JsonProperty
	public int getEnd();

	@JsonProperty
	public int getPageNo();

	@JsonProperty("alltemplates")
	@SerializationDescriptor(value = Template.class)
	public List<Template> getList();

	@JsonProperty
	public long getCount();
	

}
