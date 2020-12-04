package biz.heiges.java.jpa;


import java.util.Scanner;

import org.h2.tools.Server;

public class Main {

	public static void main(String... args) throws Exception {

		try (Database database = new Database("entities")) {
		
			BaseDAO<ParentEntityDAO> p = new BaseDAO<ParentEntityDAO>();
			p.setEm(database.getEntityManager());
			p.setClazz(ParentEntityDAO.class);
			p.create(new ParentEntityDAO("anotherValue"));

//			System.out.println("*** get all persons");
//			List<PersonDAO> resultList = personDAO.read();
//			resultList.forEach((x) -> System.out.printf("- %s - %s %s %n", x.getId(), x.getSurname(), x.getFamilyname()));
//			
//			System.out.println("*** read person with id 3");
//			PersonDAO p = personDAO.read(Long.parseLong("3"));
//			System.out.printf("- %s - %s %s %n", p.getId(), p.getSurname(), p.getFamilyname());
//			
//			System.out.println("*** update person with id 3");
//			p.setSurname("Mini");
//			p.setFamilyname("Mouse");
//			personDAO.update(p);
//			resultList = personDAO.read();
//			resultList.forEach((x) -> System.out.printf("- %s - %s %s %n", x.getId(), x.getSurname(), x.getFamilyname()));
//			
//			System.out.println("*** delete person with id 3");
//			personDAO.delete(personDAO.read(Long.parseLong("3")));
//			resultList = personDAO.read();
//			resultList.forEach((x) -> System.out.printf("- %s - %s %s %n", x.getId(), x.getSurname(), x.getFamilyname()));
//			
			System.out.println("Starting server...");
	        System.out.println("URL      : http://localhost:8082/");
	        System.out.println("JDBC URL : jdbc:h2:mem:my_database");
	        System.out.println("User Name: sa");
	        System.out.println("Password :");
			Server server = new Server();
			server.runTool("-tcp", "-web", "-ifNotExists");
			
			System.out.print("Press enter to stop database!");
			Scanner scan = new Scanner(System.in);
			scan.nextLine();
			scan.close();

			System.out.println("Stopping server");
			server.shutdown();
			
		}
	}
}