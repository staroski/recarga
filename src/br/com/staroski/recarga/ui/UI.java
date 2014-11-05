package br.com.staroski.recarga.ui;

import java.awt.*;
import java.text.*;
import java.util.*;

import javax.swing.*;

public final class UI {

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

	public static String formatInt(int value) {
		return String.valueOf(value);
	}

	public static int parseInt(String text) {
		if (text == null) {
			return 0;
		}
		text = text.trim();
		if (text.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(text);
	}

	public static String formatDouble(double value) {
		return String.valueOf(value);
	}

	public static double parseDouble(String text) {
		if (text == null) {
			return 0;
		}
		text = text.trim();
		if (text.isEmpty()) {
			return 0;
		}
		text = text.replace(',', '.');
		return Double.parseDouble(text);
	}

	public static RuntimeException wrap(Throwable t) {
		if (t instanceof RuntimeException) {
			return (RuntimeException) t;
		}
		return new RuntimeException(t);
	}

	private UI() {}

	public static void showError(Component parent, Throwable erro) {
		erro.printStackTrace();
		JOptionPane.showMessageDialog(parent, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	}
}
