/*package run.testSlowDomain;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.exception.*;
import jetbrains.sample.testSlowDomain.*;

public class InMemorySimulationTestsRepositoryTest {
	InMemoryTestsRepository repository=new InMemoryTestsRepository();
	List<BuildPrj> projects;
	List<SingleTest> tests;
	List<TimeExecution> times;
	
	@Before
	public void before(){
		repository = new InMemoryTestsRepository();
		projects = new ArrayList<BuildPrj>();
		tests = new ArrayList<SingleTest>();
	    times = new ArrayList<TimeExecution>();
	}
	
	@Test
	public void isEmptyInitiallyTest() {
		assertEquals(0, repository.size());
		assertEquals(0, repository.getProjects().size());
	}
	
	@Test
	public void setGetProjectsEmpyTest() {
		ProjectsAre();
		assertEquals(0, repository.size());
		assertEquals(0, repository.getProjects().size());
		assertNull(repository.getProjectByName("prova"));
	}
	
	@Test
	public void setGetProjectsJustNamesTest() {
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		assertEquals(3, repository.size());
		assertEquals("prova", repository.getProjectByName("prova").getName());
		assertEquals("invoice", repository.getProjects().get(1).getName());
		assertEquals("puzzle", repository.getProjects().get(2).getName());
		assertEquals(0, repository.getProjects().get(0).getTests().size());
		assertEquals(0, repository.getProjects().get(1).getTests().size());
		assertEquals(0, repository.getProjects().get(2).getTests().size());
	}
	
	@Test
	public void setGetTestsEmptyGetAllTestsTest() {
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		TestsAre();
		repository.setTestsByProject("prova",tests);
		TestsAre();
		repository.setTestsByProject("invoice",tests);
		assertEquals(repository.getAllTestsByProject("puzzle").size(), 0);
		assertEquals(0, repository.getProjectByName("prova").size());
		assertEquals(0, repository.getAllTestsByProject("prova").size());
		assertEquals(0, repository.getProjectByName("puzzle").size());
		assertNull(repository.getAllTestsByProject("Non esiste"));
	}
		
	@Test
	public void setGetTestsJustNameTest() {
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		
		TestsAre("insert","update","delete");
		repository.setTestsByProject("puzzle",tests);
				
		assertEquals(0, repository.getProjectByName("prova").size());
		assertEquals(0, repository.getProjectByName("invoice").size());
		assertEquals(3, repository.getProjectByName("puzzle").size());
		assertEquals(0, repository.getAllTestsByProject("prova").size());
		assertEquals(0, repository.getAllTestsByProject("invoice").size());
		assertEquals(3, repository.getAllTestsByProject("puzzle").size());
		assertEquals("insert", repository.getProjectByName("puzzle").getTests().get(0).getName());
		assertEquals("update", repository.getProjectByName("puzzle").getTests().get(1).getName());
		assertEquals("delete", repository.getProjectByName("puzzle").getTests().get(2).getName());
		assertEquals("insert", repository.getAllTestsByProject("puzzle").get(0).getName());
		assertEquals("update", repository.getAllTestsByProject("puzzle").get(1).getName());
		assertEquals("delete", repository.getAllTestsByProject("puzzle").get(2).getName());
		
		
	}
	
	@Test
	public void getSingleTestNullByNamesTest(){
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		TestsAre("insert","update","delete");
		repository.setTestsByProject("puzzle",tests);
		assertNull(repository.getSingleTestByNames("puzzle", "not exist"));
	}
	
	@Test
	public void getSingleTestByNamesTest(){
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		TestsAre("insert","update","delete");
		repository.setTestsByProject("puzzle",tests);
		assertEquals(repository.getSingleTestByNames("puzzle", "insert"),repository.getAllTestsByProject("puzzle").get(0));
		assertEquals(repository.getSingleTestByNames("puzzle", "update"),repository.getAllTestsByProject("puzzle").get(1));
		assertEquals(repository.getSingleTestByNames("puzzle", "delete"),repository.getAllTestsByProject("puzzle").get(2));
	}
	
	
	@Test
	public void setGetTimesEmptyTest() {
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		TestsAre("insert","update","delete");
		repository.setTestsByProject("invoice",tests);
		TimesAre();
		repository.setTimesTestByProject("invoice", "insert", times);
		assertEquals(repository.getTimesTestByProject("invoice", "insert").size(),0);
	}
	
	
	@Test
	public void setGetTimesTest() {
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		TestsAre("insert","update","delete");
		repository.setTestsByProject("invoice",tests);
		TimesAre(20, 30, 50, 60, 70, 90);
		repository.setTimesTestByProject("invoice", "insert", times);
		TimesAre(20, 50, 50);
		repository.setTimesTestByProject("invoice", "update", times);
		TimesAre(90, 40, 50, 90, 80, 60);
		repository.setTimesTestByProject("invoice", "delete", times);
		
		assertNull(repository.getTimesTestByProject("Non esiste","insert"));
		assertNull(repository.getTimesTestByProject("invoice","Non esiste"));
		assertNull(repository.getTimesTestByProject("prova","insert"));
		
		assertEquals(repository.getTimesTestByProject("invoice", "insert"), repository.getAllTestsByProject("invoice").get(0).getTimes());
		assertEquals(repository.getTimesTestByProject("invoice", "insert").size(),6);
		assertEquals(repository.getTimesTestByProject("invoice", "insert").get(0).getTime(),20);
		assertEquals(repository.getTimesTestByProject("invoice", "insert").get(1).getTime(),30);
		assertEquals(repository.getTimesTestByProject("invoice", "insert").get(2).getTime(),50);
		assertEquals(repository.getTimesTestByProject("invoice", "insert").get(3).getTime(),60);
		assertEquals(repository.getTimesTestByProject("invoice", "insert").get(4).getTime(),70);
		assertEquals(repository.getTimesTestByProject("invoice", "insert").get(5).getTime(),90);
		
		assertEquals(repository.getTimesTestByProject("invoice", "update").get(0).getTime(),20);
		assertEquals(repository.getTimesTestByProject("invoice", "update").get(1).getTime(),50);
		assertEquals(repository.getTimesTestByProject("invoice", "update").get(2).getTime(),50);
		
		assertEquals(repository.getTimesTestByProject("invoice", "delete").get(0).getTime(),90);
		assertEquals(repository.getTimesTestByProject("invoice", "delete").get(1).getTime(),40);
		assertEquals(repository.getTimesTestByProject("invoice", "delete").get(2).getTime(),50);
		assertEquals(repository.getTimesTestByProject("invoice", "delete").get(3).getTime(),90);
		assertEquals(repository.getTimesTestByProject("invoice", "delete").get(4).getTime(),80);
		assertEquals(repository.getTimesTestByProject("invoice", "delete").get(5).getTime(),60);
		
	}
	
	@Test
	public void addProjectNullSizeIsZeroTest() {
        repository.addProject(null);
        assertEquals(repository.size(),0); 
	}
	
	@Test
	public void addProjectTest() {
		ProjectsAre("prova","puzzle","game");
        repository.addProject(projects.get(0));
        assertEquals(repository.size(),1);
        repository.addProject(projects.get(1));
        assertEquals(repository.size(),2);
        repository.addProject(projects.get(2));
        assertEquals(repository.size(),3);
        assertEquals(repository.getProjects().get(0).getName(),"prova");
        assertEquals(repository.getProjects().get(1).getName(),"puzzle");
        assertEquals(repository.getProjects().get(2).getName(),"game");   
	}
	
	@Test
	public void removeProjectTest() {
		ProjectsAre("prova","puzzle","game");
        repository.setProjects(projects);
        assertEquals(repository.size(),3);
        repository.removeProject("prova");
        assertEquals(repository.size(),2);
        assertEquals(repository.getProjects().get(0).getName(),"puzzle");
        assertNotNull(repository.getProjectByName("game"));
        assertNull(repository.getProjectByName("prova"));
        repository.removeProject("game");
        assertEquals(repository.size(),1);
        assertNull(repository.getProjectByName("game"));
        repository.removeProject("puzzle");
        assertEquals(repository.size(),0);
        assertNull(repository.getProjectByName("puzzle"));
        
	}
	
	@Test
	public void addTestNullSizeIsZeroTest() {
		ProjectsAre("prova","puzzle","game");
        repository.setProjects(projects);
        repository.addTestByProject("prova", null);
        assertEquals(repository.getAllTestsByProject("prova").size(),0);
		
	}
	
	@Test
	public void addTest_Test() {
		ProjectsAre("prova","puzzle","game");
        repository.setProjects(projects);
        repository.addTestByProject("prova", new SingleTest("insert",0));
        assertEquals(repository.getAllTestsByProject("prova").size(),1);
        repository.addTestByProject("prova", new SingleTest("update",1));
        assertEquals(repository.getAllTestsByProject("prova").size(),2);
        repository.addTestByProject("prova", new SingleTest("delete",2));
        assertEquals(repository.getAllTestsByProject("prova").size(),3);
        assertEquals(repository.getAllTestsByProject("prova").get(0).getName(),"insert");
        assertEquals(repository.getAllTestsByProject("prova").get(1).getName(),"update");
        assertEquals(repository.getAllTestsByProject("prova").get(2).getName(),"delete");
        
        assertEquals(repository.getAllTestsByProject("puzzle").size(),0);
        assertEquals(repository.getAllTestsByProject("game").size(),0);
        repository.addTestByProject("game", new SingleTest("play",3));
        assertEquals(repository.getAllTestsByProject("game").size(),1);
	}
	
	@Test
	public void removeTest_Test() {
		ProjectsAre("prova","puzzle","game");
        repository.setProjects(projects);
        TestsAre("insert", "update", "delete");
        repository.setTestsByProject("puzzle", tests);
        assertEquals(repository.getAllTestsByProject("puzzle").size(),3);
        repository.removeTestByName("puzzle", "update");
        assertEquals(repository.getAllTestsByProject("puzzle").size(),2);
        repository.removeTestByName("puzzle", "delete");
        assertEquals(repository.getAllTestsByProject("puzzle").size(),1);
        repository.removeTestByName("puzzle", "insert");
        assertEquals(repository.getAllTestsByProject("puzzle").size(),0);
        assertEquals(repository.getProjectByName("prova").getTests().size(),0);
	}
	
	@Test
	public void addTimeEmptyByNamesTest() {
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		TestsAre("insert","update","delete");
		repository.setTestsByProject("puzzle", tests);
		repository.addTimeByNames("puzzle", "update", null);
		assertEquals(repository.getTimesTestByProject("puzzle", "update").size(),0);
		repository.addTimeByNames("puzzle", "non esiste", new TimeExecution(20,2));
		assertNull(repository.getTimesTestByProject("puzzle", "non esiste"));
		repository.addTimeByNames("non esiste", "non esiste", new TimeExecution(20,2));
		assertNull(repository.getTimesTestByProject("non esiste", "non esiste"));
	}
	
	
	@Test
	public void addTimeByNamesTest() {
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		TestsAre("insert","update","delete");
		repository.setTestsByProject("puzzle", tests);
		repository.addTimeByNames("puzzle", "update", new TimeExecution(20,1));
		assertEquals(repository.getTimesTestByProject("puzzle", "update").size(),1);
		repository.addTimeByNames("puzzle", "update", new TimeExecution(40,2));
		repository.addTimeByNames("puzzle", "update", new TimeExecution(50,3));
		assertEquals(repository.getTimesTestByProject("puzzle", "update").size(),3);
		assertEquals(repository.getTimesTestByProject("puzzle", "update").get(0).getTime(),20);
		assertEquals(repository.getTimesTestByProject("puzzle", "update").get(1).getTime(),40);
		assertEquals(repository.getTimesTestByProject("puzzle", "update").get(2).getTime(),50);
		
	}
	
	@Test
	public void removeTimeByNamesIdRunTest() {
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		TestsAre("insert","update","delete");
		repository.setTestsByProject("puzzle", tests);
		TimesAre(20,30,40,10,60,70);
		repository.setTimesTestByProject("puzzle", "delete", times);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").size(),6);
		repository.removeTimeByNamesIdRun("puzzle", "delete", 4);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").size(),5);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").get(0).getTime(),20);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").get(1).getTime(),30);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").get(2).getTime(),40);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").get(3).getTime(),10);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").get(4).getTime(),70);
		
		repository.getTimesTestByProject("puzzle", "delete").get(4).setRunId(99);
		repository.removeTimeByNamesIdRun("puzzle", "delete", 99);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").size(),4);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").get(0).getTime(),20);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").get(1).getTime(),30);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").get(2).getTime(),40);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").get(3).getTime(),10);
		repository.removeTimeByNamesIdRun("puzzle", "delete", 0);
		repository.removeTimeByNamesIdRun("puzzle", "delete", 1);
		repository.removeTimeByNamesIdRun("puzzle", "delete", 2);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").get(0).getTime(),10);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").size(),1);
		repository.removeTimeByNamesIdRun("puzzle", "delete", -10);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").size(),1);
		repository.removeTimeByNamesIdRun("puzzle", "delete", 3);
		assertEquals(repository.getTimesTestByProject("puzzle", "delete").size(),0);
	}
	
	@Test
	public void getTestsRegressionByProjectTest() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		TestsAre("insert","update","delete");
		repository.setTestsByProject("puzzle", tests);
		TimesAre(20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20); //20 elementi
		repository.setTimesTestByProject("puzzle", "delete", times);
		TimesAre(20,20,20,20,20,20,20,20,20,20,30,30,30,30,30,30,30,30,30,30); //20 elementi
		repository.setTimesTestByProject("puzzle", "update", times);
		TimesAre(20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20); //20 elementi
		repository.setTimesTestByProject("puzzle", "insert", times);
		
		
		assertEquals(repository.getAllTestsByProject("puzzle").size(),3);
		assertEquals(repository.getTestsRegressionByProject("puzzle", 10, 10).size(),1);
		assertEquals(repository.getTestsRegressionByProject("puzzle", 10, 10).get(0).getNameTest(), "update");
		
		repository.addTestByProject("puzzle", new SingleTest("play",1));
		TimesAre(20,21,20,21,21,21,21,21,21,20,36,35,36,35,36,35,36,35,36,35); //20 elementi
		repository.setTimesTestByProject("puzzle", "play", times);
		assertEquals(repository.getTestsRegressionByProject("puzzle", 10, 10).size(),2);
		assertEquals(repository.getTestsRegressionByProject("puzzle", 10, 10).get(0).getNameTest(), "update");
		assertEquals(repository.getTestsRegressionByProject("puzzle", 10, 10).get(1).getNameTest(), "play");
		
	}
	
	@Test
	public void getTestsRegressionValueDefaultByProjectTest() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {
		ProjectsAre("prova","invoice","puzzle");
		repository.setProjects(projects);
		TestsAre("insert","update","delete");
		repository.setTestsByProject("puzzle", tests);
		TimesAre(20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20); //20 elementi
		repository.setTimesTestByProject("puzzle", "delete", times);
		TimesAre(20,20,20,20,20,20,20,20,20,20,30,30,30,30,30,30,30,30,30,30); //20 elementi
		repository.setTimesTestByProject("puzzle", "update", times);
		TimesAre(20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20); //20 elementi
		repository.setTimesTestByProject("puzzle", "insert", times);
		//value default is 15,15 
		
		assertEquals(repository.getAllTestsByProject("puzzle").size(),3);
		assertNull(repository.getTestsRegressionByProject("puzzle"));
		TimesAre(20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30); //30 elementi
		repository.setTimesTestByProject("puzzle", "update", times);
		
		assertNotNull(repository.getTestsRegressionByProject("puzzle"));
		assertEquals(repository.getTestsRegressionByProject("puzzle").get(0).getNameTest(), "update");		
		
	}
	
	
	@Test
	public void simulation_FinalTest() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {
		ProjectsAre("invoice 8 :: invoice","game","puzzle");
		repository.setProjects(projects);
		TestsAre("db.insert","db.isEmptyInitially", "db.addOneSnack", "getAllSnacks", "ToString");
		repository.setTestsByProject("invoice 8 :: invoice", tests);
		repository.addTestByProject("invoice 8 :: invoice", new SingleTest("Fake",1));
		assertEquals(repository.getAllTestsByProject("invoice 8 :: invoice").size(),6);
		
		TimesAre(916,540,567,716,653,669,1106,1813,1411,818,494,1121,1244,1078,480,1738,582,445,1794,469); //20 elementi
		repository.setTimesTestByProject("invoice 8 :: invoice", "db.insert", times);
		assertNull(repository.getTestsRegressionByProject("invoice 8 :: invoice",10,10));
		TimesAre(359,378,306,346,628,347,368,477,581,478,319,307,322,607,285,321,327,340,904); //20 elementi
		repository.setTimesTestByProject("invoice 8 :: invoice", "db.isEmptyInitially", times);
		assertNull(repository.getTestsRegressionByProject("invoice 8 :: invoice",10,10));
		repository.addTimeByNames("invoice 8 :: invoice", "db.addOneSnack", new TimeExecution(20,1));
		repository.addTimeByNames("invoice 8 :: invoice", "db.addOneSnack", new TimeExecution(20,2));
		repository.addTimeByNames("invoice 8 :: invoice", "db.addOneSnack", new TimeExecution(20,3));
		TimesAre(20,20,20,20,20,20,20,20,20,20,30,30,30,30,30,30,30,30,30,30); //20 elementi
		repository.setTimesTestByProject("invoice 8 :: invoice", "ToString", times);
		repository.setTimesTestByProject("invoice 8 :: invoice", "Fake", times);
		
		
		assertNotNull(repository.getTestsRegressionByProject("invoice 8 :: invoice",10,10));
		assertEquals(repository.getTestsRegressionByProject("invoice 8 :: invoice",10,10).size(),2);
		assertEquals(repository.getTestsRegressionByProject("invoice 8 :: invoice",10,10).get(0).getNameTest(),"ToString");
		assertEquals(repository.getTestsRegressionByProject("invoice 8 :: invoice",10,10).get(1).getNameTest(),"Fake");
		repository.removeTestByName("invoice 8 :: invoice", "Fake");
		assertEquals(repository.getTestsRegressionByProject("invoice 8 :: invoice",10,10).size(),1);
		assertEquals(repository.getTestsRegressionByProject("invoice 8 :: invoice",10,10).get(0).getNameTest(),"ToString");
		
		assertNull(repository.getTestsRegressionByProject("invoice 8 :: invoice"));
		repository.addTimeByNames("invoice 8 :: invoice", "ToString", new TimeExecution(30,21));
		repository.addTimeByNames("invoice 8 :: invoice", "ToString", new TimeExecution(30,22));
		repository.addTimeByNames("invoice 8 :: invoice", "ToString", new TimeExecution(30,23));
		repository.addTimeByNames("invoice 8 :: invoice", "ToString", new TimeExecution(30,24));
		repository.addTimeByNames("invoice 8 :: invoice", "ToString", new TimeExecution(30,25));
		repository.addTimeByNames("invoice 8 :: invoice", "ToString", new TimeExecution(30,26));
		repository.addTimeByNames("invoice 8 :: invoice", "ToString", new TimeExecution(30,27));
		repository.addTimeByNames("invoice 8 :: invoice", "ToString", new TimeExecution(30,28));
		repository.addTimeByNames("invoice 8 :: invoice", "ToString", new TimeExecution(30,29));
		repository.addTimeByNames("invoice 8 :: invoice", "ToString", new TimeExecution(30,30));
		assertNotNull(repository.getTestsRegressionByProject("invoice 8 :: invoice"));
		
	}
		
	private void ProjectsAre(String ... nameProjects) {
		for (String i_nameProject : nameProjects) {
			projects.add(new BuildPrj(i_nameProject));
		}		
	}
	
	private void TestsAre(String ... nameTests) {
		tests = new ArrayList<SingleTest>();
		for (String i_nameTest : nameTests) {
			tests.add(new SingleTest(i_nameTest,1));
		}		
	}
	
	private void TimesAre(int... duration){
		times = new ArrayList<TimeExecution>();
		int i=0;
		for (int i_time : duration) {
			times.add(new TimeExecution(i_time, i++));
		}
		
	}
	

	
	
}
                                                               */