package com.hred.service.descriptors.output;

import org.codehaus.jackson.annotate.JsonProperty;

public class TemplateContentOutput {
	private String content;

	
	@JsonProperty
	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}

}
