package biz.heiges.java.jaxb.examples;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import biz.heiges.java.model.Person;

public class JAXBSchemaFromClass {

	@Test
	public void testSchemeToFile() {
		JAXBContext ctx;
		try {
			ctx = JAXBContext.newInstance(new Class[] { Person.class });
			ctx.generateSchema(new SchemaOutputResolver() {
				@Override
				public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
					StreamResult res = new StreamResult(new FileWriter("temp/person.xsd"));
					res.setSystemId("1");
					return res;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testSchemeToConsole() {
		JAXBContext ctx;
		try {
			ctx = JAXBContext.newInstance(new Class[] { Person.class });
			ctx.generateSchema(new SchemaOutputResolver() {
				@Override
				public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
					StreamResult res = new StreamResult(System.out);
					res.setSystemId("1");
					return res;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testSchemeToDOM() {
		JAXBContext ctx;
		try {
			final List<DOMResult> results = new ArrayList<DOMResult>();
			ctx = JAXBContext.newInstance(new Class[] { Person.class });
			ctx.generateSchema(new SchemaOutputResolver() {
				@Override
				public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
					DOMResult result = new DOMResult();
					result.setSystemId(suggestedFileName);
					results.add(result);
					return result;
				}
			});

			assertTrue(!results.isEmpty());
			DOMResult domResult = results.get(0);
			assertNotNull(domResult);
			Node node = domResult.getNode();
			assertNotNull(node);
			assertTrue(node instanceof Document);
			Document doc = (Document) node;
			assertNotNull(doc);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

}
