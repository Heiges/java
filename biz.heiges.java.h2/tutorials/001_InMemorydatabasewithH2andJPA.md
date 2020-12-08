# InMemorydatabase with H2 and JPA

Build a H2 in memory database with JPA and Hibernate from scratch.

#### 1) Create a simple maven project. Skip archetype selection.

#### 2) Add dependencies to your pom.xml

- [H2Database [com.h2database.h2]](https://mvnrepository.com/artifact/com.h2database/h2)

- [Hibernate [org.hibernate.hibernate-core]](https://mvnrepository.com/artifact/org.hibernate/hibernate-core)

- [jaxb API [javax.xml.bind.jaxb-api]](https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api)

- [jaxb Implementation [com.sun.xml.bind.jaxb-impl]](https://mvnrepository.com/artifact/com.sun.xml.bind/jaxb-impl)

- [jaxb xjc [com.sun.xml.bind.jaxb-xjc]](https://mvnrepository.com/artifact/com.sun.xml.bind/jaxb-xjc)

- [jaxb Acitivation [javax.activation.activation]](https://mvnrepository.com/artifact/javax.activation/activation)

#### 3) Create the persistence.xml

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
					<property name="hibernate.format_sql" value="true" />
					<property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
					<property name="javax.persistence.schema-generation.create-source" value="script"/>
					<property name="javax.persistence.schema-generation.create-script-source" value="sql/create.sql"/>
					<property name="javax.persistence.schema-generation.drop-source" value="script"/>
					<property name="javax.persistence.schema-generation.drop-script-source" value="sql/drop.sql"/>
					<property name="javax.persistence.sql-load-script-source" value="sql/initial_data.sql" />
				</properties>
			</persistence-unit>
		</persistence>

The property `schema-generation.database.action` defines all actions that are performed after the database is started. The value `drop-and-create` means that the tables are first deleted and then rebuilt. If only the tables are to be generated, then the value must be set to `create`.

The properties `schema-generation.create-source` and `schema-generation.create-script-source` defines that the database schema is created by the specified script 'sql/create.sql' and not by the mapper classes.

The same is true for the `drop-source` and `drop-script-source` properties. If there are no tables to delete, either delete both properties or keep an empty script as placeholder.

The property `sql-load-script-source` defines the place and name of the sql script that will be used for loading the initial data.

#### 4) Create the Entity Class

The complete PersonDAO class can be be found [here](https://github.com/Heiges/java/blob/master/biz.heiges.java.h2/src/main/java/biz/heiges/java/h2/PersonDAO.java)

The important parts are : 

The class annotation @Entity where name refers to the table name.

	@Entity(name = "PERSON")
	public class PersonDAO implements Serializable {
	
The field annotation @Column where name refers to the column of the table.

	@Column(name = "surname")
	private String surname;

The field annotation @ID where the annotation @GeneratedValue defines the strategy with which the id keys are generated.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

#### 5) Create the EntityManager and EntityManagerFactory

The class EntityManagerPool implements an easy class for handling the EntityManagerFactory and EntityManager itself.

That class must be used with the try-resource or else the objects will not be closed correctly.

#### 6) Create a main driver

		try (EntityManagerPool emp = new EntityManagerPool("h2")) {
			EntityManager em = emp.getEntityManager();
			List<PersonDAO> resultList = em.createNativeQuery("SELECT * FROM PERSON", PersonDAO.class).getResultList();
			resultList.forEach((x) -> System.out.printf("- %s %s %n", x.getSurname(), x.getFamilyname()));
		}
