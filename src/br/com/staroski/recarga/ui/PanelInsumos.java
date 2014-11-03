package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

final class PanelInsumos extends JPanel {

	private static final long serialVersionUID = 1;

	public PanelInsumos() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Insumos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));

		JButton btnPolvora = new JButton("P\u00F3lvora");
		btnPolvora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.get().exibe(Tela.LISTA_POLVORAS);
			}
		});
		add(btnPolvora);
		btnPolvora.setMinimumSize(new Dimension(100, 30));
		btnPolvora.setMaximumSize(new Dimension(100, 30));
		btnPolvora.setPreferredSize(new Dimension(100, 30));

		JButton btnEstojos = new JButton("Estojos");
		btnEstojos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.get().exibe(Tela.LISTA_ESTOJOS);
			}
		});
		add(btnEstojos);
		btnEstojos.setMinimumSize(new Dimension(100, 30));
		btnEstojos.setMaximumSize(new Dimension(100, 30));
		btnEstojos.setPreferredSize(new Dimension(100, 30));

		JButton btnEspoletas = new JButton("Espoletas");
		btnEspoletas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.get().exibe(Tela.LISTA_ESPOLETAS);
			}
		});
		add(btnEspoletas);
		btnEspoletas.setMinimumSize(new Dimension(100, 30));
		btnEspoletas.setMaximumSize(new Dimension(100, 30));
		btnEspoletas.setPreferredSize(new Dimension(100, 30));

		JButton btnProjeteis = new JButton("Proj\u00E9teis");
		btnProjeteis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.get().exibe(Tela.LISTA_PROJETEIS);
			}
		});
		add(btnProjeteis);
		btnProjeteis.setMinimumSize(new Dimension(100, 30));
		btnProjeteis.setMaximumSize(new Dimension(100, 30));
		btnProjeteis.setPreferredSize(new Dimension(100, 30));

		JButton btnChumbos = new JButton("Chumbos");
		btnChumbos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.get().exibe(Tela.LISTA_CHUMBOS);
			}
		});
		add(btnChumbos);
		btnChumbos.setMinimumSize(new Dimension(100, 30));
		btnChumbos.setMaximumSize(new Dimension(100, 30));
		btnChumbos.setPreferredSize(new Dimension(100, 30));
	}
}