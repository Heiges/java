package biz.heiges.java.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BaseDAO<T extends Serializable> {

	private Class<T> clazz = null;

	private EntityManager em = null;

	private EntityTransaction transaction = null;

	public BaseDAO() {
	}

	public void create(T entity) throws Exception {
		try {
			begin();
			em.persist(entity);
			transaction.commit();
		} catch (Exception ex) {
			rollback();
			throw ex;
		}
	}

	public T read(Long primaryKey) {
		validate();
		return (T) em.find(clazz, primaryKey);
	}

	public void update(T entity) throws Exception {
		try {
			begin();
			em.merge(entity);
			transaction.commit();
		} catch (Exception ex) {
			rollback();
			throw ex;
		}
	}

	public void delete(T entity) throws Exception {
		try {
			begin();
			em.remove(entity);
			transaction.commit();
		} catch (Exception ex) {
			rollback();
			throw ex;
		}
	}

	private void begin() {
		validate();
		transaction = em.getTransaction();
		transaction.begin();
	}

	private void rollback() {
		if(transaction != null && transaction.isActive()) {
			transaction.rollback();
		}
	}
	
	private void validate() {
		validateClazz();
		validateEntityManager();
	}

	private void validateClazz() {
		if (clazz == null)
			throw new IllegalArgumentException("no clazz given");
	}

	private void validateEntityManager() {
		if (em == null)
			throw new IllegalArgumentException("no entitymanager given");
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
}
