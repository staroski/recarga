package br.com.staroski.recarga.ui;

import java.awt.*;

import javax.swing.*;

final class TelaInicial extends JPanel {

	private static final long serialVersionUID = 1;

	public TelaInicial() {
		setOpaque(false);
		add(BorderLayout.NORTH, new JLabel("Tela Inicial", SwingConstants.CENTER));
	}
}