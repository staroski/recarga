package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import br.com.staroski.recarga.persistence.*;
import br.com.staroski.recarga.persistence.entities.*;

final class ListaProjeteis extends JPanel {

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
			return getProjeteis().size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			Projetil projetil = getProjeteis().get(row);
			switch (col) {
				case 0:
					return projetil.getDescricao();
				case 1:
					return projetil.getQuantidade();
			}
			return null;
		}
	}

	private static final long serialVersionUID = 1;

	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListaProjeteis() {
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
								novoProjetil();
							}
						});
						panel.add(buttonNovo);
						
								JButton buttonEditar = new JButton("Editar");
								buttonEditar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										editarProjetil();
									}
								});
								panel.add(buttonEditar);
								
										JButton buttonExcluir = new JButton("Excluir");
										buttonExcluir.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												excluirProjetil();
											}
										});
										panel.add(buttonExcluir);
										
										JLabel lblProjteis = new JLabel("Proj\u00E9teis");
										lblProjteis.setHorizontalAlignment(SwingConstants.CENTER);
										lblProjteis.setFont(new Font("Arial", lblProjteis.getFont().getStyle() | Font.BOLD | Font.ITALIC, lblProjteis.getFont().getSize() + 12));
										panel_1.add(lblProjteis, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					editarProjetil();
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

	private void editarProjetil() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getProjeteis().size()) {
			exibe(getProjeteis().get(linha));
		}
	}

	private void excluirProjetil() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getProjeteis().size()) {
			Projetil projetil = getProjeteis().get(linha);
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o projetil " + projetil.getDescricao() + "?", "Excluir?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcao == JOptionPane.YES_OPTION) {
				Database.get().delete(projetil);
				atualizar();
			}
		}
	}

	private void exibe(Projetil projetil) {
		CadastroProjetil dialogo = Controlador.get().registra(new CadastroProjetil(projetil));
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
		atualizar();
	}

	private List<Projetil> getProjeteis() {
		return Database.get().getProjeteis();
	}

	private void novoProjetil() {
		exibe(new Projetil());
	}
}
