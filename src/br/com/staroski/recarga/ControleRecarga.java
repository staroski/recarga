package br.com.staroski.recarga;

import java.awt.event.*;

import javax.swing.*;

import br.com.staroski.recarga.persistence.*;
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

	private void createDummyData() {
		DummyDataCreator creator = new DummyDataCreator(Database.get());
		creator.execute();
	}

	private void execute() throws Throwable {
		login();
		createDummyData();
		showFrame();
	}

	private void login() {
		try {
			Database.get().login("jdbc:hsqldb:file:db/recarga", "SA", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void logout() {
		try {
			Database.get().logout();
			System.exit(0);
		} catch (Exception e1) {
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
			final JanelaPrincipal janela = new JanelaPrincipal();
			janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			janela.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					int opcao = JOptionPane.showConfirmDialog(janela, "Deseja realmente sair?", "Sair?", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (opcao == JOptionPane.YES_OPTION) {
						janela.dispose();
						logout();
					}
				}
			});
			janela.setLocationRelativeTo(null);
			janela.setVisible(true);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
