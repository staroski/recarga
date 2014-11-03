package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;

import br.com.staroski.recarga.persistence.*;
import br.com.staroski.recarga.persistence.entities.*;

final class CadastroRecarga extends JDialog {

	private class ModeloMunicoes extends DefaultComboBoxModel<String> {

		private static final long serialVersionUID = 1;

		@Override
		public String getElementAt(int linha) {
			if (linha == 0) {
				return "Selecione...";
			}
			Municao municao = getMunicoes().get(linha - 1);
			Calibre calibre = municao.getCalibre();
			Projetil projetil = municao.getProjetil();
			Estojo estojo = municao.getEstojo();
			return calibre.getDescricao() + " - " + projetil.getDescricao() + " - " + estojo.getDescricao();
		}

		@Override
		public int getSize() {
			return getMunicoes().size() + 1;
		}
	}

	private static final long serialVersionUID = 1;

	private final JPanel contentPanel = new JPanel();

	private Consumo consumo;
	private JTextField textFieldData;
	private JComboBox<String> comboBoxMunicao;
	private JTextField textFieldQuantidade;

	/**
	 * Create the dialog.
	 */
	public CadastroRecarga() {
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelar();
			}
		});
		setResizable(false);
		setTitle("Consumo");
		setBounds(100, 100, 620, 140);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 100, 400, 100, 0 };
		gbl_contentPanel.rowHeights = new int[] { 14, 20, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblNewLabel = new JLabel("Data:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblMunio = new JLabel("Muni\u00E7\u00E3o:");
		GridBagConstraints gbc_lblMunio = new GridBagConstraints();
		gbc_lblMunio.anchor = GridBagConstraints.NORTH;
		gbc_lblMunio.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMunio.insets = new Insets(0, 0, 5, 5);
		gbc_lblMunio.gridx = 1;
		gbc_lblMunio.gridy = 0;
		contentPanel.add(lblMunio, gbc_lblMunio);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		GridBagConstraints gbc_lblQuantidade = new GridBagConstraints();
		gbc_lblQuantidade.anchor = GridBagConstraints.NORTH;
		gbc_lblQuantidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuantidade.insets = new Insets(0, 0, 5, 0);
		gbc_lblQuantidade.gridx = 2;
		gbc_lblQuantidade.gridy = 0;
		contentPanel.add(lblQuantidade, gbc_lblQuantidade);

		textFieldData = new JTextField();
		textFieldData.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldData = new GridBagConstraints();
		gbc_textFieldData.anchor = GridBagConstraints.NORTH;
		gbc_textFieldData.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldData.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldData.gridx = 0;
		gbc_textFieldData.gridy = 1;
		contentPanel.add(textFieldData, gbc_textFieldData);
		textFieldData.setColumns(10);

		comboBoxMunicao = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxMunicao = new GridBagConstraints();
		gbc_comboBoxMunicao.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxMunicao.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMunicao.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxMunicao.gridx = 1;
		gbc_comboBoxMunicao.gridy = 1;
		contentPanel.add(comboBoxMunicao, gbc_comboBoxMunicao);

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldQuantidade = new GridBagConstraints();
		gbc_textFieldQuantidade.anchor = GridBagConstraints.NORTH;
		gbc_textFieldQuantidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldQuantidade.gridx = 2;
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

	public CadastroRecarga(Consumo consumo) {
		this();
		this.consumo = consumo;
		//TODO		textFieldDescricao.setText(consumo.getDescricao());
	}

	private void cancelar() {
		int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcao == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	private void salvar() {
		int opcao = JOptionPane.showConfirmDialog(this, "Confirma as alterações?", "Salvar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcao == JOptionPane.YES_OPTION) {
			//TODO			consumo.setDescricao(textFieldDescricao.getText().trim());
			Database.get().save(consumo);
			dispose();
		}
	}

	private List<Municao> getMunicoes() {
		return Database.get().getMunicoes();
	}
}
