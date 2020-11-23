package biz.heiges.rest.persons;

import java.util.ArrayList;
import java.util.List;

public class Person {

	public Person(String surname, String familyName) {
		System.out.println("called person c-tor");
		setSurname(surname);
		setFamilyName(familyName);
	}

	private String familyName;

	private String surname;

	private List<Address> addresses;

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

	public List<Address> getAddresses() {
		System.out.println("All known addresses");
		addresses = new ArrayList<Address>();
		addresses.add(new Address(""));
		addresses.add(new Address(""));
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
