package run.testSlowDomain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.exception.*;
import jetbrains.sample.testSlowDomain.*;

//LA CLASSE E' DA AGGIUSTARE
public class FindRegressionTests_Test {
	
	FindRegressionTests FR = new FindRegressionTests();
	List<SingleTest> tests;
	List<TimeExecution> times;
	BuildPrj prj;
	@Before
	public void before() {
		prj = new BuildPrj("project");
		tests = new ArrayList<SingleTest>();
	    times = new ArrayList<TimeExecution>();
	}
		
	@Test
	public void regressionNullIfProjectIsEmptyTest() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {
		assertNull(FR.getRegression(prj));
	}
	
	@Test
	public void regressionNullIfProjectHaveNotEnoughElementsTest() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {
		//setta le impostazioni
		//regressionIs(6,6)
		TestsAre("update");
		prj.setTests(tests);
		TimesAre();
		prj.getTests().get(0).setTimes(times);
		assertNull(FR.getRegression(prj));
		TimesAre(20,20,20,20,20,20,20,50,50,50,50,50,50,50);//14 elementi
		assertNull(FR.getRegression(prj));
		FR.setSizeStart(8);
		FR.setSizeEnd(8);
		assertNull(FR.getRegression(prj));
	}
	   //
	@Test
	public void regressionTest() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {
		//setta le impostazioni
		//regressionIs(6,6)
		TestsAre("update");
		prj.setTests(tests);
		FR.setSizeStart(7);
		FR.setSizeEnd(7);
		TimesAre(20,20,20,20,20,20,20,50,50,50,50,50,50,50);//14 elementi
		prj.getTests().get(0).setTimes(times);
		
		assertNotNull(FR.getRegression(prj));
		assertEquals(FR.getRegression(prj).get(0).getNameTest(), "update");
		
	}
	
	@Test
	public void regression2Test() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {
		
		TestsAre("insert", "update","delete", "play");
		prj.setTests(tests);
		FR.setSizeStart(7);
		FR.setSizeEnd(7);
		TimesAre(20,20,20,20,20,20,20,20,20,20,20,20,20,20);//14 elementi
		prj.getTests().get(0).setTimes(times);
		TimesAre(20,20,20,20,20,20,20,50,50,50,50,50,50,50);//14 elementi
		prj.getTests().get(1).setTimes(times);
		TimesAre(20,20,20,20,20,20,20,20,20,20,20,20,20,20);//14 elementi
		prj.getTests().get(2).setTimes(times);
		//TimesAre(34,33,30,33,30,33,32,66,65,66,64,66,65,64);//14 elementi (non riesce a farlo con il boxplotbase
		TimesAre(34,33,30,33,30,33,32,30,30,30,30,66,65,66,64,66,65,64);//14 elementi
		prj.getTests().get(3).setTimes(times);
		
		assertNotNull(FR.getRegression(prj));
		assertEquals(FR.getRegression(prj).size(),2);
		assertEquals(FR.getRegression(prj).get(0).getNameTest(), "update");
		assertEquals(FR.getRegression(prj).get(1).getNameTest(), "play");
	}
    //
    @Test
    public void testFail() {
        fail("manca il test del get e set sample delle regressioni e quello della soglia");

    }
	
	
	private void TestsAre(String ... nameTests) {
		long id=0;
		tests = new ArrayList<SingleTest>();
		for (String i_nameTest : nameTests) {
			tests.add(new SingleTest(i_nameTest,id++));
		}		
	}
	
	private void TimesAre(int... duration){
		times = new ArrayList<TimeExecution>();
		int i=0;
		for (int i_time : duration) {
			times.add(new TimeExecution(i_time, i++));
		}
		
	}
	
