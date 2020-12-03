package biz.heiges.java.h2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.mockito.Mockito;

import biz.heiges.java.h2.BaseDAO;
import biz.heiges.java.h2.PersonDAO;

public class PersonDAOTest {

	@Test(expected=RuntimeException.class)
	public void testEntityManagerThrowsExceptionsWithPersist() throws Exception {
		try(BaseDAOWithMock<PersonDAO> baseDAO = new BaseDAOWithMock<PersonDAO>("h2")) {
			EntityTransaction transaction = baseDAO.getTransactionForMock();
			EntityManager mockedEntityMananger = Mockito.mock(EntityManager.class);
	        Mockito.when(mockedEntityMananger.getTransaction()).thenReturn(transaction);
	        PersonDAO personDAO = new PersonDAO("Micky", "Mouse");
	        Mockito.doThrow(new RuntimeException("mocked!")).when(mockedEntityMananger).persist(personDAO);
	        baseDAO.setMockedEntityManager(mockedEntityMananger);
	        baseDAO.setClazz(PersonDAO.class);
	        baseDAO.create(personDAO);
			fail();
		}
	}
	
	
	@Test(expected=IllegalArgumentException.class ) 
	public void testMissingClazzWithReadAllPersons() throws Exception {
		try (BaseDAO<PersonDAO> personDAO = new BaseDAO<PersonDAO>("h2")) {
			// missing clazz :: personDAO.setClazz(PersonDAO.class);
			personDAO.read();
		} 
	}

	@Test(expected=IllegalArgumentException.class ) 
	public void testMissingClazzWithReadPersonByKey() throws Exception {
		try (BaseDAO<PersonDAO> personDAO = new BaseDAO<PersonDAO>("h2")) {
			// missing clazz :: personDAO.setClazz(PersonDAO.class);
			personDAO.read(Long.parseLong("2"));
		}
	}
	
	@Test
	public void testCreate() {
		try (BaseDAO<PersonDAO> personDAO = new BaseDAO<PersonDAO>("h2")) {
			personDAO.setClazz(PersonDAO.class);
			personDAO.create(new PersonDAO("Micky", "Mouse"));
			List<PersonDAO> persons = personDAO.read();
			assertEquals(4, persons.size());
		} 
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testReadAllPersons() {
		try (BaseDAO<PersonDAO> personDAO = new BaseDAO<PersonDAO>("h2")) {
			personDAO.setClazz(PersonDAO.class);
			List<PersonDAO> persons = personDAO.read();
			assertEquals(3, persons.size());
		} 
		catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test 
	public void testReadPersonByKey() {
		try (BaseDAO<PersonDAO> personDAO = new BaseDAO<PersonDAO>("h2")) {
			personDAO.setClazz(PersonDAO.class);
			PersonDAO p = personDAO.read(Long.parseLong("2"));
			assertEquals("John", p.getSurname());
			assertEquals("Doo", p.getFamilyname());
		} 
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testUpdate() {
		try (BaseDAO<PersonDAO> personDAO = new BaseDAO<PersonDAO>("h2")) {
			personDAO.setClazz(PersonDAO.class);
			PersonDAO p = personDAO.read(Long.parseLong("2"));
			p.setSurname("Mini");
			p.setFamilyname("Mouse");
			personDAO.update(p);
			PersonDAO p2 = personDAO.read(Long.parseLong("2"));
			assertEquals("Mini", p2.getSurname());
			assertEquals("Mouse", p2.getFamilyname());
		} 
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testDelete() {
		try (BaseDAO<PersonDAO> personDAO = new BaseDAO<PersonDAO>("h2")) {
			personDAO.setClazz(PersonDAO.class);
			List<PersonDAO> persons = personDAO.read();
			assertEquals(3, persons.size());
			personDAO.delete(personDAO.read(Long.parseLong("2")));
			persons = personDAO.read();
			assertEquals(2, persons.size());
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
}
