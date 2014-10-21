package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import br.com.staroski.recarga.db.*;
import br.com.staroski.recarga.db.tables.*;

final class CadastroCalibres extends JPanel {

	private class Modelo extends AbstractTableModel {

		@Override
		public int getRowCount() {
			return getCalibres().size();
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
		public Object getValueAt(int row, int col) {
			Calibre calibre = getCalibres().get(row);
			switch (col) {
				case 0:
					return calibre.getDescricao();
				case 1:
					return calibre.getChumbos();
				case 2:
					return calibre.getCarga();
			}
			return null;
		}
	}

	private List<Calibre> calibres;

	public List<Calibre> getCalibres() {
		if (calibres == null) {
			calibres = Database.get().list(Calibre.class);
		}
		return calibres;
	}

	private static final long serialVersionUID = 1;

	private JTable table;

	/**
	 * Create the panel.
	 */
	public CadastroCalibres() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable(new Modelo());
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					editaCalibre();
				}
			}
		});
		scrollPane.setViewportView(table);

	}

	private void editaCalibre() {
		//TODO
		System.out.println("editaCalibre()");
	}
}
