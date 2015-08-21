package com.nibbledebt.intuit.cad.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class DateUtils {
	public static final String DATE_YYYYMMDD = "yyyy-MM-dd";

	public static DateUtils getInstance() {
		return new DateUtils();
	}

	public static Calendar getCalendarFromDate(String date, String format)
			throws ParseException {
		DateFormat formatter = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(formatter.parse(date));
		return cal;
	}

	public static String getCurrentDate() throws ParseException {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(currentDate.getTime());
	}

	public static String getDateWithNextDays(int noOfDays)
			throws ParseException {
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(5, noOfDays);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(currentDate.getTime());
	}

	public static String getDateWithPrevDays(int noOfDays)
			throws ParseException {
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(5, -noOfDays);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(currentDate.getTime());
	}
}