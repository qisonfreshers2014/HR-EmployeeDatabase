package com.hred.common;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author User
 *
 */
public class DateUtils {
    public static final int DAY = 1;
    public static final int MONTH = 2;
    public static final int YEAR = 3;

    public static final long YEAR_IN_MILLISECONDS = 365 * 24 * 60 * 60 * 1000L;
    public static final long MONTH_IN_MILLISECONDS = 30 * 24 * 60 * 60 * 1000L;
    public static final long DAY_IN_MILLISECONDS = 24 * 60 * 60 * 1000L;
    public static final long HOUR_IN_MILLISECONDS = 60 * 60 * 1000L;
    public static final long MINUTE_IN_MILLISECONDS = 60 * 1000L;

    private static final TimeZone localTimeZone = TimeZone.getDefault();
 
    public static long getCurrentTimeInNano() {
        return System.nanoTime();
    }
    public static long getCurrentTime() {
        return getLocalCalendar().getTimeInMillis();
    }

    public static long getCurrentTimeInGMT() {
        return System.currentTimeMillis();
    }

    public static Calendar getLocalCalendar() {
        TimeZone.setDefault(localTimeZone);
        return Calendar.getInstance();
    }

    public static Calendar getGMTCalendar() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        return Calendar.getInstance();
    }

	public static long fbUpdatedTime(String updatedTime) throws ParseException {
		// TODO parse the above one convert into GMT
		// 2013-06-24T05:27:08+0000
		int indexOfT = updatedTime.indexOf("T");
		int indexOfPlus = updatedTime.indexOf("+");
		String time = updatedTime.substring((indexOfT + 1), indexOfPlus);
		String DOB = updatedTime.substring(0, indexOfT);

		String actualDate = DOB + "-" + time;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		// sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date date = sdf.parse(actualDate);
		return date.getTime();
	}
	
	public static String dobFormat(String dob) {
		int hyphenIndex = dob.indexOf("-");
		String year = dob.substring(0, hyphenIndex);
		String dayMonth = dob.substring((hyphenIndex + 1), (dob.length()));
		dayMonth = dayMonth.replace("-", "/");
		String actualDOB = dayMonth + "/" + year;
		return actualDOB;
	}
}
 