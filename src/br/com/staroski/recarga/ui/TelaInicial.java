package br.com.staroski.recarga.ui;

import java.awt.*;

import javax.swing.*;

final class TelaInicial extends JPanel {

	private static final long serialVersionUID = 1;

	public TelaInicial() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));

		JLabel lblTitulo = new JLabel("");
		lblTitulo.setFont(new Font("Arial", lblTitulo.getFont().getStyle() | Font.BOLD | Font.ITALIC, 24));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo, BorderLayout.CENTER);

		lblTitulo.setText(Recursos.TITULO_JANELA_PRINCIPAL);
	}
}