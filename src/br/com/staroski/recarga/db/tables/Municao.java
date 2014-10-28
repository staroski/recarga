package br.com.staroski.recarga.db.tables;

import java.sql.*;

import br.com.staroski.recarga.db.*;

public final class Municao extends Table {

	private long id_calibre = -1;
	private transient Calibre calibre;

	private int quantidade;

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
	protected void initialize(ResultSet data) throws SQLException {
		quantidade = data.getInt("quantidade");
		id_calibre = data.getLong("id_calibre");
	}

	@Override
	protected void onLoad(Database db) throws SQLException {
		calibre = db.loadFirst(Calibre.class, "id", id_calibre);
	}

	@Override
	protected void beforeSave(Database db) throws SQLException {
		if (calibre != null) {
			db.save(calibre);
			id_calibre = calibre.getId();
		}
	}

}
