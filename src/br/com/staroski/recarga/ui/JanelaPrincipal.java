package br.com.staroski.recarga.ui;

import java.awt.*;

import javax.swing.*;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1;

	public JanelaPrincipal() {
		super(Recursos.TITULO_JANELA_PRINCIPAL);
		setIconImage(Recursos.IMAGEM_ICONE);
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
		controlador.registra(Tela.CALIBRES, new CadastroCalibres());
		controlador.registra(Tela.VERDE, new TelaVerde());
		controlador.registra(Tela.AZUL, new TelaAzul());
	}
}
