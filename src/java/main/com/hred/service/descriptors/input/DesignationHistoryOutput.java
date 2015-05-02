package com.hred.service.descriptors.Input;





import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonProperty;
/**
 * @author Bhargavi Uppoju
 *
 */
public class DesignationHistoryOutput
{
	
		@JsonProperty
		private Timestamp appraisalDate;		

		@JsonProperty
		private int designationId;
		
		@JsonProperty
		private double salary;

		@JsonProperty		
		private double variablePay;

}

