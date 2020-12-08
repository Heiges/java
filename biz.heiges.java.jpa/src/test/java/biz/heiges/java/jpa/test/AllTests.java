package biz.heiges.java.jpa.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	BaseBAOTestMissingClass.class, 
	BaseDAOTestCRUDwithAutoCommit.class,
	BaseDAOTestMissingEnttityManager.class,
	BaseDAOTestRollback.class
})
public class AllTests {

}
