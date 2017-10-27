package com.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static DateTimeFormatter dateTimeAbbrFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	public static LocalDateTime convertDateTimeType(Calendar cal) {
		return LocalDateTime.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE),
				cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND),
				cal.get(Calendar.MILLISECOND) * 1000000);
	}

	public static String convertToDisplayDateTime(Date date) {
		return dateTimeFormat.format(convertToLocalDateTime(date));
	}

	public static LocalDateTime convertToLocalDateTime(Date date) {
		long epochMilli = date.getTime();
		return convertToLocalDateTime(epochMilli);
	}

	public static LocalDateTime convertToLocalDateTime(long epochMilli) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
	}

	public static String convertDateToTime(String date) {
		LocalDate localDate = LocalDate.parse(date, dateFormat);
		LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
		return localDateTime.format(dateTimeFormat);
	}

	public static Date convertToDate(String date) {
		LocalDate localDate = LocalDate.parse(date, dateFormat);
		return convertToDate(localDate);
	}
	
	public static Date convertToDateTime(String date) {
		LocalDateTime localDate = LocalDateTime.parse(date, dateTimeFormat);
		return convertToDate(localDate);
	}

	public static Date convertToDate(LocalDate localDate) {
		return convertToDate(localDate.atStartOfDay());
	}
	
	public static Date convertToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
