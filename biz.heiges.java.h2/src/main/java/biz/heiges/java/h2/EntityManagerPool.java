package biz.heiges.java.h2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerPool implements AutoCloseable {

	private EntityManagerFactory emf = null;

	private EntityManager em = null;
	
	public EntityManagerPool(String persistenceUnitName) {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em= emf.createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public void close() throws Exception {
		System.out.println("calling close()");
		if (em.isOpen() == true) em.close();
		if(emf.isOpen() == true) emf.close();
	}

}
