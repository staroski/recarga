package br.com.staroski.recarga;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

public class ImagePanel extends JPanel {

	private boolean stretchEnabled;

	private static final long serialVersionUID = 1;

	private BufferedImage image;

	public ImagePanel() {
		stretchEnabled = true;
	}

	public BufferedImage getImage() {
		return image;
	}

	public boolean isStretchEnabled() {
		return stretchEnabled;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void setStretchEnabled(boolean stretch) {
		this.stretchEnabled = stretch;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (image == null) {
			super.paintComponent(g);
			return;
		}
		int w = getWidth();
		int h = getHeight();
		if (stretchEnabled) {
			g.drawImage(image, 0, 0, w, h, this);
			return;
		}
		int iw = image.getWidth();
		int ih = image.getHeight();
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
					g.drawImage(image, -(iw - x), y, iw, ih, this);
				}
				if (j == colunas - 1 && x < w) {
					g.drawImage(image, x + iw, y, iw, ih, this);
				}
				if (x > w) {
					break;
				}
				if (x < -iw) {
					break;
				}
				g.drawImage(image, x, y, iw, ih, this);
			}
		}
	}
}
