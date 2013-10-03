package run.testSlowDomain;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.exception.*;
import jetbrains.sample.testSlowDomain.*;

public class BuildPrjTest {
    //
	BuildPrj build;
	ArrayList<SingleTest> tests;
	
	@Before
	public void before(){
		build = new BuildPrj("prova");
		tests = new ArrayList<SingleTest>();
	}
	
	@Test
	public void addTest(){
		SingleTest test = new SingleTest("insert",0);
		build.addTest(test);
		assertEquals(1,build.size());
		assertEquals("insert",build.getTests().get(0).getName());
		assertEquals(0,build.getTests().get(0).getIdTest());
		test = new SingleTest("delete",1);
		build.addTest(test);
		assertEquals(2,build.size());
		assertEquals("delete",build.getTests().get(1).getName());
		assertEquals(1,build.getTests().get(1).getIdTest());
	}
	
	@Test
	public void Sort(){
		TestsNameIdAre("insert;0","delete;1","update;43","game;37","check;25","make;26");
		build.setTests(tests);
		
		build.sort();
		
		assertEquals(0,build.getTests().get(0).getIdTest());
		assertEquals(1,build.getTests().get(1).getIdTest());
		assertEquals(25,build.getTests().get(2).getIdTest());
		assertEquals(26,build.getTests().get(3).getIdTest());
		assertEquals(37,build.getTests().get(4).getIdTest());
		assertEquals(43,build.getTests().get(5).getIdTest());
		
		assertEquals(new Long(0),build.getTestsId()[0]);
		assertEquals(new Long(1),build.getTestsId()[1]);
		assertEquals(new Long(25),build.getTestsId()[2]);
		assertEquals(new Long(26),build.getTestsId()[3]);
		assertEquals(new Long(37),build.getTestsId()[4]);
		assertEquals(new Long(43),build.getTestsId()[5]);
		
		/*
		.....testsare..
		//System.out.println(tests.size());
		
		// 20-30 secondi ordinare 125 tests 1 milione di volte
		//n log n, 2,7*Operazioni*125
		 
		// 4-5 secondi ordinare 1000 tests 10 mila volte
		//n log n, 3*Operazioni*1000
		System.out.println("INIZIO ordinamento");
		for(int i=0;i<10000;i++){//10 mila build per 10 mila tests
			if((i%1000)==0)
				System.out.println(i);
			build.setTests(tests); //li scombino e isSorted diventa false
			build.sort();
		}
		System.out.println("FINE");
		*/
		
	}
	
	@Test
	public void isAlreadySorted() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, InvalideSizeTimesException, IllegalTime{
		assertEquals(false,build.getIsSorted());
		TestsNameIdAre("insert;0","delete;1","update;43","game;37","check;25","make;26");
		build.setTests(tests);
		assertEquals(build.size(),6);
		assertEquals(false,build.getIsSorted());
		build.sort();
		assertEquals(true,build.getIsSorted());
		build.setTests(tests);
		assertEquals(false,build.getIsSorted());
		build.sort();
		assertEquals(true,build.getIsSorted());
		build.findTest(3);
		assertEquals(true,build.getIsSorted());
		build.getName();
		assertEquals(true,build.getIsSorted());
		build.getTestsRegression(20, 20);
		build.size();
		build.oldRun(tests);
		assertEquals(true,build.getIsSorted());	
		assertEquals(build.size(),6);
		build.addTest(new SingleTest("fake",3));
		assertEquals(build.size(),7);
		assertEquals(false,build.getIsSorted());
		build.getName();
		assertEquals(false,build.getIsSorted());
		build.size();
		assertEquals(false,build.getIsSorted());
		build.sort();
		assertEquals(true,build.getIsSorted());
		assertEquals(build.size(),7);
		build.getTests();
		assertEquals(false,build.getIsSorted());
		assertEquals(build.getTests().get(2).getName(),"fake");
		build.sort();
		assertEquals(true,build.getIsSorted());
		build.setIsSortedAtfalse();
		assertEquals(false,build.getIsSorted());
	} 
	
