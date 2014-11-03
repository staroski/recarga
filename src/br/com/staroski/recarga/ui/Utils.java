package br.com.staroski.recarga.ui;

import java.text.*;
import java.util.*;

final class Utils {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	private static final DateFormat HOUR_FORMAT = new SimpleDateFormat("HH:mm");
	private static final DateFormat DATE_HOUR_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public static Date parseDate(String date) {
		try {
			return DATE_FORMAT.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw wrap(e);
		}
	}

	public static Date parseHour(String date) {
		try {
			return HOUR_FORMAT.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw wrap(e);
		}
	}

	public static Date parseDateHour(String date) {
		try {
			return DATE_HOUR_FORMAT.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw wrap(e);
		}
	}

	public static String formatDate(Date date) {
		return DATE_FORMAT.format(date);
	}

	public static String formatHour(Date date) {
		return HOUR_FORMAT.format(date);
	}

	public static String formatDateHour(Date date) {
		return DATE_HOUR_FORMAT.format(date);
	}

	public static RuntimeException wrap(Throwable t) {
		if (t instanceof RuntimeException) {
			return (RuntimeException) t;
		}
		return new RuntimeException(t);
	}

	private Utils() {}

}
