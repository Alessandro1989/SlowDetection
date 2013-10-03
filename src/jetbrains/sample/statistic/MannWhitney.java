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

package jetbrains.sample.statistic;

import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

import jetbrains.sample.exception.*;
import jetbrains.sample.testSlowDomain.Quicksort;


//import jsc.distributions.MannWhitneyU;


//import com.
//import com.

public class MannWhitney {
	
	private List<Integer> secondSample;
	private List<Integer> firstSample;
	private List<Integer> sortSample;
	private List<Integer> UnionSample;
	private int[] UnionSampleOrder;
	private static List<Rank> arrayRank;
	private static double sumRank1;
	private static double sumRank2;
	private int alfaPercentage;
	private static double U1;
	private static double U2;
	private TableMannWhitney table;

	//public int alfaPercentage5=5;
	//public int alfaPercentage1=1;
	
	//BoxPlot.Statistics(List<double> ello);

	public MannWhitney(List<Integer> firstSample, List<Integer> secondSample, AlfaPercentage alfa) throws IllegalSizeSamples{
		this.firstSample=firstSample;
		this.secondSample=secondSample;
		this.alfaPercentage=alfa.toInt();//0.05 = 5% nella tabella abbiamo solo 5 o 1
		
		UnionSample=new ArrayList<Integer>();
		UnionSample.addAll(firstSample);
		UnionSample.addAll(secondSample);
		if((firstSample.size()<=2)||(secondSample.size()<=2)){
			throw new IllegalSizeSamples();
		}
		UnionSampleOrder=new int[UnionSample.size()];
		UnionSampleOrder=Quicksort.quickSort(convert(UnionSample), 0, UnionSample.size()-1);
		
			
		arrayRank= new ArrayList<Rank>();
		List<Integer> copyFirst = new ArrayList<Integer>();
		List<Integer> copySecond = new ArrayList<Integer>();
		copyFirst.addAll(firstSample);
		copySecond.addAll(secondSample);
		buildRank(UnionSample, UnionSampleOrder, copyFirst, copySecond);
		sumRank(UnionSample);
		//computeU(firstSample, secondSample);
		int n1=firstSample.size();
		int n2=secondSample.size();
		U1=sumRank1-(n1*(n1+1)*0.5);
		U2=sumRank2-(n2*(n2+1)*0.5);
		
		this.table=new TableMannWhitney();
		
		 
		
	}
	
	private double min(double u1, double u2) {
		if(U1<U2)
			return U1;
		else
			return U2;
	}

	private static void computeU(List<Integer> firstSample, List<Integer> secondSample) {
		int n1=firstSample.size();
		int n2=secondSample.size();
		U1=sumRank1-n1*(n1+1)*0.5;
		U2=sumRank2-n2*(n2+1)*0.5;
	}

	private static void sumRank(List<Integer> UnionSample) {
		sumRank1=0;
		sumRank2=0;
		for(int i=0; i<UnionSample.size(); i++){
			if(arrayRank.get(i).getIdSample()==1){
				sumRank1+=arrayRank.get(i).getRank();
			}
			else
				sumRank2+=arrayRank.get(i).getRank();
		}
		
	}

	private static void buildRank(List<Integer> UnionSample,int[] UnionSampleOrder,List<Integer> copyFirst,List<Integer> copySecond) {
		
		/*
		Value: 200 300 360..
		Rank:   1   2   3  ...
		con valori uguali
		1 1 1 2   2   2   2   2   2    3     3    3    3 
		2 2 2 6.5 6.5 6.5 6.5 6.5 6.5 11.5 11.5  11.5 11.5
		1 2 3  4   5    6  7    8  9    10   11   12   13
		 */
		 
		double rank=0;
		double sum=0;
		int elements=0;
		boolean did=false;
		int position=0;

		for(int i=0;i<UnionSample.size();i++){ 
			
			if(did==false){
				for(int j=i;j<UnionSample.size();j++){
					sum+=j+1;
					elements++;
					if(j==UnionSample.size()-1){//vuol dire l'ultimo è uguale a quello prima, se no usciva 
						position=j;
						break;
					}
					if(UnionSampleOrder[j]!=UnionSampleOrder[j+1]){//se quello nuovo non è uguale
						position=j;
						break;
					}
				}
				
				rank=sum/elements;
				sum=0;
				elements=0;
				did=true;
			}
			
			int index=search(copyFirst, UnionSampleOrder[i]);
			if(index!=-1){
				copyFirst.remove(index);
				arrayRank.add(new Rank(rank, 1));
			}
			else{
				index=search(copySecond, UnionSampleOrder[i]);
				copySecond.remove(index);
				arrayRank.add(new Rank(rank, 2));
			}
			
			if(i==position)
				did=false;		
		}
	
	}
	
	
	public static List<Rank> getArrayRank() {
		return arrayRank;
	}

	public double getSumRank1() {
		return sumRank1;
	}

	public double getSumRank2() {
		return sumRank2;
	}

	
	public double getU1() {
		return U1;
	}

	public double getU2() {
		return U2;
	}


	public int getResult(){
		if(isSame())
			return 0; //0 uguali, -1 minore il primo, 1 maggiore il secondo
		else{
			if(U2>U1)
				return 1;
			else
				return -1;
		}
	}
	
	
	public boolean isMajor(int idSample){ //0 il primo, 1 il secondo
		if(!isSame()){
			if(idSample==1)
				return (U2>U1);
			else
				return (U1>U2);
		}
		else
			return false;
	}
	public boolean isSame(){
		return table.AcceptHo(firstSample.size(), secondSample.size(), min(U1,U2));
	}
	
	
	
	private static int search(List<Integer> copySample, double element) {
		for(int i=0; i<copySample.size(); i++){	
			if(copySample.get(i)==element)
				return i;
		}
		return -1;
	}
		
	
	private static int[] convert(List<Integer> Sample) {
		int[] sample=new int[Sample.size()];
		for (int i=0; i<Sample.size();i++){
			sample[i]=Sample.get(i).intValue();
		}
		return sample;
	}
}
