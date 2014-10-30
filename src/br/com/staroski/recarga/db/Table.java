package br.com.staroski.recarga.db;

import java.lang.reflect.*;
import java.sql.*;

public abstract class Table {

	private transient Field id;

	public final long getId() {
		try {
			return getIdColumn().getLong(this);
		} catch (Exception e) {
			throw Database.wrap(e);
		}
	}

	public final void setId(long id) {
		try {
			getIdColumn().setLong(this, id);
		} catch (Exception e) {
			throw Database.wrap(e);
		}
	}

	private Field getIdColumn() {
		if (id == null) {
			try {
				id = getClass().getDeclaredField(Database.idColumn(this));
				id.setAccessible(true);
			} catch (Exception e) {
				throw Database.wrap(e);
			}
		}
		return id;
	}

	protected void afterSave(Database db) throws SQLException {
		// implementação padrão não faz nada, subclasses podem especializar
	}

	protected void beforeSave(Database db) throws SQLException {
		// implementação padrão não faz nada, subclasses podem especializar
	}

	protected abstract void initialize(ResultSet rs) throws SQLException;

	protected void onDelete(Database db) throws SQLException {
		// implementação padrão não faz nada, subclasses podem especializar
	}

	protected void onLoad(Database db) throws SQLException {
		// implementação padrão não faz nada, subclasses podem especializar
	}
}