	@Test
	public void findTest_Test(){
		TestsNameIdAre("insert;0","delete;1","update;43","game;37","check;25","make;26", "fake;8");
		build.setTests(tests);
		assertEquals(build.size(),7);
		assertEquals(-1,build.findTest(999));
		assertEquals(26,build.getSingleTest(build.findTest(26)).getIdTest());
		assertEquals("make",build.getSingleTest(build.findTest(26)).getName());
		
		assertEquals(25,build.getSingleTest(build.findTest(25)).getIdTest());
		assertEquals("check",build.getSingleTest(build.findTest(25)).getName());
		
		assertEquals(43,build.getSingleTest(build.findTest(43)).getIdTest());
		assertEquals("update",build.getSingleTest(build.findTest(43)).getName());
		
		assertEquals(37,build.getSingleTest(build.findTest(37)).getIdTest());
		assertEquals("game",build.getSingleTest(build.findTest(37)).getName());
		
		assertEquals(1,build.getSingleTest(build.findTest(1)).getIdTest());
		assertEquals("delete",build.getSingleTest(build.findTest(1)).getName());
		
		assertEquals(0,build.getSingleTest(build.findTest(0)).getIdTest());
		assertEquals("insert",build.getSingleTest(build.findTest(0)).getName());
		
		assertEquals(8,build.getSingleTest(build.findTest(8)).getIdTest());
		assertEquals("fake",build.getSingleTest(build.findTest(8)).getName());
		
		//ritorna tests ordinati
		build.sort();
		assertEquals(0,build.getTests().get(0).getIdTest());
		assertEquals(1,build.getTests().get(1).getIdTest());
		assertEquals(8,build.getTests().get(2).getIdTest());
		assertEquals(25,build.getTests().get(3).getIdTest());
		assertEquals(26,build.getTests().get(4).getIdTest());
		assertEquals(37,build.getTests().get(5).getIdTest());
		assertEquals(43,build.getTests().get(6).getIdTest());
		
		assertEquals(-1,build.findTest(-2));
		
		assertEquals(26,build.getSingleTest(build.findTest(26)).getIdTest());
		assertEquals("make",build.getSingleTest(build.findTest(26)).getName());
		
		assertEquals(25,build.getSingleTest(build.findTest(25)).getIdTest());
		assertEquals("check",build.getSingleTest(build.findTest(25)).getName());
		
		assertEquals(43,build.getSingleTest(build.findTest(43)).getIdTest());
		assertEquals("update",build.getSingleTest(build.findTest(43)).getName());
		
		assertEquals(37,build.getSingleTest(build.findTest(37)).getIdTest());
		assertEquals("game",build.getSingleTest(build.findTest(37)).getName());
		
		assertEquals(1,build.getSingleTest(build.findTest(1)).getIdTest());
		assertEquals("delete",build.getSingleTest(build.findTest(1)).getName());
		
		assertEquals(0,build.getSingleTest(build.findTest(0)).getIdTest());
		assertEquals("insert",build.getSingleTest(build.findTest(0)).getName());
		
		assertEquals(8,build.getSingleTest(build.findTest(8)).getIdTest());
		assertEquals("fake",build.getSingleTest(build.findTest(8)).getName());
		
		//Test velocità, 1 miliardo di find tests su 125 tests
		//2 minuti e mezzo non ordinato: n/2 passaggi, 125/2=62 passaggi
		//1 minuto ordinato: lg(125)+1: 7-8, infatti spuntata 6-7 passaggi, ma con 4 operazioni.. quindi 7*4: 28 totale passaggi
		//Ma con tanti tests, 1000 tests, i passaggi sono 500 contro lg(1000)+1: 11 * 4 operazioni = 44 (10 volte più veloce!)  
		
		
		
	}
	
	@Test
	public void oldRun_Test() throws InvalideSizeTimesException{
		//fail è il caso di failure: non viene aggiunto il time
		TestsNameIdTimeAre(10,"insert;0;20","delete;1;18","update;43;15","game;37;fail","check;25;21","make;26;30");
		build.setTests(tests); //si mettono gli elementi dell'ultima run
		
		TestsNameIdTimeAre(9,"insert;0;15","delete;1;16","NewTest1;5;15","NewTest2;6;7","NewTest3;6;15",
				"update;43;fail","NewTest4;36;fail","NewTest5;-10;4","game;37;60","NewTest6;50;1","check;25;22","make;26;34");
		build.oldRun(tests);
		assertEquals(build.size(),6); //gli altri sono vecchi
		assertEquals(0,build.getTests().get(0).getIdTest());
		assertEquals(1,build.getTests().get(1).getIdTest());
		assertEquals(25,build.getTests().get(2).getIdTest());
		assertEquals(26,build.getTests().get(3).getIdTest());
		assertEquals(37,build.getTests().get(4).getIdTest());
		assertEquals(43,build.getTests().get(5).getIdTest());
		
		assertEquals(2,build.getTests().get(0).getTimes().size());
		assertEquals(1,build.getSingleTest(build.findTest(37)).getTimes().size());
		assertEquals(1,build.getTests().get(4).getTimes().size());
		assertEquals(1,build.getTests().get(5).getTimes().size());
		
		assertEquals(15,build.getTests().get(0).getTimes().get(0).getTime());
		assertEquals(20,build.getTests().get(0).getTimes().get(1).getTime());
		assertEquals(16,build.getTests().get(1).getTimes().get(0).getTime());
		assertEquals(18,build.getTests().get(1).getTimes().get(1).getTime());
		assertEquals(22,build.getTests().get(2).getTimes().get(0).getTime());
		assertEquals(21,build.getTests().get(2).getTimes().get(1).getTime());
		assertEquals(34,build.getTests().get(3).getTimes().get(0).getTime());
		assertEquals(30,build.getTests().get(3).getTimes().get(1).getTime());
		assertEquals(60,build.getTests().get(4).getTimes().get(0).getTime());
		assertEquals(15,build.getTests().get(5).getTimes().get(0).getTime());
	
		//Test velocità
		//1000 tests fatte 1000 volte: 20-30 secondi
		//addirittura sembra che la tecnica senza ordinamento, con il find normale lo batte (500 operazioni)*1000 tests
		//(6-7*Numero operazioni)*1000 tests build precedente..
		//ma con 10 mila tests, la tecnica senza ordinamento perde di gran lunga contro quella normale (5000 op.)*10000 tests
		//(9*Numero operazioni)*10000 tests build precedente
		//10 mila oldRun con 1000 tests
		//In sostanza: questo metodo si fa solo nell'avvio del server..se è possibile, velocizzarlo
		//IDEA: si potrebbe fare il calcolo del tempo che ci mette senza ordinarlo e con l'ordinamento
		//e scegliere la tecnica più opportuna, se ci sono pochi tests scegliere quella non efficiente.
		//cmq non è un grosso problema, perchè se sono pochi tests ci mette poco(anche se poco di più) anche la tecnica efficiente
		
		
		
	}
	
