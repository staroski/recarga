package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

final class Menu extends JPanel {

	private static final long serialVersionUID = 1;

	public Menu() {
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
		panel.setOpaque(false);
		add(panel);

		JButton botaoInicio = new JButton("Início");
		botaoInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeTelaInicial();
			}
		});
		panel.add(botaoInicio);

		JButton botaoCalibres = new JButton("Calibres");
		botaoCalibres.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaCalibres();
			}
		});
		panel.add(botaoCalibres);

		JButton botaoMunicoes = new JButton("Munições");
		botaoMunicoes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaMunicoes();
			}
		});
		panel.add(botaoMunicoes);

		JPanel insumos = new JPanel(new GridLayout(5, 1, 10, 10));
		insumos.setOpaque(false);
		insumos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Insumos"));
		add(insumos);

		JButton botaoEstojos = new JButton("Estojos");
		botaoEstojos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaEstojos();
			}
		});
		insumos.add(botaoEstojos);

		JButton botaoChumbos = new JButton("Chumbos");
		botaoChumbos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaChumbos();
			}
		});
		insumos.add(botaoChumbos);

		JButton botaoEspoletas = new JButton("Espoletas");
		botaoEspoletas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaEspoletas();
			}
		});
		insumos.add(botaoEspoletas);

		JButton botaoPolvoras = new JButton("Pólvoras");
		botaoPolvoras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaPolvoras();
			}
		});
		insumos.add(botaoPolvoras);

		JButton botaoProjeteis = new JButton("Projéteis");
		botaoProjeteis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exibeListaProjeteis();
			}
		});
		insumos.add(botaoProjeteis);
	}

	private void exibeTelaInicial() {
		Controlador.get().exibe(Tela.INICIAL);
	}

	private void exibeListaCalibres() {
		Controlador.get().exibe(Tela.LISTA_CALIBRES);
	}

	private void exibeListaEstojos() {
		Controlador.get().exibe(Tela.LISTA_ESTOJOS);
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

	private void exibeListaMunicoes() {
		Controlador.get().exibe(Tela.LISTA_MUNICOES);
	}
}