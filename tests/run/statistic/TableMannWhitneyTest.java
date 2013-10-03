package run.statistic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.statistic.TableMannWhitney;

                       //
public class TableMannWhitneyTest {	
	private TableMannWhitney table = new TableMannWhitney();
	String text1Percentage ="- - - - - - - - - - - - - - - - - - - -;"+
			"- - - - - - - - - - - - - - - - - - 0 0;"+
			"- - - - - - - - 0 0 0 1 1 1 2 2 2 2 3 3;"+
			"- - - - 0 0 1 1 2 2 3 3 4 5 5 6 6 7 7 8;"+
			"- - - - 0 1 1 2 3 4 5 6 7 7 8 9 10 11 12 13;"+
			"- - - 0 1 2 3 4 5 6 7 9 10 11 12 13 15 16 17 18;"+
			"- - - 0 1 3 4 6 7 9 10 12 13 15 16 18 19 21 22 24;"+
			"- - - 1 2 4 6 7 9 11 13 15 17 18 20 22 24 26 28 30;"+
			"- - 0 1 3 5 7 9 11 13 16 18 20 22 24 27 29 31 33 36;"+
			"- - 0 2 4 6 9 11 13 16 18 21 24 26 29 31 34 37 39 42;"+
			"- - 0 2 5 7 10 13 16 18 21 24 27 30 33 36 39 42 45 46;"+
			"- - 1 3 6 9 12 15 18 21 24 27 31 34 37 41 44 47 51 54;"+
			"- - 1 3 7 10 13 17 20 24 27 31 34 38 42 45 49 53 56 60;"+
			"- - 1 4 7 11 15 18 22 26 30 34 38 42 46 50 54 58 63 67;"+
			"- - 2 5 8 12 16 20 24 29 33 37 42 46 51 55 60 64 69 73;"+
			"- - 2 5 9 13 18 22 27 31 36 41 45 50 55 60 65 70 74 79;"+
			"- - 2 6 10 15 19 24 29 34 39 44 49 54 60 65 70 75 81 86;"+
			"- - 2 6 11 16 21 26 31 37 42 47 53 58 64 70 75 81 87 92;"+
			"- 0 3 7 12 17 22 28 33 39 45 51 56 63 69 74 81 87 93 99;"+
			"- 0 3 8 13 18 24 30 36 42 46 54 60 67 73 79 86 92 99 105;";
	
	@Test
	public void testTableEquals() {
		String[][] arrayTable= table.getTableMannWhitney();
	
		String textTable="";
		for(int j=0; j<20; j++){
			for(int i=0; i<20; i++){
				textTable+=arrayTable[j][i];
				if(i<19)
					textTable+=" ";
			}
			textTable+=";";
		}
		
		System.out.println(textTable);
	    assertTrue(textTable.equals(text1Percentage));
	}

	@Test
	public void findValue() {
		assertEquals("-",table.findValue(1,1));
		assertEquals("-",table.findValue(1,3));
		assertEquals("-",table.findValue(1,6));
		assertEquals("-",table.findValue(1,20));
		assertEquals("-",table.findValue(15,1));
		assertEquals("3",table.findValue(3,20));
		assertEquals("16",table.findValue(10,10));
		assertEquals("9",table.findValue(12,6));
		assertEquals("39",table.findValue(11,17));
		assertEquals("38",table.findValue(13,14));
		assertEquals("10",table.findValue(17,5));
		assertEquals("13",table.findValue(20,5));
		assertEquals("99",table.findValue(20,19));
		assertEquals("30",table.findValue(20,8));
		assertEquals("99",table.findValue(20,19));
		assertEquals("105",table.findValue(20,20));
	}

	@Test
	public void AcceptHo() {
		assertTrue(table.AcceptHo(10, 10, 28));//23
		assertTrue(table.AcceptHo(9, 3, 3));//0
		assertFalse(table.AcceptHo(9, 7, 7));//7
		assertFalse(table.AcceptHo(9, 7, 6));//7
		assertTrue(table.AcceptHo(9, 7, 8));//7
		assertTrue(table.AcceptHo(12, 12, 28));//27
		assertFalse(table.AcceptHo(12, 12, 10));//27
		assertTrue(table.AcceptHo(16, 20, 100));//79
		assertFalse(table.AcceptHo(20, 15, 28));//73
	}

}
