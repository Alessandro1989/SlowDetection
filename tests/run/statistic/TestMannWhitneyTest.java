package run.statistic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import jsc.independentsamples.MannWhitneyTest;
import jsc.tests.H1;

import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.exception.IllegalSizeSamples;
import jetbrains.sample.statistic.AlfaPercentage;
import jetbrains.sample.statistic.MannWhitney;


public class TestMannWhitneyTest {

	private MannWhitney Mnw;
	private List<Integer> secondSample;
	private List<Integer> firstSample;
	private int alfaPercentuage;
	private static final double DELTA = 0.01; //variazione consentit
	
	@Before
	public void before() {
		firstSample = new ArrayList<Integer>();
		secondSample = new ArrayList<Integer>();
	}
	
	@Test (expected=IllegalSizeSamples.class)
	public void testErrorEmpty() throws IllegalSizeSamples {
		Mnw=new MannWhitney(firstSample, secondSample, AlfaPercentage.ONE);
	}
	
	
	@Test (expected=IllegalSizeSamples.class)
	public void testErrorSize() throws IllegalSizeSamples {
		addElements(firstSample, 589);
		Mnw=new MannWhitney(firstSample, secondSample, AlfaPercentage.ONE);
		
	}
	
	@Test (expected=IllegalSizeSamples.class)
	public void testErrorSize2() throws IllegalSizeSamples {
			
		addElements(firstSample, 589);
		addElements(secondSample, 589);
		Mnw=new MannWhitney(firstSample, secondSample, AlfaPercentage.ONE);
		
	}
	
	@Test (expected=IllegalSizeSamples.class)
	public void testErrorSize3() throws IllegalSizeSamples {
		
		addElements(firstSample, 589, 438, 55, 62, 88);
		addElements(secondSample, 589);
		Mnw=new MannWhitney(firstSample, secondSample, AlfaPercentage.ONE);
		
	}
	
	@Test (expected=IllegalSizeSamples.class)
	public void testErrorSize4() throws IllegalSizeSamples {
		
		addElements(firstSample, 589, 438, 55);
		addElements(secondSample, 589, 26);
		Mnw=new MannWhitney(firstSample, secondSample, AlfaPercentage.ONE);
	}
	
	@Test
	public void testMannWhitney() throws IllegalSizeSamples {
		addElements(firstSample, 589, 508, 252, 606, 849, 669, 227, 254, 610, 727);
		addElements(secondSample, 838, 857, 354, 698, 765, 843, 783, 448, 441, 834);
		MannWhitneyTest mnw2=new MannWhitneyTest(convert(firstSample),convert(secondSample), H1.NOT_EQUAL);
		Mnw=new MannWhitney(firstSample, secondSample, AlfaPercentage.ONE);
		/*List<Rank> arrayRank=Mnw.getArrayRank();
		for(int i=0;i<arrayRank.size();i++){
			System.out.println(arrayRank.get(i).toString());
		}
		*/
		assertEquals(Mnw.getSumRank1(),mnw2.getRankSumA(), DELTA);
		assertEquals(Mnw.getSumRank1(), 83, DELTA);
		assertEquals(Mnw.getSumRank2(),mnw2.getRankSumB(), DELTA);
		assertEquals(Mnw.getSumRank2(), 127, DELTA);
				
		assertEquals(Mnw.getU1(), 28, DELTA);
		assertEquals(Mnw.getU2(), 72, DELTA);
	
		assertTrue(Mnw.isSame());
		assertFalse(Mnw.isMajor(0));
		assertFalse(Mnw.isMajor(1));
		assertEquals(Mnw.getResult(),0);
		
	}
	
	@Test
	public void testMannWhitney2() throws IllegalSizeSamples {
		addElements(firstSample, 1, 2, 3, 4, 5, 6);
		addElements(secondSample, 1, 2, 3, 4, 5, 6);
		MannWhitneyTest mnw2=new MannWhitneyTest(convert(firstSample),convert(secondSample), H1.NOT_EQUAL);
		Mnw=new MannWhitney(firstSample, secondSample, AlfaPercentage.ONE);
				
		assertEquals(Mnw.getSumRank1(),mnw2.getRankSumA(), DELTA);
		
		assertEquals(Mnw.getSumRank2(),mnw2.getRankSumB(), DELTA);
				
		assertEquals(Mnw.getU1(), 18, DELTA);
		assertEquals(Mnw.getU2(), 18, DELTA);
	
		assertTrue(Mnw.isSame());//18>2
		assertFalse(Mnw.isMajor(0));
		assertFalse(Mnw.isMajor(1));
		assertEquals(Mnw.getResult(),0);
		
	}
	
