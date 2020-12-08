package biz.heiges.java.jpa.test;

import org.junit.Test;

import biz.heiges.java.jpa.BaseDAO;
import biz.heiges.java.jpa.Database;

public class BaseBAOTestMissingClass {

	@Test(expected = IllegalArgumentException.class)
	public void testMissingClazzWithCreate() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			// TC: class not set
			// dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			dao.create(new ParentEntityDAO("another Value"));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingClazzWithRead() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			// TC: class not set
			// dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			dao.read(Long.parseLong("2"));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingClazzWithUpdate() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			// TC: class not set
			// dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			dao.update(new ParentEntityDAO());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingClazzWithDelete() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			// TC: class not set
			// dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			dao.delete(new ParentEntityDAO());
		}
	}
}