	@Test
	public void oldRun2_Test() throws InvalideSizeTimesException{
		
		TestsNameIdTimeAre(10,"insert;0;20","delete;1;18","create;27;15","fake;6;66","great;33;33","study;-18;17",
				"update;43;15","badTest;21;fail","game;37;fail","failTest;12;fail","check;25;21","make;26;30","NewTest1;13;fail");
		build.setTests(tests); //si mettono gli elementi dell'ultima run
		
		TestsNameIdTimeAre(9,"insert;0;15","delete;1;16","fake;6;55","OldTest1;7;15","failTest;12;fail",
				"update;43;fail","badTest;21;fail","game;37;60","great;33;23","OldTest2;50;1","check;25;22","make;26;34");
		build.oldRun(tests);
		
		TestsNameIdTimeAre(8,"insert;0;15","delete;1;10","NewTest1;120;4","OldTest1;7;15","update;43;fail","badTest;21;10",
				"game;37;50","great;33;22","check;25;20","make;26;32","failTest;12;fail");
		build.oldRun(tests);
		//non penso che un vecchio test e uno nuovo abbiano lo stesso id
		//possiamo fare volendo per sicurezza, che se nella vecchia build non è presente viene tolto dalla lista
		//non velocizza la ricerca un granchè dato che è lg n, anzi è più lenta perchè deve mettersi a eliminare gli elementi
		//la velocità dipende dal numero di tests della build precedente
		
		assertEquals(build.size(),13); //gli altri sono vecchi
		
		assertEquals(-18,build.getTests().get(0).getIdTest());
		assertEquals(0,build.getTests().get(1).getIdTest());
		assertEquals(1,build.getTests().get(2).getIdTest());
		assertEquals(6,build.getTests().get(3).getIdTest());
		assertEquals(12,build.getTests().get(4).getIdTest());
		assertEquals(13,build.getTests().get(5).getIdTest());
		assertEquals(21,build.getTests().get(6).getIdTest());
		assertEquals(25,build.getTests().get(7).getIdTest());
		assertEquals(26,build.getTests().get(8).getIdTest());
		assertEquals(27,build.getTests().get(9).getIdTest());
		assertEquals(33,build.getTests().get(10).getIdTest());
		assertEquals(37,build.getTests().get(11).getIdTest());
		assertEquals(43,build.getTests().get(12).getIdTest());
				
		assertEquals(1,build.getTests().get(0).getTimes().size());
		assertEquals(3,build.getTests().get(1).getTimes().size());
		assertEquals(3,build.getTests().get(2).getTimes().size());
		assertEquals(2,build.getTests().get(3).getTimes().size());
		assertEquals(0,build.getTests().get(4).getTimes().size());
		assertEquals(0,build.getTests().get(5).getTimes().size());
		assertEquals(1,build.getTests().get(6).getTimes().size());
		assertEquals(3,build.getTests().get(7).getTimes().size());
		assertEquals(3,build.getTests().get(8).getTimes().size());
		assertEquals(1,build.getTests().get(9).getTimes().size());
		assertEquals(3,build.getTests().get(10).getTimes().size());
		assertEquals(2,build.getTests().get(11).getTimes().size());
		assertEquals(1,build.getTests().get(12).getTimes().size());
		
		assertEquals(17,build.getTests().get(0).getTimes().get(0).getTime());
		assertEquals(15,build.getTests().get(1).getTimes().get(1).getTime());
		assertEquals(20,build.getTests().get(1).getTimes().get(2).getTime());
		assertEquals(20,build.getTests().get(1).getTimes().get(2).getTime());
		assertEquals(10,build.getTests().get(2).getTimes().get(0).getTime());
		assertEquals(16,build.getTests().get(2).getTimes().get(1).getTime());
		assertEquals(18,build.getTests().get(2).getTimes().get(2).getTime());
		assertEquals(55,build.getTests().get(3).getTimes().get(0).getTime());
		assertEquals(66,build.getTests().get(3).getTimes().get(1).getTime());
		assertEquals("failTest",build.getTests().get(4).getName());
		assertEquals("NewTest1",build.getTests().get(5).getName());
		assertEquals(10,build.getTests().get(6).getTimes().get(0).getTime());
		assertEquals(8,build.getTests().get(6).getTimes().get(0).getRunId());//runId
		assertEquals(20,build.getTests().get(7).getTimes().get(0).getTime());
		assertEquals(22,build.getTests().get(7).getTimes().get(1).getTime());
		assertEquals(21,build.getTests().get(7).getTimes().get(2).getTime());
		assertEquals(32,build.getTests().get(8).getTimes().get(0).getTime());
		assertEquals(34,build.getTests().get(8).getTimes().get(1).getTime());
		assertEquals(30,build.getTests().get(8).getTimes().get(2).getTime());
		assertEquals(15,build.getTests().get(9).getTimes().get(0).getTime());
		assertEquals(22,build.getTests().get(10).getTimes().get(0).getTime());
		assertEquals(23,build.getTests().get(10).getTimes().get(1).getTime());
		assertEquals(33,build.getTests().get(10).getTimes().get(2).getTime());
		assertEquals(50,build.getTests().get(11).getTimes().get(0).getTime());
		assertEquals(60,build.getTests().get(11).getTimes().get(1).getTime());
		assertEquals(15,build.getTests().get(12).getTimes().get(0).getTime());
		
	}
	
