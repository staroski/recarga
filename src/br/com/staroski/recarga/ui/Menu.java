package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

final class Menu extends JPanel {

	private static final long serialVersionUID = 1;

	public Menu() {
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// os bot�es ficar�o alinhados verticalmente
		setLayout(new GridLayout(4, 1, 10, 10));

		// instanciamos os bot�es
		JButton botaoTela1 = new JButton("In�cio");
		JButton botaoTela2 = new JButton("Calibres");
		JButton botaoTela3 = new JButton("Cartuchos");
		JButton botaoTela4 = new JButton("Proj�teis");

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

		// e finalmente adicionamos os bot�es neste painel
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
		Controlador.get().exibe(Tela.LISTA_CARTUCHOS);
	}

	private void exibeTelaAzul() {
		Controlador.get().exibe(Tela.AZUL);
	}
}