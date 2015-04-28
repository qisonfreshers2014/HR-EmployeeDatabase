package com.hred.service.descriptors.output;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.hred.model.Employee;
import com.hred.service.annotations.SerializationDescriptor;


public interface ActivitiesBirthDayOutputDescriptor {
	@JsonProperty
	@SerializationDescriptor(value = BirthDayOutputDescriptor.class)
	public List<Employee> getTodayBirthday();
}


