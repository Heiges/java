package biz.heiges.java.jaxb.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import biz.heiges.java.model.Address;
import biz.heiges.java.model.Person;

public class JAXBMarshalling {

	@Test
	public void testUnmashalling()  {

		try {
			JAXBContext ctx = JAXBContext.newInstance(new Class[] { Person.class, Address.class });
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			Person p2 = (Person) unmarshaller.unmarshal(new FileReader("testcases/person4unmarshalling.xml"));
			assertEquals("Heiges", p2.getFamilieName());
			assertEquals("Hansjoachim", p2.getSurName());
			assertEquals("Hajo", p2.getNickName());
			assertEquals("München", p2.getAddress().getCity());
			assertEquals("Rotenhanstrasse 6", p2.getAddress().getStreet());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testUnmashallingWithRequiredFieldMissing()  {

		try {
			JAXBContext ctx = JAXBContext.newInstance(new Class[] { Person.class, Address.class });
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			Person p2 = (Person) unmarshaller.unmarshal(new FileReader("testcases/person4unmarshallingWithRequiredFieldMissing.xml"));
			// Unmrshalling will work even when required fields are missing
			assertNull(p2.getFamilieName());
			assertEquals("Hansjoachim", p2.getSurName());
			assertEquals("Hajo", p2.getNickName());
			assertEquals("München", p2.getAddress().getCity());
			assertEquals("Rotenhanstrasse 6", p2.getAddress().getStreet());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void testMashalling()  {
		try {
			JAXBContext ctx = JAXBContext.newInstance(new Class[] { Person.class, Address.class });
			Person p1 = new Person();
			p1.setFamilieName("Heiges");
			p1.setSurName("Hansjoachim");
			p1.setNickName("Hajo");
			Address a1 = new Address();
			a1.setCity("München");
			a1.setStreet("Rotenhanstrasse 6");
			p1.setAddress(a1);
			Marshaller marshaller = ctx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1" );
			marshaller.marshal(p1, new File("temp/person.xml"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testMashallingWithRequiredFieldMissing()  {
		try {
			JAXBContext ctx = JAXBContext.newInstance(new Class[] { Person.class, Address.class });
			Person p1 = new Person();
			// familieName is required but missing!
			//p1.setFamilieName("Heiges");
			p1.setSurName("Hansjoachim");
			p1.setNickName("Hajo");
			Address a1 = new Address();
			a1.setCity("München");
			a1.setStreet("Rotenhanstrasse 6");
			p1.setAddress(a1);
			Marshaller marshaller = ctx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1" );
			marshaller.marshal(p1, new File("temp/person.xml"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
