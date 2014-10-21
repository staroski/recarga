package br.com.staroski.recarga.db.tables;

import java.sql.*;

import br.com.staroski.recarga.db.*;

public final class Calibre extends Table {

	private String descricao;
	private double carga;
	private int chumbos;

	public double getCarga() {
		return carga;
	}

	public int getChumbos() {
		return chumbos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setCarga(double carga) {
		this.carga = carga;
	}

	public void setChumbos(int chumbos) {
		this.chumbos = chumbos;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	protected void initialize(ResultSet data) throws SQLException {
		descricao = data.getString("descricao");
		carga = data.getDouble("carga");
		chumbos = data.getInt("chumbos");
	}
}
