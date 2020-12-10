package biz.heiges.java.jpa.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.Test;

import biz.heiges.java.jpa.BaseDAO;
import biz.heiges.java.jpa.Database;
import biz.heiges.java.jpa.test.entities.ParentEntityDAO;

public class DbunitTest {
	
	private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
	private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	
	@Test
	public void test() throws Exception {
		
		RunScript.execute("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "", new File(this.getClass().getResource("/sql/create.sql").toURI()).toString(), null, false);

		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(this.getClass().getResource("/dbunit/dataset.xml").toURI()));

		IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();

		try (Database databaseWithoutCreate = new Database("entitiesWithoutCreate")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(databaseWithoutCreate.getEntityManager());
			assertEquals("aValueForEntityWithID2_andDBUnit", dao.read(Long.parseLong("3")).getaSimpleCharValue());
		}			
	}
}
