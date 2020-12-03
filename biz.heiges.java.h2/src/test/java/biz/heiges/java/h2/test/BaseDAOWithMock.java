package biz.heiges.java.h2.test;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import biz.heiges.java.h2.BaseDAO;

public class BaseDAOWithMock<T extends Serializable> extends BaseDAO<T> {

	public BaseDAOWithMock(String persistenceUnitName) {
		super(persistenceUnitName);
	}
	
	public void setMockedEntityManager(EntityManager em) {
		super.setEm(em);
	}
	
	public EntityTransaction getTransactionForMock() {
		return getEM().getTransaction();
	}
}
