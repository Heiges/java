package biz.heiges.java.xml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;

public class ReadXMLToDOM {

	@Test
	public void testUnmashalling() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder(); 
			Document doc = db.parse(new File("testcases/person.xml"));
			assertNotNull(doc);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
