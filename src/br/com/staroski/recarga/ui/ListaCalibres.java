package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import br.com.staroski.recarga.persistence.*;

final class ListaCalibres extends JPanel {

	private class Modelo extends AbstractTableModel {

		private static final long serialVersionUID = 1;

		@Override
		public Class<?> getColumnClass(int col) {
			switch (col) {
				case 0:
					return String.class;
				case 1:
					return Integer.class;
				case 2:
					return Double.class;
			}
			return Object.class;
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int col) {
			switch (col) {
				case 0:
					return "Descrição";
				case 1:
					return "Chumbos";
				case 2:
					return "Carga";
			}
			return null;
		}

		@Override
		public int getRowCount() {
			return getCalibres().size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			Calibre calibre = getCalibres().get(row);
			switch (col) {
				case 0:
					return calibre.getDescricao();
				case 1:
					return calibre.getChumbos();
				case 2:
					return calibre.getPolvora();
			}
			return null;
		}
	}

	private static final long serialVersionUID = 1;

	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListaCalibres() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panel, BorderLayout.NORTH);

		JButton buttonNovo = new JButton("Novo");
		buttonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoCalibre();
			}
		});
		panel.add(buttonNovo);

		JButton buttonEditar = new JButton("Editar");
		buttonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCalibre();
			}
		});
		panel.add(buttonEditar);

		JButton buttonExcluir = new JButton("Excluir");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCalibre();
			}
		});
		panel.add(buttonExcluir);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					editarCalibre();
				}
			}
		});
		scrollPane.setViewportView(table);
		afterConstruct();
	}

	private void afterConstruct() {
		table.setModel(new Modelo());
	}

	private void atualizar() {
		((Modelo) table.getModel()).fireTableDataChanged();
	}

	private void editarCalibre() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getCalibres().size()) {
			exibe(getCalibres().get(linha));
		}
	}

	private void excluirCalibre() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getCalibres().size()) {
			Calibre calibre = getCalibres().get(linha);
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o calibre " + calibre.getDescricao() + "?", "Excluir?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcao == JOptionPane.YES_OPTION) {
				Database.get().delete(calibre);
				atualizar();
			}
		}
	}

	private void exibe(Calibre calibre) {
		CadastroCalibre dialogo = new CadastroCalibre(calibre);
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
		atualizar();
	}

	private List<Calibre> getCalibres() {
		return Database.get().getCalibres();
	}

	private void novoCalibre() {
		exibe(new Calibre());
	}
}
