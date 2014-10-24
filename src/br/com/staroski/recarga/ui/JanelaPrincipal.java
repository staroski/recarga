package br.com.staroski.recarga.ui;

import java.awt.*;

import javax.swing.*;

public final class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1;

	public JanelaPrincipal() {
		super(Recursos.TITULO_JANELA_PRINCIPAL);
		setIconImage(Recursos.IMAGEM_ICONE);
		setContentPane(new ContentPane());
		Dimension size = new Dimension(640, 480);
		setSize(size);
		setMinimumSize(size);
		montaLayout();
	}

	private void montaLayout() {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		// criar intância do menu
		Menu menu = new Menu();

		// criar intância do Visualizador
		Visualizador visualizador = new Visualizador();

		// o menu vai ficar fixo à esquerda da janela
		container.add(BorderLayout.WEST, menu);

		// e o visualizador vai apresentar as telas no centro da janela
		container.add(BorderLayout.CENTER, visualizador);

		// agora precisamos configurar a classe controladora
		// para isso, obtemos uma instância dela
		Controlador controlador = Controlador.get();

		// definimos o container visualizador
		controlador.setVisualizador(visualizador);

		// e registramos cada tela com sua respectiva contante 
		controlador.registra(Tela.INICIAL, new TelaInicial());
		controlador.registra(Tela.LISTA_CALIBRES, new ListaCalibres());
		controlador.registra(Tela.LISTA_CARTUCHOS, new ListaCartuchos());
		controlador.registra(Tela.AZUL, new TelaAzul());
	}

	@Override
	public void paint(Graphics g) {
		Rectangle r = getBounds();
		g.drawImage(Recursos.IMAGEM_FUNDO, 0, 0, r.width, r.height, this);
		super.paintComponents(g);
	}
}
