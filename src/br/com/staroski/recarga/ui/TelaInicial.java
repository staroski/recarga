package br.com.staroski.recarga.ui;

import java.awt.*;

import javax.swing.*;

final class TelaInicial extends JPanel {

	private static final long serialVersionUID = 1;

	public TelaInicial() {
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Rectangle r = getBounds();
		g.drawImage(Recursos.IMAGEM_FUNDO, 0, 0, r.width, r.height, this);
		super.paintComponent(g);
	}
}