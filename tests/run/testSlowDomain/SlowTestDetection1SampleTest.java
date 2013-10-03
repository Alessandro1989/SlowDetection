package run.testSlowDomain;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.exception.*;
import jetbrains.sample.statistic.*;
import jetbrains.sample.testSlowDomain.*;

public class SlowTestDetection1SampleTest {


	private List<TimeExecution> testExecutions;
	private SlowTestDetection detection;
	private static final double DELTA = 0.1; //variazione consentita
	
	@Before
	public void before() {
		testExecutions = new ArrayList<TimeExecution>();
		detection = new SlowTestDetection(testExecutions);
	}
	
	@Test
	public void SampleEmpty() {
		assertNull(getSampleBase());
		assertNull(getSampleLast());
	}
	
	
	@Test (expected=IllegalExecutionsSampleBasis.class)
	public void rejectsInvalidExecutionsSampleBasis() throws Exception {
		testsAre(20, 20, 20, 20); 
		detection.findRegressionId(0,4);
	}
	@Test (expected=IllegalExecutionsSampleLast.class)
	public void rejectsInvalidExecutionsSampleLast() throws Exception {
		testsAre(20, 20, 20, 20); 
		detection.findRegressionId(7,0);
	}
	
	@Test
	public void SampleLastandBaseEmptyWithFewExecutionsTest() throws Exception{
		detection.findRegressionId(10,5);
		assertEquals(getSampleLast().size(),0);
		assertEquals(getSampleBase().size(),0);
		
		testsAre(20, 20, 20, 20, 20, 20, 20, 20, 20, 20);//10
		detection.findRegressionId(10,5);
		assertEquals(getSampleLast().size(),0);
		assertEquals(getSampleBase().size(),0);
		testsAre(20, 20, 20);
		detection.findRegressionId(10,5);
		assertEquals(getSampleLast().size(),0);
		assertEquals(getSampleBase().size(),0);
		testsAre(20);//14
		detection.findRegressionId(10,5);
		assertEquals(getSampleLast().size(),0);
		assertEquals(getSampleBase().size(),0);
		testsAre(20);//15
		detection.findRegressionId(10,5);
		assertEquals(getSampleBase().size(), 10);
		
		
		
	}
	
	@Test
	public void SameElementsTest() throws Exception{
		testsAre(20);
		detection.findRegressionId(10,5);
		assertEquals(getSampleBase().size(),0);
		assertEquals(getSampleLast().size(),0);
		testsAre(20);
		detection.findRegressionId(10,5);
		assertEquals(getSampleBase().size(),0);
		assertEquals(getSampleLast().size(),0);
		testsAre(20, 20, 20, 20, 20, 20, 20, 20); //10 elementi
		detection.findRegressionId(10,5);
		assertEquals(getSampleBase().size(),0);
		assertEquals(getSampleLast().size(),0);
		testsAre(20, 20, 20, 20); //14 elementi, ancora null
		detection.findRegressionId(10,5);
		assertEquals(getSampleLast().size(),0);
		assertEquals(getSampleBase().size(),0);
		testsAre(20); //15 elementi
		detection.findRegressionId(10,5);
		assertEquals(getSampleBase().size(),10);
		assertEquals(getSampleLast().size(),5);
		assertEquals(15, this.testExecutions.size());
		assertEquals(getSampleBase().size(),10);
		assertEquals(getSampleBase().get(0).getTime(), 20);
		assertEquals(getSampleBase().get(1).getTime(), 20);
		assertEquals(getSampleBase().get(9).getTime(), 20);
		assertEquals(getSampleLast().get(0).getTime(), 20);
		
	}
	
