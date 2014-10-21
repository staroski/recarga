package br.com.staroski.recarga.ui;

import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

public final class Recursos {

	public static final String TITULO_JANELA_PRINCIPAL = "Controle de Recarga de Munições";

	public static final BufferedImage IMAGEM_ICONE = load("/icone_64x64.png");
	public static final BufferedImage IMAGEM_FUNDO = load("/fundo_600x600.png");

	public static BufferedImage load(String resource) {
		try {
			return ImageIO.read(Recursos.class.getResourceAsStream(resource));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Recursos() {}
}
