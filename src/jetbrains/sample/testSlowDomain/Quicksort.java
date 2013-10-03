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

public class Quicksort {
	//caso ottimale spesso.
	//questo algoritmo va bene per i dati casuali come i tempi di esecuzione, caso peggiore è raro
	//Inoltre viene usato per ordinare dei piccoli insieme di elemenenti per MannWithney, quindi
	//nel caso si verifica il caso peggiore che succede raramente, n è piccolo (15).. quindi non è grave 
	
	//"Il caso peggiore si verifica se una lista è già ordinata (quasi ordinata) e nella scelta del pivot?!"
	//quindi per ordinare la lista dei tests BuildPrj usiamo il merge sort!! anche perchè un n^2 di migliaia 
	//di tests è pesante anche se i dati fossero casuali
	//ottimo	medio		peggiore
	//O(nlog n)	O(nlog n)	O(n^2)

	
	private static int partition(int arr[], int left, int right)
	{
	      int i = left, j = right;
	      int tmp;
	      int pivot = arr[(left + right) / 2];
	     
	      while (i <= j) {
	            while (arr[i] < pivot)
	                  i++;
	            while (arr[j] > pivot)
	                  j--;
	            if (i <= j) {
	                  tmp = arr[i];
	                  arr[i] = arr[j];
	                  arr[j] = tmp;
	                  i++;
	                  j--;
	            }
	      };
	     
	      return i;
	}
	
	public static int[] quickSort(int arr[], int left, int right) {
		if(right<=0)//nessun elemento, o solo 1 elemento, restituisci così com'è
			return arr;
		return quickSortMaking(arr, left, right);
	}
	
	private static int[] quickSortMaking(int arr[], int left, int right) {
	      int index = partition(arr, left, right);
	      if (left < index - 1)
	            quickSort(arr, left, index - 1);
	      if (index < right)
	            quickSort(arr, index, right);
	      return arr;
	}
}
