package br.com.staroski.recarga.db;

import java.sql.*;

public abstract class Table {

	private long id = -1;

	public final long getId() {
		return id;
	}

	public final void setId(long id) {
		this.id = id;
	}

	protected abstract void initialize(ResultSet data) throws SQLException;

	protected void onSave(Database db) throws SQLException {
		// implementação padrão não faz nada, subclasses podem especializar
	}

	protected void onDelete(Database db) throws SQLException {
		// implementação padrão não faz nada, subclasses podem especializar
	}
}
