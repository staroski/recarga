package br.com.staroski.recarga.db.tables;

import java.sql.*;

import br.com.staroski.recarga.db.*;

public final class Consumo extends Table {

	private long id_consumo = -1;
	private Date data;
	private int quantidade;
	private long id_consumo_municao;
	private transient Municao municao;

	public Date getData() {
		return data;
	}

	public Municao getMunicao() {
		return municao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setMunicao(Municao municao) {
		this.municao = municao;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	protected void beforeSave(Database db) throws SQLException {
		if (municao != null) {
			db.save(municao);
			id_consumo_municao = municao.getId();
		}
	}

	@Override
	protected void initialize(ResultSet rs) throws SQLException {
		this.data = rs.getDate("data");
		quantidade = rs.getInt("quantidade");
		id_consumo_municao = rs.getLong("id_consumo_municao");
	}

	@Override
	protected void onLoad(Database db) throws SQLException {
		municao = db.find(Municao.class, "id_municao=?", id_consumo_municao);
	}

}
