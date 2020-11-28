package biz.heiges.java.h2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String... args) throws Exception {

		// via Hibernate
//		try (SessionPool sp = new SessionPool();) {
//			System.out.println("-- loading persons --");
//			Session session = sp.getSession();
//			List<PersonDAO> persons = session.createQuery("FROM PERSON", PersonDAO.class).list();
//			persons.forEach((x) -> System.out.printf("- %s %s %n", x.getSurname(), x.getFamilyname()));
//		}
		
		// via JPA
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
		EntityManager em= emf.createEntityManager();
		List<PersonDAO> resultList = em.createNativeQuery("SELECT * FROM PERSON", PersonDAO.class).getResultList();
		resultList.forEach((x) -> System.out.printf("- %s %s %n", x.getSurname(), x.getFamilyname()));
		em.close();
		emf.close();
	}
}