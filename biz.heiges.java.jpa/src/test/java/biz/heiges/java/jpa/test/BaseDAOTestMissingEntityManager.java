package biz.heiges.java.jpa.test;

import org.junit.Test;

import biz.heiges.java.jpa.BaseDAO;
import biz.heiges.java.jpa.Database;
import biz.heiges.java.jpa.test.entities.ParentEntityDAO;

public class BaseDAOTestMissingEntityManager {

	@Test(expected = IllegalArgumentException.class)
	public void testMissingEntityManagerWithCreate() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			// TC: entitymanager not set
			// dao.setEntityManager(database.getEntityManager());
			dao.create(new ParentEntityDAO("another Value"));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingEntityManagerWithRead() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			// TC: entitymanager not set
			// dao.setEntityManager(database.getEntityManager());
			dao.read(Long.parseLong("2"));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingEntityManagerWithUpdate() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			// TC: entitymanager not set
			// dao.setEntityManager(database.getEntityManager());
			dao.update(new ParentEntityDAO());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingEntityManagerWithDelete() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			// TC: entitymanager not set
			// dao.setEntityManager(database.getEntityManager());
			dao.delete(new ParentEntityDAO());
		}
	}


}
