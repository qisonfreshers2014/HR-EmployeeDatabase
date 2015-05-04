package com.hred.model;


import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.hred.persistence.annotations.Increment;

/**
 * @author saisudha
 *
 */

@Entity
@Table(name = "HOLIDAY")
@Increment
public class Holiday  extends AbstractObject{

	 @Column(name = "from_date")
	 private Timestamp  fromDate; 
	 
	 @Column(name = "to_date")
	 private Timestamp  toDate;
	 
	 @Column(name = "description")
	 private String description; 
	
	 @Column(name = "type")
	 private String type; 
	
	
	public Holiday() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Holiday(AbstractObject abstractObject) {
		super(abstractObject);
		// TODO Auto-generated constructor stub
	}


	public String getDescription() {
		return description;
	}

	

	public Timestamp getFromDate() {
		return fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	public Timestamp getToDate() {
		return toDate;
	}

	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
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

	public Holiday(Timestamp fromDate, Timestamp toDate, String description,
			String type) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.description = description;
		this.type = type;
	}

	@Override
	public int getObjectType() {
		
		return ObjectTypes.HOLIDAY;
	}

	

}
