package biz.heiges.java.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BaseDAO<T extends Serializable> {

	private Class<T> clazz = null;

	private EntityManager em = null;

	private EntityTransaction transaction = null;

	private boolean autoCommit = true;
	
	public BaseDAO() {
	}

	public BaseDAO(boolean autoComit ) {
		this.autoCommit = autoComit; 
	}

	public void create(T entity) throws Exception {
		try {
			begin();
			em.persist(entity);
			if(autoCommit == true) transaction.commit();
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
			if(autoCommit == true) transaction.commit();
		} catch (Exception ex) {
			rollback();
			throw ex;
		}
	}

	public void delete(T entity) throws Exception {
		try {
			begin();
			em.remove(entity);
			if(autoCommit == true) transaction.commit();
		} catch (Exception ex) {
			rollback();
			throw ex;
		}
	}

	private void begin() {
		validate();
		if (autoCommit == false && transaction == null) {
			// if not in autocommit mode get transaction only once
			transaction = em.getTransaction();
		}
		else {
			// if in autocommit mode always get a fresh transaction
			transaction = em.getTransaction();
		}
		
		if (autoCommit == true) {
			transaction.begin();
		} 
		else if (transaction.isActive() == false) {
			transaction.begin();
		}
	}

	public void rollback() {
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

	public void commit() {
		if (autoCommit == true) {
			throw new IllegalStateException();
		}
		else if (transaction.isActive()){
			transaction.commit();
		}
	}
	
	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
}