	@Test
	public void newRun_Test(){
		TestsNameIdTimeAre(9,"insert;0;15","delete;1;16","NewTest1;5;15","NewTest2;6;7","NewTest3;6;15",
				"update;43;fail","NewTest4;36;fail","NewTest5;-10;4","game;37;60","NewTest6;50;1","check;25;22","make;26;34");
		build.setTests(tests); //si mettono gli elementi dell'ultima run
		TestsNameIdTimeAre(10,"insert;0;20","delete;1;18","update;43;15","game;37;fail","check;25;21","make;26;30");
				
		build.newRun(tests);
		
		assertEquals(build.size(),6); //gli altri sono vecchi
		assertEquals(0,build.getTests().get(0).getIdTest());
		assertEquals(1,build.getTests().get(1).getIdTest());
		assertEquals(25,build.getTests().get(2).getIdTest());
		assertEquals(26,build.getTests().get(3).getIdTest());
		assertEquals(37,build.getTests().get(4).getIdTest());
		assertEquals(43,build.getTests().get(5).getIdTest());
		
		assertEquals(2,build.getTests().get(0).getTimes().size());
		assertEquals(1,build.getSingleTest(build.findTest(37)).getTimes().size());
		assertEquals(1,build.getTests().get(4).getTimes().size());
		assertEquals(1,build.getTests().get(5).getTimes().size());
		
		assertEquals(15,build.getTests().get(0).getTimes().get(0).getTime());
		assertEquals(20,build.getTests().get(0).getTimes().get(1).getTime());
		assertEquals(16,build.getTests().get(1).getTimes().get(0).getTime());
		assertEquals(18,build.getTests().get(1).getTimes().get(1).getTime());
		assertEquals(22,build.getTests().get(2).getTimes().get(0).getTime());
		assertEquals(21,build.getTests().get(2).getTimes().get(1).getTime());
		assertEquals(34,build.getTests().get(3).getTimes().get(0).getTime());
		assertEquals(30,build.getTests().get(3).getTimes().get(1).getTime());
		assertEquals(60,build.getTests().get(4).getTimes().get(0).getTime());
		assertEquals(15,build.getTests().get(5).getTimes().get(0).getTime());
		
		//questa operazione viene fatta ogni volta che si fa run quindi nessun problema di prestazioni
	}
	
