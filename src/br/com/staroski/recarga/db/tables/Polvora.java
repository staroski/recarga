package br.com.staroski.recarga.db.tables;

import java.sql.*;

import br.com.staroski.recarga.db.*;

public final class Polvora extends Table {

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
	protected void load(ResultSet data) throws SQLException {
		descricao = data.getString("descricao");
		quantidade = data.getInt("quantidade");
	}
}
