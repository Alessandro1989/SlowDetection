package run.statistic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import jsc.independentsamples.MannWhitneyTest;
import jsc.tests.H1;

import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.exception.*;
import jetbrains.sample.statistic.BoxPlot;

   //
public class BoxPlotTest {

	private static final double DELTA = 0.01;
	private BoxPlot box;
	private List<Integer> Sample;
	
	
	@Before
	public void before() {
		Sample = new ArrayList<Integer>();
	}
	
	@Test (expected=IllegalSizeSamples.class)
	public void testErrorEmpty() throws Exception {
		box=new BoxPlot(Sample, true);
	}
	@Test (expected=IllegalSizeSamples.class)
	public void testErrorSize() throws Exception {
		addElements(Sample, 589);
		box=new BoxPlot(Sample, true);
	}
	
	@Test (expected=IllegalSizeSamples.class)
	public void testErrorSize2() throws Exception {
		addElements(Sample, 589, 566);
		box=new BoxPlot(Sample, true);
	}
	
	@Test (expected=IllegalSizeSamples.class)
	public void testErrorSize3() throws Exception {
		addElements(Sample, 102,  141,  144);
		box=new BoxPlot(Sample, false);
			
	}
	
	@Test
	public void testBoxPlot0SameElements() throws Exception {

		addElements(Sample, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
				
		box=new BoxPlot(Sample, false);
		assertEquals(box.getQ1(),  20  ,DELTA);//102
		assertEquals(box.getQ2(), 20 ,DELTA);//144
		assertEquals(box.getMinimum(), 20 ,DELTA);
		assertEquals(box.getMaximum(),  20 ,DELTA);
		assertEquals(box.getCorrectSample().size(), 10);
		
		for(int i=0; i<box.getCorrectSample().size(); i++){
			assertEquals(box.getCorrectSample().get(i).intValue(), 20);
		}
		
	}
	
	
	@Test
	public void testBoxPlot1() throws Exception {
		addElements(Sample, 145,  149, 147,  144, 164,  145,  102, 146,  147, 147,  144,  141,  151, 159,  144);
		box=new BoxPlot(Sample, false);
		assertEquals(box.getQ1(), 144 ,DELTA);
		assertEquals(box.getQ2(), 149 ,DELTA);
		assertEquals(box.getMinimum(), 136.5 ,DELTA);
		assertEquals(box.getMaximum(), 156.5 ,DELTA);
		assertTrue(box.getCorrectSample().size()>3);
		for(int i=0; i<box.getCorrectSample().size(); i++){
			assertTrue(box.getCorrectSample().get(i)<=box.getMaximum());
			assertTrue(box.getCorrectSample().get(i)>=box.getMinimum());
		}
		
	}
	
	@Test
	public void testBoxPlot2() throws Exception {
		addElements(Sample, 15, 16, 17, 13, 16, 14, 12, 10, 16, 13, 15, 14, 18, 19, 20);
		box=new BoxPlot(Sample, false);
		assertEquals(box.getQ1(), 13 ,DELTA);
		assertEquals(box.getQ2(), 17 ,DELTA);
		assertEquals(box.getMinimum(), 7 ,DELTA);
		assertEquals(box.getMaximum(), 23 ,DELTA);
		assertTrue(box.getCorrectSample().size()>3);
		for(int i=0; i<box.getCorrectSample().size(); i++){
			assertTrue(box.getCorrectSample().get(i)<=box.getMaximum());
			assertTrue(box.getCorrectSample().get(i)>=box.getMinimum());
		}
		
	}
	
	@Test
	public void testBoxPlot3() throws Exception {
		addElements(Sample, 102,  141,  144,   144,  144,  145,  145,  146,  147, 147, 147, 149, 151, 159, 164);
		box=new BoxPlot(Sample, false);
		assertEquals(box.getQ1(), 144  ,DELTA);
		assertEquals(box.getQ2(), 149  ,DELTA);
		assertEquals(box.getMinimum(), 136.5 ,DELTA);
		assertEquals(box.getMaximum(), 156.5 ,DELTA);
		assertTrue(box.getCorrectSample().size()>3);
		for(int i=0; i<box.getCorrectSample().size(); i++){
			assertTrue(box.getCorrectSample().get(i)<=box.getMaximum());
			assertTrue(box.getCorrectSample().get(i)>=box.getMinimum());
		}
		
	}
	
	@Test
	public void testBoxPlot4() throws Exception {
		addElements(Sample, 102,  141,  144, 150);
		box=new BoxPlot(Sample, false);
		assertEquals(box.getQ1(), 121.5  ,DELTA);//102
		assertEquals(box.getQ2(), 147 ,DELTA);//144
		assertEquals(box.getMinimum(), 83.25 ,DELTA);
		assertEquals(box.getMaximum(),  185.25 ,DELTA);
		assertTrue(box.getCorrectSample().size()>3);
		for(int i=0; i<box.getCorrectSample().size(); i++){
			assertTrue(box.getCorrectSample().get(i)<=box.getMaximum());
			assertTrue(box.getCorrectSample().get(i)>=box.getMinimum());
		}
		
	}
	
	@Test
	public void testBoxPlot5() throws Exception {
		addElements(Sample, 102,  141, 144, 150, 160);
		box=new BoxPlot(Sample, false);
		assertEquals(box.getQ1(), 121.5  ,DELTA);//102
		assertEquals(box.getQ2(), 155 ,DELTA);//144
		assertEquals(box.getMinimum(), 71.25 ,DELTA);
		assertEquals(box.getMaximum(),  205.25 ,DELTA);
		assertTrue(box.getCorrectSample().size()>3);
		for(int i=0; i<box.getCorrectSample().size(); i++){
			assertTrue(box.getCorrectSample().get(i)<=box.getMaximum());
			assertTrue(box.getCorrectSample().get(i)>=box.getMinimum());
		}
		
	}
	
	@Test
	public void testBoxPlot6() throws Exception {
		addElements(Sample, 10, 10, 20, 20, 40, 60, 68, 72, 80, 83, 90, 100, 100, 115);
		box=new BoxPlot(Sample, false);
		assertEquals(box.getQ1(), 20  ,DELTA);//102
		assertEquals(box.getQ2(), 90 ,DELTA);//144
		assertEquals(box.getMinimum(), -85 ,DELTA);
		assertEquals(box.getMaximum(),  195 ,DELTA);
		assertTrue(box.getCorrectSample().size()>3);
		for(int i=0; i<box.getCorrectSample().size(); i++){
			assertTrue(box.getCorrectSample().get(i)<=box.getMaximum());
			assertTrue(box.getCorrectSample().get(i)>=box.getMinimum());
		}
		
	}
	
	@Test
	public void testBoxPlot7() throws Exception {
		addElements(Sample, 59, 60, 61, 62, 62, 63, 63, 64, 64, 64, 65, 65, 65, 65, 65, 65, 65, 65);
		addElements(Sample, 65, 66, 66, 67, 67, 68, 68, 69, 70, 70, 70, 70, 70, 71, 71, 72, 72, 73, 74, 74, 75, 77);
				
		box=new BoxPlot(Sample, false);
		assertEquals(box.getQ1(),  64.5  ,DELTA);//102
		assertEquals(box.getQ2(), 70 ,DELTA);//144
		assertEquals(box.getMinimum(), 56.25 ,DELTA);
		assertEquals(box.getMaximum(),  78.25 ,DELTA);
		assertTrue(box.getCorrectSample().size()>3);
		for(int i=0; i<box.getCorrectSample().size(); i++){
			assertTrue(box.getCorrectSample().get(i)<=box.getMaximum());
			assertTrue(box.getCorrectSample().get(i)>=box.getMinimum());
		}
		
	}
	
	@Test
	public void testBoxPlotBase1() throws Exception {
		addElements(Sample, 59, 60, 61, 62, 62, 63, 63, 64, 64, 64, 65, 65, 65, 65, 65, 65, 65, 65);
		addElements(Sample, 65, 66, 66, 67, 67, 68, 68, 69, 70, 70, 70, 70, 70, 71, 71, 72, 72, 73, 74, 74, 75, 77);
				
		box=new BoxPlot(Sample, true);
		assertEquals(box.getQ1(),  64.5  ,DELTA);//102
		assertEquals(box.getQ2(), 70 ,DELTA);//144
		assertEquals(box.getMinimum(), -1 ,DELTA);
		assertEquals(box.getMaximum(),  70 ,DELTA);
		assertTrue(box.getCorrectSample().size()>3);
		for(int i=0; i<box.getCorrectSample().size(); i++){
			assertTrue(box.getCorrectSample().get(i)<=box.getMaximum());
			assertTrue(box.getCorrectSample().get(i)>=box.getMinimum());
		}
		
	}

	
	private static void addElements(List<Integer> sample,int... duration){
		for (int i_time : duration) {
			sample.add(i_time); 
		}
	}
}
