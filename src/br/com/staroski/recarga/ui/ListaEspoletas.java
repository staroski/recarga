package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import br.com.staroski.recarga.db.*;
import br.com.staroski.recarga.db.tables.*;

final class ListaEspoletas extends JPanel {

	private class Modelo extends AbstractTableModel {

		private static final long serialVersionUID = 1;

		@Override
		public Class<?> getColumnClass(int col) {
			switch (col) {
				case 0:
					return String.class;
				case 1:
					return Integer.class;
			}
			return Object.class;
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public String getColumnName(int col) {
			switch (col) {
				case 0:
					return "Descrição";
				case 1:
					return "Quantidade";
			}
			return null;
		}

		@Override
		public int getRowCount() {
			return getEspoletas().size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			Espoleta espoleta = getEspoletas().get(row);
			switch (col) {
				case 0:
					return espoleta.getDescricao();
				case 1:
					return espoleta.getQuantidade();
			}
			return null;
		}
	}

	private List<Espoleta> espoletas;

	private static final long serialVersionUID = 1;

	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListaEspoletas() {
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
				novoEspoleta();
			}
		});
		panel.add(buttonNovo);

		JButton buttonEditar = new JButton("Editar");
		buttonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarEspoleta();
			}
		});
		panel.add(buttonEditar);

		JButton buttonExcluir = new JButton("Excluir");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirEspoleta();
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
					editarEspoleta();
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
		espoletas = Database.get().load(Espoleta.class);
		((Modelo) table.getModel()).fireTableDataChanged();
	}

	private void editarEspoleta() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < espoletas.size()) {
			exibe(espoletas.get(linha));
		}
	}

	private void excluirEspoleta() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < espoletas.size()) {
			Espoleta espoleta = espoletas.get(linha);
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o espoleta " + espoleta.getDescricao() + "?", "Excluir?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcao == JOptionPane.YES_OPTION) {
				Database.get().delete(espoleta);
				atualizar();
			}
		}
	}

	private void exibe(Espoleta espoleta) {
		CadastroEspoleta dialogo = new CadastroEspoleta(espoleta);
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
		atualizar();
	}

	private List<Espoleta> getEspoletas() {
		if (espoletas == null) {
			espoletas = Database.get().load(Espoleta.class);
		}
		return espoletas;
	}

	private void novoEspoleta() {
		exibe(new Espoleta());
	}
}
