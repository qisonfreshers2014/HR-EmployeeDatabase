 	package com.hred.service.descriptors.outputDescriptors;

	import java.util.List;

	import org.codehaus.jackson.annotate.JsonProperty;

 

import com.hred.model.FilterEmployee;
	import com.hred.service.annotations.SerializationDescriptor;
import com.hred.service.descriptors.JSONSerializationDescriptor;
import com.hred.service.descriptors.input.EmployeeOutput;

	public class EmployeeListOutputDescriptors implements
			JSONSerializationDescriptor {
		
		List<FilterEmployee> Filters;
		
		@SerializationDescriptor(EmployeeOutput.class)
		@JsonProperty
		public List<FilterEmployee> getEmployees() {
			return Filters;
		}

		public void setEmployees(List<FilterEmployee> employees) {
			this.Filters = Filters;
		}

	}