	@Test
	public void newRun2_Test(){
		TestsNameIdTimeAre(8,"insert;0;15","delete;1;10","NewTest1;120;4","OldTest1;7;15","update;43;fail","badTest;21;10",
				"game;37;50","great;33;22","check;25;20","make;26;32","failTest;12;fail");
		build.setTests(tests);
		
		TestsNameIdTimeAre(9,"insert;0;15","delete;1;16","fake;6;55","OldTest1;7;15","failTest;12;fail",
				"update;43;fail","badTest;21;fail","game;37;60","great;33;23","OldTest2;50;1","check;25;22","make;26;34");
		build.newRun(tests);
		
		TestsNameIdTimeAre(10,"insert;0;20","delete;1;18","create;27;15","fake;6;66","great;33;33","study;-18;17",
				"update;43;15","badTest;21;fail","game;37;fail","failTest;12;fail","check;25;21","make;26;30","NewTest1;13;fail");
		build.newRun(tests);
			
		assertEquals(build.size(),13);
		
		assertEquals(-18,build.getTests().get(0).getIdTest());
		assertEquals(0,build.getTests().get(1).getIdTest());
		assertEquals(1,build.getTests().get(2).getIdTest());
		assertEquals(6,build.getTests().get(3).getIdTest());
		assertEquals(12,build.getTests().get(4).getIdTest());
		assertEquals(13,build.getTests().get(5).getIdTest());
		assertEquals(21,build.getTests().get(6).getIdTest());
		assertEquals(25,build.getTests().get(7).getIdTest());
		assertEquals(26,build.getTests().get(8).getIdTest());
		assertEquals(27,build.getTests().get(9).getIdTest());
		assertEquals(33,build.getTests().get(10).getIdTest());
		assertEquals(37,build.getTests().get(11).getIdTest());
		assertEquals(43,build.getTests().get(12).getIdTest());
				
		assertEquals(1,build.getTests().get(0).getTimes().size());
		assertEquals(3,build.getTests().get(1).getTimes().size());
		assertEquals(3,build.getTests().get(2).getTimes().size());
		assertEquals(2,build.getTests().get(3).getTimes().size());
		assertEquals(0,build.getTests().get(4).getTimes().size());
		assertEquals(0,build.getTests().get(5).getTimes().size());
		assertEquals(1,build.getTests().get(6).getTimes().size());
		assertEquals(3,build.getTests().get(7).getTimes().size());
		assertEquals(3,build.getTests().get(8).getTimes().size());
		assertEquals(1,build.getTests().get(9).getTimes().size());
		assertEquals(3,build.getTests().get(10).getTimes().size());
		assertEquals(2,build.getTests().get(11).getTimes().size());
		assertEquals(1,build.getTests().get(12).getTimes().size());
		
		assertEquals(17,build.getTests().get(0).getTimes().get(0).getTime());
		assertEquals(15,build.getTests().get(1).getTimes().get(1).getTime());
		assertEquals(20,build.getTests().get(1).getTimes().get(2).getTime());
		assertEquals(20,build.getTests().get(1).getTimes().get(2).getTime());
		assertEquals(10,build.getTests().get(2).getTimes().get(0).getTime());
		assertEquals(16,build.getTests().get(2).getTimes().get(1).getTime());
		assertEquals(18,build.getTests().get(2).getTimes().get(2).getTime());
		assertEquals(55,build.getTests().get(3).getTimes().get(0).getTime());
		assertEquals(66,build.getTests().get(3).getTimes().get(1).getTime());
		assertEquals("failTest",build.getTests().get(4).getName());
		assertEquals("NewTest1",build.getTests().get(5).getName());
		assertEquals(10,build.getTests().get(6).getTimes().get(0).getTime());
		assertEquals(8,build.getTests().get(6).getTimes().get(0).getRunId());//runId
		assertEquals(20,build.getTests().get(7).getTimes().get(0).getTime());
		assertEquals(22,build.getTests().get(7).getTimes().get(1).getTime());
		assertEquals(21,build.getTests().get(7).getTimes().get(2).getTime());
		assertEquals(32,build.getTests().get(8).getTimes().get(0).getTime());
		assertEquals(34,build.getTests().get(8).getTimes().get(1).getTime());
		assertEquals(30,build.getTests().get(8).getTimes().get(2).getTime());
		assertEquals(15,build.getTests().get(9).getTimes().get(0).getTime());
		assertEquals(22,build.getTests().get(10).getTimes().get(0).getTime());
		assertEquals(23,build.getTests().get(10).getTimes().get(1).getTime());
		assertEquals(33,build.getTests().get(10).getTimes().get(2).getTime());
		assertEquals(50,build.getTests().get(11).getTimes().get(0).getTime());
		assertEquals(60,build.getTests().get(11).getTimes().get(1).getTime());
		assertEquals(15,build.getTests().get(12).getTimes().get(0).getTime());
	}
		
