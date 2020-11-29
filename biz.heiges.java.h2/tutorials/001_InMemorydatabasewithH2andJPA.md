# InMemorydatabase with H2 and JPA

Build a H2 in memory database with JPA and Hibernate from scratch.

1) Create a simple maven project. Skip archetype selection.

2) Add dependencies to your pom.xml

- [H2Database [com.h2database.h2]](https://mvnrepository.com/artifact/com.h2database/h2)

- [Hibernate [org.hibernate.hibernate-core]](https://mvnrepository.com/artifact/org.hibernate/hibernate-core)

- [jaxb API [javax.xml.bind.jaxb-api]](https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api)

- [jaxb Implementation [com.sun.xml.bind.jaxb-impl]](https://mvnrepository.com/artifact/com.sun.xml.bind/jaxb-impl)

- [jaxb xjc [com.sun.xml.bind.jaxb-xjc]](https://mvnrepository.com/artifact/com.sun.xml.bind/jaxb-xjc)

- [jaxb Acitivation [javax.activation.activation]](https://mvnrepository.com/artifact/javax.activation/activation)

3) Create the persistence.xml

		<persistence xmlns="http://java.sun.com/xml/ns/persistence" 
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd" 
		version="2.0">
			<persistence-unit name="h2" transaction-type="RESOURCE_LOCAL">
				<class>biz.heiges.java.h2.PersonDAO</class>
				<properties>
					<property name="javax.persistence.jdbc.url" value="jdbc:h2:mem:test" />
					<property name="javax.persistence.jdbc.driver" value="org.h2.Driver" />
					<property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect" />
					<property name="javax.persistence.schema-generation.database.action" value="drop-and-create" />
					<property name="hibernate.format_sql" value="true" />
					<property name="hibernate.show_sql" value="true" />
					<property name="javax.persistence.sql-load-script-source" value="sql/initial_data.sql" />
				</properties>
			</persistence-unit>
		</persistence>

The property sql-load-script-source defines the place and name of the sql script that will be used for loading the initial data.

4) Create the Entity Class

@todo

5) Create the EntityManager and EntityManagerFactory

The class EntityManagerPool implements an easy class for handling the EntityManagerFactory and EntityManager itself.

That class must be used with the try-resource or else the objects will not be closed correctly.

6) Create a main driver

		try (EntityManagerPool emp = new EntityManagerPool("h2")) {
			EntityManager em = emp.getEntityManager();
			List<PersonDAO> resultList = em.createNativeQuery("SELECT * FROM PERSON", PersonDAO.class).getResultList();
			resultList.forEach((x) -> System.out.printf("- %s %s %n", x.getSurname(), x.getFamilyname()));
		}
