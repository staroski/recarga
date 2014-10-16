package br.com.staroski.recarga;

import java.net.*;
import java.sql.*;

import javax.swing.*;

public final class ControleRecarga {

	public static void main(String[] args) {
		try {
			new ControleRecarga().execute();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private void execute() throws Throwable {
		//		showFrame();
		connect();
	}

	private void connect() throws Throwable {
		String url = "db/recarga";
		String user = "SA";
		String pass = "";
		final Connection c = DriverManager.getConnection("jdbc:hsqldb:file:" + url, user, pass);
		try {
			ResultSet rs = c.getMetaData().getTables(null, "PUBLIC", null, null);
			while (rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));
			}
		} finally {
			c.close();
		}
	}

	private void showFrame() {
		JFrame frame = new JFrame("HelloWorld");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private ControleRecarga() {
	}
}
