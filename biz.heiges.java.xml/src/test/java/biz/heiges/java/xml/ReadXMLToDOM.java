package biz.heiges.java.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import biz.heiges.java.model.Address;
import biz.heiges.java.model.Person;

public class ReadXMLToDOM {

	@Test
	public void testUnmashalling() {
		try {
			JAXBContext ctx = JAXBContext.newInstance(new Class[] { Person.class, Address.class });
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			Person p2 = (Person) unmarshaller.unmarshal(new FileReader("testcases/person.xml"));
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

}
