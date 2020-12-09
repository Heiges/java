package biz.heiges.java.jpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import biz.heiges.java.jpa.BaseDAO;
import biz.heiges.java.jpa.Database;
import biz.heiges.java.jpa.test.entities.ParentEntityDAO;

public class BaseDAOTestRollback {

	@Test
	public void testEntityManagerThrowsExceptionsWithPersist() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>(false);
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			ParentEntityDAO e1 = new ParentEntityDAO("aValue1");
			dao.create(e1);
			assertEquals("aValue1", dao.read(Long.parseLong("3")).getaSimpleCharValue());
			dao.rollback();
			assertNull(dao.read(Long.parseLong("3")));
		}
	}
	
	
	@Test
	public void testEntityManagerThrowsExceptionsWithMerge() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>(false);
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			ParentEntityDAO e1 = dao.read(Long.parseLong("2"));
			assertEquals("aValueForEntityWithID2", dao.read(Long.parseLong("2")).getaSimpleCharValue());
			e1.setaSimpleCharValue("changed!");
			dao.update(e1);
			assertEquals("changed!", dao.read(Long.parseLong("2")).getaSimpleCharValue());
			dao.rollback();
			assertEquals("aValueForEntityWithID2", dao.read(Long.parseLong("2")).getaSimpleCharValue());
		}
	}
	
	@Test
	public void testEntityManagerThrowsExceptionsWithRemove() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>(false);
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			ParentEntityDAO e1 = dao.read(Long.parseLong("2"));
			dao.delete(e1);
			assertNull(dao.read(Long.parseLong("2")));
			dao.rollback();
			assertEquals("aValueForEntityWithID2", dao.read(Long.parseLong("2")).getaSimpleCharValue());
		}
	}
}
