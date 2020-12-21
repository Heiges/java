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

	@Column(name = "aEnumValue")
	private EnumType aEnumValue;
	
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
	
	public EnumType getaEnumValue() {
		return aEnumValue;
	}

	public void setaEnumValue(EnumType aEnumValue) {
		this.aEnumValue = aEnumValue;
	}

	public enum EnumType {
		
		VAL1("value desc 0"), VAL2("value desc 1"), VAL3("value desc 2");
		
		private String description;

		private EnumType(String desc) {
			this.setDescription(desc);				
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}
}