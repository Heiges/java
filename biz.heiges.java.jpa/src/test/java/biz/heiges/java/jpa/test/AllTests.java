package biz.heiges.java.jpa.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	BaseDAOTestCrud.class,	
	BaseBAOTestMissingClass.class, 
	BaseDAOTestMissingEntityManager.class,
	BaseDAOTestCRUDwithAutoCommitTrue.class,
	BaseDAOTestCRUDwithAutoCommitTrueCallingCommit.class,
	BaseDAOTestCRUDwithAutoCommitFalse.class,
	BaseDAOTestMockedEntityManagerExceptionRethrown.class,
	BaseDAOTestRollback.class
})
public class AllTests {

}
