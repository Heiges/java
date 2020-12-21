package biz.heiges.java.jpa.test.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ChildEntityDAO implements Serializable {

	private static final long serialVersionUID = -3494014420634267258L;

	public ChildEntityDAO() {
	}

	public ChildEntityDAO(ParentEntityDAO parent) {
		setParent(parent);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentid")
	private ParentEntityDAO parentID;

	private String aSimpleCharValue;	
	
	public ParentEntityDAO getParent() {
		return parentID;
	}

	public void setParent(ParentEntityDAO parent) {
		this.parentID = parent;
	}

	public String getaSimpleCharValue() {
		return aSimpleCharValue;
	}

	public void setaSimpleCharValue(String aSimpleCharValue) {
		this.aSimpleCharValue = aSimpleCharValue;
	}
}