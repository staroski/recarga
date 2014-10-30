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

	private static String idColumn(Class<?> clazz) {
		return ("id_" + clazz.getSimpleName()).toLowerCase();
	}

	private static String tableName(Class<?> clazz) {
		return clazz.getSimpleName();
	}

	private static <T extends Table> String tableName(T object) {
		return tableName(object.getClass());
	}

	static <T extends Table> String idColumn(T object) {
		return idColumn(object.getClass());
	}

	static RuntimeException wrap(Throwable t) {
		t.printStackTrace();
		if (t instanceof RuntimeException) {
			return (RuntimeException) t;
		}
		return new RuntimeException(t);
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
			throw wrap(e);
		} finally {
			close(stmt);
		}
	}

	public <T extends Table> boolean isPersistent(T object) {
		return object.getId() != -1;
	}

	public <T extends Table> List<T> load(Class<T> table) {
		return load(table, (String) null, new Object[] {});
	}

	public <T extends Table> List<T> load(Class<T> table, String where, Object... params) {
		List<T> objects = new ArrayList<>();
		PreparedStatement stmt = null;
		try {
			stmt = sqlSelect(table, where, params);
			ResultSet data = stmt.executeQuery();
			while (data.next()) {
				T object = table.newInstance();
				object.setId(data.getLong(idColumn(table)));
				object.initialize(data);
				objects.add(object);
				object.onLoad(this);
			}
			data.close();
		} catch (Exception e) {
			throw wrap(e);
		} finally {
			close(stmt);
		}
		return objects;
	}

	public <T extends Table> T find(Class<T> table, String where, Object... params) {
		List<T> all = load(table, where, params);
		return all.isEmpty() ? null : all.get(0);
	}

	public void login(String url, Properties props) {
		if (connection != null) {
			throw new IllegalStateException("Já está conectado à base de dados!");
		}
		try {
			System.out.println("conectando...");
			connection = DriverManager.getConnection(url, props);
			System.out.println("conectado!");
		} catch (SQLException e) {
			throw wrap(e);
		}
	}

	public void login(String url, String user, String password) {
		Properties props = new Properties();
		props.put("user", user);
		props.put("password", password);
		login(url, props);
	}

	public void logout() {
		try {
			System.out.println("desconectando...");
			getConnection().close();
			this.connection = null;
			System.out.println("desconectado!");
		} catch (SQLException e) {
			throw wrap(e);
		}
	}

	public <T extends Table> void save(T object) {
		PreparedStatement stmt = null;
		try {
			object.beforeSave(this);
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
			object.afterSave(this);
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

	private Field[] getColumns(Class<?> table) {
		Field[] array = table.getDeclaredFields();
		List<Field> fields = new ArrayList<>();
		fields.addAll(Arrays.asList(array));
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			if (ignore(field)) {
				fields.remove(i);
				i--;
			}
		}
		array = new Field[fields.size()];
		return fields.toArray(array);
	}

	private Connection getConnection() {
		if (connection == null) {
			throw new IllegalStateException("Não está conectado à base de dados!");
		}
		return connection;
	}

	private boolean ignore(Field field) {
		// se é transiente, ignora
		if (Modifier.isTransient(field.getModifiers())) {
			return true;
		}
		// tenta verificar se não é a coluna do id
		if (field.getName().equalsIgnoreCase(idColumn(field.getDeclaringClass()))) {
			return true;
		}
		return false;
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
		String sql = "delete from " + tableName(object) + " where " + idColumn(object) + "=?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setLong(1, object.getId());
		return stmt;
	}

	private <T extends Table> PreparedStatement sqlInsert(T object) throws Exception {
		Class<?> table = object.getClass();
		Field[] fields = getColumns(table);
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
		Field[] fields = getColumns(table);
		String columnsValues = prepareUpdateParams(fields);
		String sql = "update " + tableName(object) + " set " + columnsValues + " where " + idColumn(object) + "=?";
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
}
