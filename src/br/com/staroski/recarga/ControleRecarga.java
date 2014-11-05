package br.com.staroski.recarga;

import static br.com.staroski.recarga.Application.*;
import static br.com.staroski.recarga.ui.UI.*;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import br.com.staroski.recarga.persistence.*;
import br.com.staroski.recarga.ui.*;
import br.com.staroski.tools.io.*;

public final class ControleRecarga {

	public static void main(String[] args) {
		try {
			new ControleRecarga().execute();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private JanelaPrincipal janela;

	private ControleRecarga() {}

	private boolean checkStorage() {
		if (!DATABASE_DIR.exists() || DATABASE_DIR.listFiles().length < 3) {
			janela.setVisible(true);
			int opcao = JOptionPane.showConfirmDialog(janela,
					"Será necessário criar uma base de dados no seguinte diretório:\n\n" + STORAGE_DATA.getAbsolutePath() + "\n\nDeseja prosseguir?",
					"Criar Base de Dados?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcao == JOptionPane.YES_OPTION) {
				createStorage();
				return true;
			}
			return false;
		}
		return true;
	}

	private JanelaPrincipal createFrame() {
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
			return janela;
		} catch (Throwable t) {
			showError(null, t);
			return null;
		}
	}

	private void createStorage() {
		DATABASE_DIR.mkdirs();
		String[] extensoes = new String[] { ".data", ".properties", ".script" };
		for (String extensao : extensoes) {
			try {
				String nome = DATABASE_NAME + extensao;
				InputStream input = getClass().getResourceAsStream("/db_empty/" + nome);
				OutputStream output = new FileOutputStream(new File(DATABASE_DIR, nome));
				IO.copy(input, output);
			} catch (IOException e) {
				showError(null, e);
			}
		}
	}

	private void execute() throws Throwable {
		janela = createFrame();
		janela.setLocationRelativeTo(null);
		if (!checkStorage()) {
			System.exit(0);
			return;
		}
		login();
		janela.montaLayout();
		janela.setVisible(true);
	}

	private void login() {
		try {
			String path = new File(DATABASE_DIR, DATABASE_NAME).getAbsolutePath();
			Database.get().login("jdbc:hsqldb:file:" + path, "SA", "");
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
}
