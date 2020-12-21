package biz.heiges.java.jpa.test.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
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

	private String aSimpleCharValue;

	@OneToMany(mappedBy = "parentID", cascade=CascadeType.ALL )
	private List<ChildEntityDAO> childs = new ArrayList<ChildEntityDAO>();

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

	public List<ChildEntityDAO> getChilds() {
		return childs;
	}

	public void setChilds(List<ChildEntityDAO> childs) {
		this.childs = childs;
	}
}