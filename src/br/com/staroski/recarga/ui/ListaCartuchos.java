package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import br.com.staroski.recarga.db.*;
import br.com.staroski.recarga.db.tables.*;

final class ListaCartuchos extends JPanel {

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
			return getCartuchos().size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			Cartucho cartucho = getCartuchos().get(row);
			switch (col) {
				case 0:
					return cartucho.getDescricao();
				case 1:
					return cartucho.getQuantidade();
			}
			return null;
		}
	}

	private List<Cartucho> cartuchos;

	private static final long serialVersionUID = 1;

	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListaCartuchos() {
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
				novoCartucho();
			}
		});
		panel.add(buttonNovo);

		JButton buttonEditar = new JButton("Editar");
		buttonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCartucho();
			}
		});
		panel.add(buttonEditar);

		JButton buttonExcluir = new JButton("Excluir");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCartucho();
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
					editarCartucho();
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
		cartuchos = Database.get().list(Cartucho.class);
		((Modelo) table.getModel()).fireTableDataChanged();
	}

	private void editarCartucho() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < cartuchos.size()) {
			exibe(cartuchos.get(linha));
		}
	}

	private void excluirCartucho() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < cartuchos.size()) {
			Cartucho cartucho = cartuchos.get(linha);
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o cartucho " + cartucho.getDescricao() + "?", "Excluir?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcao == JOptionPane.YES_OPTION) {
				Database.get().delete(cartucho);
				atualizar();
			}
		}
	}

	private void exibe(Cartucho cartucho) {
		CadastroCartucho dialogo = new CadastroCartucho(cartucho);
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
		atualizar();
	}

	private List<Cartucho> getCartuchos() {
		if (cartuchos == null) {
			cartuchos = Database.get().list(Cartucho.class);
		}
		return cartuchos;
	}

	private void novoCartucho() {
		exibe(new Cartucho());
	}
}
