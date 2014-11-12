package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

import br.com.staroski.recarga.*;
import br.com.staroski.recarga.ui.calibres.*;
import br.com.staroski.recarga.ui.chumbos.*;
import br.com.staroski.recarga.ui.consumos.*;
import br.com.staroski.recarga.ui.espoletas.*;
import br.com.staroski.recarga.ui.estojos.*;
import br.com.staroski.recarga.ui.municoes.*;
import br.com.staroski.recarga.ui.polvoras.*;
import br.com.staroski.recarga.ui.projeteis.*;
import br.com.staroski.recarga.ui.recargas.*;

public final class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1;

	public JanelaPrincipal() {
		super(Application.NAME + " - " + Application.VENDOR_URL);
		setIconImage(Application.IMAGE_ICON);
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setStretchEnabled(false);
		imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		imagePanel.setImage(Application.IMAGE_BACKGROUND);
		setContentPane(imagePanel);
		Dimension size = new Dimension(640, 480);
		setSize(size);
		setMinimumSize(size);
	}

	public void montaLayout() {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		PanelTela visualizador = new PanelTela();
		Controlador controlador = Controlador.get();
		controlador.setVisualizador(visualizador);
		controlador.registra(Tela.INICIAL, new TelaInicial());
		controlador.registra(Tela.LISTA_CALIBRES, new ListaCalibres());
		controlador.registra(Tela.LISTA_ESTOJOS, new ListaEstojos());
		controlador.registra(Tela.LISTA_CHUMBOS, new ListaChumbos());
		controlador.registra(Tela.LISTA_ESPOLETAS, new ListaEspoletas());
		controlador.registra(Tela.LISTA_POLVORAS, new ListaPolvoras());
		controlador.registra(Tela.LISTA_PROJETEIS, new ListaProjeteis());
		controlador.registra(Tela.LISTA_MUNICOES, new ListaMunicoes());
		controlador.registra(Tela.LISTA_CONSUMOS, new ListaConsumos());
		controlador.registra(Tela.LISTA_RECARGAS, new ListaRecargas());

		PanelCadastros panelCadastros = new PanelCadastros();
		PanelInsumos panelInsumos = new PanelInsumos();

		container.add(BorderLayout.NORTH, panelCadastros);
		container.add(BorderLayout.CENTER, visualizador);
		container.add(BorderLayout.SOUTH, panelInsumos);
	}

	@Override
	public void paint(Graphics g) {
		BufferedImage image = Application.IMAGE_BACKGROUND;
		int x = 0;
		int y = 0;
		int w = getWidth();
		int h = getHeight();

		g.drawImage(image, x, y, w, h, null);

		super.paintComponents(g);
	}

	//	private void pintaFundo(Graphics g) {
	//		BufferedImage image = Recursos.IMAGEM_FUNDO;
	//		int w = getWidth();
	//		int h = getHeight();
	//		int iw = image.getWidth();
	//		int ih = image.getHeight();
	//		int colunas = w / iw;
	//		int linhas = h / ih;
	//		if (colunas * iw < w) {
	//			colunas++;
	//		}
	//		if (linhas * ih < h) {
	//			linhas++;
	//		}
	//
	//		for (int i = 0; i < linhas; i++) {
	//			int y = i * ih;
	//			if (y > h) {
	//				break;
	//			}
	//			for (int j = 0; j < colunas; j++) {
	//				int x = j * iw;
	//				System.out.println(x + ", " + y + ", " + iw + ", " + ih);
	//				g.drawImage(image, x, y, iw, ih, null);
	//			}
	//		}
	//	}
}
