package br.com.staroski.recarga.ui;
import java.awt.*;

final class Controlador {

	private static class Holder {

		private static final Controlador INSTANCIA = new Controlador();
	}

	/**
	 * Obt�m a �nica inst�ncia dispon�vel desta classe
	 */
	public static Controlador get() {
		return Holder.INSTANCIA;
	}

	private Container visualizador;

	// construtor privado, classe singleton
	private Controlador() {}

	/**
	 * M�todo utilizado para definir o container visualizador
	 */
	public void setVisualizador(Container container) {
		LayoutManager layout = container.getLayout();
		if (!(layout instanceof CardLayout)) {
			throw new IllegalArgumentException("Container deve possuir CardLayout!");
		}
		visualizador = container;
	}

	/**
	 * M�todo utilizado para registrar uma contante de tela, com sua inst�ncia
	 */
	public void registra(Tela tela, Container container) {
		visualizador.add(container, tela.name());
	}

	/**
	 * M�todo utilizado para apresentar uma tela a partir da sua contante
	 */
	public void exibe(Tela tela) {
		Container visualizador = getVisualizador();
		CardLayout cards = (CardLayout) visualizador.getLayout();
		cards.show(visualizador, tela.name());
	}

	private Container getVisualizador() {
		if (visualizador == null) {
			throw new IllegalStateException("Container visualidor n�o informado!");
		}
		return visualizador;
	}
}