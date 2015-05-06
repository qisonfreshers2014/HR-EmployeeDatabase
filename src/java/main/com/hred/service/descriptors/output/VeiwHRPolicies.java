package com.hred.service.descriptors.output;

import org.codehaus.jackson.annotate.JsonProperty;

public class VeiwHRPolicies {
    private String url;
	private String policyName ;
	private long fileID;
	
	@JsonProperty
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	@JsonProperty
	public long getFileID() {
		return fileID;
	}
	public void setFileID(long fileID) {
		this.fileID = fileID;
	}
	
	@JsonProperty
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}





	
}
