package br.com.staroski.recarga.persistence;

import java.util.*;

import javax.persistence.*;

import org.hibernate.*;
import org.hibernate.exception.*;

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
	private List<Consumo> consumos;
	private List<Recarga> recargas;

	private EntityManagerFactory factory;
	private EntityManager manager;

	private Database() {}

	public void delete(Object object) throws DatabaseException {
		try {
			boolean commit = false;
			try {
				manager.getTransaction().begin();
				manager.createQuery("delete from " + tableName(object) + " o where o = :object").setParameter("object", object).executeUpdate();
				commit = true;
			} finally {
				if (commit) {
					manager.getTransaction().commit();
					needReload();
				} else {
					manager.getTransaction().rollback();
				}
			}
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	private DatabaseException handleException(Throwable error) {
		Throwable cause = error.getCause();
		if (cause instanceof ConstraintViolationException) {
			return new DatabaseException("Não é possível excluir o registro, ele é referenciado em outro lugar!");
		}
		if (cause instanceof PropertyValueException) {
			return new DatabaseException("Não é possível salvar o registro, verifique os campos preenchidos!");
		}
		return new DatabaseException(error);
	}

	private static String tableName(Object object) {
		return object.getClass().getSimpleName();
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

	public List<Consumo> getConsumos() {
		if (consumos == null) {
			manager.getTransaction().begin();
			consumos = manager.createQuery("from Consumo", Consumo.class).getResultList();
			manager.getTransaction().commit();
		}
		return consumos;
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

	public List<Municao> getMunicoes() {
		if (municoes == null) {
			manager.getTransaction().begin();
			municoes = manager.createQuery("from Municao", Municao.class).getResultList();
			manager.getTransaction().commit();
		}
		return municoes;
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

	public List<Recarga> getRecargas() {
		if (recargas == null) {
			manager.getTransaction().begin();
			recargas = manager.createQuery("from Recarga", Recarga.class).getResultList();
			manager.getTransaction().commit();
		}
		return recargas;
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

	public void save(Object object) throws DatabaseException {
		try {
			boolean commit = false;
			try {
				manager.getTransaction().begin();
				manager.persist(object);
				commit = true;
			} finally {
				if (commit) {
					manager.getTransaction().commit();
					needReload();
				} else {
					manager.getTransaction().rollback();
				}
			}
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public void save(Recarga recarga) throws DatabaseException {
		try {
			boolean commit = false;
			try {
				manager.getTransaction().begin();
				atualizaEstoque(recarga);
				Municao municao=recarga.getMunicao();
				manager.persist(municao.getEstojo());
				manager.persist(municao.getProjetil());
				manager.persist(municao.getEspoleta());
				manager.persist(municao.getPolvora());
				manager.persist(municao.getChumbo());
				manager.persist(municao);
				manager.persist(recarga);
				commit = true;
			} finally {
				if (commit) {
					manager.getTransaction().commit();
					needReload();
				} else {
					manager.getTransaction().rollback();
				}
			}
		} catch (Throwable e) {
			throw handleException(e);
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
		consumos = null;
		recargas = null;
	}

	private void atualizaEstoque(Recarga recarga) {
		int quantidade = recarga.getQuantidade();
		Municao municao = recarga.getMunicao();
		int municoesDisponiveis = municao.getQuantidade();
		municoesDisponiveis += quantidade;
		municao.setQuantidade(municoesDisponiveis);

		Estojo estojo = municao.getEstojo();
		if (estojo != null) {
			int estojosDisponiveis = estojo.getQuantidade();
			estojosDisponiveis -= quantidade;
			estojo.setQuantidade(estojosDisponiveis);
		}

		Projetil projetil = municao.getProjetil();
		if (projetil != null) {
			int projeteisDisponiveis = projetil.getQuantidade();
			projeteisDisponiveis -= quantidade;
			projetil.setQuantidade(projeteisDisponiveis);
		}

		Espoleta espoleta = municao.getEspoleta();
		if (espoleta != null) {
			int espoletasDisponiveis = espoleta.getQuantidade();
			espoletasDisponiveis -= quantidade;
			espoleta.setQuantidade(espoletasDisponiveis);
		}

		Polvora polvora = municao.getPolvora();
		if (polvora != null) {
			int polvoraDisponivel = polvora.getQuantidade();
			polvoraDisponivel -= municao.getQuantidadePolvora() * quantidade;
			polvora.setQuantidade(polvoraDisponivel);
		}

		Chumbo chumbo = municao.getChumbo();
		if (chumbo != null) {
			int chumboDisponivel = chumbo.getQuantidade();
			chumboDisponivel -= municao.getQuantidadeChumbo() * quantidade;
			chumbo.setQuantidade(chumboDisponivel);
		}

	}
}
