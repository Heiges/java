package biz.heiges.rest.persons;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("persons")
public class Persons {

    @GET
    @Produces({"text/plain"})
    public String getPersons() {
        return "VieleHajos!";
    }

    @GET
    @Path("/all")
    @Produces({"application/json"})
    public List<Person> getAllPersons() {
    	System.out.println("All known person");    	
     	List<Person> list = new ArrayList<Person>();
    	list.add(new Person("Hajo", "Heiges"));
    	list.add(new Person("Hajo2", "Heiges2"));
    	return list;
    }
    
    @GET
    @Path("/person")
    @Produces({"application/json"})
    public Person getPerson() {
    	System.out.println("Looking for a default person");    	
    	return new Person("Hajo", "Heiges");
    }
    
    @GET
    @Path("/person/{id}")
    @Produces({"application/json"})
    public Person getPerson(@PathParam("id") Long id) {
    	System.out.println("Looking for a person with id == " + id);    	
    	return new Person("Hajo", "Heiges");
    }
    
}
