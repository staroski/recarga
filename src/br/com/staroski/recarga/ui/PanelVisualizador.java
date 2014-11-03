package br.com.staroski.recarga.ui;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

final class PanelVisualizador extends JPanel {

	private static final long serialVersionUID = 1;

	public PanelVisualizador() {
		super(new CardLayout());
		setOpaque(false);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
}
