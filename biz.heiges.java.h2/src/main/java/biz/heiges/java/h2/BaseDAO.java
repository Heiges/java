package biz.heiges.java.h2;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BaseDAO<T extends Serializable> implements AutoCloseable {

	private Class< T > clazz = null;

	private EntityManagerFactory emf = null;

	private EntityManager em = null;
	
	private EntityTransaction transaction = null;	
	
	public BaseDAO(String persistenceUnitName) {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em= emf.createEntityManager();
	}
	
	public void create(T entity) {
		begin();
		em.persist(new PersonDAO("Micky", "Mouse"));
		transaction.commit();
	}
	
	// FIXME  
	@SuppressWarnings("unchecked")
	public List<PersonDAO> read() {
		validateClazz();
		return em.createNativeQuery("SELECT * FROM PERSON", clazz).getResultList();
	}

	public PersonDAO read(Long primaryKey) {
		validateClazz();
		return (PersonDAO) em.find(clazz, primaryKey);
	}

	public void update(PersonDAO entity) {
		begin();
		em.merge(entity);
		transaction.commit();
	}

	public void delete(PersonDAO entity) {
		begin();
		em.remove(entity);
		transaction.commit();
	}

	private void begin() {
		validateClazz();
		transaction = em.getTransaction();
		transaction.begin();
	}
	
	private void validateClazz() {
		if (clazz == null) throw new IllegalArgumentException("no clazz given");
	}
	
	public void setClazz(Class< T > clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public void close() throws Exception {
		System.out.println("calling close()");
		if (em.isOpen() == true) em.close();
		if(emf.isOpen() == true) emf.close();
	}
}
