package com.hred.service.descriptors.output;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.hred.model.AllHandsMeeting;
import com.hred.model.Employee;
import com.hred.service.annotations.SerializationDescriptor;
import com.hred.service.descriptors.JSONSerializationDescriptor;

public interface AllhandsOutputDescriptor  extends JSONSerializationDescriptor {
	@JsonProperty
	public int getStart();

	@JsonProperty
	public int getEnd();

	@JsonProperty
	public int getPageNo();

	@JsonProperty("allhands")
	@SerializationDescriptor(value = AllHandsMeeting.class)
	public List<AllHandsMeeting> getList();

	@JsonProperty
	public long getCount();
	
	
}
