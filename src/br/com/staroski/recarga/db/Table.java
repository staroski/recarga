package br.com.staroski.recarga.db;

import java.sql.*;

public abstract class Table {

	private long id = -1;

	public final long getId() {
		return id;
	}

	public final boolean isPersistent() {
		return id != -1;
	}

	protected abstract void load(ResultSet data) throws SQLException;

	final void setId(long id) {
		this.id = id;
	}
}
