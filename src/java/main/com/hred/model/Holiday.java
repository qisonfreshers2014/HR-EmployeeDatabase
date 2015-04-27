/**
 * 
 */
package com.hred.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hred.persistence.annotations.Increment;

/**
 * @author saisudha
 *
 */

@Entity
@Table(name = "HOLIDAY")
@Increment
public class Holiday  extends AbstractObject{

	 @Temporal(TemporalType.DATE)
	 @Column(name = "from_date")
	 private java.util.Date  from_date; 
	 
	 @Temporal(TemporalType.DATE)
	 @Column(name = "to_date")
	 private java.util.Date  to_date;
	 
	 @Column(name = "description")
	 private String description; 
	 @Column(name = "type")
	 private String type; 
	
	
	public Holiday() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*public Holiday(HolidayInput holiday) {
		super();
		this.from_date = holiday.getFromDate();
		this.to_date = holiday.getToDate();
		this.description =  holiday.getDescription();
		this.type = holiday.getType();
	}*/

	public Holiday(Date from_date, Date to_date, String description, String type) {
		super();
		this.from_date = from_date;
		this.to_date = to_date;
		this.description = description;
		this.type = type;
	}

	public Holiday(AbstractObject abstractObject) {
		super(abstractObject);
		// TODO Auto-generated constructor stub
	}



	/*public Holiday( java.util.Date from_date, java.util.Date to_date, String description, String type) {
		super();
		
		this.from_date = from_date;
		this.to_date = to_date;
		this.description = description;
		this.type = type;
	}
*/

	

	@Override
	public int getObjectType() {
		
		return ObjectTypes.holidays;
	}

	public java.util.Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(java.util.Date from_date) {
		this.from_date = from_date;
	}

	public java.util.Date getTo_date() {
		return to_date;
	}

	public void setTo_date(java.util.Date to_date) {
		this.to_date = to_date;
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
