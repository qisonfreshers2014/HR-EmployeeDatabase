/**
 * 
 */
package com.hred.service.descriptors.Input;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author saisudha
 *
 */
public class HolidayTypeInput {

	private int id;
	private String type;
	
	
	@JsonProperty
	
	public int id() {
		return id;
	}
	
	public void setid(int id) {
		this.id = id;
	}
	
	@JsonProperty
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
