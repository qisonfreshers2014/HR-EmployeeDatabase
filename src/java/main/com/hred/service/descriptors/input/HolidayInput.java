/**
 * 
 */
package com.hred.service.descriptors.input;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author saisudha
 *
 */
public class HolidayInput {
	
	 @Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fromDate;
	 @Temporal(TemporalType.DATE)
	private java.util.Date toDate;
	private String description; 
	private String type;
	
	
	public java.util.Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(java.util.Date fromDate) {
		this.fromDate = fromDate;
	}
	

	public java.util.Date getToDate() {
		return toDate;
	}
	public void setToDate(java.util.Date toDate) {
		this.toDate = toDate;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}