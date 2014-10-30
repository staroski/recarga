package br.com.staroski.recarga.db.tables;

import java.sql.*;

import br.com.staroski.recarga.db.*;

public final class Municao extends Table {

	private long id_municao = -1;
	private long id_municao_calibre = -1;
	private int quantidade;

	private transient Calibre calibre;

	public Calibre getCalibre() {
		return calibre;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setCalibre(Calibre calibre) {
		this.calibre = calibre;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	protected void beforeSave(Database db) throws SQLException {
		if (calibre != null) {
			db.save(calibre);
			id_municao_calibre = calibre.getId();
		}
	}

	@Override
	protected void initialize(ResultSet rs) throws SQLException {
		quantidade = rs.getInt("quantidade");
		id_municao_calibre = rs.getLong("id_municao_calibre");
	}

	@Override
	protected void onLoad(Database db) throws SQLException {
		calibre = db.find(Calibre.class, "id_calibre=?", id_municao_calibre);
	}
}
