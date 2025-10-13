package com.bluetrident.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import jakarta.mail.internet.ParseException;

public class AppUtil {
	
	public static Date getCurrentUtcDateTime() throws ParseException {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

		return new Date();
	}
	
	public static boolean isNullString(String value) {
		boolean isNull = false;
		if (value == null) {
			isNull = true;
		} else if (value.isEmpty()) {
			isNull = true;
		} else if (value.isBlank()) {
			isNull = true;
		} else if (value.equals(null)) {
			isNull = true;
		} else if (value.equals("null")) {
			isNull = true;
		} else if (value.equals("undefined")) {
			isNull = true;
		}
		return isNull;
	}

	public static boolean isIdNull(String value) {
		boolean isNull = false;
		if (value == null) {
			isNull = true;
		} else if (value.isEmpty()) {
			isNull = true;
		} else if (value.equals("0")) {
			isNull = true;
		} else if (value.isBlank()) {
			isNull = true;
		} else if (value.equals(null)) {
			isNull = true;
		} else if (value.equals("null")) {
			isNull = true;
		}
		return isNull;
	}

	public static Date getUtcDateTime(Date utcDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		Date d1 = null;
		try {
			d1 = localDateFormat.parse(simpleDateFormat.format(utcDate));
		} catch (Exception e) {
		}

		return d1;
	}


}
