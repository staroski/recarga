package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import br.com.staroski.recarga.db.*;
import br.com.staroski.recarga.db.tables.*;

final class CadastroEstojo extends JDialog {

	private static final long serialVersionUID = 1;

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldDescricao;
	private JTextField textFieldQuantidade;

	private Estojo estojo;

	private void salvar() {
		int opcao = JOptionPane.showConfirmDialog(this, "Confirma as alterações?", "Salvar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcao == JOptionPane.YES_OPTION) {
			estojo.setDescricao(textFieldDescricao.getText().trim());
			estojo.setQuantidade(Integer.parseInt(textFieldQuantidade.getText().trim()));
			Database.get().save(estojo);
			dispose();
		}
	}

	private void cancelar() {
		int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcao == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	public CadastroEstojo(Estojo estojo) {
		this();
		this.estojo = estojo;
		textFieldDescricao.setText(estojo.getDescricao());
		textFieldQuantidade.setText(String.valueOf(estojo.getQuantidade()));
	}

	/**
	 * Create the dialog.
	 */
	public CadastroEstojo() {
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelar();
			}
		});
		setResizable(false);
		setTitle("Estojo");
		setBounds(100, 100, 310, 140);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 189, 95, 0 };
		gbl_contentPanel.rowHeights = new int[] { 14, 20, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
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

		JLabel lblNewLabel_2 = new JLabel("Quantidade:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 1;
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

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldQuantidade = new GridBagConstraints();
		gbc_textFieldQuantidade.anchor = GridBagConstraints.NORTH;
		gbc_textFieldQuantidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldQuantidade.gridx = 1;
		gbc_textFieldQuantidade.gridy = 1;
		contentPanel.add(textFieldQuantidade, gbc_textFieldQuantidade);
		textFieldQuantidade.setColumns(10);
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
