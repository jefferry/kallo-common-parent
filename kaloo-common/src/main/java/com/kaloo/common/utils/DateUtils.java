package com.kaloo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static final String MSFormat = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String SecondFormat = "yyyy-MM-dd HH:mm:ss";
	public static final String MinuteFormat = "yyyy-MM-dd HH:mm";
	public static final String HourFormat = "yyyy-MM-dd HH";
	public static final String DayFormat = "yyyy-MM-dd";
	public static final String MSFormat2 = "yyyyMMddHHmmssSSS";
	public static final String SecondFormat2 = "yyyyMMddHHmmss";
	public static final String MinuteFormat2 = "yyyyMMddHHmm";
	public static final String HourFormat2 = "yyyyMMddHH";
	public static final String DayFormat2 = "yyyyMMdd";

	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(true);
		return dateFormat.format(date);
	}

	public static Date parseDate(String date) {
		if (date == null || date.equals("")) {
			return null;
		}
		Date result = null;
		result = parseDate(date, MSFormat);
		if (result != null) {
			return result;
		}
		result = parseDate(date, SecondFormat);
		if (result != null) {
			return result;
		}
		result = parseDate(date, MinuteFormat);
		if (result != null) {
			return result;
		}
		result = parseDate(date, HourFormat);
		if (result != null) {
			return result;
		}
		result = parseDate(date, DayFormat);
		if (result != null) {
			return result;
		}
		result = parseDate(date, MSFormat2);
		if (result != null) {
			return result;
		}
		result = parseDate(date, SecondFormat2);
		if (result != null) {
			return result;
		}
		result = parseDate(date, MinuteFormat2);
		if (result != null) {
			return result;
		}
		result = parseDate(date, HourFormat2);
		if (result != null) {
			return result;
		}
		result = parseDate(date, DayFormat2);
		if (result != null) {
			return result;
		}
		throw new RuntimeException("date=" + date);
	}

	public static Date parseDate(String date, String format) {
		if (date == null || date.equals("")) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(true);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
