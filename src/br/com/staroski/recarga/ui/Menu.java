package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

final class Menu extends JPanel {

	private static final long serialVersionUID = 1;

	public Menu() {
		setOpaque(false);

		// os botões ficarão alinhados verticalmente
		setLayout(new GridLayout(4, 1, 10, 10));

		// instanciamos os botões
		JButton botaoTela1 = new JButton("Início");
		JButton botaoTela2 = new JButton("Calibres");
		JButton botaoTela3 = new JButton("Tela Verde");
		JButton botaoTela4 = new JButton("Tela Azul");

		// registramos os ActionListeners de cada um
		botaoTela1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeTelaBranca();
			}
		});
		botaoTela2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeTelaVermelha();
			}
		});
		botaoTela3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeTelaVerde();
			}
		});
		botaoTela4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeTelaAzul();
			}
		});

		// e finalmente adicionamos os botões neste painel
		add(botaoTela1);
		add(botaoTela2);
		add(botaoTela3);
		add(botaoTela4);
	}

	private void exibeTelaBranca() {
		Controlador.get().exibe(Tela.INICIAL);
	}

	private void exibeTelaVermelha() {
		Controlador.get().exibe(Tela.LISTA_CALIBRES);
	}

	private void exibeTelaVerde() {
		Controlador.get().exibe(Tela.VERDE);
	}

	private void exibeTelaAzul() {
		Controlador.get().exibe(Tela.AZUL);
	}
}