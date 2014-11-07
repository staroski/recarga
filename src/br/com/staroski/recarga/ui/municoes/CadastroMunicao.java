package br.com.staroski.recarga.ui.municoes;

import static br.com.staroski.recarga.ui.UI.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;

import br.com.staroski.recarga.persistence.*;
import br.com.staroski.recarga.persistence.entities.*;

public final class CadastroMunicao extends JDialog {

	private class ModeloCalibres extends DefaultComboBoxModel<String> {

		private static final long serialVersionUID = 1;

		@Override
		public String getElementAt(int linha) {
			if (linha == 0) {
				return "Selecione...";
			}
			return getCalibres().get(linha - 1).getDescricao();
		}

		@Override
		public int getSize() {
			return getCalibres().size() + 1;
		}
	}

	private class ModeloChumbos extends DefaultComboBoxModel<String> {

		private static final long serialVersionUID = 1;

		@Override
		public String getElementAt(int linha) {
			if (linha == 0) {
				return "Selecione...";
			}
			return getChumbos().get(linha - 1).getDescricao();
		}

		@Override
		public int getSize() {
			return getChumbos().size() + 1;
		}
	}

	private class ModeloEspoletas extends DefaultComboBoxModel<String> {

		private static final long serialVersionUID = 1;

		@Override
		public String getElementAt(int linha) {
			if (linha == 0) {
				return "Selecione...";
			}
			return getEspoletas().get(linha - 1).getDescricao();
		}

		@Override
		public int getSize() {
			return getEspoletas().size() + 1;
		}
	}

	private class ModeloEstojos extends DefaultComboBoxModel<String> {

		private static final long serialVersionUID = 1;

		@Override
		public String getElementAt(int linha) {
			if (linha == 0) {
				return "Selecione...";
			}
			return getEstojos().get(linha - 1).getDescricao();
		}

		@Override
		public int getSize() {
			return getEstojos().size() + 1;
		}
	}

	private class ModeloPolvoras extends DefaultComboBoxModel<String> {

		private static final long serialVersionUID = 1;

		@Override
		public String getElementAt(int linha) {
			if (linha == 0) {
				return "Selecione...";
			}
			return getPolvoras().get(linha - 1).getDescricao();
		}

		@Override
		public int getSize() {
			return getPolvoras().size() + 1;
		}
	}

	private class ModeloProjeteis extends DefaultComboBoxModel<String> {

		private static final long serialVersionUID = 1;

		@Override
		public String getElementAt(int linha) {
			if (linha == 0) {
				return "Selecione...";
			}
			return getProjeteis().get(linha - 1).getDescricao();
		}

		@Override
		public int getSize() {
			return getProjeteis().size() + 1;
		}
	}

