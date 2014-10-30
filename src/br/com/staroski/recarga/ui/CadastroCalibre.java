package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import br.com.staroski.recarga.persistence.*;

final class CadastroCalibre extends JDialog {

	private static final long serialVersionUID = 1;

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldDescricao;
	private JTextField textFieldChumbos;
	private JTextField textFieldPolvora;

	private Calibre calibre;

	private void salvar() {
		int opcao = JOptionPane.showConfirmDialog(this, "Confirma as alterações?", "Salvar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcao == JOptionPane.YES_OPTION) {
			calibre.setDescricao(textFieldDescricao.getText().trim());
			calibre.setChumbos(Integer.parseInt(textFieldChumbos.getText().trim()));
			calibre.setPolvora(Double.parseDouble(textFieldPolvora.getText().trim()));
			Database.get().save(calibre);
			dispose();
		}
	}

	private void cancelar() {
		int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcao == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	public CadastroCalibre(Calibre calibre) {
		this();
		this.calibre = calibre;
		textFieldDescricao.setText(calibre.getDescricao());
		textFieldChumbos.setText(String.valueOf(calibre.getChumbos()));
		textFieldPolvora.setText(String.valueOf(calibre.getPolvora()));
	}

	/**
	 * Create the dialog.
	 */
	public CadastroCalibre() {
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelar();
			}
		});
		setResizable(false);
		setTitle("Calibre");
		setBounds(100, 100, 420, 140);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 200, 100, 100, 0 };
		gbl_contentPanel.rowHeights = new int[] { 14, 20, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblNewLabel = new JLabel("Descri\u00E7\u00E3o:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Chumbos:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("P\u00F3lvora:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 0;
		contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textFieldDescricao = new JTextField();
		textFieldDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagConstraints gbc_textFieldDescricao = new GridBagConstraints();
		gbc_textFieldDescricao.anchor = GridBagConstraints.NORTH;
		gbc_textFieldDescricao.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescricao.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldDescricao.gridx = 0;
		gbc_textFieldDescricao.gridy = 1;
		contentPanel.add(textFieldDescricao, gbc_textFieldDescricao);
		textFieldDescricao.setColumns(10);

		textFieldChumbos = new JTextField();
		textFieldChumbos.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldChumbos = new GridBagConstraints();
		gbc_textFieldChumbos.anchor = GridBagConstraints.NORTH;
		gbc_textFieldChumbos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldChumbos.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldChumbos.gridx = 1;
		gbc_textFieldChumbos.gridy = 1;
		contentPanel.add(textFieldChumbos, gbc_textFieldChumbos);
		textFieldChumbos.setColumns(10);

		textFieldPolvora = new JTextField();
		textFieldPolvora.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldPolvora = new GridBagConstraints();
		gbc_textFieldPolvora.anchor = GridBagConstraints.NORTH;
		gbc_textFieldPolvora.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPolvora.gridx = 2;
		gbc_textFieldPolvora.gridy = 1;
		contentPanel.add(textFieldPolvora, gbc_textFieldPolvora);
		textFieldPolvora.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						salvar();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
