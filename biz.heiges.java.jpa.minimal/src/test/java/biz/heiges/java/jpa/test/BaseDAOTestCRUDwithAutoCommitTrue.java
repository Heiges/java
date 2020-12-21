package biz.heiges.java.jpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

				ParentEntityDAO parent = new ParentEntityDAO("neu angelegt");

				ChildEntityDAO child = null;

				child = new ChildEntityDAO(parent);
				child.setaSimpleCharValue("child val1");
				parent.getChilds().add(child);

				child = new ChildEntityDAO(parent);
				child.setaSimpleCharValue("child val2");
				parent.getChilds().add(child);

				{
					try (Database databaseWithoutCreate = new Database("entitiesWithoutCreate")) {
						BaseDAO<ParentEntityDAO> dao3 = new BaseDAO<ParentEntityDAO>();
						dao3.setClazz(ParentEntityDAO.class);
						dao3.setEntityManager(databaseWithoutCreate.getEntityManager());
						assertNull(dao3.read(Long.parseLong("1")));
					}
				}
				dao.create(parent);

				{
					try (Database databaseWithoutCreate = new Database("entitiesWithoutCreate")) {
						BaseDAO<ParentEntityDAO> dao4 = new BaseDAO<ParentEntityDAO>();
						dao4.setClazz(ParentEntityDAO.class);
						dao4.setEntityManager(databaseWithoutCreate.getEntityManager());
						assertNotNull(dao4.read(Long.parseLong("1")));
					}
				}
			}

			try (Database databaseWithoutCreate = new Database("entitiesWithoutCreate")) {
				BaseDAO<ParentEntityDAO> dao2 = new BaseDAO<ParentEntityDAO>();
				dao2.setClazz(ParentEntityDAO.class);
				dao2.setEntityManager(databaseWithoutCreate.getEntityManager());
				assertEquals("neu angelegt", dao2.read(Long.parseLong("1")).getaSimpleCharValue());
				assertEquals("child val1", dao2.read(Long.parseLong("1")).getChilds().get(0).getaSimpleCharValue());
				assertEquals("child val2", dao2.read(Long.parseLong("1")).getChilds().get(1).getaSimpleCharValue());
			}
		}
	}
}
