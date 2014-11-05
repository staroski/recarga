package br.com.staroski.recarga.ui.recargas;

import static br.com.staroski.recarga.ui.UI.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import br.com.staroski.recarga.persistence.*;
import br.com.staroski.recarga.persistence.entities.*;
import br.com.staroski.recarga.ui.*;

public final class ListaRecargas extends JPanel {

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
			return getRecargas().size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			Recarga recarga = getRecargas().get(row);
			Municao municao = recarga.getMunicao();
			Calibre calibre = municao.getCalibre();
			Projetil projetil = municao.getProjetil();
			Estojo estojo = municao.getEstojo();
			switch (col) {
				case 0:
					return formatDate(recarga.getData());
				case 1:
					return calibre.getDescricao() + " - " + projetil.getDescricao() + " - " + estojo.getDescricao();
				case 2:
					return recarga.getQuantidade();
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
				novoRecarga();
			}
		});
		panel.add(buttonNovo);

		JButton buttonEditar = new JButton("Editar");
		buttonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarRecarga();
			}
		});
		panel.add(buttonEditar);

		JButton buttonExcluir = new JButton("Excluir");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirRecarga();
			}
		});
		panel.add(buttonExcluir);

		JLabel lblRecarga = new JLabel("Recargas");
		lblRecarga.setFont(new Font("Arial", lblRecarga.getFont().getStyle() | Font.BOLD | Font.ITALIC, lblRecarga.getFont().getSize() + 12));
		lblRecarga.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblRecarga, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					editarRecarga();
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

	private void editarRecarga() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getRecargas().size()) {
			exibe(getRecargas().get(linha));
		}
	}

	private void excluirRecarga() {
		int linha = table.getSelectedRow();
		if (linha >= 0 && linha < getRecargas().size()) {
			Recarga recarga = getRecargas().get(linha);
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o recarga do dia " + formatDate(recarga.getData()) + "?", "Excluir?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcao == JOptionPane.YES_OPTION) {
				try {
					Database.get().delete(recarga);
					atualizar();
				} catch (Exception e) {
					showError(this, e);
				}
			}
		}
	}

	private void exibe(Recarga recarga) {
		CadastroRecarga dialogo = Controlador.get().registra(new CadastroRecarga(recarga));
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
		atualizar();
	}

	private List<Recarga> getRecargas() {
		return Database.get().getRecargas();
	}

	private void novoRecarga() {
		exibe(new Recarga());
	}
}
