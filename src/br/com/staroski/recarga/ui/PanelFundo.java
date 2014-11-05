package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

import br.com.staroski.recarga.*;

final class PanelFundo extends JPanel {

	private static final long serialVersionUID = 1;

	public PanelFundo() {
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	@Override
	protected void paintComponent(Graphics g) {
		BufferedImage background = Application.BACKGROUND;

		int w = getWidth();
		int h = getHeight();
		int iw = background.getWidth();
		int ih = background.getHeight();
		int colunas = w / iw;
		int linhas = h / ih;
		if (colunas * iw < w) {
			colunas++;
		}
		if (linhas * ih < h) {
			linhas++;
		}

		int offsetX = 0;
		for (int i = 0; i < linhas; i++) {
			int y = i * ih;
			if (y > h) {
				break;
			}
			for (int j = 0; j < colunas; j++) {
				int x = j * iw + offsetX;
				if (j == 0 && x > 0) {
					g.drawImage(background, -(iw - x), y, iw, ih, null);
				}
				if (j == colunas - 1 && x < w) {
					g.drawImage(background, x + iw, y, iw, ih, null);
				}
				if (x > w) {
					break;
				}
				if (x < -iw) {
					break;
				}
				g.drawImage(background, x, y, iw, ih, null);
			}
		}
	}
}
