package biz.heiges.java.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person extends Party {

	@XmlElement(required=true)
	private String familyName = null;

	private String surName = null;

	private String nickName = null;

	public String getFamilieName() {
		return familyName;
	}

	public void setFamilieName(String familieName) {
		this.familyName = familieName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickname) {
		this.nickName = nickname;
	}

}