	private static final long serialVersionUID = 1;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBoxCalibre;
	private JComboBox<String> comboBoxProjetil;
	private JComboBox<String> comboBoxEstojo;
	private JComboBox<String> comboBoxEspoleta;
	private JComboBox<String> comboBoxPolvora;
	private JComboBox<String> comboBoxChumbo;
	private JTextField textFieldQuantidadeDisponivel;
	private JTextField textFieldQuantidadePolvora;
	private JTextField textFieldQuantidadeChumbo;

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
		setBounds(100, 100, 390, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setOpaque(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 184, 186, 0 };
		gbl_contentPanel.rowHeights = new int[] { 14, 20, 15, 20, 14, 20, 14, 20, 14, 20, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblNewLabel = new JLabel("Calibre:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblProjtil = new JLabel("Proj\u00E9til:");
		GridBagConstraints gbc_lblProjtil = new GridBagConstraints();
		gbc_lblProjtil.anchor = GridBagConstraints.NORTH;
		gbc_lblProjtil.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProjtil.insets = new Insets(0, 0, 5, 0);
		gbc_lblProjtil.gridx = 1;
		gbc_lblProjtil.gridy = 0;
		contentPanel.add(lblProjtil, gbc_lblProjtil);

		comboBoxCalibre = new JComboBox<String>();
		comboBoxCalibre.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagConstraints gbc_comboBoxCalibre = new GridBagConstraints();
		gbc_comboBoxCalibre.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxCalibre.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCalibre.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCalibre.gridx = 0;
		gbc_comboBoxCalibre.gridy = 1;
		contentPanel.add(comboBoxCalibre, gbc_comboBoxCalibre);

		comboBoxProjetil = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxProjetil = new GridBagConstraints();
		gbc_comboBoxProjetil.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxProjetil.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxProjetil.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxProjetil.gridx = 1;
		gbc_comboBoxProjetil.gridy = 1;
		contentPanel.add(comboBoxProjetil, gbc_comboBoxProjetil);

		JLabel lblEstojo = new JLabel("Estojo:");
		GridBagConstraints gbc_lblEstojo = new GridBagConstraints();
		gbc_lblEstojo.anchor = GridBagConstraints.NORTH;
		gbc_lblEstojo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstojo.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstojo.gridx = 0;
		gbc_lblEstojo.gridy = 2;
		contentPanel.add(lblEstojo, gbc_lblEstojo);

		JLabel lblEspoleta = new JLabel("Espoleta:");
		GridBagConstraints gbc_lblEspoleta = new GridBagConstraints();
		gbc_lblEspoleta.anchor = GridBagConstraints.SOUTH;
		gbc_lblEspoleta.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEspoleta.insets = new Insets(0, 0, 5, 0);
		gbc_lblEspoleta.gridx = 1;
		gbc_lblEspoleta.gridy = 2;
		contentPanel.add(lblEspoleta, gbc_lblEspoleta);

		comboBoxEstojo = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxEstojo = new GridBagConstraints();
		gbc_comboBoxEstojo.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxEstojo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstojo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEstojo.gridx = 0;
		gbc_comboBoxEstojo.gridy = 3;
		contentPanel.add(comboBoxEstojo, gbc_comboBoxEstojo);

		comboBoxEspoleta = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxEspoleta = new GridBagConstraints();
		gbc_comboBoxEspoleta.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxEspoleta.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEspoleta.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxEspoleta.gridx = 1;
		gbc_comboBoxEspoleta.gridy = 3;
		contentPanel.add(comboBoxEspoleta, gbc_comboBoxEspoleta);

		JLabel lblChumbo = new JLabel("P\u00F3lvora:");
		GridBagConstraints gbc_lblChumbo = new GridBagConstraints();
		gbc_lblChumbo.anchor = GridBagConstraints.NORTH;
		gbc_lblChumbo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblChumbo.insets = new Insets(0, 0, 5, 5);
		gbc_lblChumbo.gridx = 0;
		gbc_lblChumbo.gridy = 4;
		contentPanel.add(lblChumbo, gbc_lblChumbo);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		GridBagConstraints gbc_lblQuantidade = new GridBagConstraints();
		gbc_lblQuantidade.anchor = GridBagConstraints.NORTH;
		gbc_lblQuantidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuantidade.insets = new Insets(0, 0, 5, 0);
		gbc_lblQuantidade.gridx = 1;
		gbc_lblQuantidade.gridy = 4;
		contentPanel.add(lblQuantidade, gbc_lblQuantidade);

		comboBoxPolvora = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxPolvora = new GridBagConstraints();
		gbc_comboBoxPolvora.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxPolvora.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPolvora.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPolvora.gridx = 0;
		gbc_comboBoxPolvora.gridy = 5;
		contentPanel.add(comboBoxPolvora, gbc_comboBoxPolvora);

		textFieldQuantidadePolvora = new JTextField();
		textFieldQuantidadePolvora.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldQuantidadePolvora = new GridBagConstraints();
		gbc_textFieldQuantidadePolvora.anchor = GridBagConstraints.NORTH;
		gbc_textFieldQuantidadePolvora.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldQuantidadePolvora.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldQuantidadePolvora.gridx = 1;
		gbc_textFieldQuantidadePolvora.gridy = 5;
		contentPanel.add(textFieldQuantidadePolvora, gbc_textFieldQuantidadePolvora);
		textFieldQuantidadePolvora.setColumns(10);

		JLabel lblChumbo_1 = new JLabel("Chumbo:");
		GridBagConstraints gbc_lblChumbo_1 = new GridBagConstraints();
		gbc_lblChumbo_1.anchor = GridBagConstraints.NORTH;
		gbc_lblChumbo_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblChumbo_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblChumbo_1.gridx = 0;
		gbc_lblChumbo_1.gridy = 6;
		contentPanel.add(lblChumbo_1, gbc_lblChumbo_1);

		JLabel lblQuantidade_1 = new JLabel("Quantidade:");
		GridBagConstraints gbc_lblQuantidade_1 = new GridBagConstraints();
		gbc_lblQuantidade_1.anchor = GridBagConstraints.NORTH;
		gbc_lblQuantidade_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuantidade_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblQuantidade_1.gridx = 1;
		gbc_lblQuantidade_1.gridy = 6;
		contentPanel.add(lblQuantidade_1, gbc_lblQuantidade_1);

		comboBoxChumbo = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxChumbo = new GridBagConstraints();
		gbc_comboBoxChumbo.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxChumbo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxChumbo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxChumbo.gridx = 0;
		gbc_comboBoxChumbo.gridy = 7;
		contentPanel.add(comboBoxChumbo, gbc_comboBoxChumbo);

		textFieldQuantidadeChumbo = new JTextField();
		textFieldQuantidadeChumbo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldQuantidadeChumbo = new GridBagConstraints();
		gbc_textFieldQuantidadeChumbo.anchor = GridBagConstraints.NORTH;
		gbc_textFieldQuantidadeChumbo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldQuantidadeChumbo.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldQuantidadeChumbo.gridx = 1;
		gbc_textFieldQuantidadeChumbo.gridy = 7;
		contentPanel.add(textFieldQuantidadeChumbo, gbc_textFieldQuantidadeChumbo);
		textFieldQuantidadeChumbo.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Quantidade Dispon\u00EDvel:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 8;
		contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textFieldQuantidadeDisponivel = new JTextField();
		textFieldQuantidadeDisponivel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldQuantidadeDisponivel = new GridBagConstraints();
		gbc_textFieldQuantidadeDisponivel.anchor = GridBagConstraints.NORTH;
		gbc_textFieldQuantidadeDisponivel.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldQuantidadeDisponivel.gridx = 1;
		gbc_textFieldQuantidadeDisponivel.gridy = 9;
		contentPanel.add(textFieldQuantidadeDisponivel, gbc_textFieldQuantidadeDisponivel);
		textFieldQuantidadeDisponivel.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setOpaque(false);
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
		comboBoxProjetil.setModel(new ModeloProjeteis());
		comboBoxEstojo.setModel(new ModeloEstojos());
		comboBoxEspoleta.setModel(new ModeloEspoletas());
		comboBoxPolvora.setModel(new ModeloPolvoras());
		comboBoxChumbo.setModel(new ModeloChumbos());

		this.municao = municao;

		Calibre calibre = municao.getCalibre();
		comboBoxCalibre.setSelectedIndex(getCalibres().indexOf(calibre) + 1);

		Projetil projetil = municao.getProjetil();
		comboBoxProjetil.setSelectedIndex(getProjeteis().indexOf(projetil) + 1);

		Estojo estojo = municao.getEstojo();
		comboBoxEstojo.setSelectedIndex(getEstojos().indexOf(estojo) + 1);

		Espoleta espoleta = municao.getEspoleta();
		comboBoxEspoleta.setSelectedIndex(getEspoletas().indexOf(espoleta) + 1);

		Polvora polvora = municao.getPolvora();
		comboBoxPolvora.setSelectedIndex(getPolvoras().indexOf(polvora) + 1);
		textFieldQuantidadePolvora.setText(String.valueOf(municao.getQuantidadePolvora()));

		Chumbo chumbo = municao.getChumbo();
		comboBoxChumbo.setSelectedIndex(getChumbos().indexOf(chumbo) + 1);
		textFieldQuantidadeChumbo.setText(String.valueOf(municao.getQuantidadeChumbo()));

		textFieldQuantidadeDisponivel.setText(String.valueOf(municao.getQuantidade()));
	}

	private void cancelar() {
		int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcao == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	private List<Calibre> getCalibres() {
		return Database.get().getCalibres();
	}

	private List<Chumbo> getChumbos() {
		return Database.get().getChumbos();
	}

	private List<Espoleta> getEspoletas() {
		return Database.get().getEspoletas();
	}

	private List<Estojo> getEstojos() {
		return Database.get().getEstojos();
	}

	private List<Polvora> getPolvoras() {
		return Database.get().getPolvoras();
	}

	private List<Projetil> getProjeteis() {
		return Database.get().getProjeteis();
	}

	private void salvar() {
		int opcao = JOptionPane.showConfirmDialog(this, "Confirma as alterações?", "Salvar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcao == JOptionPane.YES_OPTION) {
			try {
				int linha = comboBoxCalibre.getSelectedIndex() - 1;
				Calibre calibre = linha < 0 ? null : getCalibres().get(linha);
				municao.setCalibre(calibre);

				linha = comboBoxProjetil.getSelectedIndex() - 1;
				Projetil projetil = linha < 0 ? null : getProjeteis().get(linha);
				municao.setProjetil(projetil);

				linha = comboBoxEstojo.getSelectedIndex() - 1;
				Estojo estojo = linha < 0 ? null : getEstojos().get(linha);
				municao.setEstojo(estojo);

				linha = comboBoxEspoleta.getSelectedIndex() - 1;
				Espoleta espoleta = linha < 0 ? null : getEspoletas().get(linha);
				municao.setEspoleta(espoleta);

				linha = comboBoxPolvora.getSelectedIndex() - 1;
				Polvora polvora = linha < 0 ? null : getPolvoras().get(linha);
				municao.setPolvora(polvora);

				municao.setQuantidadePolvora(parseDouble(textFieldQuantidadePolvora.getText()));

				linha = comboBoxChumbo.getSelectedIndex() - 1;
				Chumbo chumbo = linha < 0 ? null : getChumbos().get(linha);
				municao.setChumbo(chumbo);

				municao.setQuantidadeChumbo(parseInt(textFieldQuantidadeChumbo.getText()));

				municao.setQuantidade(parseInt(textFieldQuantidadeDisponivel.getText()));
				Database.get().save(municao);
				dispose();
			} catch (Exception e) {
				showError(this, e);
			}
		}
	}
}
