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

public class TableMannWhitney {
	
		//                        n2=20
		//n1=20
		private String text1Percentage ="- - - - - - - - - - - - - - - - - - - -;"+
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

		private String Rows1Percentage[]=text1Percentage.split(";");
		private String[][] table1Percentage= new String [20][20];
		public TableMannWhitney(){
			for(int j=0;j<20;j++){
				String[] numbers=Rows1Percentage[j].split(" ");
				for(int i=0;i<20;i++){
					table1Percentage[j][i]=numbers[i];
				}
			}
		}
		
		public String[][] getTableMannWhitney(){
			return table1Percentage;
		}
		
		public String findValue(int n1, int n2){
			//eccezione se n1 o n2 maggiori di 20
			return table1Percentage[n1-1][n2-1]; //da 0 a 19
		}
		
		//i 2 campioni non sono diversi, Ho non viene rifiutata
		public boolean AcceptHo(int n1,int n2, double uMin){
			//per non aver problemi con 0.01 n1 e n2 >=5
			//con 0.05 n1 e n2 >=4
			if(uMin > Integer.valueOf(table1Percentage[n1-1][n2-1])) //eccezione se c'è -..
				return true;
			else
				return false;
		}
		

}
