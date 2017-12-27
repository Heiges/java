package biz.heiges.java.jaxb.examples;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.junit.Test;
import org.xml.sax.SAXException;

import biz.heiges.java.model.Address;
import biz.heiges.java.model.Person;

public class JAXBMarshallingWithValidation {

	@Test(expected = MarshalException.class)
	public void testMashallingWithValidation() throws JAXBException, SAXException {

		JAXBContext ctx = JAXBContext.newInstance(new Class[] { Person.class, Address.class });
		Marshaller marshaller = ctx.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("testcases/person.xsd"));
		marshaller.setSchema(schema);

		marshaller.marshal(buildPerson(), new File("temp/personWithRequiredFieldMissing.xml"));

	}

	private Person buildPerson() {
		Person p1 = new Person();
		// familieName is missing!
		p1.setSurName("Hansjoachim");
		p1.setNickName("Hajo");
		Address a1 = new Address();
		a1.setCity("München");
		a1.setStreet("Rotenhanstrasse 6");
		p1.setAddress(a1);
		return p1;
	}
}
