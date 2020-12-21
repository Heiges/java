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

			BaseDAO<ChildEntityDAO> cdao = new BaseDAO<ChildEntityDAO>();
			cdao.setClazz(ChildEntityDAO.class);
			cdao.setEntityManager(database.getEntityManager());

			
			
			
			ParentEntityDAO parent = new ParentEntityDAO("neu angelegt");

			ChildEntityDAO child = null;
			
			child = new ChildEntityDAO(parent);
			child.setaSimpleCharValue("val1");
			parent.getChilds().add(child);
			
			child = new ChildEntityDAO(parent);
			child.setaSimpleCharValue("val2");
			parent.getChilds().add(child);
			
			child = new ChildEntityDAO(parent);
			child.setaSimpleCharValue("val3");
			parent.getChilds().add(child);

			dao.create(parent);

			
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