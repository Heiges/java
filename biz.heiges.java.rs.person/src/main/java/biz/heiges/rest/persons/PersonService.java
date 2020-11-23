package biz.heiges.rest.persons;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("person")
public class PersonService {

	@GET
	@Produces({ "application/json" })
	public Person getPerson() {
		System.out.println("Looking for a default person");
		return new Person("", "");
	};
	
    @GET
    @Path("/{id}")
    @Produces({"application/json"})
    public Person getPerson(@PathParam("id") Long id) {
    	System.out.println("Looking for a person with id == " + id);    	
    	return new Person("Hajo", "Heiges by id");
    }
}
