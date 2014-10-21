package br.com.staroski.recarga.ui;
import java.awt.*;

import javax.swing.*;

final class TelaVerde extends JPanel {

	private static final long serialVersionUID = 1;

	public TelaVerde() {
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, new JLabel("Tela Verde"));
		setBackground(Color.GREEN);
	}
}