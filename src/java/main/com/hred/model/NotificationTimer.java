package com.hred.model;

import java.util.TimerTask;

import com.hred.handler.SendNotificationHistoryHandler;



public class NotificationTimer extends TimerTask implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
			SendNotificationHistoryHandler.getInstance().SentAutomatedMailMail();
		
			// TODO Auto-generated catch block
			
		
		
	}

}
