package biz.heiges.java.h2;

import java.util.List;

/**
 * A very simple class that shows how to load the driver, create a database,
 * create a table, and insert some data.
 */
public class H2DB {

	/**
	 * Called when ran from command line.
	 *
	 * @param args ignored
	 */
	public static void main(String... args) throws Exception {

		try (SessionPool sp = new SessionPool();) {
			System.out.println("-- loading persons --");
			List<PersonDAO> persons = sp.getSession().createQuery("FROM PERSON").list();
			persons.forEach((x) -> System.out.printf("- %s %s %n", x.getSurname(), x.getFamilyname()));
		}
	}
}