	@Test
	public void SampleLastWithSameElementsTest() throws Exception{
		
		testsAre(20, 20, 20, 20, 20, 20, 20, 20, 20, 20); //10 elementi
		detection.findRegressionId(10,5);
		assertEquals(getSampleLast().size(),0);
		testsAre(20, 20, 20, 20, 20); //15 elementi
		detection.findRegressionId(10,5);
		assertEquals(getSampleLast().size(),5);
		assertEquals(getSampleLast().get(0).getTime(), 20);
		assertEquals(getSampleLast().get(1).getTime(), 20);
		assertEquals(getSampleLast().get(4).getTime(), 20);
		assertEquals(getSampleBase().get(0).getTime(), 20);
		}

	
	@Test
	public void SampleBaseWithDifferentElements() throws Exception{
		testsAre(20, 35, 16, 17, 2, 16, 14, 12, 10, 16);
		detection.findRegressionId(10,5);
		assertEquals(getSampleBase().size(),0);
		testsAre(20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
		detection.findRegressionId(10,5);
		assertEquals(getSampleBase().size(),10);
		assertEquals(getSampleBase().get(0).getTime(), 20);
		assertEquals(getSampleBase().get(1).getTime(), 16);
		assertEquals(getSampleBase().get(2).getTime(), 17);
		assertEquals(getSampleBase().get(3).getTime(), 2);
		assertEquals(getSampleBase().get(4).getTime(), 16);
		assertEquals(getSampleBase().get(5).getTime(), 14);
		assertEquals(getSampleBase().get(6).getTime(), 12);
		assertEquals(getSampleBase().get(7).getTime(), 10);
		assertEquals(getSampleBase().get(8).getTime(), 16);
		assertEquals(getSampleBase().get(9).getTime(), 20);
		
		
		testExecutions.clear();
		testsAre(20, 35, 16, 17, 2, 16, 14, 12, 10, 16);
		testsAre(13, 15, 14, 18, 19, 20);
		detection.findRegressionId(10,5);
		
		assertEquals(getSampleBase().size(),10);
		assertEquals(getSampleBase().get(0).getTime(), 20);
		assertEquals(getSampleBase().get(1).getTime(), 16);
		assertEquals(getSampleBase().get(2).getTime(), 17);
		assertEquals(getSampleBase().get(3).getTime(), 16);
		assertEquals(getSampleBase().get(4).getTime(), 14);
		assertEquals(getSampleBase().get(5).getTime(), 12);
		assertEquals(getSampleBase().get(6).getTime(), 10);
		assertEquals(getSampleBase().get(7).getTime(), 16);
		assertEquals(getSampleBase().get(8).getTime(), 13);
		assertEquals(getSampleBase().get(9).getTime(), 15);
		
		detection.findRegressionId(8,5);
		assertEquals(getSampleBase().size(),8);
		assertEquals(getSampleBase().get(0).getTime(), 20);
		assertEquals(getSampleBase().get(1).getTime(), 16);
		assertEquals(getSampleBase().get(2).getTime(), 17);
		assertEquals(getSampleBase().get(3).getTime(), 2);
		assertEquals(getSampleBase().get(4).getTime(), 16);
		assertEquals(getSampleBase().get(5).getTime(), 14);
		assertEquals(getSampleBase().get(6).getTime(), 12);
		assertEquals(getSampleBase().get(7).getTime(), 10);
		
	}
	
	@Test
	public void SampleLastWithDifferentElements() throws Exception{
		testsAre(20, 20, 20, 20, 20, 20, 20, 20, 20, 20); //10 elementi
		testsAre(20, 35, 16, 17, 2, 16, 14, 12, 10, 16);//10
		detection.findRegressionId(10,10);
		
		assertEquals(getSampleBase().size(),10);
		assertEquals(getSampleLast().size(),8);
		
		System.out.println("Sample Last: ");
		for(int i=0; i<getSampleLast().size();i++)
			System.out.println(getSampleLast().get(i));
		System.out.println("BOXPLOT");
		System.out.println();
		
		assertEquals(getSampleLast().get(0).getTime(), 16);
		assertEquals(getSampleLast().get(1).getTime(), 10);
		assertEquals(getSampleLast().get(2).getTime(), 12);
		assertEquals(getSampleLast().get(3).getTime(), 14);
		assertEquals(getSampleLast().get(4).getTime(), 16);
		assertEquals(getSampleLast().get(5).getTime(), 17);
		assertEquals(getSampleLast().get(6).getTime(), 16);
		assertEquals(getSampleLast().get(7).getTime(), 20);
		
		testsAre(13, 15);
		detection.findRegressionId(10,10);
		assertEquals(getSampleLast().size(),10);
	
		assertEquals(getSampleLast().get(0).getTime(), 15);
		assertEquals(getSampleLast().get(1).getTime(), 13);
		assertEquals(getSampleLast().get(2).getTime(), 16);
		assertEquals(getSampleLast().get(3).getTime(), 10);
		assertEquals(getSampleLast().get(4).getTime(), 12);
		assertEquals(getSampleLast().get(5).getTime(), 14);
		assertEquals(getSampleLast().get(6).getTime(), 16);
		assertEquals(getSampleLast().get(7).getTime(), 17);
		assertEquals(getSampleLast().get(8).getTime(), 16);
		assertEquals(getSampleLast().get(9).getTime(), 20);
		
		
		testsAre(14, 18, 19, 20, 40, 20);//altri 10
		detection.findRegressionId(10,12);
				
		assertEquals(getSampleLast().size(),12);
		assertEquals(getSampleLast().get(0).getTime(), 20);
		assertEquals(getSampleLast().get(1).getTime(), 20);
		assertEquals(getSampleLast().get(2).getTime(), 19);
		assertEquals(getSampleLast().get(3).getTime(), 18);
		assertEquals(getSampleLast().get(4).getTime(), 14);
		assertEquals(getSampleLast().get(5).getTime(), 15);
		assertEquals(getSampleLast().get(6).getTime(), 13);
		assertEquals(getSampleLast().get(7).getTime(), 16);
		assertEquals(getSampleLast().get(8).getTime(), 10);
		assertEquals(getSampleLast().get(9).getTime(), 12);
		assertEquals(getSampleLast().get(10).getTime(), 14);
		assertEquals(getSampleLast().get(11).getTime(), 16);
		
	}
	//HEre
	
	
	
	@Test
	public void SampleBaseLastWithDifferentElements2() throws Exception{

		testsAre(15, 130, 159, 209);
		detection.findRegressionId(10,5);
		assertEquals(getSampleBase().size(),0);
		testsAre(250, 400, 162, 208, 170, 550); //sample 10
		testsAre(300,300,300,340,320);
		detection.findRegressionId(10,5);
				
		assertEquals(getSampleBase().size(),10);
		assertEquals(getSampleBase().get(0).getTime(), 15);
		assertEquals(getSampleBase().get(1).getTime(), 130);
		assertEquals(getSampleBase().get(2).getTime(), 159);
		assertEquals(getSampleBase().get(3).getTime(), 209);
		assertEquals(getSampleBase().get(4).getTime(), 250);
		assertEquals(getSampleBase().get(5).getTime(), 400);
		assertEquals(getSampleBase().get(6).getTime(), 162);
		assertEquals(getSampleBase().get(7).getTime(), 208);
		assertEquals(getSampleBase().get(8).getTime(), 170);
		assertEquals(getSampleBase().get(9).getTime(), 300);
		
		
		detection.findRegressionId(7,7);
		assertEquals(getSampleBase().size(),7);
		assertEquals(getSampleBase().get(0).getTime(), 15);
		assertEquals(getSampleBase().get(1).getTime(), 130);
		assertEquals(getSampleBase().get(2).getTime(), 159);
		assertEquals(getSampleBase().get(3).getTime(), 209);
		assertEquals(getSampleBase().get(4).getTime(), 250);
		assertEquals(getSampleBase().get(5).getTime(), 400);
		assertEquals(getSampleBase().get(6).getTime(), 162);
		
		
		assertEquals(getSampleLast().size(),7);
		
		detection.findRegressionId(6,7);
		assertEquals(getSampleBase().get(0).getTime(), 15);
		assertEquals(getSampleBase().get(1).getTime(), 130);
		assertEquals(getSampleBase().get(2).getTime(), 159);
		assertEquals(getSampleBase().get(3).getTime(), 209);
		assertEquals(getSampleBase().get(4).getTime(), 250);
		assertEquals(getSampleBase().get(5).getTime(), 400);
		
		testsAre(300,800);//17 elementi
		detection.findRegressionId(12, 5);
		assertEquals(getSampleBase().size(),12);
		assertEquals(getSampleBase().get(0).getTime(), 15);
		assertEquals(getSampleBase().get(1).getTime(), 130);
		assertEquals(getSampleBase().get(2).getTime(), 159);
		assertEquals(getSampleBase().get(3).getTime(), 209);
		assertEquals(getSampleBase().get(4).getTime(), 250);
		assertEquals(getSampleBase().get(5).getTime(), 400);
		assertEquals(getSampleBase().get(6).getTime(), 162);
		assertEquals(getSampleBase().get(7).getTime(), 208);
		assertEquals(getSampleBase().get(8).getTime(), 170);
		assertEquals(getSampleBase().get(9).getTime(), 300);
		assertEquals(getSampleBase().get(10).getTime(), 300);
		assertEquals(getSampleBase().get(11).getTime(), 300);
		
		assertEquals(getSampleLast().size(),4);
		//minimo -105, massimo 975
		assertEquals(getSampleLast().get(0).getTime(), 800);
		assertEquals(getSampleLast().get(1).getTime(), 300);
		assertEquals(getSampleLast().get(2).getTime(), 320);
		assertEquals(getSampleLast().get(3).getTime(), 340);
		
		
		detection.findRegressionId(11, 5);
		//minimo -105, massimo 975
		assertEquals(getSampleLast().size(),5);
		assertEquals(getSampleLast().get(0).getTime(), 800);
		assertEquals(getSampleLast().get(1).getTime(), 300);
		assertEquals(getSampleLast().get(2).getTime(), 320);
		assertEquals(getSampleLast().get(3).getTime(), 340);
		assertEquals(getSampleLast().get(4).getTime(), 300);
		
		detection.findRegressionId(10, 6);
		//Maximum: 400.0
		//Minimum: 240.0
		assertEquals(getSampleLast().size(),5);
		assertEquals(getSampleLast().get(0).getTime(), 300);
		assertEquals(getSampleLast().get(1).getTime(), 320);
		assertEquals(getSampleLast().get(2).getTime(), 340);
		assertEquals(getSampleLast().get(3).getTime(), 300);
		assertEquals(getSampleLast().get(4).getTime(), 300);
		
		testsAre(250, 400, 162, 208, 170, 550);
		detection.findRegressionId(10, 10);
		assertEquals(testExecutions.size(),23);
		assertEquals(getSampleBase().size(),10);
		assertEquals(getSampleBase().get(0).getTime(), 15);
		assertEquals(getSampleBase().get(1).getTime(), 130);
		assertEquals(getSampleBase().get(2).getTime(), 159);
		assertEquals(getSampleBase().get(3).getTime(), 209);
		assertEquals(getSampleBase().get(4).getTime(), 250);
		assertEquals(getSampleBase().get(5).getTime(), 400);
		assertEquals(getSampleBase().get(6).getTime(), 162);
		assertEquals(getSampleBase().get(7).getTime(), 208);
		assertEquals(getSampleBase().get(8).getTime(), 170);
		assertEquals(getSampleBase().get(9).getTime(), 300);
		
		assertEquals(getSampleLast().size(),10);
		assertEquals(getSampleLast().get(0).getTime(), 550);
		assertEquals(getSampleLast().get(1).getTime(), 170);
		assertEquals(getSampleLast().get(2).getTime(), 208);
		assertEquals(getSampleLast().get(3).getTime(), 162);
		assertEquals(getSampleLast().get(4).getTime(), 400);
		assertEquals(getSampleLast().get(5).getTime(), 250);
		assertEquals(getSampleLast().get(6).getTime(), 300);
		assertEquals(getSampleLast().get(7).getTime(), 320);
		assertEquals(getSampleLast().get(8).getTime(), 340);
		assertEquals(getSampleLast().get(9).getTime(), 300);
		
		
		testsAre(1400,0,800,500,500,500,700,200);
		detection.findRegressionId(10, 8);
		assertEquals(getSampleLast().size(),8);
		assertEquals(getSampleLast().get(0).getTime(), 200);
		assertEquals(getSampleLast().get(1).getTime(), 700);
		assertEquals(getSampleLast().get(2).getTime(), 500);
		assertEquals(getSampleLast().get(3).getTime(), 500);
		assertEquals(getSampleLast().get(4).getTime(), 500);
		assertEquals(getSampleLast().get(5).getTime(), 800);
		assertEquals(getSampleLast().get(6).getTime(), 0);
		assertEquals(getSampleLast().get(7).getTime(), 550);
		/*
		List<Integer> list= new ArrayList<Integer>();
		System.out.println("size testExecutions: "+testExecutions.size());
		for(int i=testExecutions.size()-1;i>testExecutions.size()-10;i--){
			list.add(testExecutions.get(i).getDuration());
		}
		BoxPlot p = new BoxPlot(list);
		System.out.println("Maximum: "+p.getMaximum());
		System.out.println("Minimum: "+p.getMinimum());
		list.clear();
		list=p.getCorrectSample();
		System.out.println("size:"+list.size());
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		*/
		
	}
	
	private List<TimeExecution> getSampleBase(){
		return detection.getSampleBase();
	}
	
	private List<TimeExecution> getSampleLast() {
		return detection.getSampleLast();
	}
	
	private void testsAre(int... duration){
		int i=0;
		for (int i_time : duration) {
			testExecutions.add(new TimeExecution(i_time,i++));
		}
		
	}
	/*
	System.out.println("TestExecution: ");
	for(int i=0;i<testExecutions.size();i++){
		System.out.println(testExecutions.get(i).getDuration());
	}
	
	System.out.println(getSampleBase().size());
	System.out.println("elementi: ");
	for(int i=0;i<getSampleBase().size();i++)
		System.out.println(getSampleBase().get(i));
	
	System.out.println("BOXPLOT");
	System.out.println();
	
	
	List<Integer> list= new ArrayList<Integer>();
	System.out.println("size testExecutions: "+testExecutions.size());
	for(int i=0;i<11;i++){
		list.add(testExecutions.get(i).getDuration());
	}
	BoxPlot p = new BoxPlot(list);
	System.out.println("Maximum: "+p.getMaximum());
	System.out.println("Minimum: "+p.getMinimum());
	list.clear();
	list=p.getCorrectSample();
	System.out.println("size:"+list.size());
	for(int i=0;i<list.size();i++){
		System.out.println(list.get(i));
	}*/


}
