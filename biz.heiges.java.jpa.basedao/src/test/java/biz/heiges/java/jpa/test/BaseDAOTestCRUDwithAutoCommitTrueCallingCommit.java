package biz.heiges.java.jpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import biz.heiges.java.jpa.BaseDAO;
import biz.heiges.java.jpa.Database;
import biz.heiges.java.jpa.test.entities.ParentEntityDAO;

public class BaseDAOTestCRUDwithAutoCommitTrueCallingCommit {

	@Test(expected = IllegalStateException.class)
	public void testCreate() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			dao.create(new ParentEntityDAO("another Value"));
			assertEquals("another Value", dao.read(Long.parseLong("3")).getaSimpleCharValue());
			dao.commit();
		}
	}

	@Test(expected = IllegalStateException.class)
	public void testUpdate() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			ParentEntityDAO parentEntityDAO = dao.read(Long.parseLong("0"));
			assertEquals("aValueForEntityWithID0", parentEntityDAO.getaSimpleCharValue());
			parentEntityDAO.setaSimpleCharValue("changed the value");
			dao.update(parentEntityDAO);
			assertEquals("changed the value", dao.read(Long.parseLong("0")).getaSimpleCharValue());
			dao.commit();
		}
	}

	@Test(expected = IllegalStateException.class)
	public void testDelete() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			assertEquals("aValueForEntityWithID0", dao.read(Long.parseLong("0")).getaSimpleCharValue());
			dao.delete(dao.read(Long.parseLong("0")));
			assertNull(dao.read(Long.parseLong("0")));
			dao.commit();
		}
	}
}
