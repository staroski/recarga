package br.com.staroski.recarga;

import java.util.*;

import javax.swing.*;

import br.com.staroski.recarga.db.*;
import br.com.staroski.recarga.db.tables.*;

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
		DataBase db = DataBase.get();
		List<Calibre> calibres = db.list(Calibre.class);
		for (Calibre c : calibres) {
			System.out.println(c.getId());
			System.out.println(c.getDescricao());
			System.out.println(c.getCarga());
			System.out.println(c.getChumbos());
			System.out.println();
			db.delete(c);
		}
	}

	private void showFrame() {
		JFrame frame = new JFrame("HelloWorld");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private ControleRecarga() {}
}
