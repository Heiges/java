package biz.heiges.java.jpa.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import biz.heiges.java.jpa.BaseDAO;
import biz.heiges.java.jpa.Database;
import biz.heiges.java.jpa.test.entities.ParentEntityDAO;

public class DbunitTest extends DataSourceBasedDBTestCase  {
	
	@Test
	public void testDatabaseHasBeenSetup() throws Exception {
		
		try (Database databaseWithoutCreate = new Database("entitiesWithoutCreate")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(databaseWithoutCreate.getEntityManager());
			assertEquals("aValueForEntityWithID0_andDBUnit", dao.read(Long.parseLong("0")).getaSimpleCharValue());
			assertEquals("aValueForEntityWithID1_andDBUnit", dao.read(Long.parseLong("1")).getaSimpleCharValue());
			assertEquals("aValueForEntityWithID2_andDBUnit", dao.read(Long.parseLong("2")).getaSimpleCharValue());
		}		
		
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("PARENT_ENTITIES");

		FlatXmlDataSet expectedData = readDataset("/dbunit/expected_dataset.xml");
		ITable expectedTable = expectedData.getTable("PARENT_ENTITIES");
		
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void testDatabaseAfterAddingARow() throws Exception {
		
		try (Database databaseWithoutCreate = new Database("entitiesWithoutCreate")) {
			BaseDAO<ParentEntityDAO> dao = new BaseDAO<ParentEntityDAO>();
			dao.setClazz(ParentEntityDAO.class);
			dao.setEntityManager(databaseWithoutCreate.getEntityManager());
			dao.create(new ParentEntityDAO("another Value"));

			assertEquals("aValueForEntityWithID0_andDBUnit", dao.read(Long.parseLong("0")).getaSimpleCharValue());
			assertEquals("aValueForEntityWithID1_andDBUnit", dao.read(Long.parseLong("1")).getaSimpleCharValue());
			assertEquals("aValueForEntityWithID2_andDBUnit", dao.read(Long.parseLong("2")).getaSimpleCharValue());
			assertEquals("another Value", dao.read(Long.parseLong("3")).getaSimpleCharValue());
			assertNull(dao.read(Long.parseLong("4")));
		}
		
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("PARENT_ENTITIES");

		FlatXmlDataSet expectedData = readDataset("/dbunit/expected_dataset_afterCreate.xml");
		ITable expectedTable = expectedData.getTable("PARENT_ENTITIES");
		 
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	private FlatXmlDataSet readDataset(String initialData) throws MalformedURLException, DataSetException, URISyntaxException, ClassNotFoundException, Exception {
		return new FlatXmlDataSetBuilder().build(new File(this.getClass().getResource(initialData).toURI()));
	}

	@Override
	protected DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:/dbunit/create.sql'");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        return dataSource;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return readDataset("/dbunit/dataset.xml");
	}	
}
