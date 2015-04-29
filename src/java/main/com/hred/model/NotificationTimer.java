package com.hred.model;

import java.util.TimerTask;

import com.hred.exception.BusinessException;
import com.hred.handler.SendNotificationHistoryHandler;



public class NotificationTimer extends TimerTask implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
			try {
				SendNotificationHistoryHandler.getInstance().SentAutomatedMailMail();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			// TODO Auto-generated catch block
			
		
		
	}

}