	/*
	private List<SingleTest> testsRun;
	List<List<SingleTest>> testsExecutions; //N tests with M Executions
	List<SlowTestDetection> detectionTests;//N detection Tests with N tests
	List<Regression> regression;
	
	@Before
	public void before() {
		handleT=new HandleTests();
		testsExecutions = new ArrayList<List<SingleTest>>(); //N tests with M Executions
		detectionTests = new ArrayList<SlowTestDetection>(); //N detection Tests with N tests
		regression = new ArrayList<Regression>();
	}
	
	
	@Test (expected=IllegalRun.class)
	public void testIllegalRunTestsIsNull() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalRun {
		testsRun = null;
		handleT.Run(testsRun , 10, 10);
 	
	}
	
	@Test (expected=IllegalRun.class)
	public void testEmptyTestsSizeIsZero() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalRun {
		NewRun();
		handleT.Run(testsRun, 10, 10);
  	
	}
	
	@Test 
	public void runSize() throws IllegalRun, IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast{
		NewRun(20, 30, 40, 50);
		handleT.Run(testsRun, 10, 10);
		NewRun(20, 30, 40, 50);
		handleT.Run(testsRun, 10, 10);
		assertEquals(handleT.getRunSize(), 2);
  	
	}
	
	@Test
	public void testGetTestsExecutionsDetectionZero() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalRun {
		//List<List<TestExecution>> testsExecutions = new ArrayList<List<TestExecution>>(); //N tests with M Executions
    	//List<SlowTestDetection> detectionTests = new ArrayList<SlowTestDetection>(); //N detection Tests with N tests
		NewRun(20, 30, 40, 50, 60, 70, 80, 90, 40, 60);
		handleT.Run(testsRun, 10, 10);
		NewRun(20, 30, 40, 50, 60, 70, 80, 90, 40, 60);
		handleT.Run(testsRun, 10, 10);
		
		testsExecutions = handleT.getTestsExecutions();
		detectionTests = handleT.getDetectionTests();
		regression = handleT.getRegression();
		
		assertEquals(testsExecutions.size(), 10);
		assertEquals(detectionTests.size(), 0); //sono meno di 10+10..
		assertEquals(regression.size(), 0);
		
		assertEquals(testsExecutions.get(0).get(0).getDuration(), 20);
		assertEquals(testsExecutions.get(0).get(1).getDuration(), 20);
				
		assertEquals(testsExecutions.get(1).get(0).getDuration(), 30);
		assertEquals(testsExecutions.get(1).get(1).getDuration(), 30);
		
		assertEquals(testsExecutions.get(2).get(0).getDuration(), 40);
		assertEquals(testsExecutions.get(2).get(1).getDuration(), 40);
		
		assertEquals(testsExecutions.get(3).get(0).getDuration(), 50);
		assertEquals(testsExecutions.get(3).get(1).getDuration(), 50);
		
		assertEquals(testsExecutions.get(8).get(0).getDuration(), 40);
		assertEquals(testsExecutions.get(8).get(1).getDuration(), 40);
		
		assertEquals(testsExecutions.get(9).get(0).getDuration(), 60);
		assertEquals(testsExecutions.get(9).get(1).getDuration(), 60);
		
        NewRun(40, 50, 60, 55, 65, 75, 85, 95, 45, 65, 75, 85);
        handleT.Run(testsRun, 10, 10);
        NewRun(24, 34, 44, 54, 64, 74, 84, 94, 44, 60, 75, 80);
        handleT.Run(testsRun, 10, 10);
        
        testsExecutions = handleT.getTestsExecutions();
    	detectionTests = handleT.getDetectionTests();
    	regression = handleT.getRegression();
    	
    	assertEquals(testsExecutions.size(), 12);
		assertEquals(detectionTests.size(), 0); //sono meno di 10+10..
		assertEquals(regression.size(), 0);
		
		assertEquals(testsExecutions.get(0).get(0).getDuration(), 20);
		assertEquals(testsExecutions.get(0).get(1).getDuration(), 20);
		assertEquals(testsExecutions.get(0).get(2).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(3).getDuration(), 24);
				
		assertEquals(testsExecutions.get(4).get(0).getDuration(), 60);
		assertEquals(testsExecutions.get(4).get(1).getDuration(), 60);
		assertEquals(testsExecutions.get(4).get(2).getDuration(), 65);
		assertEquals(testsExecutions.get(4).get(3).getDuration(), 64);
		
		assertEquals(testsExecutions.get(5).get(0).getDuration(), 70);
		assertEquals(testsExecutions.get(5).get(1).getDuration(), 70);
		assertEquals(testsExecutions.get(5).get(2).getDuration(), 75);
		assertEquals(testsExecutions.get(5).get(3).getDuration(), 74);
		
		assertEquals(testsExecutions.get(6).get(0).getDuration(), 80);
		assertEquals(testsExecutions.get(6).get(1).getDuration(), 80);
		assertEquals(testsExecutions.get(6).get(2).getDuration(), 85);
		assertEquals(testsExecutions.get(6).get(3).getDuration(), 84);
		
		assertEquals(testsExecutions.get(8).get(0).getDuration(), 40);
		assertEquals(testsExecutions.get(8).get(1).getDuration(), 40);
		assertEquals(testsExecutions.get(8).get(2).getDuration(), 45);
		assertEquals(testsExecutions.get(8).get(3).getDuration(), 44);
		
		assertEquals(testsExecutions.get(10).get(0).getDuration(), 75);
		assertEquals(testsExecutions.get(10).get(1).getDuration(), 75);
		
		assertEquals(testsExecutions.get(11).get(0).getDuration(), 85);
		assertEquals(testsExecutions.get(11).get(1).getDuration(), 80);
    	
    	assertEquals(detectionTests.size(), 0);
  	
	}
	
	
	@Test
	public void testDetectionsRegression() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalRun {
		
		NewRun(40, 50, 60); //1
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 50, 60); //2
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 50, 60);
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 50, 60);
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 50, 60);
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 50, 60);
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 50, 60); //7
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 150, 60);   
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 150, 60);
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 150, 60); //10
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 150, 60);
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 150, 60);
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 150, 60);
		handleT.Run(testsRun, 7, 7);
		NewRun(40, 150, 60);  //14
		handleT.Run(testsRun, 7, 7);
		
		testsExecutions = handleT.getTestsExecutions();
		detectionTests = handleT.getDetectionTests();
		regression = handleT.getRegression();
		
		assertEquals(testsExecutions.size(), 3);
		assertEquals(detectionTests.size(), 3);
		assertEquals(regression.size(), 3);
		assertEquals(testsExecutions.get(0).size(), 14);
		
		////////////////////////////////////////////////////////////////////////////////
		assertEquals(testsExecutions.get(0).get(0).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(1).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(2).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(3).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(4).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(5).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(6).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(7).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(8).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(9).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(10).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(11).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(12).getDuration(), 40);
		assertEquals(testsExecutions.get(0).get(13).getDuration(), 40);
		
		assertEquals(detectionTests.get(0).getTests().get(0).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(1).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(2).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(3).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(4).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(5).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(6).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(7).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(8).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(9).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(10).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(11).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(12).getDuration(), 40);
		assertEquals(detectionTests.get(0).getTests().get(13).getDuration(), 40);
		
		////////////////////////////////////////////////////////////////////////////////	
		
		assertEquals(testsExecutions.get(1).get(0).getDuration(), 50);
		assertEquals(testsExecutions.get(1).get(1).getDuration(), 50);
		assertEquals(testsExecutions.get(1).get(2).getDuration(), 50);
		assertEquals(testsExecutions.get(1).get(3).getDuration(), 50);
		assertEquals(testsExecutions.get(1).get(4).getDuration(), 50);
		assertEquals(testsExecutions.get(1).get(5).getDuration(), 50);
		assertEquals(testsExecutions.get(1).get(6).getDuration(), 50);
		assertEquals(testsExecutions.get(1).get(7).getDuration(), 150);
		assertEquals(testsExecutions.get(1).get(8).getDuration(), 150);
		assertEquals(testsExecutions.get(1).get(9).getDuration(), 150);
		assertEquals(testsExecutions.get(1).get(10).getDuration(), 150);
		assertEquals(testsExecutions.get(1).get(11).getDuration(), 150);
		assertEquals(testsExecutions.get(1).get(12).getDuration(), 150);
		assertEquals(testsExecutions.get(1).get(13).getDuration(), 150);
		
		assertEquals(detectionTests.get(1).getTests().get(0).getDuration(), 50);
		assertEquals(detectionTests.get(1).getTests().get(1).getDuration(), 50);
		assertEquals(detectionTests.get(1).getTests().get(2).getDuration(), 50);
		assertEquals(detectionTests.get(1).getTests().get(3).getDuration(), 50);
		assertEquals(detectionTests.get(1).getTests().get(4).getDuration(), 50);
		assertEquals(detectionTests.get(1).getTests().get(5).getDuration(), 50);
		assertEquals(detectionTests.get(1).getTests().get(6).getDuration(), 50);
		assertEquals(detectionTests.get(1).getTests().get(7).getDuration(), 150);
		assertEquals(detectionTests.get(1).getTests().get(8).getDuration(), 150);
		assertEquals(detectionTests.get(1).getTests().get(9).getDuration(), 150);
		assertEquals(detectionTests.get(1).getTests().get(10).getDuration(), 150);
		assertEquals(detectionTests.get(1).getTests().get(11).getDuration(), 150);
		assertEquals(detectionTests.get(1).getTests().get(12).getDuration(), 150);
		assertEquals(detectionTests.get(1).getTests().get(13).getDuration(), 150);
		
		////////////////////////////////////////////////////////////////////////////////
		
		assertEquals(testsExecutions.get(2).get(0).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(1).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(2).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(3).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(4).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(5).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(6).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(7).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(8).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(9).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(10).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(11).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(12).getDuration(), 60);
		assertEquals(testsExecutions.get(2).get(13).getDuration(), 60);
		
		assertEquals(detectionTests.get(2).getTests().get(0).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(1).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(2).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(3).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(4).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(5).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(6).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(7).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(8).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(9).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(10).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(11).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(12).getDuration(), 60);
		assertEquals(detectionTests.get(2).getTests().get(13).getDuration(), 60);
		
		////////////////////////////////////////////////////////////////////////////////
		
		
		assertNull(regression.get(0));
		assertNotNull(regression.get(1));
		assertNull(regression.get(2));
		
	}
	
	@Test
	public void testDetectionsRegression2() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalRun {
		for(int i=0; i<100; i++){
			handleT=new HandleTests();
			testsExecutions = new ArrayList<List<SingleTest>>(); //N tests with M Executions
			detectionTests = new ArrayList<SlowTestDetection>(); //N detection Tests with N tests
			regression = new ArrayList<Regression>();
			NewRun(40, 50, 60, 70); //1
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 50, 60, 70); //2
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 50, 60, 70);
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 50, 60, 70);
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 50, 60, 70);
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 50, 60, 70);
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 50, 60, 70);
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 150, 60, 90);   
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 150, 60, 90);
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 150, 60, 90); //10
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 150, 60, 90);
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 150, 60, 90);
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 150, 60, 90);
			handleT.Run(testsRun, 7, 7);
			NewRun(40, 150, 60, 90);  //14
			handleT.Run(testsRun, 7, 7);
			
			testsExecutions = handleT.getTestsExecutions();
			detectionTests = handleT.getDetectionTests();
			regression = handleT.getRegression();
			
			assertEquals(testsExecutions.size(), 4);
			assertEquals(detectionTests.size(), 4);
			assertEquals(regression.size(), 4);
				
			assertNull(regression.get(0));
			assertNotNull(regression.get(1));
			assertNull(regression.get(2));
			assertNotNull(regression.get(3));
		}
	}
	
	@Test
	public void missTests(){
		fail("manca la simulazione della cancellazione e aggiunta di nuovi tests e diverse proprieta dei tests");
		//progetto, nome, build, eccetera!
	}
	
	private void NewRun(int... duration){
		testsRun = new ArrayList<SingleTest>();
		for (int i_time : duration) {
			testsRun.add(new SingleTest(i_time));
		}
	}
	*/
}

