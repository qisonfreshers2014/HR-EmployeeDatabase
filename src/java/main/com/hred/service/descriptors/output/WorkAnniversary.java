package com.hred.service.descriptors.output;

import org.codehaus.jackson.annotate.JsonProperty;

public class WorkAnniversary {
	private String employee_name;
	private String email;
	private String DOB;
	private String DOJ;
	
	@JsonProperty
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	@JsonProperty
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}

	
	@JsonProperty
	public String getDOJ() {
		return DOJ;
	}
	public void setDOJ(String dOJ) {
		DOJ = dOJ;
	}

}
