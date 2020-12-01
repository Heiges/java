package biz.heiges.java.h2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import biz.heiges.java.h2.BaseDAO;
import biz.heiges.java.h2.PersonDAO;

public class PersonDAOTest {

	@Test(expected=IllegalArgumentException.class ) 
	public void testMissingClazzWithReadAllPersons() throws Exception {
		try (BaseDAO<PersonDAO> personDAO = new BaseDAO<PersonDAO>("h2")) {
			personDAO.read();
		} 
	}

	@Test(expected=IllegalArgumentException.class ) 
	public void testMissingClazzWithReadPersonByKey() throws Exception {
		try (BaseDAO<PersonDAO> personDAO = new BaseDAO<PersonDAO>("h2")) {
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
