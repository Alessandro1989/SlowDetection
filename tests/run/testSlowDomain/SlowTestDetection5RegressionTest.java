package run.testSlowDomain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.exception.*;
import jetbrains.sample.statistic.*;
import jetbrains.sample.testSlowDomain.*;

public class SlowTestDetection5RegressionTest {
     //
	private List<TimeExecution> testExecutions;
	private SlowTestDetection detection;
	private static final double DELTA = 0.1; //variazione consentit
	
	@Before
	public void before() {
		testExecutions = new ArrayList<TimeExecution>();
		detection = new SlowTestDetection(testExecutions);
	}
	
	@Test
	public void testRegressionRegressionExample1() throws Exception {
		timesAre(634, 653, 745, 608, 700, 637, 639, 660, 644, 600, 634, 653, 745, 608, 700);
		
		timesAre(907, 800, 750, 841, 890, 715, 778, 697, 720, 740 ,690, 800, 746, 700, 750);
		 
		assertNotNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNotNull(detection.findRegressionId(10,7));
	}
	
	@Test
	public void testRegressionRegressionExample2() throws Exception {
		timesAre(8, 8, 8, 9, 7, 8, 7, 9, 8, 9);
		timesAre(14, 8, 9, 9, 14, 15, 11, 12, 13, 10, 12, 13, 15); 
		
		assertNotNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNotNull(detection.findRegressionId(10,7));
	}
	
	@Test
	public void testRegressionRegressionExample3() throws Exception {
		
		timesAre(1279, 1816, 1343, 1314, 921, 1018, 975, 1433, 1518, 916, 1174, 870, 1500, 1381, 1236, 1054);
		timesAre(1327, 1961, 1454, 1300, 1324, 1554, 1340, 1416, 1511, 1575, 1413, 1469, 1684, 1655, 1870, 1688, 1761);
				
		
		assertNotNull(detection.findRegressionId(10,7));
		detection = new SlowTestDetection(testExecutions);
		assertNotNull(detection.findRegressionId(10,8)); //forse il 1816 nel primo campione
		detection = new SlowTestDetection(testExecutions);
		assertNotNull(detection.findRegressionId(10,10)); //1416
		
	}
	
	@Test
	public void testRegressionRegressionExample4() throws Exception {
			
		timesAre(1414, 1632, 1487, 1388, 1331, 1317, 1662, 1519, 1456, 1716, 1398, 1654, 2135, 1670, 2012);
		timesAre(2067, 1709, 1548, 1495, 1771, 1737, 1731, 1654, 1668, 1995, 1711, 1839, 1552, 1757, 1864);
		
		
		assertNotNull(detection.findRegressionId(10,10));
		assertNotNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testRegressionRegressionExample5() throws Exception {
		timesAre(5603, 4998, 5102, 4416, 5036, 5002, 4732, 4773, 4594, 5418, 5057);
		timesAre(10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000);
		
		assertNotNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNotNull(detection.findRegressionId(10,7));
	}
	
	@Test
	public void testRegressionRegressionExample6() throws Exception {
		timesAre(5603, 4998, 5102, 4416, 5036, 5002, 4732, 4773, 4594, 5418, 5057);
		timesAre(4453, 6407, 4955, 6074, 5929, 6151, 6741, 6688, 6761, 6538, 6711, 6621, 5776);

		
		assertNotNull(detection.findRegressionId(10,7));
		detection = new SlowTestDetection(testExecutions);
		assertNotNull(detection.findRegressionId(10,10));
		
		
	}
	
	@Test
	public void testRegressionRegressionExample7() throws Exception {
		timesAre(5603, 4998, 5102, 4416, 5036, 5002, 4732, 4773, 4594, 5418, 5057);
		timesAre(4453, 6407, 4955, 6074, 5929, 6151, 6741, 6688, 6761, 6538, 6711, 6621, 5776);
		timesAre(6151, 6741, 6688, 6761, 6538, 6711, 6621, 5776);
		//Infatti nessun problema!
		
		assertNotNull(detection.findRegressionId(10,7));
		detection = new SlowTestDetection(testExecutions);
		assertNotNull(detection.findRegressionId(10,10));
		
		
	}
	
	@Test
	public void testRegressionRegressionExample8() throws Exception {
		timesAre(5603, 4998, 5102, 4416, 5036, 5002, 4732, 4773, 4594, 5418, 5057);
		timesAre(4453, 6407, 4955, 6074, 5929, 6151, 6741, 6688, 6761, 6538, 6711, 6621, 5776);
		timesAre(5651, 5741, 5688, 5761, 5838, 5555, 5721, 5776);//solo un elemento minore del massimo!, 5555
		
		//verificare con il box plot
		
		assertNotNull(detection.findRegressionId(10,7));
		detection = new SlowTestDetection(testExecutions);
		assertNotNull(detection.findRegressionId(10,10));
		
		
	}
	
	@Test
	public void testRegressionRegressionExample9() throws Exception {
		timesAre(5603, 5498, 5102, 4416, 5336, 5002, 4732, 4773, 4594, 5418, 5057);
		timesAre(4453, 6407, 4955, 6074, 5929, 6151, 6741, 6688, 6761, 6538, 6711, 6621, 5776);
		timesAre(5651, 5741, 5688, 5761, 5838, 5300, 5721, 5776);//solo un elemento minore a metà
		//Infatti nessun problema!

		//verificare con il box plot
		assertNotNull(detection.findRegressionId(10,7));
		detection = new SlowTestDetection(testExecutions);
		assertNotNull(detection.findRegressionId(10,10));
		
		
	}
	
	@Test
	public void testRegressionRegressionExample10() throws Exception {
		timesAre(5603, 4998, 5102, 4416, 5036, 5002, 4732, 4773, 4594, 5418, 5057);
		timesAre(4453, 6407, 6955, 7074, 6929, 5651, 6741, 6688, 6761, 7538, 6711, 6621, 7476);
		
		assertNotNull(detection.findRegressionId(10,7));
		detection = new SlowTestDetection(testExecutions);
		assertNotNull(detection.findRegressionId(10,10));
		
		
	}
	
	private void timesAre(int... duration){
		int i=0;
		for (int i_time : duration) {
			testExecutions.add(new TimeExecution(i_time,i++));
		}
		
	}

}
