package biz.heiges.java.h2;

import java.util.List;

import javax.persistence.EntityManager;

public class Main {

	public static void main(String... args) throws Exception {
	
		// via JPA
		try (EntityManagerPool emp = new EntityManagerPool("h2")) {
			EntityManager em = emp.getEntityManager();
			List<PersonDAO> resultList = em.createNativeQuery("SELECT * FROM PERSON", PersonDAO.class).getResultList();
			resultList.forEach((x) -> System.out.printf("- %s %s %n", x.getSurname(), x.getFamilyname()));
		}
	}
}