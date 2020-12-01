package biz.heiges.java.h2;

import java.util.List;

public class Main {

	public static void main(String... args) throws Exception {
	
		try (BaseDAO<PersonDAO> personDAO = new BaseDAO<PersonDAO>("h2")) {
			personDAO.setClazz(PersonDAO.class);
			personDAO.create(new PersonDAO("Micky", "Mouse"));

			System.out.println("*** get all persons");
			List<PersonDAO> resultList = personDAO.read();
			resultList.forEach((x) -> System.out.printf("- %s - %s %s %n", x.getId(), x.getSurname(), x.getFamilyname()));
			
			System.out.println("*** read person with id 3");
			PersonDAO p = personDAO.read(Long.parseLong("3"));
			System.out.printf("- %s - %s %s %n", p.getId(), p.getSurname(), p.getFamilyname());
			
			System.out.println("*** update person with id 3");
			p.setSurname("Mini");
			p.setFamilyname("Mouse");
			personDAO.update(p);
			resultList = personDAO.read();
			resultList.forEach((x) -> System.out.printf("- %s - %s %s %n", x.getId(), x.getSurname(), x.getFamilyname()));
			
			System.out.println("*** delete person with id 3");
			personDAO.delete(personDAO.read(Long.parseLong("3")));
			resultList = personDAO.read();
			resultList.forEach((x) -> System.out.printf("- %s - %s %s %n", x.getId(), x.getSurname(), x.getFamilyname()));
		}
	}
}