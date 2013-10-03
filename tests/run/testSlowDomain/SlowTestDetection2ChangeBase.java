package run.testSlowDomain;

import static org.junit.Assert.*;

import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import jetbrains.sample.exception.*;
import jetbrains.sample.statistic.*;
import jetbrains.sample.testSlowDomain.*;

public class SlowTestDetection2ChangeBase{
		//codice doppio, facciamo una classe?

	private List<TimeExecution> testExecutions;
	private SlowTestDetection detection;
	private static final double DELTA = 0.1; //variazione consentita
		
	@Before
	public void before() {
		testExecutions = new ArrayList<TimeExecution>();
		detection = new SlowTestDetection(testExecutions);
	}
		
	//NoChangeBase 
	//ChangeBase1
	//ChangeBase2
	//ChangeBase1SampleLast0
	//ChangeBase2SampleLast0
	//finalTest1
	//finalTest2
	
	@Test
	public void NoChangeBase() throws Exception{
		testsAre(20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 30, 25, 20, 20, 25, 19);
		detection.findRegressionId(10,5);
		assertEquals(getSampleBase().size(),10);
		assertEquals(getSampleLast().size(),5);
		
		//no change base
		assertEquals(getSampleBase().get(0).getTime(), 20);
		assertEquals(getSampleBase().get(1).getTime(), 20);
		assertEquals(getSampleBase().get(2).getTime(), 20);
		assertEquals(getSampleBase().get(3).getTime(), 20);
		assertEquals(getSampleBase().get(4).getTime(), 20);
		assertEquals(getSampleBase().get(5).getTime(), 20);
		assertEquals(getSampleBase().get(6).getTime(), 20);
		assertEquals(getSampleBase().get(7).getTime(), 20);
		assertEquals(getSampleBase().get(8).getTime(), 20);
		assertEquals(getSampleBase().get(9).getTime(), 20);
		
	}
	
