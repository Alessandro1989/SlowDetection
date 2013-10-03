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

public class MergeSort {
	//complessità
	//O(nlog n)	O(nlog n)	O(nlog n)
	//rispetto al quicksort è stabile!, lo svantaggio è di chiedere spazio in memoria aggiuntivo
	//Comunque il quicksort è circa 2 volte più veloce nel caso medio
	//Per ordinare la lista dei tests scegliere assolutamente (questa classe) il mergeSort!
	
	//oggetti che contengono un degli elementi comparable
	public static Object[] mergeSort( Object [ ] objects, Comparable [ ] comparable_a ) {
		
		if(objects==null)
			objects=comparable_a;
		if(comparable_a.length!=objects.length)
			return null;
        Comparable [ ] tmpArray = new Comparable[ comparable_a.length ];
        Object [ ] tmpObjects = new Object[ objects.length ];
        mergeSort( objects, tmpObjects, comparable_a, tmpArray, 0, comparable_a.length - 1 );
        return objects;
    }
	
	private static void mergeSort( Object [ ] objects, Object [ ] tmpObjects, Comparable [ ] a, Comparable [ ] tmpArray,
            int left, int right ) {
        if( left < right ) {
            int center = ( left + right ) / 2;
            mergeSort( objects, tmpObjects, a, tmpArray, left, center );
            mergeSort( objects, tmpObjects, a, tmpArray, center + 1, right );
            merge( objects, tmpObjects, a, tmpArray, left, center + 1, right );
        }
    }
	
	private static void merge( Object [ ] objects, Object [ ] tmpObjects, Comparable [ ] a, Comparable [ ] tmpArray,
            int leftPos, int rightPos, int rightEnd ) {
		
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        int cloneLeft;
        int cloneRight;
        
        // Main loop
        while( leftPos <= leftEnd && rightPos <= rightEnd )
            if( a[ leftPos ].compareTo( a[ rightPos ] ) <= 0 ){
            	cloneLeft=leftPos;
                tmpArray[ tmpPos++ ] = a[ leftPos++ ];
                tmpObjects [ tmpPos-1 ] = objects [cloneLeft];
            }
            else{
                cloneRight=rightPos;
                tmpArray[ tmpPos++ ] = a[ rightPos++ ];
                tmpObjects [ tmpPos-1 ] = objects [cloneRight];
            }
        while( leftPos <= leftEnd ){    // Copy rest of first half
        	cloneLeft=leftPos;
            tmpArray[ tmpPos++ ] = a[ leftPos++ ];
            tmpObjects [ tmpPos-1 ] = objects [cloneLeft];
        }
        
        while( rightPos <= rightEnd ){  // Copy rest of right half
        	cloneRight=rightPos;
            tmpArray[ tmpPos++ ] = a[ rightPos++ ];
            tmpObjects [ tmpPos-1 ] = objects [cloneRight];
        }
        
        // Copy tmpArray back
        for( int i = 0; i < numElements; i++, rightEnd-- ){
            a[ rightEnd ] = tmpArray[ rightEnd ];
            objects[ rightEnd ] = tmpObjects[ rightEnd ];
  
        }
    }
	
	/**
     * Mergesort algorithm.
     * @param a an array of Comparable items.
     */
	
	
	/*
    public static Comparable[] mergeSort( Comparable [ ] a ) {
        Comparable [ ] tmpArray = new Comparable[ a.length ];
        mergeSort( a, tmpArray, 0, a.length - 1 );
        return a;
    }
    
    /**
     * Internal method that makes recursive calls.
     * @param a an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
	/*
    private static void mergeSort( Comparable [ ] a, Comparable [ ] tmpArray,
            int left, int right ) {
        if( left < right ) {
            int center = ( left + right ) / 2;
            mergeSort( a, tmpArray, left, center );
            mergeSort( a, tmpArray, center + 1, right );
            merge( a, tmpArray, left, center + 1, right );
        }
    }
    
    /**
     * Internal method that merges two sorted halves of a subarray.
     * @param a an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param leftPos the left-most index of the subarray.
     * @param rightPos the index of the start of the second half.
     * @param rightEnd the right-most index of the subarray.
     */
	/*
    private static void merge( Comparable [ ] a, Comparable [ ] tmpArray,
            int leftPos, int rightPos, int rightEnd ) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        
        // Main loop
        while( leftPos <= leftEnd && rightPos <= rightEnd )
            if( a[ leftPos ].compareTo( a[ rightPos ] ) <= 0 )
                tmpArray[ tmpPos++ ] = a[ leftPos++ ];
            else
                tmpArray[ tmpPos++ ] = a[ rightPos++ ];
        
        while( leftPos <= leftEnd )    // Copy rest of first half
            tmpArray[ tmpPos++ ] = a[ leftPos++ ];
        
        while( rightPos <= rightEnd )  // Copy rest of right half
            tmpArray[ tmpPos++ ] = a[ rightPos++ ];
        
        // Copy tmpArray back
        for( int i = 0; i < numElements; i++, rightEnd-- )
            a[ rightEnd ] = tmpArray[ rightEnd ];
    }
    */
}
