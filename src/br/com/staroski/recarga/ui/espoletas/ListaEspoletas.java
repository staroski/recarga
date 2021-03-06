package br.com.staroski.recarga.ui.espoletas;

import static br.com.staroski.recarga.ui.UI.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import br.com.staroski.recarga.persistence.*;
import br.com.staroski.recarga.persistence.entities.*;
import br.com.staroski.recarga.ui.*;

public final class ListaEspoletas extends JPanel {

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

	private static final long serialVersionUID = 1;

	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListaEspoletas() {
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

		JLabel lblEspoletas = new JLabel("Espoletas");
		lblEspoletas.setFont(new Font("Arial", lblEspoletas.getFont().getStyle() | Font.BOLD | Font.ITALIC, lblEspoletas.getFont().getSize() + 12));
		lblEspoletas.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblEspoletas, BorderLayout.CENTER);

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
		((Modelo) table.getModel()).fireTableDataChanged();
	}

	private void editarEspoleta() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getEspoletas().size()) {
			exibe(getEspoletas().get(linha));
		}
	}

	private void excluirEspoleta() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getEspoletas().size()) {
			Espoleta espoleta = getEspoletas().get(linha);
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o espoleta " + espoleta.getDescricao() + "?", "Excluir?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcao == JOptionPane.YES_OPTION) {
				try {
					Database.get().delete(espoleta);
					atualizar();
				} catch (Exception e) {
					showError(this, e);
				}
			}
		}
	}

	private void exibe(Espoleta espoleta) {
		CadastroEspoleta dialogo = Controlador.get().registra(new CadastroEspoleta(espoleta));
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
		atualizar();
	}

	private List<Espoleta> getEspoletas() {
		return Database.get().getEspoletas();
	}

	private void novoEspoleta() {
		exibe(new Espoleta());
	}
}
