package biz.heiges.java.jpa.test;

import javax.persistence.EntityManager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import biz.heiges.java.jpa.BaseDAO;
import biz.heiges.java.jpa.Database;

public class BaseDAOTestRollback {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testEntityManagerThrowsExceptionsWithPersist() throws Exception {
		try (Database database = new Database("entities")) {
			ParentEntityDAO e = new ParentEntityDAO("aValue");
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			EntityManager mockedEntityMananger = Mockito.mock(EntityManager.class);
			Mockito.when(mockedEntityMananger.getTransaction()).thenReturn(database.getEntityManager().getTransaction());
			Mockito.doThrow(new RuntimeException("mocked!")).when(mockedEntityMananger).persist(e);
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(mockedEntityMananger);
			thrown.expect(RuntimeException.class);
			thrown.expectMessage("mocked!");
			dao.create(e);
		}
	}
}
