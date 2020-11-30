package biz.heiges.java.h2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {

	public static void main(String... args) throws Exception {
	
		// via JPA
		try (EntityManagerPool emp = new EntityManagerPool("h2")) {
			EntityManager em = emp.getEntityManager();

			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(new PersonDAO("Micky", "Mouse"));
			t.commit();
			
			System.out.println("*** get all persons");
			List<PersonDAO> resultList = em.createNativeQuery("SELECT * FROM PERSON", PersonDAO.class).getResultList();
			resultList.forEach((x) -> System.out.printf("- %s - %s %s %n", x.getId(), x.getSurname(), x.getFamilyname()));
			
			System.out.println("*** read person with id 3");
			t.begin();
			PersonDAO p = em.find(PersonDAO.class, Long.parseLong("3"));
			System.out.printf("- %s - %s %s %n", p.getId(), p.getSurname(), p.getFamilyname());
			t.commit();
			
			System.out.println("*** update person with id 3");
			t.begin();
			p.setSurname("Mini");
			p.setFamilyname("Mouse");
			em.merge(p);
			t.commit();
			resultList = em.createNativeQuery("SELECT * FROM PERSON", PersonDAO.class).getResultList();
			resultList.forEach((x) -> System.out.printf("- %s - %s %s %n", x.getId(), x.getSurname(), x.getFamilyname()));
			
			System.out.println("*** delete person with id 3");
			t.begin();
			em.remove(em.find(PersonDAO.class, Long.parseLong("3")));
			t.commit();
			resultList = em.createNativeQuery("SELECT * FROM PERSON", PersonDAO.class).getResultList();
			resultList.forEach((x) -> System.out.printf("- %s - %s %s %n", x.getId(), x.getSurname(), x.getFamilyname()));
			
		}
	}
}