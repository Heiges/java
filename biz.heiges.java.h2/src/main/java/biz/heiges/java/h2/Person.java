package biz.heiges.java.h2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {

	public Person() {
	}

	public Person(long id, String surname, String familyname) {
		this.id = id;
		this.surname = surname;
		this.familyname = familyname;
	}

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "surname")
	private String surname;

	@Column(name = "familyname")
	private String familyname;

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFamilyname() {
		return familyname;
	}

	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}
}