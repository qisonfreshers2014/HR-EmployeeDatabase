/**
 * 
 */
package com.hred.service.descriptors.output;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.hred.model.Holiday;
import com.hred.service.annotations.SerializationDescriptor;
import com.hred.service.descriptors.JSONSerializationDescriptor;
import com.hred.service.descriptors.Input.HolidayTypeInput;

/**
 * @author saisudha
 *
 */
public class HolidayOutput implements JSONSerializationDescriptor {
	
List<Holiday> holidays;


@SerializationDescriptor(HolidayTypeInput.class)
@JsonProperty
public List<Holiday> getHolidays() {
	return holidays;
}

public void setHolidays(List<Holiday> holidays) {
	this.holidays = holidays;
}


}