	@Test
	public void getTestsRegression() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime{
		TestsNameIdTimeAre(1,"insert;0;24","delete;1;18","update;43;15","game;37;fail","make;26;22");
		build.setTests(tests);
		TestsNameIdTimeAre(2,"insert;0;22","delete;1;18","update;43;15","game;37;fail","check;25;21","make;26;30");
		build.newRun(tests);
		TestsNameIdTimeAre(3,"insert;0;20","delete;1;18","update;43;15","game;37;fail","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(4,"insert;0;22","delete;1;18","update;43;15","game;37;fail","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(5,"insert;0;23","delete;1;18","update;43;15","game;37;fail","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(6,"insert;0;21","delete;1;18","update;43;15","game;37;fail","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(7,"insert;0;26","delete;1;18","update;43;15","game;37;fail","check;25;21");
		build.newRun(tests);
		//-----------------------------------------------------------------------------------------------
		TestsNameIdTimeAre(8,"insert;0;20","delete;1;18","update;43;25","game;37;fail","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(9,"insert;0;25","delete;1;18","update;43;25","game;37;fail","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(10,"insert;0;33","delete;1;18","update;43;25","game;37;fail","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(11,"insert;0;32","delete;1;18","update;43;25","game;37;fail","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(12,"insert;0;31","delete;1;18","update;43;25","game;37;fail","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(13,"insert;0;28","delete;1;18","update;43;25","game;37;fail","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(14,"insert;0;27","delete;1;18","update;43;25","game;37;20","check;25;21");
		build.newRun(tests);
		
		
		assertEquals(build.size(),5);
		//assertEquals(build.getTestsRegression(7,7).size(),2);
		assertEquals(build.getTestsRegression(7,7).size(),1);
		assertEquals(build.getTestsRegression(7,7).get(0).getNameTest(),"insert");
		assertEquals(build.getTestsRegression(7,7).get(1).getNameTest(),"update");
		assertEquals(build.getTestsRegression(7,7).get(0).getId(),0);
		assertEquals(build.getTestsRegression(7,7).get(1).getId(),43);
		
		assertNull(build.getTestsRegression());
	}
	
	@Test
	public void getTestsRegression2() throws InvalideSizeTimesException, IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime{
		TestsNameIdTimeAre(12,"insert;0;24","delete;1;20","update;43;15","game;37;fail","check;25;22");
		build.setTests(tests);
		TestsNameIdTimeAre(11,"insert;0;22","delete;1;22","update;43;15","game;37;fail","check;25;21");
		build.oldRun(tests);
		TestsNameIdTimeAre(10,"insert;0;20","delete;1;18","update;43;15","game;37;fail","check;25;21");
		build.oldRun(tests);
		TestsNameIdTimeAre(9,"insert;0;22","delete;1;18","update;43;15","game;37;fail","check;25;21");
		build.oldRun(tests);
		TestsNameIdTimeAre(8,"insert;0;23","delete;1;18","update;43;15","game;37;fail","check;25;21");
		build.oldRun(tests);
		TestsNameIdTimeAre(7,"insert;0;21","delete;1;18","update;43;15","game;37;fail","check;25;21");
		build.oldRun(tests);
		TestsNameIdTimeAre(6,"insert;0;26","delete;1;18","update;43;15","game;37;fail","check;25;21");
		build.oldRun(tests);
		TestsNameIdTimeAre(5,"insert;0;30","delete;1;18","update;43;25","game;37;fail","check;25;21");
		build.oldRun(tests);
		TestsNameIdTimeAre(4,"insert;0;25","delete;1;18","update;43;25","game;37;fail","check;25;21");
		build.oldRun(tests);
		TestsNameIdTimeAre(3,"insert;0;33","delete;1;18","update;43;25","game;37;fail","check;25;21");
		build.oldRun(tests);
		TestsNameIdTimeAre(2,"insert;0;32","delete;1;18","update;43;25","game;37;fail","check;25;21");
		build.oldRun(tests);
		TestsNameIdTimeAre(1,"insert;0;31","delete;1;18","update;43;25","game;37;fail","check;25;21","make;30;30");
		build.oldRun(tests);
			
		assertEquals(build.size(),5);
		assertNull(build.getTestsRegression(6, 6));
		
		TestsNameIdTimeAre(13,"insert;0;24","delete;1;21","update;43;15","game;37;fail","check;25;22");
		build.newRun(tests);
		TestsNameIdTimeAre(14,"insert;0;22","delete;1;22","update;43;15","game;37;20","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(15,"insert;0;26","delete;1;21","update;43;15","game;37;20","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(16,"insert;0;22","delete;1;20","update;43;15","game;37;20","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(17,"insert;0;23","delete;1;22","update;43;15","game;37;15","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(18,"insert;0;21","delete;1;24","update;43;15","game;37;15","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(19,"insert;0;26","delete;1;23","update;43;15","game;37;15","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(20,"insert;0;28","delete;1;21","update;43;15","game;37;15","check;26;22");
		build.newRun(tests);
		
		assertNotNull(build.getTestsRegression(9, 9));
		assertEquals(build.getTestsRegression(9, 9).get(0).getNameTest(),"delete");
		assertEquals(build.getTestsRegression(9, 9).get(0).getId(),1);
		TestsNameIdTimeAre(21,"insert;0;29","delete;1;34","update;43;15","game;37;15","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(22,"insert;0;30","delete;1;32","update;43;15","game;37;40","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(23,"insert;0;25","delete;1;32","update;43;15","game;37;40","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(24,"insert;0;26","delete;1;32","update;43;15","game;37;40","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(25,"insert;0;27","delete;1;32","update;43;15","game;37;40","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(26,"insert;0;24","delete;1;32","update;43;15","game;37;40","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(27,"insert;0;24","delete;1;32","update;43;15","game;37;40","check;25;22");
		build.newRun(tests);
		TestsNameIdTimeAre(28,"insert;0;29","delete;1;32","update;43;15","game;37;46","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(29,"insert;0;30","delete;1;32","update;43;15","game;37;50","check;25;21");
		build.newRun(tests);
		TestsNameIdTimeAre(30,"insert;0;32","delete;1;32","update;43;15","game;37;55","check;25;21");
		build.newRun(tests);
		assertNotNull(build.getTestsRegression());
		assertEquals(build.getTestsRegression().size(),1);
		assertNotNull(build.getTestsRegression(8, 8));
		assertEquals(build.getTestsRegression(8,8).size(),2);
		assertEquals(build.getTestsRegression(8, 8).get(0).getNameTest(),"delete");
		assertEquals(build.getTestsRegression(8, 8).get(0).getId(),1);
		assertEquals(build.getTestsRegression(8, 8).get(1).getNameTest(),"game");
		assertEquals(build.getTestsRegression(8, 8).get(1).getId(),37);
	}


    @Test
    public void getTestsRegressionThreShold() throws InvalideSizeTimesException, IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime{
        TestsNameIdTimeAre(1,"insert;0;24","delete;1;18","update;43;15","game;37;fail","make;26;22");
        build.setTests(tests);
        TestsNameIdTimeAre(2,"insert;0;22","delete;1;18","update;43;15","game;37;fail","check;25;21","make;26;30");
        build.newRun(tests);
        TestsNameIdTimeAre(3,"insert;0;20","delete;1;18","update;43;15","game;37;fail","check;25;21");
        build.newRun(tests);
        TestsNameIdTimeAre(4,"insert;0;22","delete;1;18","update;43;15","game;37;fail","check;25;21");
        build.newRun(tests);
        TestsNameIdTimeAre(5,"insert;0;23","delete;1;18","update;43;15","game;37;fail","check;25;21");
        build.newRun(tests);
        TestsNameIdTimeAre(6,"insert;0;21","delete;1;18","update;43;15","game;37;fail","check;25;21");
        build.newRun(tests);
        TestsNameIdTimeAre(7,"insert;0;26","delete;1;18","update;43;15","game;37;fail","check;25;21");
        build.newRun(tests);
        //-----------------------------------------------------------------------------------------------
        TestsNameIdTimeAre(8,"insert;0;20","delete;1;60","update;43;25","game;37;fail","check;25;21");
        build.newRun(tests);
        TestsNameIdTimeAre(9,"insert;0;25","delete;1;60","update;43;25","game;37;fail","check;25;21");
        build.newRun(tests);
        TestsNameIdTimeAre(10,"insert;0;33","delete;1;60","update;43;25","game;37;fail","check;25;21");
        build.newRun(tests);
        TestsNameIdTimeAre(11,"insert;0;32","delete;1;60","update;43;25","game;37;fail","check;25;21");
        build.newRun(tests);
        TestsNameIdTimeAre(12,"insert;0;31","delete;1;60","update;43;25","game;37;fail","check;25;21");
        build.newRun(tests);
        TestsNameIdTimeAre(13,"insert;0;28","delete;1;60","update;43;25","game;37;fail","check;25;21");
        build.newRun(tests);
        TestsNameIdTimeAre(14,"insert;0;27","delete;1;60","update;43;25","game;37;20","check;25;21");
        build.newRun(tests);


        assertEquals(build.size(),5);
        //assertEquals(build.getTestsRegression(7,7).size(),2);
        assertEquals(build.getTestsRegression(7,7).size(),1);
        assertEquals(build.getTestsRegression(7,7).get(0).getNameTest(),"insert");
        assertEquals(build.getTestsRegression(7,7).get(1).getNameTest(),"update");
        assertEquals(build.getTestsRegression(7,7).get(0).getId(),0);
        assertEquals(build.getTestsRegression(7,7).get(1).getId(),43);

        assertNull(build.getTestsRegression());
    }

	private void TestsNameIdAre(String ... nameIdTests) {
		tests = new ArrayList<SingleTest>();
		String id;
		String name;
		for (String i_nameTest : nameIdTests) {
			String[] pieces = i_nameTest.split(";");
			name=pieces[0];
			id=pieces[1];
			tests.add(new SingleTest(name,Long.parseLong(id)));
		}		
	}
	
	private void TestsNameIdTimeAre(int runId,String ... nameIdTimeTests) {
		tests = new ArrayList<SingleTest>();
		String id;
		String name;
		String time;
		for (String i_nameTest : nameIdTimeTests) {
			String[] pieces = i_nameTest.split(";");
			name=pieces[0];
			id=pieces[1];
			time=pieces[2];
			SingleTest st= new SingleTest(name,Long.parseLong(id));
			if(!time.equals("fail"))
				st.getTimes().add(new TimeExecution(Integer.parseInt(time),runId));
			tests.add(st);
		}
	}
	
	
	/*
	Old Run
	SingleTest st=null;
	TestsNameIdTimeAre(10,"insert;0;20","delete;1;20","newTest1;5;20","newTest2;6;20",
			"update;43;fail","NewTest4;36;20","NewTest5;-10;20","game;37;fail","NewTest6;50;20","check;25;20","make;26;20",
			"NewTest7;99;20","NewTest8;-199;20","NewTest9;299;20","NewTest10;399;20","NewTest11;400;fail","NewTest12;40;20",
			"NewTest13;360;20","NewTest14;22;20");
	
	for(int i=0; i<5000;i++){
		st=new SingleTest("newTest"+(1000+i+2),i+2);
		st.getTimes().add(new TimeExecution(20,8000));
		
		tests.add(st); //7 6 9 8 11 10
		st=new SingleTest("newTest"+(1000+i+1),i+1);
		st.getTimes().add(new TimeExecution(20,8000));
		tests.add(st);
	}
	
	st=new SingleTest("newTest999",999);
	st.getTimes().add(new TimeExecution(20,8000));
	tests.add(st);
	st=new SingleTest("newTest998",998);
	st.getTimes().add(new TimeExecution(20,8000));
	tests.add(st);
	st=new SingleTest("newTest997",997);
	st.getTimes().add(new TimeExecution(20,8000));
	tests.add(st);;
	st=new SingleTest("newTest996",996);
	st.getTimes().add(new TimeExecution(20,8000));
	tests.add(st);
	st=new SingleTest("newTest995",995);
	//fail
	tests.add(st);
	st=new SingleTest("newTest994",994);
	tests.add(st);
	
	build.setTests(tests); //facciamo che le old run sono tutte uguali, cerca sempre una per una..
	System.out.println("size: "+build.size());
	/*
	for(int i=0;i<tests.size();i++){
		if(tests.get(i).getTimes().size()>1){
			System.out.println(tests.get(i).getTimes().size());
			System.out.println(i);
		}
	}
	
	System.out.println("INIZIO oldRuns");
	for(int i=0;i<10000;i++){//circa 20 secondi per 1000 tests
		if((i%100)==0)
			System.out.println(i);
		/*
		for(int j=0;j<tests.size();j++){
			if(tests.get(j).getTimes().size()>1){
				System.out.println(tests.get(j).getTimes().size());
				System.out.println(j);
			}
		}
		
		build.oldRun(tests);
	}
	System.out.println("FINE");
	/**/
	
	
	/*
	TestsNameIdAre("insert;0","delete;1","NewTest1;5","NewTest2;6","NewTest3;6",
			"update;43","NewTest4;36","NewTest5;-10","game;37","NewTest6;50","check;25","make;26",
			"NewTest7;99","NewTest8;-199","NewTest9;299","NewTest10;399","NewTest11;400","NewTest12;401",
			"NewTest13;360","NewTest14;22");//20 tests
	for(int i=0; i<500;i++){
		tests.add(new SingleTest("newTest"+(1000+i+2),i+2)); //7 6 9 8 11 10
		tests.add(new SingleTest("newTest"+(1000+i+1),i+1));

	}
	tests.add(new SingleTest("newTest999",999));
	tests.add(new SingleTest("newTest998",998));
	tests.add(new SingleTest("newTest997",997));
	tests.add(new SingleTest("newTest996",996));
	tests.add(new SingleTest("newTest995",995));
	tests.add(new SingleTest("newTest994",994));
	tests.add(new SingleTest("newTest993",993));
	tests.add(new SingleTest("newTest992",992));
	tests.add(new SingleTest("newTest991",991));
	//System.out.println(tests.size());
	*/

}
