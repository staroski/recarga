package br.com.staroski.recarga.ui;
import java.awt.*;

import javax.swing.*;

final class TelaAzul extends JPanel {

	private static final long serialVersionUID = 1;

	public TelaAzul() {
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, new JLabel("Tela Azul"));
		setBackground(Color.BLUE);
	}
}