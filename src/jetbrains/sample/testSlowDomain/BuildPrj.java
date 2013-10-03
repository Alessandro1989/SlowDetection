  /*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jetbrains.sample.testSlowDomain;

import java.util.ArrayList;
import java.util.List;

import jetbrains.sample.exception.*;

//ricerca binaria log n, n volte: n log n.
//ordinamento del quicksort è n^2 caso peggiore, ed è quando la lista è ordinata!, quindi se aggiungiamo un elemento
//alla fine ((getlist().add(test)), e la lista è già ordinata, il quicksort fa n^2, e il controllo IsSorted fallisce!
//usiamo il merge sort che è stabile sempre O(n log n)

public class BuildPrj {
	private String name;
	private List<SingleTest> tests;
	//qualsiasi operazione che può compromettere l'ordine come il getTests, viene segnato IsSorted a false
	private boolean IsSorted=false;
	private Comparable[] testsId; 
	
	public BuildPrj(String nameProject){
		this.name=nameProject;
		tests=new ArrayList<SingleTest>();
	}
	
	public BuildPrj(String nameProject, List<SingleTest> tests){
		this.name=nameProject;
		this.tests=tests;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SingleTest> getTests() {
		this.IsSorted=false; //perchè un utente può mettersi a aggiungere e scombinarlo
		return tests;
	}
	
	
	public SingleTest getSingleTest(int position) {
		if(position<0 && position>=tests.size())
			return null;
		return tests.get(position);
	}
	
	public void setTests(List<SingleTest> tests2) {
		this.IsSorted=false;
		tests=new ArrayList<SingleTest>();
		for(int i=0;i<tests2.size();i++){
			SingleTest test=tests2.get(i);
			List<TimeExecution> times=new ArrayList<TimeExecution>();
			times.addAll(test.getTimes());
			tests.add(new SingleTest(test.getName(),test.getIdTest(), times));
		}
			//tests.add(tests2.get(i)); Sbagliato!
			
	}
	//this.tests = tests; errore, se nella lista fuori viene aggiunto qualcosa automaticamente anche qui
	//puntano allo stesso oggetto 
	/*build.setTests(tests); System.out.println(build.size());19
	st=new SingleTest("newTest4444",4444); tests.add(st); System.out.println(build.size());20*/
	//Sbagliato: tests.add(new SingleTest(test.getName(),test.getIdTest(), test.getTimes()));
	//la lista times punta allo stesso oggetto della lista della lista contenuta nel lista passata!
	//se aggiungi un elemento in times la lista fuori avrà un elemento in più nella lista times
	
	public int size(){
		return tests.size();
	}

	public int findTest(long id){
		//l'ordinamento deve essere fatto prima se no diventa un operazione n log n apposto di +n/2
				
		if(IsSorted && testsId!=null){ //se è ordinato il testsId dovrebbe essere già ordinato
			return ricercaBinaria(testsId, id);
		}
		else{
			//prestazioni: n/2
			for(int i=0;i<tests.size();i++){
				if(tests.get(i).getIdTest()==id){
					//System.out.println(i);
					return i;
				}
			}
		}
		//se non lo trova..
		return -1;
			
	}
		
	public void addTest(SingleTest test){
		this.IsSorted=false;
		tests.add(test);
	}
	
	//algoritmo di ricerca binaria non più di lg[N]+1
	private static int ricercaBinaria(Comparable[] testsId, long idValue){
		  int passaggi=0;
	      int posizione=-1;
	      int primo=0,medio,ultimo=(testsId.length-1);      
	    	      
	      while(posizione==-1 && primo<=ultimo){
	         medio=(primo+ultimo)/2;
	         passaggi++;
	         if(((Long)testsId[medio])==idValue){
	        	 posizione=medio;
	         }else if(((Long)testsId[medio])<idValue){
	            primo=(medio+1);
	         }else if(((Long)testsId[medio])>idValue){
	            ultimo=(medio-1);
	         }
	      }
	      
	      //System.out.println(passaggi); 
	      return posizione;
	   }
	
	public void sort() {
		/*ArrayList
		 * The size, isEmpty, get, set, iterator, and listIterator operations run in constant time.
		 *The add operation runs in amortized constant time, that is, adding n elements requires O(n) time. All of the other operations run in linear time (roughly speaking). 
		 */
		System.out.println("ordinamento");
		if(!this.IsSorted){//se non è ordinato
			testsId = new Long[tests.size()];
			SingleTest[] arrayTests = new SingleTest[tests.size()];
			//= (SingleTest[]) tests.toArray(); //linear O(n)
			for(int i=0; i<tests.size();i++){ //O(n)
				arrayTests[i] = tests.get(i);
				testsId[i] = arrayTests[i].getIdTest();
			}
			//O(n log n)
			arrayTests=(SingleTest[]) MergeSort.mergeSort(arrayTests, testsId);//ordiniamo gli id in un array..
			
			for (int i=0;i<tests.size();i++){
				tests.set(i, arrayTests[i]);
			}
			this.IsSorted=true;
		}
	}
	
	
	
	
	
	public Comparable[] getTestsId() {
		return testsId;
	}

	public boolean getIsSorted() {
		return IsSorted;
	}

	//settiamo nel caso si vuole per qualche motivo forzare l'ordinamento a false
	public void setIsSortedAtfalse() {
		this.IsSorted=false;
	}
	
	public void oldRun(List<SingleTest> testsRun) throws InvalideSizeTimesException{
		if(!IsSorted)
			sort();
		for(int i=0;i<testsRun.size();i++){
			int position=this.findTest(testsRun.get(i).getIdTest());
			//System.out.println(testsRun.get(i).getName());
			if(position != -1){//se il test è presente anche nella vecchia run (non è nuovo!)
				TimeExecution time = null;
				if(testsRun.get(i).getTimes().size()>1)
					throw new InvalideSizeTimesException(testsRun.get(i).getTimes().size(),testsRun.get(i).getIdTest());
				if(testsRun.get(i).getTimes().size()==1){ //se è fail non viene messo il tempo
					time = new TimeExecution(testsRun.get(i).getTimes().get(0).getTime(),testsRun.get(i).getTimes().get(0).getRunId());
					//System.out.println();
					tests.get(position).getTimes().add(0,time);//inserisco sempre prima, all'incontrario
					//i run ultimi alla fine e i run vecchi all'inizio
				}
			}
		}
		//time = testsRun.get(i).getTimes().get(0); SBAGLIATO
		//nota: time si riferisce all'oggetto testsRun che nel test viene passato.
		//aggiungendo l'oggetto time viene aggiunto anche i collegamenti con la lista visto che viene inserito
		//anche all'inizio.
		//testsRun viene modificato e così a sua volta anche l'elemento singletests
		
	}
	
	public void newRun(List<SingleTest> testsRun){
		List<SingleTest> tmpTests=new ArrayList<SingleTest>();
		for(int i=0;i<tests.size();i++){
			SingleTest test=tests.get(i);
			List<TimeExecution> times=new ArrayList<TimeExecution>();
			times.addAll(test.getTimes());
			tmpTests.add(new SingleTest(test.getName(),test.getIdTest(), times));
		}
		
		setTests(testsRun);
		sort(); //se sono tanti tests ci mette di meno a fare una ricerca
		
		for(int i=0;i<tmpTests.size();i++){
			int position=this.findTest(tmpTests.get(i).getIdTest());
			if(position != -1){//se il test è presente anche nella vecchia run (non è nuovo!)
				//sta volta i times di solito sono più di 0 e 1
				if(tmpTests.get(i).getTimes().size()>0){ 
					List<TimeExecution> times = tmpTests.get(i).getTimes();
					tests.get(position).getTimes().addAll(0,times);//inserisco sempre prima, all'incontrario
					//i run ultimi alla fine e i run vecchi all'inizio
				}
			}
		}
	
	}  

	
	public List<Regression> getTestsRegression() throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {
		FindRegressionTests FR = new FindRegressionTests();
		return FR.getRegression(this);
	}
	
	
	public List<Regression> getTestsRegression(int sizeStart, int sizeEnd) throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {
		
		FindRegressionTests FR = new FindRegressionTests();
		FR.setSizeStart(sizeStart);
		FR.setSizeEnd(sizeEnd);
		return FR.getRegression(this);	
	}

    public List<Regression> getTestsRegression(int sizeStart, int sizeEnd, int numericalThreshold, int percentualThreshold) throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {

        FindRegressionTests FR = new FindRegressionTests();
        FR.setSizeStart(sizeStart);
        FR.setSizeEnd(sizeEnd);
        FR.setNumericalThreshold(numericalThreshold);
        FR.setPercentualThreshold(percentualThreshold);
        return FR.getRegression(this);
    }

	


	
	
}
