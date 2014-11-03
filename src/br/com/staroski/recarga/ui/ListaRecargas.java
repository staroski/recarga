package br.com.staroski.recarga.ui;

import static br.com.staroski.recarga.ui.Utils.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import br.com.staroski.recarga.persistence.*;
import br.com.staroski.recarga.persistence.entities.*;

final class ListaRecargas extends JPanel {

	private class Modelo extends AbstractTableModel {

		private static final long serialVersionUID = 1;

		@Override
		public Class<?> getColumnClass(int col) {
			switch (col) {
				case 0:
				case 1:
					return String.class;
				case 2:
					return Integer.class;
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
					return "Data";
				case 1:
					return "Munição";
				case 2:
					return "Quantidade";
			}
			return null;
		}

		@Override
		public int getRowCount() {
			return getConsumos().size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			Consumo consumo = getConsumos().get(row);
			switch (col) {
				case 0:
					return formatDate(consumo.getData());
				case 1:
					Municao municao = consumo.getMunicao();
					Calibre calibre = municao.getCalibre();
					Projetil projetil = municao.getProjetil();
					Estojo estojo = municao.getEstojo();
					return calibre.getDescricao() + " - " + projetil.getDescricao() + " - " + estojo.getDescricao();
				case 2:
					return consumo.getQuantidade();
			}
			return null;
		}
	}

	private static final long serialVersionUID = 1;

	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListaRecargas() {
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
								novoConsumo();
							}
						});
						panel.add(buttonNovo);
						
								JButton buttonEditar = new JButton("Editar");
								buttonEditar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										editarConsumo();
									}
								});
								panel.add(buttonEditar);
								
										JButton buttonExcluir = new JButton("Excluir");
										buttonExcluir.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												excluirConsumo();
											}
										});
										panel.add(buttonExcluir);
										
										JLabel lblChumbos = new JLabel("Recargas");
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
					editarConsumo();
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

	private void editarConsumo() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getConsumos().size()) {
			exibe(getConsumos().get(linha));
		}
	}

	private void excluirConsumo() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getConsumos().size()) {
			Consumo consumo = getConsumos().get(linha);
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o consumo do dia " + formatDate(consumo.getData()) + "?", "Excluir?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcao == JOptionPane.YES_OPTION) {
				Database.get().delete(consumo);
				atualizar();
			}
		}
	}

	private void exibe(Consumo consumo) {
		CadastroConsumo dialogo = new CadastroConsumo(consumo);
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
		atualizar();
	}

	private List<Consumo> getConsumos() {
		return Database.get().getConsumos();
	}

	private void novoConsumo() {
		exibe(new Consumo());
	}
}
