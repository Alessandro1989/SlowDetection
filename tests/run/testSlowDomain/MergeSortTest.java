package run.testSlowDomain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jetbrains.sample.exception.*;
import jetbrains.sample.statistic.*;
import jetbrains.sample.testSlowDomain.*;


public class MergeSortTest {


	
	Comparable arr[];
	
	@Test
	public void SizeZeroTest() throws Exception {
		arr=new Integer[0];
		int f=2;
		Comparable c=f;
		
		
		arr=(Comparable[]) MergeSort.mergeSort(null, arr);
		assertEquals(arr.length,0);
	}
	
	@Test
	public void SizeOneTest() throws Exception {
		arr=new Integer[1];
		arr[0]=5;
		arr=(Comparable[]) MergeSort.mergeSort(null, arr);
		
		assertEquals(arr[0],5);
		
	}
	
	@Test
	public void SizeTwooTest() throws Exception {
		arr=new Integer[2];
		arr[0]=5;
		arr[1]=3;
		arr=(Comparable[]) MergeSort.mergeSort(null, arr);
		assertEquals(arr[0],3);
		assertEquals(arr[1],5);
	}
	
	@Test
	public void SizeThreeTest() throws Exception {
		arr=new Integer[3];
		arr[0]=5;
		arr[1]=3;
		arr[2]=4;
		arr=(Comparable[]) MergeSort.mergeSort(null, arr);
		assertEquals(arr[0],3);
		assertEquals(arr[1],4);
		assertEquals(arr[2],5);
	}
	
	@Test
	public void MergeSortTest() throws Exception {
		arr=new Integer[8];
		arr[0]=5;
		arr[1]=3;
		arr[2]=4;
		arr[3]=6;
		arr[4]=7;
		arr[5]=2;
		arr[6]=8;
		arr[7]=9;
		arr=(Comparable[]) MergeSort.mergeSort(null, arr);
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
	public void MergeSort2Test() throws Exception {
		arr=new Integer[8];
		arr[0]=5;
		arr[1]=3;
		arr[2]=5;
		arr[3]=6;
		arr[4]=9;
		arr[5]=1;
		arr[6]=7;
		arr[7]=7;
		arr=(Comparable[]) MergeSort.mergeSort(null, arr);
		assertEquals(arr[0],1);
		assertEquals(arr[1],3);
		assertEquals(arr[2],5);
		assertEquals(arr[3],5);
		assertEquals(arr[4],6);
		assertEquals(arr[5],7);
		assertEquals(arr[6],7);
		assertEquals(arr[7],9);
	}
	
	@Test
	public void MergeSortObjectsTest() throws Exception {
		arr=new Integer[8];
		arr[0]=10;
		arr[1]=8;
		arr[2]=7;
		arr[3]=6;
		arr[4]=5;
		arr[5]=9;
		arr[6]=4;
		arr[7]=3;
		typeObjects[] obj=new typeObjects[arr.length];
		for(int i=0;i<arr.length;i++){
			obj[i]=new typeObjects("asd",(Integer) arr[i]);
		}
		obj=(typeObjects[]) MergeSort.mergeSort(obj, arr);
		assertEquals(obj[0].getNumb(),3);
		assertEquals(obj[1].getNumb(),4);
		assertEquals(obj[2].getNumb(),5);
		assertEquals(obj[3].getNumb(),6);
		assertEquals(obj[4].getNumb(),7);
		assertEquals(obj[5].getNumb(),8);
		assertEquals(obj[6].getNumb(),9);
		assertEquals(obj[7].getNumb(),10);
	}
	 
	private static class typeObjects{
		private Integer numb;
		private String description;

		typeObjects(String description, Integer numb){
			this.description=description;
			this.numb=numb;
		}
		public int getNumb(){
			return numb;
		}
	}
	
		
	

}
