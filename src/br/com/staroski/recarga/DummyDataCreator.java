package br.com.staroski.recarga;

import br.com.staroski.recarga.persistence.*;

final class DummyDataCreator {

	private static final boolean ENABLED = true;

	private final Database db;

	DummyDataCreator(Database db) {
		if (db == null) {
			throw new IllegalArgumentException("null");
		}
		this.db = db;
	}

	void execute() {
		if (!ENABLED) {
			System.err.println(getClass().getSimpleName() + " disabled!");
			return;
		}
		System.err.println(getClass().getSimpleName() + " enabled!");
		criarCalibres();
	}

	private void criarCalibres() {

	}
}
