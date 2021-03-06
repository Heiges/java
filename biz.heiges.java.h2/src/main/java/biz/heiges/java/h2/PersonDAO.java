package biz.heiges.java.h2;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "PERSON")
public class PersonDAO implements Serializable {

	private static final long serialVersionUID = 3284115550039763032L;

	public PersonDAO() {
	}

	public PersonDAO(String surname, String familyname) {
		this.surname = surname;
		this.familyname = familyname;
	}
	
	public PersonDAO(long id, String surname, String familyname) {
		this.setId(id);
		this.surname = surname;
		this.familyname = familyname;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}