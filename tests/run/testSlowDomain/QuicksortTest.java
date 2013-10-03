package run.testSlowDomain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.exception.*;
import jetbrains.sample.statistic.*;
import jetbrains.sample.testSlowDomain.*;


public class QuicksortTest {


	int arr[];
	
	@Test
	public void testSizeZero() throws Exception {
		arr=new int[0];
		arr=Quicksort.quickSort(arr,0,arr.length-1);
		assertEquals(arr.length,0);
	}
	
	@Test
	public void testSizeOne() throws Exception {
		arr=new int[1];
		arr[0]=5;
		arr=Quicksort.quickSort(arr,0,arr.length-1);
		assertEquals(arr[0],5);
		
	}
	
	@Test
	public void testSizeTwoo() throws Exception {
		arr=new int[2];
		arr[0]=5;
		arr[1]=3;
		arr=Quicksort.quickSort(arr,0,arr.length-1);
		assertEquals(arr[0],3);
		assertEquals(arr[1],5);
	}
	
	@Test
	public void testSizeThree() throws Exception {
		arr=new int[3];
		arr[0]=5;
		arr[1]=3;
		arr[2]=4;
		arr=Quicksort.quickSort(arr,0,arr.length-1);
		assertEquals(arr[0],3);
		assertEquals(arr[1],4);
		assertEquals(arr[2],5);
	}
	
	@Test
	public void testQuickSort() throws Exception {
		arr=new int[8];
		arr[0]=5;
		arr[1]=3;
		arr[2]=4;
		arr[3]=6;
		arr[4]=7;
		arr[5]=2;
		arr[6]=8;
		arr[7]=9;
		arr=Quicksort.quickSort(arr,0,arr.length-1);
		assertEquals(arr[0],2);
		assertEquals(arr[1],3);
		assertEquals(arr[2],4);
		assertEquals(arr[3],5);
		assertEquals(arr[4],6);
		assertEquals(arr[5],7);
		assertEquals(arr[6],8);
		assertEquals(arr[7],9);
		
	}
	
	@Test
	public void testQuickSort2() throws Exception {
		arr=new int[8];
		arr[0]=5;
		arr[1]=3;
		arr[2]=5;
		arr[3]=6;
		arr[4]=9;
		arr[5]=1;
		arr[6]=7;
		arr[7]=7;
		arr=Quicksort.quickSort(arr,0,arr.length-1);
		assertEquals(arr[0],1);
		assertEquals(arr[1],3);
		assertEquals(arr[2],5);
		assertEquals(arr[3],5);
		assertEquals(arr[4],6);
		assertEquals(arr[5],7);
		assertEquals(arr[6],7);
		assertEquals(arr[7],9);
	}
	

}
