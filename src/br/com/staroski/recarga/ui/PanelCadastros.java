package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

final class PanelCadastros extends JPanel {

	private static final long serialVersionUID = 1;

	public PanelCadastros() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Cadastros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1, BorderLayout.WEST);
		
				JButton btnRecargas = new JButton("Recargas");
				panel_1.add(btnRecargas);
				btnRecargas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Controlador.get().exibe(Tela.LISTA_RECARGAS);
					}
				});
				btnRecargas.setPreferredSize(new Dimension(100, 30));
				btnRecargas.setMinimumSize(new Dimension(100, 30));
				btnRecargas.setMaximumSize(new Dimension(100, 30));
				
//						JButton btnConsumos = new JButton("Consumos");
//						panel_1.add(btnConsumos);
//						btnConsumos.addActionListener(new ActionListener() {
//							public void actionPerformed(ActionEvent e) {
//								Controlador.get().exibe(Tela.LISTA_CONSUMOS);
//							}
//						});
//						btnConsumos.setPreferredSize(new Dimension(100, 30));
//						btnConsumos.setMinimumSize(new Dimension(100, 30));
//						btnConsumos.setMaximumSize(new Dimension(100, 30));
						
						JButton btnMunies = new JButton("Muni\u00E7\u00F5es");
						btnMunies.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Controlador.get().exibe(Tela.LISTA_MUNICOES);
							}
						});
						panel_1.add(btnMunies);
						btnMunies.setMinimumSize(new Dimension(100, 30));
						btnMunies.setMaximumSize(new Dimension(100, 30));
						btnMunies.setPreferredSize(new Dimension(100, 30));
						
								JButton btnCalibres = new JButton("Calibres");
								panel_1.add(btnCalibres);
								btnCalibres.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										Controlador.get().exibe(Tela.LISTA_CALIBRES);
									}
								});
								btnCalibres.setPreferredSize(new Dimension(100, 30));
								btnCalibres.setMinimumSize(new Dimension(100, 30));
								btnCalibres.setMaximumSize(new Dimension(100, 30));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(16, 0, 0, 0));
		add(panel, BorderLayout.EAST);
		
				JButton btnInicio = new JButton("In\u00EDcio");
				panel.add(btnInicio);
				btnInicio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Controlador.get().exibe(Tela.INICIAL);
					}
				});
				btnInicio.setMinimumSize(new Dimension(100, 30));
				btnInicio.setMaximumSize(new Dimension(100, 30));
				btnInicio.setPreferredSize(new Dimension(100, 30));
	}
}