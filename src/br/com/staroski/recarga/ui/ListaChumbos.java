package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import br.com.staroski.recarga.persistence.*;
import br.com.staroski.recarga.persistence.entities.*;

final class ListaChumbos extends JPanel {

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
			return getChumbos().size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			Chumbo chumbo = getChumbos().get(row);
			switch (col) {
				case 0:
					return chumbo.getDescricao();
				case 1:
					return chumbo.getQuantidade();
			}
			return null;
		}
	}

	private static final long serialVersionUID = 1;

	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListaChumbos() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
				JPanel panel = new JPanel();
				panel_1.add(panel, BorderLayout.EAST);
				panel.setOpaque(false);
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				
						JButton buttonNovo = new JButton("Novo");
						buttonNovo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								novoChumbo();
							}
						});
						panel.add(buttonNovo);
						
								JButton buttonEditar = new JButton("Editar");
								buttonEditar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										editarChumbo();
									}
								});
								panel.add(buttonEditar);
								
										JButton buttonExcluir = new JButton("Excluir");
										buttonExcluir.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												excluirChumbo();
											}
										});
										panel.add(buttonExcluir);
										
										JLabel lblChumbos = new JLabel("Chumbos");
										lblChumbos.setFont(new Font("Arial", lblChumbos.getFont().getStyle() | Font.BOLD | Font.ITALIC, lblChumbos.getFont().getSize() + 12));
										lblChumbos.setHorizontalAlignment(SwingConstants.CENTER);
										panel_1.add(lblChumbos, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					editarChumbo();
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

	private void editarChumbo() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getChumbos().size()) {
			exibe(getChumbos().get(linha));
		}
	}

	private void excluirChumbo() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getChumbos().size()) {
			Chumbo chumbo = getChumbos().get(linha);
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o chumbo " + chumbo.getDescricao() + "?", "Excluir?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcao == JOptionPane.YES_OPTION) {
				Database.get().delete(chumbo);
				atualizar();
			}
		}
	}

	private void exibe(Chumbo chumbo) {
		CadastroChumbo dialogo = Controlador.get().registra(new CadastroChumbo(chumbo));
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
		atualizar();
	}

	private List<Chumbo> getChumbos() {
		return Database.get().getChumbos();
	}

	private void novoChumbo() {
		exibe(new Chumbo());
	}
}
