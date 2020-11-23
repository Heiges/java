package biz.heiges.rest.persons;

import jakarta.ws.rs.Path;

@Path("addresses")
public class Address {

	public Address(String streetname) {
		System.out.println("called address c-tor");
		setStreetName(streetname);
	}
	
	private String streetName;

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
}
