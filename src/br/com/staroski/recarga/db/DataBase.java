package br.com.staroski.recarga.db;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

public final class Database {

	private static final class Holder {

		private static final Database INSTANCE = new Database();

		private Holder() {}
	}

	public static Database get() {
		return Holder.INSTANCE;
	}

	private Connection connection;

	private Database() {}

	public <T extends Table> void delete(T object) {
		PreparedStatement stmt = null;
		try {
			if (isPersistent(object)) {
				stmt = sqlDelete(object);
				stmt.executeUpdate();
				getConnection().commit();
				object.onDelete(this);
				object.setId(-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public <T extends Table> boolean isPersistent(T object) {
		return object.getId() != -1;
	}

	public <T extends Table> List<T> list(Class<T> table) {
		return list(table, (String) null, new Object[] {});
	}

	public <T extends Table> List<T> list(Class<T> table, String where, Object... params) {
		List<T> objects = new ArrayList<>();
		PreparedStatement stmt = null;
		try {
			stmt = sqlSelect(table, where, params);
			ResultSet data = stmt.executeQuery();
			while (data.next()) {
				T object = table.newInstance();
				object.setId(data.getLong("id"));
				object.initialize(data);
				objects.add(object);
			}
			data.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return objects;
	}

	public void login(String url, Properties props) throws SQLException {
		if (connection != null) {
			throw new IllegalStateException("Já está conectado à base de dados!");
		}
		System.out.println("conectando...");
		connection = DriverManager.getConnection(url, props);
		System.out.println("conectado!");
	}

	public void login(String url, String user, String password) throws SQLException {
		Properties props = new Properties();
		props.put("user", user);
		props.put("password", password);
		login(url, props);
	}

	public void logout() throws SQLException {
		System.out.println("desconectando...");
		getConnection().close();
		this.connection = null;
		System.out.println("desconectado!");
	}

	public <T extends Table> void save(T object) {
		PreparedStatement stmt = null;
		try {
			if (isPersistent(object)) {
				stmt = sqlUpdate(object);
			} else {
				stmt = sqlInsert(object);
			}
			stmt.executeUpdate();
			getConnection().commit();
			ResultSet id = stmt.getGeneratedKeys();
			if (id.next()) {
				object.setId(id.getLong(1));
			}
			object.onSave(this);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Connection getConnection() {
		if (connection == null) {
			throw new IllegalStateException("Não está conectado à base de dados!");
		}
		return connection;
	}

	private String[] prepareInsertParams(Field[] fields) {
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
		return new String[] { columns.toString(), values.toString() };
	}

	private String prepareUpdateParams(Field[] fields) {
		StringBuilder columns = new StringBuilder();
		for (int i = 0, n = fields.length; i < n; i++) {
			Field field = fields[i];
			if (i > 0) {
				columns.append(", ");
			}
			columns.append(field.getName() + "=?");
		}
		return columns.toString();
	}

	private <T extends Table> PreparedStatement sqlDelete(T object) throws Exception {
		String sql = "delete from " + tableName(object) + " where id=?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setLong(1, object.getId());
		return stmt;
	}

	private <T extends Table> PreparedStatement sqlInsert(T object) throws Exception {
		Class<?> table = object.getClass();
		Field[] fields = table.getDeclaredFields();
		String[] columnsValues = prepareInsertParams(fields);
		String sql = "insert into " + tableName(object) + " (" + columnsValues[0] + ") values (" + columnsValues[1] + ")";
		PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		for (int i = 0, n = fields.length; i < n; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			stmt.setObject(i + 1, field.get(object));
		}
		return stmt;
	}

	private <T extends Table> PreparedStatement sqlSelect(Class<T> table, String conditions, Object... params) throws SQLException {
		boolean hasParams = conditions != null;
		String sql = "select * from " + tableName(table) + (hasParams ? " where " + conditions : "");
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		if (hasParams) {
			for (int i = 0, n = params.length; i < n; i++) {
				stmt.setObject(i + 1, params[i]);
			}
		}
		return stmt;
	}

	private <T extends Table> PreparedStatement sqlUpdate(T object) throws Exception {
		Class<?> table = object.getClass();
		Field[] fields = table.getDeclaredFields();
		String columnsValues = prepareUpdateParams(fields);
		String sql = "update " + tableName(object) + " set " + columnsValues + " where id=?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		int count = fields.length;
		for (int i = 0; i < count; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			stmt.setObject(i + 1, field.get(object));
		}
		stmt.setLong(count + 1, object.getId());
		return stmt;
	}

	private <T extends Table> String tableName(Class<T> clazz) {
		return clazz.getSimpleName();
	}

	private <T extends Table> String tableName(T object) {
		return tableName(object.getClass());
	}
}
