package biz.heiges.java.jpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import biz.heiges.java.jpa.BaseDAO;
import biz.heiges.java.jpa.Database;
import biz.heiges.java.jpa.test.entities.ChildEntityDAO;
import biz.heiges.java.jpa.test.entities.ParentEntityDAO;

public class BaseDAOTestCRUDwithAutoCommitTrue {

	@Test()
	public void testCreate() throws Exception {
		try (Database database = new Database("entities")) {
			
			{
				BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
				dao.setClazz(ParentEntityDAO.class);
				dao.setEntityManager(database.getEntityManager());

				ParentEntityDAO entity = new ParentEntityDAO("another Value");
				ChildEntityDAO child = null;
				
				child = new ChildEntityDAO(entity);
				child.setaSimpleCharValue("val1");
				entity.getChilds().add(child);
				
				child = new ChildEntityDAO(entity);
				child.setaSimpleCharValue("val2");
				entity.getChilds().add(child);
				
				child = new ChildEntityDAO(entity);
				child.setaSimpleCharValue("val3");
				entity.getChilds().add(child);
				
				dao.create(entity);
			}
			
			try (Database databaseWithoutCreate = new Database("entitiesWithoutCreate")) {
				BaseDAO<ParentEntityDAO> dao2 = new BaseDAO<ParentEntityDAO>();
				dao2.setClazz(ParentEntityDAO.class);
				dao2.setEntityManager(databaseWithoutCreate.getEntityManager());
				assertEquals("another Value", dao2.read(Long.parseLong("3")).getaSimpleCharValue());
				assertEquals("val1", dao2.read(Long.parseLong("3")).getChilds().get(0).getaSimpleCharValue());
				assertEquals("val2", dao2.read(Long.parseLong("3")).getChilds().get(1).getaSimpleCharValue());
				assertEquals("val3", dao2.read(Long.parseLong("3")).getChilds().get(2).getaSimpleCharValue());
			}			
		}
	}

	@Test()
	public void testUpdate() throws Exception {
		try (Database database = new Database("entities")) {

			{
				BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
				dao.setClazz(ParentEntityDAO.class);
				dao.setEntityManager(database.getEntityManager());
				ParentEntityDAO parentEntityDAO = dao.read(Long.parseLong("0"));
				assertEquals("aValueForEntityWithID0", parentEntityDAO.getaSimpleCharValue());
				parentEntityDAO.setaSimpleCharValue("changed the value");
				dao.update(parentEntityDAO);
				assertEquals("changed the value", dao.read(Long.parseLong("0")).getaSimpleCharValue());
			}
			
			try (Database databaseWithoutCreate = new Database("entitiesWithoutCreate")) {
				BaseDAO<ParentEntityDAO> dao2 = new BaseDAO<ParentEntityDAO>();
				dao2.setClazz(ParentEntityDAO.class);
				dao2.setEntityManager(databaseWithoutCreate.getEntityManager());
				assertEquals("changed the value", dao2.read(Long.parseLong("0")).getaSimpleCharValue());
			}			
		}
	}

	@Test()
	public void testDelete() throws Exception {
		try (Database database = new Database("entities")) {
			
			{
				BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
				dao.setClazz(ParentEntityDAO.class);
				dao.setEntityManager(database.getEntityManager());
				assertEquals("aValueForEntityWithID0", dao.read(Long.parseLong("0")).getaSimpleCharValue());
				dao.delete(dao.read(Long.parseLong("0")));
				assertNull(dao.read(Long.parseLong("0")));
			}
			
			try (Database databaseWithoutCreate = new Database("entitiesWithoutCreate")) {
				BaseDAO<ParentEntityDAO> dao2 = new BaseDAO<ParentEntityDAO>();
				dao2.setClazz(ParentEntityDAO.class);
				dao2.setEntityManager(databaseWithoutCreate.getEntityManager());
				assertNull(dao2.read(Long.parseLong("0")));
			}			
		}
	}
}
