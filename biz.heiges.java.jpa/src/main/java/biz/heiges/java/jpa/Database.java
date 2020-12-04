package biz.heiges.java.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database implements AutoCloseable {

	private EntityManagerFactory emf = null;

	private EntityManager em = null;

	public Database(String persistenceUnitName) {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();
	}

	public void close() {
		System.out.println("calling close()");
		if (em.isOpen() == true) em.close();
		if (emf.isOpen() == true) emf.close();
	}

	protected EntityManager getEntityManager() {
		return em;
	}
}
