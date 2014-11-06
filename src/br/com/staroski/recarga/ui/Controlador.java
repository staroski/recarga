package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

public final class Controlador {

	private static class Holder {

		private static final Controlador INSTANCIA = new Controlador();
	}

	/**
	 * Obt�m a �nica inst�ncia dispon�vel desta classe
	 */
	public static Controlador get() {
		return Holder.INSTANCIA;
	}

	private static final FocusListener FOCUS_SELECTOR = new FocusAdapter() {

		@Override
		public void focusGained(FocusEvent e) {
			Object source = e.getSource();
			if (source instanceof TextComponent) {
				((TextComponent) source).selectAll();
			} else if (source instanceof JTextComponent) {
				((JTextComponent) source).selectAll();
			}
		}
	};

	private Container visualizador;

	// construtor privado, classe singleton
	private Controlador() {}

	/**
	 * M�todo utilizado para apresentar uma tela a partir da sua contante
	 */
	public void exibe(Tela tela) {
		Container visualizador = getVisualizador();
		CardLayout cards = (CardLayout) visualizador.getLayout();
		cards.show(visualizador, tela.name());
	}

	/**
	 * M�todo utilizado para registrar uma contante de tela, com sua inst�ncia
	 */
	public void registra(Tela tela, Container container) {
		visualizador.add(container, tela.name());
		applyFocusSelector(container);
	}

	public <T extends JDialog> T registra(T dialog) {
		applyFocusSelector(dialog);
		return dialog;
	}

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

	private void applyFocusSelector(Container container) {
		for (Component component : container.getComponents()) {
			if (component instanceof TextComponent) {
				((TextComponent) component).addFocusListener(FOCUS_SELECTOR);
			} else if (component instanceof JTextComponent) {
				((JTextComponent) component).addFocusListener(FOCUS_SELECTOR);
			} else if (component instanceof Container) {
				applyFocusSelector((Container) component);
			}
		}
	}

	private Container getVisualizador() {
		if (visualizador == null) {
			throw new IllegalStateException("Container visualidor n�o informado!");
		}
		return visualizador;
	}
}