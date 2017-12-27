package biz.heiges.java.xjc.examples;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.xml.sax.InputSource;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;

public class XJCGenerateClassesFromSchema {

	@Test
	public void test() {
        SchemaCompiler sc = XJC.createSchemaCompiler();
        sc.forcePackageName("temp.biz.heiges.generated");
        File schemaFile = new File("testcases/person.xsd");
        InputSource is = new InputSource(schemaFile.toURI().toString());
        sc.parseSchema(is);
        S2JJAXBModel model = sc.bind();
        JCodeModel jCodeModel = model.generateCode(null, null);
        try {
			jCodeModel.build(new File("."));
		} catch (IOException e) {
			fail();
		}
	}

}
