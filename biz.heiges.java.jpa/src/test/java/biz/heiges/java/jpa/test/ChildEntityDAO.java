package biz.heiges.java.jpa.test;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "ChildEntity")
public class ChildEntityDAO implements Serializable {

	private static final long serialVersionUID = -3494014420634267258L;

	public ChildEntityDAO() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
}