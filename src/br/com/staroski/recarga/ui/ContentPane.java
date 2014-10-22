package br.com.staroski.recarga.ui;

import java.awt.*;

import javax.swing.*;

final class ContentPane extends JPanel {

	private static final long serialVersionUID = 1;

	public ContentPane() {
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Recursos.IMAGEM_FUNDO, 0, 0, getWidth(), getHeight(), this);
	}

}
