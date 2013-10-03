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

import jetbrains.sample.exception.*;
import jetbrains.sample.testSlowDomain.Quicksort;
import jetbrains.sample.testSlowDomain.TimeExecution;


public class BoxPlot {
	private double Q1;
	private double Q2;
	private double minimum;
	private double maximum;

	private List<TimeExecution> BeginSample;
	
	public BoxPlot(List<Integer> firstSample, boolean base) throws IllegalSizeSamples{
		if((firstSample.size()<4)){
			throw new IllegalSizeSamples();
		}
		this.BeginSample=new ArrayList<TimeExecution>();
        for(int i=0;i<firstSample.size();i++)
		    BeginSample.add(new TimeExecution(firstSample.get(i), 0));
				
		//Per esserci un Quartile, una mediana, e un secondo quartile ci vogliono almeno 3 elementi
		//Con 3 elementi chi puoi scartare? nessuno, quindi minimo 4 elementi
        findValues(firstSample, base);

		
	}

    public BoxPlot(List<TimeExecution> firstSample, int numberbase) throws IllegalSizeSamples{
        boolean base;
        if(numberbase==0)
            base=false;
        else
            base = true;
        if((firstSample.size()<4)){
            throw new IllegalSizeSamples();
        }
        this.BeginSample=new ArrayList<TimeExecution>();
        BeginSample.addAll(firstSample);

        //Per esserci un Quartile, una mediana, e un secondo quartile ci vogliono almeno 3 elementi
        //Con 3 elementi chi puoi scartare? nessuno, quindi minimo 4 elementi

        findValues(convertTimeExecution(firstSample), base);


    }

    private void findValues(List<Integer> firstSample, boolean base){

        int arr[]=Quicksort.quickSort(convert(firstSample), 0, firstSample.size()-1);


        //mediana: 15 elementi: mediana in (15+1)/2=8, 7 elementi a sinistra, 7 a destra.
        double PositionMe=((double)(firstSample.size()+1))/2;
        int elementsLefts;
        if((int)PositionMe < PositionMe){ //se è un numero con la virgola come 8.5 prendi i primi 8
            elementsLefts=(int) PositionMe;
        }else//se non ha la virgola, 8, prendi i primi 7
            elementsLefts=(int) (PositionMe-1);


        //stesso procedimento, per il primo quartile e il secondo quartile
        double PositionQ1=((double)(elementsLefts+1))/2;

        if((int)PositionQ1 < PositionQ1){ //se è un numero con la virgola ad esempio 4.5 fai la media
            this.Q1=((double)(arr[(int)PositionQ1-1]+arr[(int)PositionQ1]))/2;
        }else//se non ha la virgola, 8, prendi i primi 7
            this.Q1=arr[(int)PositionQ1-1];

        //secondo quartile
        double PositionQ2=(int)PositionMe+PositionQ1;
        if((int)PositionQ2 < PositionQ2){ //se è un numero con la virgola ad esempio 4.5 fai la media
            Q2=((double)(arr[(int)PositionQ2-1]+arr[(int)PositionQ2]))/2;
        }else//se non ha la virgola, 8, prendi i primi 7
            Q2=arr[(int)PositionQ2-1];

        double IQR=Q2-Q1;
        if(base){
            this.minimum=-1; //non abbiamo minimo, dato che il tempo minore possibile è 0
            this.maximum=Q2;
        }
        else
        {
            this.minimum=Q1-1.5*IQR;
            this.maximum=Q2+1.5*IQR;
        }
    }

	public double getQ1() {
		return this.Q1;
	}

	
	public double getQ2() {
		return this.Q2;
	}

	
	public double getMinimum() {
		return this.minimum;
	}

	public double getMaximum() {
		return this.maximum;
	}


	public List<Integer> getCorrectSample() {
		
		List<Integer> correctSample=new ArrayList<Integer>();
		int j=0;
		for(int i=0; i<BeginSample.size();i++){
			if((BeginSample.get(i).getTime()>=minimum)&&(BeginSample.get(i).getTime()<=maximum)){
				correctSample.add(BeginSample.get(i).getTime());
				j++;
			}
		}
		return correctSample;
	}

    public List<TimeExecution> getCorrectSampleTimeExecution() {

        List<TimeExecution> correctSample=new ArrayList<TimeExecution>();
        int j=0;
        for (int i=0; i<BeginSample.size();i++){
            if((BeginSample.get(i).getTime()>=minimum)&&(BeginSample.get(i).getTime()<=maximum)){
                correctSample.add(new TimeExecution(BeginSample.get(i).getTime(), BeginSample.get(i).getRunId()));
                j++;
            }
        }
        return correctSample;
    }


	private static int[] convert(List<Integer> Sample) {
		int[] sample=new int[Sample.size()];
		for (int i=0; i<Sample.size();i++){
			sample[i]=Sample.get(i).intValue();
		}
		return sample;
	}

    private static List<Integer> convertTimeExecution(List<TimeExecution> Sample) {
        List<Integer> sample=new ArrayList<Integer>();
        for (int i=0; i<Sample.size();i++){
            sample.add(Sample.get(i).getTime());
        }
        return sample;
    }

}
