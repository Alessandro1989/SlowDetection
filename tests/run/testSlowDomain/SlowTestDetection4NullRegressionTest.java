package run.testSlowDomain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.exception.*;
import jetbrains.sample.statistic.*;
import jetbrains.sample.testSlowDomain.*;

public class SlowTestDetection4NullRegressionTest{

	
	private List<TimeExecution> testExecutions;
	private SlowTestDetection detection;
	private static final double DELTA = 0.1; //variazione consentita
	
	@Before
	public void before() {
		testExecutions = new ArrayList<TimeExecution>();
		detection = new SlowTestDetection(testExecutions);
	}
	
	@Test
	public void testNullRegressionRegressionExample1() throws Exception {
		timesAre(6196, 6251, 6214, 5670, 6415, 6253, 5569, 5953, 5584, 6085, 6011, 6099);
		timesAre(6082, 6202, 6381, 6711, 5731, 6243, 5575, 6121, 5891, 6153, 5995, 5978);
		timesAre(5639, 5549, 5692, 6065, 5943, 5984, 5950, 6011, 5834, 5980, 5661, 5941);
		timesAre(6022, 6068, 5591, 6088, 6045, 5552, 5632, 15377, 10350, 5626, 6199, 5591, 5605, 5557);
		 
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
	}
	
	@Test
	public void testNullRegressionRegressionExample2() throws Exception {
		timesAre(5591, 5093, 5710, 5858, 6119, 5732, 5261, 5424, 5434);
		timesAre(5656, 5076, 5532, 5430, 5161, 5314, 5535, 5217, 5182);
		timesAre(5246, 5474, 5440, 5148, 5078, 5092, 5082, 5177, 5603);
		timesAre(5122, 5577, 5169, 5484, 5513, 6359, 12554, 5710, 5102, 5555, 5652, 5546); 
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
	}
	
	@Test
	public void testNullRegressionRegressionExample3() throws Exception {
		
		timesAre(1279, 1816, 1343, 1314, 921, 1018, 975, 1433, 1518, 916, 1174, 870, 1500, 1381, 1236, 1054);
		timesAre(1027, 961, 1004, 988, 1004, 954, 1340, 1016, 1311, 925, 1213, 869, 884, 1255, 1070, 938, 861);
		timesAre(1062, 715, 1132, 973, 844, 930, 808, 706, 678, 651, 444, 911, 690, 639, 658, 657, 642);
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
				
		
	}

	@Test
	public void testNullRegressionRegressionExample4() throws Exception {
			
		timesAre(1414, 1632, 1487, 1388, 1331, 1317, 1662, 1519, 1456, 1716, 1398, 1654, 2135, 1670, 2012);
		timesAre(2067, 1409, 1248, 1295, 1771, 1737, 1531, 1354, 1568, 1295, 1711, 1239, 1552, 1757, 1464);
		timesAre(1645, 1503, 1644, 1370, 1616, 1220, 1570, 1337, 1561, 1572, 1532, 631, 618, 784, 611, 635);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample5() throws Exception {
		timesAre(3825, 4141, 3901, 3974, 6082, 8542, 4937, 3937, 4057, 3906, 4094, 3626);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(6,6));
	}
	
