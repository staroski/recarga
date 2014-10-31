package br.com.staroski.recarga.persistence;

import java.util.*;

import javax.persistence.*;

import br.com.staroski.recarga.persistence.entities.*;

public final class Database {

	private static final class Holder {

		private static final Database INSTANCE = new Database();

		private Holder() {}
	}

	public static Database get() {
		return Holder.INSTANCE;
	}

	private List<Calibre> calibres;
	private List<Chumbo> chumbos;
	private List<Espoleta> espoletas;
	private List<Estojo> estojos;
	private List<Polvora> polvoras;
	private List<Projetil> projeteis;
	private List<Municao> municoes;

	private EntityManagerFactory factory;
	private EntityManager manager;

	private Database() {}

	public void delete(Object object) {
		manager.getTransaction().begin();
		manager.remove(object);
		manager.getTransaction().commit();
		needReload();
	}

	public void save(Object object) {
		manager.getTransaction().begin();
		manager.persist(object);
		manager.getTransaction().commit();
		needReload();
	}

	public List<Calibre> getCalibres() {
		if (calibres == null) {
			manager.getTransaction().begin();
			calibres = manager.createQuery("from Calibre", Calibre.class).getResultList();
			manager.getTransaction().commit();
		}
		return calibres;
	}

	public List<Chumbo> getChumbos() {
		if (chumbos == null) {
			manager.getTransaction().begin();
			chumbos = manager.createQuery("from Chumbo", Chumbo.class).getResultList();
			manager.getTransaction().commit();
		}
		return chumbos;
	}

	public List<Espoleta> getEspoletas() {
		if (espoletas == null) {
			manager.getTransaction().begin();
			espoletas = manager.createQuery("from Espoleta", Espoleta.class).getResultList();
			manager.getTransaction().commit();
		}
		return espoletas;
	}

	public List<Estojo> getEstojos() {
		if (estojos == null) {
			manager.getTransaction().begin();
			estojos = manager.createQuery("from Estojo", Estojo.class).getResultList();
			manager.getTransaction().commit();
		}
		return estojos;
	}

	public List<Polvora> getPolvoras() {
		if (polvoras == null) {
			manager.getTransaction().begin();
			polvoras = manager.createQuery("from Polvora", Polvora.class).getResultList();
			manager.getTransaction().commit();
		}
		return polvoras;
	}

	public List<Projetil> getProjeteis() {
		if (projeteis == null) {
			manager.getTransaction().begin();
			projeteis = manager.createQuery("from Projetil", Projetil.class).getResultList();
			manager.getTransaction().commit();
		}
		return projeteis;
	}

	public List<Municao> getMunicoes() {
		if (municoes == null) {
			manager.getTransaction().begin();
			municoes = manager.createQuery("from Municao", Municao.class).getResultList();
			manager.getTransaction().commit();
		}
		return municoes;
	}

	public void login(String url, String user, String pass) {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.format_sql", "true");
		props.put("javax.persistence.jdbc.driver", "org.hsqldb.jdbcDriver");
		props.put("javax.persistence.jdbc.url", url);
		props.put("javax.persistence.jdbc.user", user);
		props.put("javax.persistence.jdbc.password", pass);
		props.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		props.put("hibernate.hbm2ddl.auto", "validate");

		factory = Persistence.createEntityManagerFactory("br.com.staroski.recarga", props);
		manager = factory.createEntityManager();
	}

	public void logout() {
		if (manager != null) {
			manager.close();
			manager = null;
		}
		if (factory != null) {
			factory.close();
			factory = null;
		}
	}

	private void needReload() {
		calibres = null;
		chumbos = null;
		espoletas = null;
		estojos = null;
		polvoras = null;
		projeteis = null;
		municoes = null;
	}
}
