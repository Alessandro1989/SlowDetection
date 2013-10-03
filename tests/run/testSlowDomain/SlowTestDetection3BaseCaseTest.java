package run.testSlowDomain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.exception.*;
import jetbrains.sample.statistics.*;
import jetbrains.sample.testSlowDomain.*;

public class SlowTestDetection3BaseCaseTest{

	private List<TimeExecution> testExecutions;
	private SlowTestDetection detection;
	private static final double DELTA = 0.1;
	
	@Before
	public void before() {
		testExecutions = new ArrayList<TimeExecution>();
		detection = new SlowTestDetection(testExecutions);
	}

	@Test
	public void testGetTestsExecution() throws Exception {
		timesAre(20, 20, 30);
		detection = new SlowTestDetection(testExecutions);
		assertEquals(detection.getTests(), testExecutions);
	}
	//Empty
	@Test
	public void testNullRegressionDetectedEmpty() throws Exception {
		assertNull(detection.findRegressionId(10,10));
	}
	
	@Test
	public void testNullRegressionDetectedUntilTotalExecutions() throws Exception {

		timesAre(20, 20, 20, 20, 20, 20, 20, 20, 20, 20); //sample: 10 elements
		assertNull(detection.findRegressionId(10,5));
		timesAre(100, 100, 100, 100);
		assertNull(detection.findRegressionId(10,5)); //non abbiamo abbastanza elementi
	}
	
		
	@Test
	public void testRegressionExample1() throws Exception {
		
		System.out.println("last Test");
		timesAre(20, 25, 35, 25, 20, 25, 35, 25, 20, 23); //sample: 10 elements
		timesAre(20, 25, 35, 25, 50, 60, 50, 45, 50, 55, 50);
		
		assertNotNull(detection.findRegressionId(10,5));
		//assertEquals(14, detection.findRegressionId(10,5).getId()); //sarebbe 4 
	}
	
	
	
	@Test
	public void testRegressionExample2() throws Exception {
		timesAre(20, 20, 22, 19, 20, 22, 20, 21, 18, 21); //sample: 10 elements
		timesAre(20, 22, 29, 30, 35, 35, 36, 32);
		assertNotNull(detection.findRegressionId(10,5)); 
	}
	
	@Test
	public void testRegressionSampleBaseChange() throws Exception {
		timesAre(20, 20, 22, 19, 20, 22, 20, 21, 18, 21); //sample: 10 elements
		timesAre(15, 25, 15, 25, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5); //si è abbassata per 24 elementi..
		timesAre(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5); 
		timesAre(15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15);
		
		assertNotNull(detection.findRegressionId(10,5));
	}
	
	@Test
	public void testRegressionSampleBaseChange2NoRegression() throws Exception {
		timesAre(20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
		timesAre(20, 15, 15, 15, 20, 18, 17, 16, 18, 17, 30, 25, 17, 18, 25, 19);
		//detection.findRegressionId(7,5);
		assertNull(detection.findRegressionId(7,5));
	}
	
	@Test
	public void testRegressionSampleBaseChange3Regression() throws Exception {
		timesAre(20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
		timesAre(20, 15, 15, 15, 15, 18, 17, 16, 18, 17, 30, 25, 18, 23, 25, 24);
		//detection.findRegressionId(7,5);
		assertNotNull(detection.findRegressionId(7,5));
	}
	
	@Test
	public void test() {
		fail("position regression");
	}
	
	
	/*
	@Test
	public void testSpeedProgram() throws Exception {
		for(int i=0;i<10000;i++){
		testExecutions = new ArrayList<TestExecution>();
		detection = new SlowTestDetection(testExecutions);
		testsAre(20, 20, 22, 19, 20, 22, 20, 21, 18, 21); //sample: 10 elements
		testsAre(20, 22, 29, 30, 35, 35);
		detection.findRegressionId(10,5);
		System.out.println(i);
		} 
	}*/
	
	
	
	
	  
	private void timesAre(int... duration){
		int i=0;
		for (int i_time : duration) {
			testExecutions.add(new TimeExecution(i_time, i++));
		}
		
	}
	
	//test Performance: test di 100mila casi, ok!
	 
	 
}