	@Test
	public void testNullRegressionRegressionExample6() throws Exception {
	
		timesAre(4078, 3606, 3650, 3948, 7661, 5324, 5222, 3615, 4819, 3567, 3627, 4200);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(6,6));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample7() throws Exception {
		
		timesAre(5603, 4998, 5102, 4416, 5036, 5002, 4732, 4773, 4594, 5418, 5057);
		timesAre(4453, 6407, 4955, 5074, 4929, 5151, 6741, 4688, 4761, 3538, 3711, 3621, 3476);
		timesAre(3395, 3723, 7341, 6169, 3612, 3641, 3494, 3597, 3544, 3735); 
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample8() throws Exception {
		//Alta dispersione!..	
		timesAre(75, 305, 81, 257, 222, 70, 78, 219, 376, 114, 277, 75, 406, 137, 103, 68, 263, 70, 260, 75);
		timesAre(69, 285, 73, 230, 268, 264, 219, 75, 76, 78, 305, 73, 73, 72, 70, 73, 245, 325, 72, 275, 261);
		timesAre(68, 72, 71, 221, 221, 263, 79, 82, 75); 
	
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
	}
	
	@Test
	public void testNullRegressionRegressionExample9() throws Exception {
		
		timesAre(128, 238, 276, 222, 69, 221, 71, 217, 282, 307, 74, 78, 288, 371, 73, 229, 77, 286, 228, 69);
		timesAre(73, 69, 227, 230, 223, 71, 67, 101, 71, 68, 71, 73, 72, 72, 75, 71, 227, 229, 73, 225, 225);
		timesAre(228, 74, 224, 221, 69, 224, 72, 70, 69); 
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample10() throws Exception {
		
		
		timesAre(111, 101, 93, 71, 221, 70, 220, 70, 87, 98, 236, 72, 78, 81, 80, 69, 268, 78, 88, 221, 67);
		timesAre(224, 69, 74, 72, 230, 224, 90, 66, 217, 221, 67, 68, 67, 68, 269, 69, 74, 66, 122, 68, 69, 68);
		timesAre(71, 69, 225, 70, 67, 215, 231);
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample11() throws Exception {
			
		
		timesAre(14230, 4361, 5525, 4694, 5428, 4363, 4754, 4584, 4278, 4242, 4485, 6186, 4328, 4062, 5900);
		timesAre(4552, 4223, 4477, 4988, 6222, 4337, 4146, 3070, 3006, 2984, 2918, 2910, 3224, 5014, 6782);
		timesAre(5037, 3160, 3228, 2896, 3157, 2975);
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample12() throws Exception {
		
		timesAre(3425, 3339, 3210, 3089, 3058, 2866, 11545, 4618, 5845, 2762, 4143, 3085, 3090, 2831); //sample 10
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(7,6));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample13() throws Exception {
		timesAre(5401, 6771, 8720, 6919, 7074, 8224, 6290, 5773, 5851, 5793, 7020, 5969, 5825, 7598, 6906);
		timesAre(6074, 6175, 6128, 7176, 5793, 6232, 5415, 5560, 5089, 5215, 4990, 5353, 11135, 13105, 5834);
		timesAre(5268, 5623, 4801, 5272, 5119);	
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
	}
	
	@Test
	public void testNullRegressionRegressionExample14() throws Exception {	
		timesAre(147, 436, 567, 117, 386, 101, 367, 358, 108, 153, 402, 154, 138, 183, 649, 127, 120, 120, 124); //sample 10
		timesAre(395, 141, 126, 107, 398, 134, 135, 359, 142, 381, 382, 392, 404, 397, 403, 131, 124, 103, 122);
		timesAre(129, 138, 111, 126, 154, 115, 440, 379, 121, 125, 364, 392);
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample15() throws Exception {
			
		timesAre(342, 67, 95, 63, 66, 63, 66, 63, 77, 69, 113, 244, 76, 81, 250, 63, 63, 72, 93, 68, 65, 64); 
		timesAre(63, 65, 64, 65, 64, 271, 215, 67, 68, 63, 242, 220, 221, 73, 63, 64, 65, 70, 62, 63, 218, 63);
		timesAre(64, 66, 63, 66, 67, 68);
			
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample16() throws Exception {
				
		timesAre(278, 81, 46, 37, 35, 34, 34, 35, 37, 63, 37, 443, 69, 77, 60, 295, 38, 38, 40, 73, 44, 38, 37);
		timesAre(36, 64, 38, 40, 45, 35, 39, 35, 37, 36, 34, 280, 284, 37, 37, 40, 64, 38, 39, 269, 37, 41, 37);
		timesAre(39, 41, 36, 36);
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample17() throws Exception {

		timesAre(1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1);
		timesAre(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1);
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
	}
	
	@Test
	public void testNullRegressionRegressionExample18() throws Exception {
		
		timesAre(5262, 4904, 5227, 4703, 4573, 4948, 4653, 4689, 4560, 5484, 4734, 4406, 5937, 4984, 4521);
		timesAre(4883, 4938, 6434, 4362, 4699, 3517, 3273, 3215, 3416, 3257, 3265, 7008, 3168, 3491, 3228);
		timesAre(3501, 3360, 3511, 3217);
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	@Test
	public void testNullRegressionRegressionExample19() throws Exception {
		
		timesAre(198, 131, 159, 209, 233, 218, 162, 208, 143, 250); //sample 10
		timesAre(195, 216, 245, 213, 216, 271, 127, 116, 197, 212, 221, 242, 263, 194, 252, 201, 190);
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
	}
	@Test
	public void testNullRegressionRegressionExample20() throws Exception {
		timesAre(2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 112, 106, 1, 1, 1, 1, 1, 1, 107, 1, 1, 1, 2, 1, 1);
		timesAre(2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 6, 1, 1, 1, 1, 1, 1);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
	}@Test
	public void testNullRegressionRegressionExample21() throws Exception {
		
		timesAre(3425, 3339, 3210, 3089, 3058, 2866, 11545, 4618, 5845, 2762, 4143, 3085, 3090, 2831);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));

	}
	@Test
	public void testNullRegressionRegressionExample22() throws Exception {
		
		timesAre(639, 444, 637, 457, 612, 642, 406, 618, 668, 609, 450, 616, 472, 645, 649, 669, 594, 406, 431); //sample 10
		timesAre(572, 446, 606, 455, 574, 584, 468, 612, 578, 610, 607, 582, 429, 385, 459, 592, 563, 591, 587);
		timesAre(609, 390, 383, 380, 579, 389, 576, 443, 384, 608, 609, 385); 
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}@Test
	public void testNullRegressionRegressionExample23() throws Exception {
	
		timesAre(618, 680, 839, 655, 803, 801, 622, 846, 870, 664, 593, 704, 714, 663, 642, 651, 587, 572, 634);
		timesAre(715, 620, 596, 598, 772, 776, 613, 584, 775, 670, 852, 577, 606, 573, 643, 761, 565, 581, 776);
		timesAre(572, 580, 761, 577, 767, 572, 573, 627, 572, 580, 594, 570); 
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
	
	}
	@Test
	public void testNullRegressionRegressionExample24() throws Exception {

		timesAre(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		timesAre(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1);
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));

	}
	
	@Test
	public void testNullRegressionRegressionExample25() throws Exception {

		timesAre(1, 2, 1, 2, 1, 1, 1, 0, 1, 0, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		timesAre(2, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));

	}
	
	
	@Test
	public void testNullRegressionRegressionExample26() throws Exception {
		
		timesAre(153, 390, 371, 403, 118, 139, 407, 367, 318, 357, 548, 128, 417, 150, 137, 148, 124, 123, 143);
		timesAre(138, 323, 124, 341, 357, 429, 424, 326, 145, 145, 332, 377, 124, 406, 477, 321, 408, 422, 119);
		timesAre(370, 327, 125, 312, 118, 119, 123, 135, 118, 120, 359, 122);
		
		assertNull(detection.findRegressionId(10,10));	
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	@Test
	public void testNullRegressionRegressionExample27() throws Exception {
		
		timesAre(113, 353, 330, 129, 107, 108, 316, 129, 343, 129, 348, 130, 370, 135, 114, 128, 119, 117, 334);
		timesAre(321, 325, 121, 351, 111, 128, 331, 125, 110, 113, 121, 107, 443, 306, 318, 114, 318, 106, 105);
		timesAre(114, 316, 302, 312, 103, 302, 133, 349, 308, 117, 418, 314);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	@Test
	public void testNullRegressionRegressionExample28() throws Exception {
		
		timesAre(134, 350, 126, 119, 129, 119, 344, 127, 130, 374, 112, 145, 381, 376, 136, 365, 324, 318, 354);
		timesAre(361, 324, 130, 325, 111, 124, 116, 114, 112, 368, 122, 116, 112, 322, 119, 109, 322, 133, 109);
		timesAre(337, 319, 312, 107, 110, 109, 309, 342, 318, 117, 330, 314);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	@Test
	public void testNullRegressionRegressionExample29() throws Exception {
		
		timesAre(123, 363, 121, 435, 126, 115, 348, 116, 119, 119, 118, 120, 362, 126, 138, 125, 105, 294, 344);
		timesAre(105, 326, 120, 339, 103, 111, 124, 112, 102, 132, 115, 107, 329, 309, 120, 103, 107, 137, 114);
		timesAre(112, 298, 294, 102, 105, 103, 307, 325, 303, 108, 107, 296);
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample30() throws Exception {
		
		timesAre(87, 97, 74, 76, 71, 226, 65, 71, 93, 527, 75, 82, 100, 317, 115, 66, 76, 270, 75, 76, 313, 82);
		timesAre(233, 72, 78, 74, 70, 278, 66, 68, 88, 71, 67, 71, 68, 66, 75, 75, 297, 80, 79, 317, 69, 264);
		timesAre(71, 72, 79, 296, 67, 67); 
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample31() throws Exception {
		
		timesAre(248, 74, 87, 72, 85, 69, 222, 70, 75, 93, 78, 391, 95, 79, 420, 82, 100, 76, 70, 229, 231, 73);
		timesAre(71, 76, 69, 73, 73, 232, 226, 226, 76, 227, 228, 228, 228, 225, 69, 71, 224, 72, 69, 75, 226);
		timesAre(69, 69, 74, 70, 228, 227, 227); 
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7)); //FAIL
	}
	@Test
	public void testNullRegressionRegressionExample32() throws Exception {
			
		
		timesAre(1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 110); //sample 10
		timesAre(1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0, 1, 3, 1, 1, 1, 1, 1, 1);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	@Test
	public void testNullRegressionRegressionExample33_1() throws Exception {
		
		
		timesAre(699,  684,  760,  639,  637,  909,  698,  745,  653,  734,  655,  634,  854,  805,  685);
		timesAre(661,  711,  665,  595,  652,  708,  645,  672,  593,  660,  651,  862,  812,  660,  676);
		timesAre(813,  647,  843,  608,  724,  887,  670,  646,  697,  601,  660,  671,  697,  778,  715); 
		timesAre(890,  841,  643,  595, 907);
		
		assertNull(detection.findRegressionId(10,10));	
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample34_2() throws Exception {
			
		timesAre(9,  9,  10,  24,  9,  9,  9,  92,  107,  9,  8,  8,  8,  9,  9,  92,  93,  92,  8,  90,  9);
		timesAre(9,  10,  91,  92,  10,  9,  7,  9,  8,  11,  8,  8,  94,  9,  8,  9,  8,  8,  10,  8,  10);
		timesAre(10,  10,  9,  10,  8,  8,  9, 11); 
					
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample35_3() throws Exception {
	
		//non molto corretta
		timesAre(1703, 597, 499, 460, 433, 483, 492, 372, 356, 465, 446, 500, 459, 472, 411, 371, 488, 372); //sample 10
		timesAre(389, 394, 534, 371, 416, 444, 366, 467, 593, 397, 483, 363, 432, 519, 512, 401, 524, 528);
	
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));

	}
	
	@Test
	public void testNullRegressionRegressionExample36_4() throws Exception {

		timesAre(190, 201, 245, 201, 252, 194, 263, 273, 242, 221, 212, 197, 116, 127, 271, 216, 213, 245);
		timesAre(216, 195, 195, 259, 198, 244, 267, 201, 231, 279, 203, 203, 191, 185, 265, 201, 127, 248, 219);
		timesAre(201, 198, 121, 198, 131, 159, 209, 233, 218, 162, 208, 143, 250);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample37_5() throws Exception {
		
		timesAre(389, 394, 534, 371, 416, 444, 366, 467, 593, 397, 483, 363, 432, 519, 512, 401, 524, 528);
		timesAre(414, 459, 502, 539, 510, 361, 557, 440, 436, 359, 372, 468, 420, 356, 465, 446, 500, 459, 472);
		timesAre(411, 371, 488, 372, 492, 483, 437, 407, 433, 460, 499, 597, 1000);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample38_6() throws Exception {	
	
		timesAre(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		timesAre(89, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1);
			
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
	
	}
	
	@Test
	public void testNullRegressionRegressionExample39_7() throws Exception {
	
		timesAre(10, 8, 8, 9, 11, 10, 97, 94, 96, 8, 98, 8, 9, 9, 8, 9, 8, 9, 9, 9, 10, 9, 8, 8, 12, 8, 8, 10); //sample 10
		timesAre(9, 9, 8, 9, 8, 11, 8, 9, 9, 9, 8, 8, 92, 9, 9, 9, 12, 9, 8, 10, 9, 10);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample40_8() throws Exception {	
		
		timesAre(9, 8, 9, 91, 9, 98, 9, 7, 8, 8, 7, 8, 8, 15, 8, 9, 8, 8, 8, 8, 8, 9, 8, 8, 10, 8, 9, 106, 8); //sample 10
		timesAre(9, 7, 90, 8, 8, 8, 8, 10, 8, 8, 9, 8, 8, 8, 8, 8, 93, 8, 9, 9, 8);
				
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));
		
	}
	
	@Test
	public void testNullRegressionRegressionExample41_9() throws Exception {

		timesAre(9, 96, 8, 8, 9, 9, 8, 8, 8, 8, 9, 7, 8, 107, 95, 8, 10, 97, 98, 9, 8, 8, 7, 8, 10, 10, 10); //sample 10
		timesAre(10, 9, 9, 9, 9, 9, 10, 9, 8, 8, 8, 8, 8, 8, 10, 9, 8, 8, 9, 8, 11, 12, 8);

		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));

	}
	
	@Test
	public void testNullRegressionRegressionExample41_10() throws Exception {
		timesAre(2, 2, 2, 97, 2, 2, 2, 2, 2, 2, 2, 3, 3, 2, 2, 3, 4, 2, 3, 2, 2, 2, 3, 2, 2, 2, 4, 2, 2, 3, 2); //sample 10
		timesAre(2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 3, 2, 2, 2, 2);
		
		assertNull(detection.findRegressionId(10,10));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(10,7));

	}
		

	//Le regressioni che ha restituito sul progetto prova e che non dovrebbe dare!
	
	@Test
	public void testNullRegressionRegressionExample42_1() throws Exception {
		timesAre(5, 5, 4, 6, 6, 5, 7, 4, 4, 6, 4, 4, 23, 5, 5, 4, 4, 5, 5, 4, 5, 4, 5, 5, 4, 4, 4, 5, 6, 6, 4);
		timesAre(7, 4, 6, 5, 5, 5, 5, 6, 4, 29, 5, 6, 4, 6, 5, 7, 5, 5, 5, 6, 8, 9, 13, 9, 8, 14, 11, 13, 7, 14, 5, 5);
		
		//assertNull(detection.findRegressionId(20,20));
		
		
		//5, 5, 4, 6, 6, 5, 7, 4, 4, 6, 4, 4, 5, 5, 4, 4, 5, 5, 4, 5
		//5, 5, 14, 7, 13, 11, 14, 8, 9, 13, 9, 8, 6, 5, 5, 5, 7, 5, 6, 4
		//ha la maggior parte degli elementi maggiori
		
		assertNull(detection.findRegressionId(15,15));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(11,11));// errore
		 
		
		
	}
		
	
	@Test
	public void testNullRegressionRegressionExample43_2() throws Exception {
		timesAre(23, 15, 16, 18, 20, 15, 17, 17, 17, 16, 18, 21, 19, 19, 28, 15, 15, 15, 17, 25, 21, 16, 24, 22, 18, 23, 20, 16, 18, 28, 41);
		timesAre(19, 18, 17, 19, 21, 16, 15, 17, 17, 19, 53, 19, 15, 17, 45, 68, 17, 22, 20, 104, 18, 26, 24, 19, 17, 18, 20, 23, 24, 17, 13, 14);
		
		
		assertNull(detection.findRegressionId(15,15));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(11,11)); //infatti su prova da regression test!
	}
	
	@Test
	public void testNullRegressionRegressionExample44_3() throws Exception {
		timesAre(31, 31, 32, 49, 45, 34, 31, 29, 43, 53, 80, 32, 39, 33, 39, 33, 32, 30, 31, 31, 32, 29, 111, 33, 36, 44, 45, 47, 37, 61);
		timesAre(50, 40, 42, 39, 45, 78, 28, 45, 37, 46, 30, 34, 41, 31, 35, 90, 73, 196, 52, 65, 33, 131, 114, 69, 63, 33, 119, 34, 108, 66, 31, 139, 97);
		
		detection.findRegressionId(20,20);
		System.out.println("campione base: ");
		for(int i=0;i<detection.getSampleBase().size();i++){
			System.out.print(detection.getSampleBase().get(i)+", ");
		}
		System.out.println("campione finale: ");
		for(int i=0;i<detection.getSampleLast().size();i++){
			System.out.print(detection.getSampleLast().get(i)+", ");
		}
		/*31, 31, 32, 49, 45, 34, 31, 29, 43, 53, 32, 39, 33, 39, 33, 32, 30, 31, 31, 32;
		 *97, 139, 31, 66, 108, 34, 119, 33, 63, 69, 114, 131, 33, 65, 52, 196, 73, 90, 35, 31;
		 */
		assertNull(detection.findRegressionId(20,20));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(15,15));
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(11,11));
	}
	
	@Test
	public void testNullRegressionRegressionExample45_4() throws Exception {
		timesAre(31, 26, 26, 35, 31, 29, 29, 27, 37, 29, 30, 40, 63, 38, 34, 30, 27, 27, 27, 31, 27, 61, 27, 32, 70, 48, 32, 33, 34, 56, 36);
		timesAre(39, 30, 47, 37, 44, 34, 31, 28, 28, 30, 31, 39, 35, 29, 37, 53, 43, 29, 37, 29, 48, 40, 48, 31, 32, 33, 34, 37, 132, 74, 34, 38);
			
		
		assertNull(detection.findRegressionId(15,15)); //qui passa
		detection = new SlowTestDetection(testExecutions);
		assertNull(detection.findRegressionId(11,11)); //infatti su prova da regression test!
	}
	
	@Test
	public void testNullRegressionRegressionExample46_5() throws Exception {
		timesAre(701, 482, 483, 1229, 739, 545, 557, 553, 547, 538, 540, 554, 985, 600, 943, 647, 459, 495, 550, 435, 1128, 452, 2811, 656);
		timesAre(1950, 2036, 569, 2333, 1010, 2767, 1509, 936, 729, 592, 714, 527, 583, 1035, 916, 540, 567, 716, 653, 669, 1106, 1813, 1411, 818, 494);
		timesAre(1121, 1244, 1078, 480, 1738, 582, 445, 1794, 469, 1648, 2046, 1244, 3472, 806);
			
		assertNull(detection.findRegressionId(11,11)); //infatti su prova da regression test!
	}
	
	@Test
	public void testNullRegressionRegressionExample47_6() throws Exception {
		timesAre(7, 6, 6, 6, 8, 7, 6, 8, 6, 7, 6, 8, 7, 8, 7, 8, 7, 6, 6, 9, 7, 10, 9, 6, 9, 6, 9, 6, 8, 18, 10, 7, 7, 7, 7, 8, 7, 6, 7, 7);
		timesAre(7, 13, 9, 6, 6, 9, 8, 8, 8, 7, 33, 8, 11, 10, 7, 8, 8, 6, 7, 14, 5, 5, 7);
		//addSport
		assertNull(detection.findRegressionId(11,11)); //infatti su prova da regression test!
	}

    @Test
    public void testNullRegressionThresholdExample48() throws Exception {
        timesAre(7,  7, 8, 9, 8, 7, 7, 8, 7, 8, 9, 7,  7, 8, 9, 8);
        timesAre(8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9);
        //addSport
        assertNotNull(detection.findRegressionId(11,11)); //infatti su prova da regression test!
        detection = new SlowTestDetection(testExecutions);
        assertNull(detection.findRegressionId(11,11,2,0));
        detection = new SlowTestDetection(testExecutions);
        assertNotNull(detection.findRegressionId(11,11,0,10));
        detection = new SlowTestDetection(testExecutions);
        assertNull(detection.findRegressionId(11,11,0,40));
        detection = new SlowTestDetection(testExecutions);
        assertNull(detection.findRegressionId(11,11,3,10));

    }

	
	
	private void timesAre(int... duration){
		int i=0;
		for (int i_time : duration) {
			testExecutions.add(new TimeExecution(i_time,i++));
		}
		
	}
	
	

}