	@Test
	public void ChangeBase1() throws Exception{
		testsAre(20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
		testsAre(20, 15, 15, 15, 15, 18, 17, 16, 18, 17, 30, 25, 20, 20, 25, 19);
		detection.findRegressionId(7,5);
		assertEquals(getSampleBase().size(),7);
		assertEquals(getSampleLast().size(),5);
		
		/*List<Integer> list= new ArrayList<Integer>();
		for(int i=0;i<9;i++){
			list.add(testExecutions.get(i+11).getDuration());
		}
		
		BoxPlotBase p = new BoxPlotBase(list);
		System.out.println("Maximum: "+p.getMaximum());
		System.out.println("Minimum: "+p.getMinimum());
		list.clear();
		list=p.getCorrectSample();
		System.out.println("size:"+list.size());
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}*/
		//no change base
		assertEquals(getSampleBase().get(0).getTime(), 15);
		assertEquals(getSampleBase().get(1).getTime(), 15);
		assertEquals(getSampleBase().get(2).getTime(), 15);
		assertEquals(getSampleBase().get(3).getTime(), 15);
		assertEquals(getSampleBase().get(4).getTime(), 17);
		assertEquals(getSampleBase().get(5).getTime(), 16);
		assertEquals(getSampleBase().get(6).getTime(), 17);
	}
	
	@Test
	public void ChangeBase2() throws Exception{
		testsAre(20, 20, 22, 20, 21, 20, 22, 20, 20, 20);
		testsAre(20, 20, 15, 19, 17, 18, 19, 16, 18, 19, 20, 21);
		System.out.println("here");
		detection.findRegressionId(7,5);
		assertEquals(getSampleBase().size(),7);
		assertEquals(getSampleLast().size(),3); 
		//no change base
				
		assertEquals(getSampleBase().get(0).getTime(), 15);
		assertEquals(getSampleBase().get(1).getTime(), 19);
		assertEquals(getSampleBase().get(2).getTime(), 17);
		assertEquals(getSampleBase().get(3).getTime(), 18);
		assertEquals(getSampleBase().get(4).getTime(), 19);
		assertEquals(getSampleBase().get(5).getTime(), 16);
		assertEquals(getSampleBase().get(6).getTime(), 18);

	}
	
	@Test
	public void ChangeBase3() throws Exception{
		testsAre(75, 305, 81, 257, 222, 70, 78, 219, 376, 114, 277, 75, 406, 137, 103);
		testsAre(68, 263, 70, 260, 75, 69, 285, 73, 230, 268, 264, 219);
		testsAre(75, 76, 78, 305, 73, 73, 72, 70, 73);
		System.out.println("here");
		detection.findRegressionId(9,6);
		assertEquals(getSampleBase().size(),9);
		assertEquals(getSampleLast().size(),6); 
		//no change base
				
		assertEquals(getSampleBase().get(0).getTime(), 75);
		assertEquals(getSampleBase().get(1).getTime(), 81);//non è stata cambiata la base.
		
		testsAre(69); //questo valore provoca il cambia base
		testsAre(75, 76, 78, 72, 250, 73, 73, 72, 70, 73, 75);
		detection.findRegressionId(9,6);
		assertEquals(getSampleBase().size(),9);
		assertEquals(getSampleLast().size(),0); //il cambia base è stato cambiato, quindi gli ultimi elementi
		//non hanno di sicuro regressione
		assertNull(detection.findRegressionId(9,6));
		/*List<Integer> list= new ArrayList<Integer>();
		for(int i=36;i<testExecutions.size()-1;i++){
			list.add(testExecutions.get(i).getDuration());
		}
		
		BoxPlotBase p = new BoxPlotBase(list);
		System.out.println("Maximum: "+p.getMaximum());
		System.out.println("Minimum: "+p.getMinimum());
		list.clear();
		list=p.getCorrectSample();
		System.out.println("size:"+list.size());
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}*/
		//no change base
		
		assertEquals(getSampleBase().get(0).getTime(), 69);
		assertEquals(getSampleBase().get(1).getTime(), 75);
		assertEquals(getSampleBase().get(2).getTime(), 76);
		assertEquals(getSampleBase().get(3).getTime(), 72);
		assertEquals(getSampleBase().get(4).getTime(), 73);
		assertEquals(getSampleBase().get(5).getTime(), 73);
		assertEquals(getSampleBase().get(6).getTime(), 72);
		assertEquals(getSampleBase().get(7).getTime(), 70);
		assertEquals(getSampleBase().get(8).getTime(), 73);
		
		//System.out.println("HERE");
		detection.findRegressionId(7,6);
		
		assertEquals(getSampleBase().size(),7);
		assertEquals(getSampleLast().size(),6);
		
		assertEquals(getSampleBase().get(0).getTime(), 75);
		assertEquals(getSampleBase().get(1).getTime(), 81);
		assertEquals(getSampleBase().get(2).getTime(), 257);
		assertEquals(getSampleBase().get(3).getTime(), 222);
		assertEquals(getSampleBase().get(4).getTime(), 70);
		assertEquals(getSampleBase().get(5).getTime(), 78);
		assertEquals(getSampleBase().get(6).getTime(), 219);
		//confronti con:
		//68; 70; 260; 75; 69; 73; 230; 70
		//69; 73; 230; 264; 219; 75; 76; 70
		//69; 75; 76; 72; 73; 73; 72;
		/*List<Integer> anotherSample=new ArrayList<Integer>();
		anotherSample.add(69); anotherSample.add(75);anotherSample.add(76);
		anotherSample.add(72);anotherSample.add(73);anotherSample.add(73);
		anotherSample.add(72);
		MannWhitney Mnw = new MannWhitney(getSampleBase(), anotherSample, AlfaPercentage.ONE);
		System.out.println(Mnw.getU1());
		System.out.println(Mnw.getU2());
		System.out.println(Mnw.isSame());
		//41.5
		//7.5
		//7.5>4 alfa 0.01 (alfa 0.05 è 8, quindi falliva)
		//true
		 * */		
		 
		testsAre(55, 64, 65, 75, 73, 71, 67, 68, 60);
		detection.findRegressionId(7,6);
		
		assertEquals(getSampleBase().size(),7);
		assertEquals(getSampleLast().size(),0);
		
		assertEquals(getSampleBase().get(0).getTime(), 55);
		assertEquals(getSampleBase().get(1).getTime(), 64);
		assertEquals(getSampleBase().get(2).getTime(), 65);
		assertEquals(getSampleBase().get(3).getTime(), 71);
		assertEquals(getSampleBase().get(4).getTime(), 67);
		assertEquals(getSampleBase().get(5).getTime(), 68);
		assertEquals(getSampleBase().get(6).getTime(), 60);
		
		testsAre(58,63);
		detection.findRegressionId(7,6);
		assertEquals(getSampleLast().size(),2);
		
		testsAre(80, 70, 75, 66, 67, 85, 58, 60);
		detection.findRegressionId(7,6);
		assertEquals(getSampleLast().get(0).getTime(), 60);
		assertEquals(getSampleLast().get(1).getTime(), 58);
		assertEquals(getSampleLast().get(2).getTime(), 85);
		assertEquals(getSampleLast().get(3).getTime(), 67);
		assertEquals(getSampleLast().get(4).getTime(), 66);
		assertEquals(getSampleLast().get(5).getTime(), 75);
		
		assertNull(detection.findRegressionId(7,6));
		
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
			testExecutions.add(new TimeExecution(i_time, i++));
		}
		
	}

}



