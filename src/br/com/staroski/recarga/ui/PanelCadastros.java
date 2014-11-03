package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

final class PanelCadastros extends JPanel {

	private static final long serialVersionUID = 1;

	public PanelCadastros() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Cadastros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));

		JButton btnInicio = new JButton("In\u00EDcio");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.get().exibe(Tela.INICIAL);
			}
		});
		btnInicio.setMinimumSize(new Dimension(100, 30));
		btnInicio.setMaximumSize(new Dimension(100, 30));
		btnInicio.setPreferredSize(new Dimension(100, 30));
		add(btnInicio);

		JButton btnRecargas = new JButton("Recargas");
		btnRecargas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.get().exibe(Tela.LISTA_RECARGAS);
			}
		});
		add(btnRecargas);
		btnRecargas.setPreferredSize(new Dimension(100, 30));
		btnRecargas.setMinimumSize(new Dimension(100, 30));
		btnRecargas.setMaximumSize(new Dimension(100, 30));

		JButton btnConsumos = new JButton("Consumos");
		btnConsumos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.get().exibe(Tela.LISTA_CONSUMOS);
			}
		});
		add(btnConsumos);
		btnConsumos.setPreferredSize(new Dimension(100, 30));
		btnConsumos.setMinimumSize(new Dimension(100, 30));
		btnConsumos.setMaximumSize(new Dimension(100, 30));

		JButton btnCalibres = new JButton("Calibres");
		btnCalibres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.get().exibe(Tela.LISTA_CALIBRES);
			}
		});
		add(btnCalibres);
		btnCalibres.setPreferredSize(new Dimension(100, 30));
		btnCalibres.setMinimumSize(new Dimension(100, 30));
		btnCalibres.setMaximumSize(new Dimension(100, 30));
	}
}