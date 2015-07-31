package com.hred.model;

import java.util.TimerTask;

import org.apache.commons.mail.EmailException;
import com.hred.handler.SendNotificationHistoryHandler;

public class NotificationTimer extends TimerTask implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			SendNotificationHistoryHandler.getInstance().sentAutomatedMailMail();
		} catch (EmailException e) {
			// TODO Auto-generated catch block

			System.out.println("Unable to send mail");
		}

		// TODO Auto-generated catch block

	}

}
