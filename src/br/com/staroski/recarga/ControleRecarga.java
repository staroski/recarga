package br.com.staroski.recarga;

import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import br.com.staroski.recarga.db.*;
import br.com.staroski.recarga.db.tables.*;
import br.com.staroski.recarga.ui.*;

public final class ControleRecarga {

	public static void main(String[] args) {
		try {
			new ControleRecarga().execute();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private ControleRecarga() {}

	private void execute() throws Throwable {
		login();
		//		createDummyData();
		showFrame();
	}

	//	private void createDummyData() {
	//		Database db = Database.get();
	//		String[] nomes = new String[] { //
	//		        "teste 1", //
	//				"teste 2", //
	//				"teste 3", //
	//				"teste 4", //
	//				"teste 5", //
	//				"teste 6", //
	//				"teste 7", //
	//		};
	//		for (String nome : nomes) {
	//			Calibre calibre = new Calibre();
	//			calibre.setDescricao(nome);
	//			calibre.setCarga(Math.random() * 10);
	//			calibre.setChumbos((int) (Math.random() * 10));
	//			db.save(calibre);
	//		}
	//	}

	private void login() {
		try {
			Database.get().login("jdbc:hsqldb:file:db/recarga", "SA", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void logout() {
		try {
			Database.get().logout();
			System.exit(0);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void showFrame() {
		try {
			// tentar deixar a aplicação com aspecto nativo do sistema operacional
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		try {
			// apresentar a janela do nosso sistema
			JanelaPrincipal janela = new JanelaPrincipal();
			janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			janela.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					logout();
				}
			});
			janela.setLocationRelativeTo(null);
			janela.setVisible(true);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
