package com.hred.model.output;

/*@author Rizwan Khan
This class can be used if the comparison of dates have to be done irrespective of the year
*/


import java.util.Calendar;
import java.util.Comparator;

import com.hred.service.descriptors.output.DisplayNotificationHome;

public class CompareDates implements Comparator<DisplayNotificationHome> {

	public int compare(DisplayNotificationHome emp1,
			DisplayNotificationHome emp2) {
	
		Calendar todate = Calendar.getInstance();
		todate.setTime(emp1.getDate());
		int tomonth = todate.get(Calendar.MONTH) + 1;
		int today = todate.get(Calendar.DATE) ;
		int i = 0;

		Calendar todatetocomapre = Calendar.getInstance();
		todatetocomapre.setTime(emp2.getDate());
		int tomonthtocomapre = todatetocomapre.get(Calendar.MONTH) + 1;
		int todaytocomapre = todatetocomapre.get(Calendar.DATE);

		
		if (today == todaytocomapre && tomonth == tomonthtocomapre) {
			
			i = 0;
		}
       else if (tomonth == tomonthtocomapre && today < todaytocomapre) {
    	   System.out.println(" Inside date 1 is greater");
			i = -1;
		}

		else if (tomonth == tomonthtocomapre && today > todaytocomapre) {
			
			i = 1;
		} 
		else if (tomonth < tomonthtocomapre) {
			System.out.println(" Inside month 1 is greater");
		
			i = -1;
		} else if (tomonthtocomapre < tomonth) {
			System.out.println(" Inside month 2 is greater");
			i = 1;
		} 
	
		return i;
	}

}
