package biz.heiges.java.jpa.test.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "PARENT_ENTITIES")
public class ParentEntityDAO implements Serializable {

	private static final long serialVersionUID = 3284115550039763032L;

	public ParentEntityDAO() {
	}

	public ParentEntityDAO(String val) {
		this.setaSimpleCharValue(val);
	}	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "aSimpleCharValue")
	private String aSimpleCharValue;

	public String getaSimpleCharValue() {
		return aSimpleCharValue;
	}

	public void setaSimpleCharValue(String aSimpleCharValue) {
		this.aSimpleCharValue = aSimpleCharValue;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
	
}