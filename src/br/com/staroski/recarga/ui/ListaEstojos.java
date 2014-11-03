package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import br.com.staroski.recarga.persistence.*;
import br.com.staroski.recarga.persistence.entities.*;

final class ListaEstojos extends JPanel {

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
					return "Descri��o";
				case 1:
					return "Quantidade";
			}
			return null;
		}

		@Override
		public int getRowCount() {
			return getEstojos().size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			Estojo estojo = getEstojos().get(row);
			switch (col) {
				case 0:
					return estojo.getDescricao();
				case 1:
					return estojo.getQuantidade();
			}
			return null;
		}
	}

	private static final long serialVersionUID = 1;

	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListaEstojos() {
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
								novoEstojo();
							}
						});
						panel.add(buttonNovo);
						
								JButton buttonEditar = new JButton("Editar");
								buttonEditar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										editarEstojo();
									}
								});
								panel.add(buttonEditar);
								
										JButton buttonExcluir = new JButton("Excluir");
										buttonExcluir.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												excluirEstojo();
											}
										});
										panel.add(buttonExcluir);
										
										JLabel lblEstojos = new JLabel("Estojos");
										lblEstojos.setFont(new Font("Arial", lblEstojos.getFont().getStyle() | Font.BOLD | Font.ITALIC, lblEstojos.getFont().getSize() + 12));
										lblEstojos.setHorizontalAlignment(SwingConstants.CENTER);
										panel_1.add(lblEstojos, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					editarEstojo();
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

	private void editarEstojo() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getEstojos().size()) {
			exibe(getEstojos().get(linha));
		}
	}

	private void excluirEstojo() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getEstojos().size()) {
			Estojo estojo = getEstojos().get(linha);
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o estojo " + estojo.getDescricao() + "?", "Excluir?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcao == JOptionPane.YES_OPTION) {
				Database.get().delete(estojo);
				atualizar();
			}
		}
	}

	private void exibe(Estojo estojo) {
		CadastroEstojo dialogo = new CadastroEstojo(estojo);
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
		atualizar();
	}

	private List<Estojo> getEstojos() {
		return Database.get().getEstojos();
	}

	private void novoEstojo() {
		exibe(new Estojo());
	}
}
