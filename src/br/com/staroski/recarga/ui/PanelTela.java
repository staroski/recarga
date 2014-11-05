package br.com.staroski.recarga.ui;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

final class PanelTela extends JPanel {

	private static final long serialVersionUID = 1;

	public PanelTela() {
		super(new CardLayout());
		setOpaque(false);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
}
