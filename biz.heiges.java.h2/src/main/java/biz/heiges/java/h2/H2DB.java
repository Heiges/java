package biz.heiges.java.h2;
/*
 * Copyright 2004-2019 H2 Group. Multiple-Licensed under the MPL 2.0,
 * and the EPL 1.0 (https://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		System.out.println("-- loading persons --");
		Session session = sessionFactory.openSession();
		List<Person> persons = session.createQuery("FROM Person").list();
		persons.forEach((x) -> System.out.printf("- %s %s %n", x.getSurname(), x.getFamilyname()));
		session.close();
		sessionFactory.close();
	}

}