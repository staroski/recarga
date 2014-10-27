package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

final class Menu extends JPanel {

	private static final long serialVersionUID = 1;

	public Menu() {
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// os botões ficarão alinhados verticalmente
		setLayout(new GridLayout(7, 1, 10, 10));

		// instanciamos os botões

		// registramos os ActionListeners de cada um
		JButton botaoInicio = new JButton("Início");
		botaoInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeTelaInicial();
			}
		});
		add(botaoInicio);

		JButton botaoCalibres = new JButton("Calibres");
		botaoCalibres.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaCalibres();
			}
		});
		add(botaoCalibres);

		JButton botaoCartuchos = new JButton("Cartuchos");
		botaoCartuchos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaCartuchos();
			}
		});
		add(botaoCartuchos);

		JButton botaoChumbos = new JButton("Chumbos");
		botaoChumbos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaChumbos();
			}
		});
		add(botaoChumbos);

		JButton botaoEspoletas = new JButton("Espoletas");
		botaoEspoletas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaEspoletas();
			}
		});
		add(botaoEspoletas);

		JButton botaoPolvoras = new JButton("Pólvoras");
		botaoPolvoras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaPolvoras();
			}
		});
		add(botaoPolvoras);

		JButton botaoProjeteis = new JButton("Projéteis");
		botaoProjeteis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaProjeteis();
			}
		});
		add(botaoProjeteis);
	}

	private void exibeTelaInicial() {
		Controlador.get().exibe(Tela.INICIAL);
	}

	private void exibeListaCalibres() {
		Controlador.get().exibe(Tela.LISTA_CALIBRES);
	}

	private void exibeListaCartuchos() {
		Controlador.get().exibe(Tela.LISTA_CARTUCHOS);
	}

	private void exibeListaChumbos() {
		Controlador.get().exibe(Tela.LISTA_CHUMBOS);
	}

	private void exibeListaEspoletas() {
		Controlador.get().exibe(Tela.LISTA_ESPOLETAS);
	}

	private void exibeListaPolvoras() {
		Controlador.get().exibe(Tela.LISTA_POLVORAS);
	}

	private void exibeListaProjeteis() {
		Controlador.get().exibe(Tela.LISTA_PROJETEIS);
	}
}