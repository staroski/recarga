package br.com.staroski.recarga.db.tables;

import java.sql.*;

import br.com.staroski.recarga.db.*;

public final class Calibre extends Table {

	private long id_calibre = -1;
	private String descricao;
	private double polvora;
	private int chumbos;

	public int getChumbos() {
		return chumbos;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPolvora() {
		return polvora;
	}

	public void setChumbos(int chumbos) {
		this.chumbos = chumbos;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setPolvora(double polvora) {
		this.polvora = polvora;
	}

	@Override
	protected void initialize(ResultSet data) throws SQLException {
		descricao = data.getString("descricao");
		polvora = data.getDouble("polvora");
		chumbos = data.getInt("chumbos");
	}
}
