package br.com.staroski.recarga.db;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

public final class DataBase {

	private static final class Holder {

		private static final DataBase INSTANCE;

		static {
			try {
				INSTANCE = new DataBase();
			} catch (SQLException e) {
				throw new ExceptionInInitializerError(e);
			}
		}

		private Holder() {}
	}

	public static DataBase get() {
		return Holder.INSTANCE;
	}

	private final Connection connection;

	private DataBase() throws SQLException {
		String url = "db/recarga";
		String user = "SA";
		String pass = "";
		connection = DriverManager.getConnection("jdbc:hsqldb:file:" + url, user, pass);
	}

	public <T extends Table> void delete(T object) {
		try {
			if (object.isPersistent()) {
				sqlDelete(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T extends Table> List<T> list(Class<T> table) {
		return list(table, (String) null, new Object[] {});
	}

	public <T extends Table> List<T> list(Class<T> table, String where, Object... params) {
		List<T> objects = new ArrayList<>();
		try {
			ResultSet data = sqlSelect(table, where, params);
			while (data.next()) {
				T object = table.newInstance();
				object.setId(data.getLong("id"));
				object.load(data);
				objects.add(object);
			}
			data.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	public <T extends Table> void save(T object) {
		try {
			if (object.isPersistent()) {
				sqlUpdate(object);
			} else {
				sqlInsert(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder[] prepareInsertParams(Field[] fields) {
		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();
		for (int i = 0, n = fields.length; i < n; i++) {
			Field field = fields[i];
			if (i > 0) {
				columns.append(", ");
				values.append(", ");
			}
			columns.append(field.getName());
			values.append("?");
		}
		return new StringBuilder[] { columns, values };
	}

	private StringBuilder prepareUpdateParams(Field[] fields) {
		StringBuilder columns = new StringBuilder();
		for (int i = 0, n = fields.length; i < n; i++) {
			Field field = fields[i];
			if (i > 0) {
				columns.append(", ");
			}
			columns.append(field.getName() + "=?");
		}
		return columns;
	}

	private <T extends Table> void sqlDelete(T object) throws Exception {
		String sql = "delete from " + tableName(object) + " where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, object.getId());
		stmt.executeUpdate();
		connection.commit();
		object.setId(-1);
	}

	private <T extends Table> void sqlInsert(T object) throws Exception {
		Class<?> table = object.getClass();
		Field[] fields = table.getDeclaredFields();
		StringBuilder[] columnsValues = prepareInsertParams(fields);
		String sql = "insert into " + tableName(object) + " (" + columnsValues[0] + ") values (" + columnsValues[1] + ")";
		PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		for (int i = 0, n = fields.length; i < n; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			stmt.setObject(i + 1, field.get(object));
		}
		stmt.executeUpdate();
		connection.commit();
		ResultSet id = stmt.getGeneratedKeys();
		if (id.next()) {
			object.setId(id.getLong(1));
		}
	}

	private <T extends Table> ResultSet sqlSelect(Class<T> table, String conditions, Object... params) throws SQLException {
		boolean hasParams = conditions != null;
		String sql = "select * from " + tableName(table) + (hasParams ? " where " + conditions : "");
		PreparedStatement stmt = connection.prepareStatement(sql);
		if (hasParams) {
			for (int i = 0, n = params.length; i < n; i++) {
				stmt.setObject(i + 1, params[i]);
			}
		}
		return stmt.executeQuery();
	}

	private <T extends Table> void sqlUpdate(T object) throws Exception {
		Class<?> table = object.getClass();
		Field[] fields = table.getDeclaredFields();
		StringBuilder columnsValues = prepareUpdateParams(fields);
		String sql = "update " + tableName(object) + " set (" + columnsValues + ") where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		int count = fields.length;
		for (int i = 0; i < count; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			stmt.setObject(i + 1, field.get(object));
		}
		stmt.setLong(count, object.getId());
		stmt.executeUpdate();
		connection.commit();
	}

	private <T extends Table> String tableName(Class<T> clazz) {
		return clazz.getSimpleName();
	}

	private <T extends Table> String tableName(T object) {
		return tableName(object.getClass());
	}
}
