package com.hred.service.descriptors.output;

import java.util.Date;

public class NotificationHomeFilterInputDiscriptor {
	
	
	private java.util.Date todate;
	private java.util.Date fromdate;
	private String selectedEvent;
	private String selectedState;
	
	public NotificationHomeFilterInputDiscriptor()
	{
		
	}
	
	
	
	public NotificationHomeFilterInputDiscriptor(Date todate, Date fromdate,
			String selectedEvent, String selectedState) {
		super();
		this.todate = todate;
		this.fromdate = fromdate;
		this.selectedEvent = selectedEvent;
		this.selectedState = selectedState;
	}
	public java.util.Date getTodate() {
		return todate;
	}
	public void setTodate(java.util.Date todate) {
		this.todate = todate;
	}
	public java.util.Date getFromdate() {
		return fromdate;
	}
	public void setFromdate(java.util.Date fromdate) {
		this.fromdate = fromdate;
	}
	public String getSelectedEvent() {
		return selectedEvent;
	}
	public void setSelectedEvent(String selectedEvent) {
		this.selectedEvent = selectedEvent;
	}
	public String getSelectedState() {
		return selectedState;
	}
	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}
	

}
