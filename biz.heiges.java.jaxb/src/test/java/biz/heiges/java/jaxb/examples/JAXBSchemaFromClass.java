package biz.heiges.java.jaxb.examples;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;

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
					return new StreamResult(new File("temp/person.xsd"));
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
}
