package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import br.com.staroski.recarga.persistence.*;
import br.com.staroski.recarga.persistence.entities.*;

final class ListaMunicoes extends JPanel {

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
			return getMunicoes().size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			Municao municao = getMunicoes().get(row);
			switch (col) {
				case 0:
					Calibre calibre = municao.getCalibre();
					Projetil projetil = municao.getProjetil();
					Estojo estojo = municao.getEstojo();
					return calibre.getDescricao() + " - " + projetil.getDescricao() + " - " + estojo.getDescricao();
				case 1:
					return municao.getQuantidade();
			}
			return null;
		}
	}

	private static final long serialVersionUID = 1;

	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListaMunicoes() {
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
								novoMunicao();
							}
						});
						panel.add(buttonNovo);
						
								JButton buttonEditar = new JButton("Editar");
								buttonEditar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										editarMunicao();
									}
								});
								panel.add(buttonEditar);
								
										JButton buttonExcluir = new JButton("Excluir");
										buttonExcluir.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												excluirMunicao();
											}
										});
										panel.add(buttonExcluir);
										
										JLabel lblMunies = new JLabel("Muni\u00E7\u00F5es");
										lblMunies.setFont(new Font("Arial", lblMunies.getFont().getStyle() | Font.BOLD | Font.ITALIC, lblMunies.getFont().getSize() + 12));
										lblMunies.setHorizontalAlignment(SwingConstants.CENTER);
										panel_1.add(lblMunies, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					editarMunicao();
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

	private void editarMunicao() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getMunicoes().size()) {
			exibe(getMunicoes().get(linha));
		}
	}

	private void excluirMunicao() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getMunicoes().size()) {
			Municao municao = getMunicoes().get(linha);
			Calibre calibre = municao.getCalibre();
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir a muni��o" + (calibre == null ? "" : " " + calibre.getDescricao()) + "?",
					"Excluir?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcao == JOptionPane.YES_OPTION) {
				Database.get().delete(municao);
				atualizar();
			}
		}
	}

	private void exibe(Municao municao) {
		CadastroMunicao dialogo = new CadastroMunicao(municao);
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
		atualizar();
	}

	private List<Municao> getMunicoes() {
		return Database.get().getMunicoes();
	}

	private void novoMunicao() {
		exibe(new Municao());
	}
}