	@Test
	public void testMannWhitney3() throws IllegalSizeSamples {
		addElements(firstSample, 1, 1, 2, 2, 2, 3, 3);
		addElements(secondSample, 1, 2, 2, 2, 3, 3);
		
		MannWhitneyTest mnw2=new MannWhitneyTest(convert(firstSample),convert(secondSample), H1.NOT_EQUAL);
		Mnw=new MannWhitney(firstSample, secondSample, AlfaPercentage.ONE);
		
			
		assertEquals(Mnw.getSumRank1(),mnw2.getRankSumA(), DELTA);
		assertEquals(Mnw.getSumRank2(),mnw2.getRankSumB(), DELTA);
		
		
		assertEquals(Mnw.getU1(), 18.5, DELTA);
		assertEquals(Mnw.getU2(), 23.5, DELTA);
	
		assertTrue(Mnw.isSame());//18.5>3
		assertFalse(Mnw.isMajor(0));
		assertFalse(Mnw.isMajor(1));
		assertEquals(Mnw.getResult(),0);
		
	}
	
	
	@Test
	public void testMannWhitney4() throws IllegalSizeSamples {
		addElements(firstSample, 30, 65, 74, 69, 90, 63, 63, 76, 68, 69);
		addElements(secondSample, 9, 5, 6, 8, 8, 7, 7);
		
		MannWhitneyTest mnw2=new MannWhitneyTest(convert(firstSample),convert(secondSample), H1.NOT_EQUAL);
		Mnw=new MannWhitney(firstSample, secondSample, AlfaPercentage.ONE);
				
		assertEquals(Mnw.getSumRank1(),mnw2.getRankSumA(), DELTA);
		assertEquals(Mnw.getSumRank2(),mnw2.getRankSumB(), DELTA);
		
		
		assertEquals(Mnw.getU1(), 70, DELTA);
		assertEquals(Mnw.getU2(), 0, DELTA);
	
		assertFalse(Mnw.isSame());//0<9
		assertTrue(Mnw.isMajor(0));
		assertFalse(Mnw.isMajor(1));
		assertEquals(Mnw.getResult(),-1);
		
	}
	
	
	@Test
	public void testMannWhitney5() throws IllegalSizeSamples {
		
		addElements(firstSample, 634, 653, 645, 608, 650, 637, 639, 660, 644, 600);
		addElements(secondSample, 820, 595, 645, 841, 790, 715, 778, 697, 671, 700);
		
		MannWhitneyTest mnw2=new MannWhitneyTest(convert(firstSample),convert(secondSample), H1.NOT_EQUAL);
		Mnw=new MannWhitney(firstSample, secondSample, AlfaPercentage.ONE);
	
		
		assertEquals(Mnw.getSumRank1(),mnw2.getRankSumA(), DELTA);
		assertEquals(Mnw.getSumRank2(),mnw2.getRankSumB(), DELTA);
		
		
		assertEquals(Mnw.getU1(), 13.5, DELTA);
		assertEquals(Mnw.getU2(), 86.5, DELTA);
	
		assertFalse(Mnw.isSame());//13.5<16
		assertTrue(Mnw.isMajor(1));
		assertFalse(Mnw.isMajor(0));
		assertEquals(Mnw.getResult(),1);
		
	}
	
	@Test
	public void testMannWhitney6() throws IllegalSizeSamples {
		
		addElements(firstSample, 8, 8, 8, 9, 7, 8, 7, 9, 8, 9); 
		addElements(secondSample, 14, 8, 8, 9, 14, 15, 11, 12, 13, 7, 12, 13, 15);
		
		MannWhitneyTest mnw2=new MannWhitneyTest(convert(firstSample),convert(secondSample), H1.NOT_EQUAL);
		Mnw=new MannWhitney(firstSample, secondSample, AlfaPercentage.ONE);
		
		System.out.println(mnw2.getRankSumA());
		System.out.println(mnw2.getRankSumB());
		
		assertEquals(Mnw.getSumRank1(),mnw2.getRankSumA(), DELTA);
		assertEquals(Mnw.getSumRank2(),mnw2.getRankSumB(), DELTA);
		
		
		assertEquals(Mnw.getU1(), 21.5, DELTA);
		assertEquals(Mnw.getU2(), 108.5, DELTA);
	
		assertFalse(Mnw.isSame());//21.5<18
		assertTrue(Mnw.isMajor(1));
		assertFalse(Mnw.isMajor(0));
		assertEquals(Mnw.getResult(),1);
		
	}
	
	
	private static double[] convert(List<Integer> Sample) {
		double[] sample=new double[Sample.size()];
		for (int i=0; i<Sample.size();i++){
			sample[i]=Sample.get(i).intValue();
		}
		return sample;
	}
	
	
	private static void addElements(List<Integer> sample,int... duration){
		for (int i_time : duration) {
			sample.add(i_time); 
		}
	}
	
}
