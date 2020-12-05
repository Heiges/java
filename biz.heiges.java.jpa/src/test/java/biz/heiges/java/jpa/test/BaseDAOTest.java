package biz.heiges.java.jpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import biz.heiges.java.jpa.BaseDAO;
import biz.heiges.java.jpa.Database;

public class BaseDAOTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testEntityManagerThrowsExceptionsWithPersist() throws Exception {
		try (Database database = new Database("entities")) {
			ParentEntityDAO e = new ParentEntityDAO("aValue");
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			EntityManager mockedEntityMananger = Mockito.mock(EntityManager.class);
			Mockito.when(mockedEntityMananger.getTransaction())
					.thenReturn(database.getEntityManager().getTransaction());
			Mockito.doThrow(new RuntimeException("mocked!")).when(mockedEntityMananger).persist(e);
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(mockedEntityMananger);
			thrown.expect(RuntimeException.class);
			thrown.expectMessage("mocked!");
			dao.create(e);
		}
	}

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

	@Test()
	public void testCreate() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			dao.create(new ParentEntityDAO("another Value"));
			assertEquals("another Value", dao.read(Long.parseLong("3")).getaSimpleCharValue());
		}
	}

	@Test()
	public void testRead() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			assertEquals("aValueForEntityWithID0", dao.read(Long.parseLong("0")).getaSimpleCharValue());
			assertEquals("aValueForEntityWithID1", dao.read(Long.parseLong("1")).getaSimpleCharValue());
			assertEquals("aValueForEntityWithID2", dao.read(Long.parseLong("2")).getaSimpleCharValue());
			assertNull(dao.read(Long.parseLong("3")));
		}
	}

	@Test()
	public void testUpdate() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			ParentEntityDAO parentEntityDAO = dao.read(Long.parseLong("0"));
			assertEquals("aValueForEntityWithID0", parentEntityDAO.getaSimpleCharValue());
			parentEntityDAO.setaSimpleCharValue("changed the value");
			assertEquals("changed the value", dao.read(Long.parseLong("0")).getaSimpleCharValue());
		}
	}

	@Test()
	public void testDelete() throws Exception {
		try (Database database = new Database("entities")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());
			assertEquals("aValueForEntityWithID0", dao.read(Long.parseLong("0")).getaSimpleCharValue());
			dao.delete(dao.read(Long.parseLong("0")));
			assertNull(dao.read(Long.parseLong("0")));
		}
	}
}
