package br.com.staroski.recarga;

import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

public final class Application {

	public static final String NAME;
	public static final String URL;
	public static final String VENDOR_URL;
	public static final File VENDOR_DIR;
	public static final BufferedImage IMAGE_ICON;
	public static final BufferedImage IMAGE_BACKGROUND;
	public static final BufferedImage IMAGE_SPLASH;
	public static final File DIR;
	public static final File STORAGE;
	public static final File STORAGE_DATA;
	public static final File STORAGE_BACKUP;
	public static final String DATABASE_NAME;
	public static final File DATABASE_DIR;

	static {
		VENDOR_URL = "http://www.staroski.com.br";
		VENDOR_DIR = new File(OS.USER_HOME, "staroski.com.br");

		NAME = "Recarga de Muni\u00E7\u00F5es";
		URL = VENDOR_URL + "/recarga";

		IMAGE_ICON = load("/icone_64x64.png");
		IMAGE_BACKGROUND = load("/cammo-01-claro_500x500.jpg");
		IMAGE_SPLASH = load("/fundo-claro_600x600.png");

		DIR = new File(VENDOR_DIR, "recarga");

		STORAGE = new File(DIR, "storage");
		STORAGE_DATA = new File(STORAGE, "data");
		STORAGE_BACKUP = new File(STORAGE, "backup");

		DATABASE_NAME = "recarga";
		DATABASE_DIR = new File(STORAGE_DATA, DATABASE_NAME);
	}

	private static BufferedImage load(String resource) {
		try {
			return ImageIO.read(Application.class.getResourceAsStream(resource));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Application() {}
}
