package biz.heiges.java.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Party {
	
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
