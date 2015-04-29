package com.hred.service.descriptors.output;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import com.hred.service.annotations.SerializationDescriptor;
import com.hred.service.descriptors.JSONSerializationDescriptor;

public class DisplayNotificationHomeDescriptor implements
JSONSerializationDescriptor {

List<DisplayNotificationHome> displayData;

@SerializationDescriptor(DisplayNotificationHome.class)
@JsonProperty
public List<DisplayNotificationHome> getData() {
	return displayData;
}

public void setData(List<DisplayNotificationHome> displayData) {
	this.displayData = displayData;
}


}
