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
			
			List<PersonDAO> resultList = em.createNativeQuery("SELECT * FROM PERSON", PersonDAO.class).getResultList();
			resultList.forEach((x) -> System.out.printf("- %s - %s %s %n", x.getId(), x.getSurname(), x.getFamilyname()));
		}
	}
}