package com.hred.service.descriptors.output;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.hred.model.Employee;
import com.hred.service.annotations.SerializationDescriptor;
import com.hred.service.descriptors.JSONSerializationDescriptor;


public class EmployeeListOutputDescriptors implements
		JSONSerializationDescriptor {
	
	List<Employee> employees;

	@SerializationDescriptor(EmployeeOutput.class)
	@JsonProperty
	public List<Employee> getEmployees() {
	return employees;
	}

	public void setEmployees(List<Employee> employees) {
	this.employees = employees;
	}
}
