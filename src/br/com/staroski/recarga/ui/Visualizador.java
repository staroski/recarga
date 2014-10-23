package br.com.staroski.recarga.ui;
import java.awt.*;

import javax.swing.*;

final class Visualizador extends JPanel {

	private static final long serialVersionUID = 1;

	public Visualizador() {
		super(new CardLayout());
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
}
