package br.com.staroski.recarga.db.tables;

import java.sql.*;

import br.com.staroski.recarga.db.*;

public final class Espoleta extends Table {

	private long id_espoleta = -1;
	private String descricao;
	private int quantidade;
	
	public String getDescricao() {
		return descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	protected void initialize(ResultSet rs) throws SQLException {
		descricao = rs.getString("descricao");
		quantidade = rs.getInt("quantidade");
	}
}
