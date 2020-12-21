package biz.heiges.java.jpa.main;

import java.util.Scanner;

import org.h2.tools.Server;

import biz.heiges.java.jpa.BaseDAO;
import biz.heiges.java.jpa.Database;
import biz.heiges.java.jpa.test.entities.ChildEntityDAO;
import biz.heiges.java.jpa.test.entities.ParentEntityDAO;

public class Main {

	public static void main(String... args) throws Exception {
		try (Database database = new Database("entities")) {

			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(database.getEntityManager());

			ParentEntityDAO parent = new ParentEntityDAO("neu angelegt");

			ChildEntityDAO child = null;

			child = new ChildEntityDAO(parent);
			child.setaSimpleCharValue("child val1");
			parent.getChilds().add(child);

			child = new ChildEntityDAO(parent);
			child.setaSimpleCharValue("child val2");
			parent.getChilds().add(child);

			dao.create(parent);

			{
				System.out.println("create another database");

				try (Database databaseWithoutCreate = new Database("entitiesWithoutCreate")) {
					BaseDAO<ParentEntityDAO> dao2 = new BaseDAO<ParentEntityDAO>();
					dao2.setClazz(ParentEntityDAO.class);
					dao2.setEntityManager(databaseWithoutCreate.getEntityManager());
					ParentEntityDAO parentEntityDAO = dao2.read(Long.parseLong("1"));
					System.out.println("read parent entity");
					if (parentEntityDAO == null) {
						System.out.println("Shit!");
					} 
					else {
						System.out.println(parentEntityDAO.getaSimpleCharValue());
						System.out.println(parentEntityDAO.getChilds().get(0).getaSimpleCharValue());
						System.out.println(parentEntityDAO.getChilds().get(1).getaSimpleCharValue());
					}
				}
			}

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