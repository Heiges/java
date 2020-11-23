package biz.heiges.rest.persons;

import java.util.ArrayList;
import java.util.List;

import biz.heiges.rest.persons.model.Person;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("persons")
public class PersonsService {

    public PersonsService() {
    	System.out.println("called PersonsService");
	}

    @GET
    @Produces({"application/json"})
    public Person getAnEmptyPerson() {
    	System.out.println("An empty person");    	
    	return new Person("", "");
    }

    @GET
    @Path("/all")
    @Produces({"application/json"})
    public List<Person> getAllPersons() {
    	System.out.println("All known persons");    	
     	List<Person> list = new ArrayList<Person>();
    	list.add(new Person("Hajo1", "Heiges1"));
    	list.add(new Person("Hajo2", "Heiges2"));
    	return list;
    }
}
