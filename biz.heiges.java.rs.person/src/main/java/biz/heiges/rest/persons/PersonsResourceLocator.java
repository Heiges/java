package biz.heiges.rest.persons;

import jakarta.ws.rs.Path;

@Path("/persons")
public class PersonsResourceLocator {

	@Path("/")
	public PersonsService locatePerson() {
		System.out.println("called PersonsResourceLocator");
		return new PersonsService();
	}
}
