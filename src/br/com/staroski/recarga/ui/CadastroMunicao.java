package br.com.staroski.recarga.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;

import br.com.staroski.recarga.db.*;
import br.com.staroski.recarga.db.tables.*;

final class CadastroMunicao extends JDialog {

	private class ModeloCalibres extends DefaultComboBoxModel<String> {

		private static final long serialVersionUID = 1;

		@Override
		public String getElementAt(int linha) {
			if (linha == 0) {
				return "Selecione...";
			}
			Calibre calibre = getCalibres().get(linha - 1);
			return calibre.getDescricao();
		}

		@Override
		public int getSize() {
			return getCalibres().size() + 1;
		}
	}

	private List<Calibre> calibres;

	private static final long serialVersionUID = 1;

	private final JPanel contentPanel = new JPanel();

	private JComboBox<String> comboBoxCalibre;
	private JTextField textFieldQuantidade;
	private Municao municao;

	/**
	 * Create the dialog.
	 */
	public CadastroMunicao() {
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelar();
			}
		});
		setResizable(false);
		setTitle("Munição");
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

		JLabel lblNewLabel = new JLabel("Calibre:");
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

		comboBoxCalibre = new JComboBox<String>();
		comboBoxCalibre.setEditable(true);
		comboBoxCalibre.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagConstraints gbc_comboBoxCalibre = new GridBagConstraints();
		gbc_comboBoxCalibre.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxCalibre.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCalibre.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxCalibre.gridx = 0;
		gbc_comboBoxCalibre.gridy = 1;
		contentPanel.add(comboBoxCalibre, gbc_comboBoxCalibre);

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

	public CadastroMunicao(Municao municao) {
		this();
		comboBoxCalibre.setModel(new ModeloCalibres());
		this.municao = municao;
		Calibre calibre = municao.getCalibre();
		comboBoxCalibre.setSelectedItem(calibre.getDescricao());
		textFieldQuantidade.setText(String.valueOf(municao.getQuantidade()));
	}

	private List<Calibre> getCalibres() {
		if (calibres == null) {
			calibres = Database.get().load(Calibre.class);
		}
		return calibres;
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
			Calibre calibre = getCalibres().get(comboBoxCalibre.getSelectedIndex() - 1);
			municao.setCalibre(calibre);
			municao.setQuantidade(Integer.parseInt(textFieldQuantidade.getText().trim()));
			Database.get().save(municao);
			dispose();
		}
	}

}
