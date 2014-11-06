package br.com.staroski.recarga;

import static br.com.staroski.recarga.Application.*;
import static br.com.staroski.recarga.ui.UI.*;

import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;

import br.com.staroski.recarga.persistence.*;
import br.com.staroski.recarga.ui.*;
import br.com.staroski.tools.io.*;
import br.com.staroski.tools.zip.*;

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
		try {
			if (!DATABASE_DIR.exists() || DATABASE_DIR.listFiles().length < 3) {
				if (restoreBackup()) {
					return true;
				}
				janela.setVisible(true);
				int opcao = JOptionPane.showConfirmDialog(janela,
						"Será necessário criar a base de dados no seguinte diretório:\n\n" + STORAGE.getAbsolutePath() + "\n\nDeseja prosseguir?",
						"Criar Base de Dados?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (opcao == JOptionPane.YES_OPTION) {
					createStorage();
					return true;
				}
				return false;
			}
			return true;
		} catch (Exception e) {
			showError(janela, e);
			return false;
		}
	}

	private boolean restoreBackup() throws Exception {
		List<File> backups = getBackups();
		if (backups.size() < 1) {
			return false;
		}
		System.out.println("restaurando backup...");
		int ultimo = backups.size() - 1;
		File backup = backups.get(ultimo);
		ZipUtils.extract(backup, STORAGE_DATA);
		System.out.println("restaurado!");
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
			deleteOldBackups();
			createBackup();
			System.exit(0);
		} catch (Exception e) {
			showError(janela, e);
		}
	}

	private void createBackup() throws Exception {
		System.out.println("criando backup da base de dados...");
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String fileName = DATABASE_NAME + "_" + formatter.format(new Date()) + ".bkp";
		File backupFile = new File(STORAGE_BACKUP, fileName);
		ZipUtils.compress(DATABASE_DIR, backupFile);
		System.out.println("backup criado!");
	}

	private void deleteOldBackups() {
		if (!STORAGE_BACKUP.exists()) {
			STORAGE_BACKUP.mkdirs();
			return;
		}
		List<File> backups = getBackups();
		if (backups.size() >= 10) {
			System.out.println("verificando backups antigos...");
			while (backups.size() >= 10) {
				File toRemove = backups.remove(0);
				toRemove.delete();
			}
			System.out.println("backups antigos verificados!");
		}
	}

	private List<File> getBackups() {
		FilenameFilter bkps = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".bkp");
			}
		};
		File[] files = STORAGE_BACKUP.listFiles(bkps);
		List<File> backups = new ArrayList<>();
		backups.addAll(Arrays.asList(files));
		Comparator<File> ascendente = new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		Collections.sort(backups, ascendente);
		return backups;
	}
}
