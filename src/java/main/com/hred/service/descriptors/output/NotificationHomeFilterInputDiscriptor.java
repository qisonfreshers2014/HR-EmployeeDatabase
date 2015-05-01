package com.hred.service.descriptors.output;

import java.sql.Timestamp;
import java.util.Date;

public class NotificationHomeFilterInputDiscriptor {

	private String selectedEvent;
	private Date todate;
	private Date fromdate;

	public NotificationHomeFilterInputDiscriptor() {

	}

	public NotificationHomeFilterInputDiscriptor(String selectedEvent,
			Date todate, Date fromdate) {
		super();
		this.selectedEvent = selectedEvent;
		this.todate = todate;
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public String getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(String selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

}
