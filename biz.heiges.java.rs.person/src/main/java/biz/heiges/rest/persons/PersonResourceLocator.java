package biz.heiges.rest.persons;

import jakarta.ws.rs.Path;

@Path("persons/person")
public class PersonResourceLocator {

	@Path("/")
	public PersonService locatePerson() {
		System.out.println("called PersonResourceLocator");
		return new PersonService();
	}
}
