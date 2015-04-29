package com.hred.service.descriptors.output;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import com.hred.model.Template;
import com.hred.service.annotations.SerializationDescriptor;
import com.hred.service.descriptors.JSONSerializationDescriptor;

public class TemplateContentOutputDescriptor implements 	JSONSerializationDescriptor
	{

List<Template> template;
	
	@SerializationDescriptor(TemplateContentOutput.class)
	@JsonProperty
	public List<Template>  getContent() {
		return template;
	}

	public void getContent(List<Template> template) {
		this.template = template;
	}
	
	
}
