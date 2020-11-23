package biz.heiges.rest.persons;

import jakarta.ws.rs.Path;

@Path("person")
public class Person {

//    @GET
//    @Produces({"text/plain"})
//    public Person getPerson() {
//    	System.out.println("called getPerson");
//    	return this;
//    }	

	public Person(String surname, String familyName) {
    	System.out.println("called person c-tor");
    	setSurname(surname);
    	setFamilyName(familyName);
	}

	private String familyName;

    private String surname;


	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


    
    
    
}
