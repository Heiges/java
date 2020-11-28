package biz.heiges.java.h2;

import java.util.List;

import org.hibernate.Session;

public class Main {

	public static void main(String... args) throws Exception {

		// via Hibernate
		try (SessionPool sp = new SessionPool();) {
			System.out.println("-- loading persons --");
			Session session = sp.getSession();
			List<PersonDAO> persons = session.createQuery("FROM PERSON", PersonDAO.class).list();
			persons.forEach((x) -> System.out.printf("- %s %s %n", x.getSurname(), x.getFamilyname()));
		}
	}
}