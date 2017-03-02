package com.progressoft.utilites;

public class Test {
	public static void main(String[] args) {
		MatrixOperationsUtility matrixOperationsUtility = new MatrixOperationsUtilityImpl();
		int matrixOne[][] = { 
				{ 3, 4, 5, 9 },
				{ 6, 7, 8, 5 } 
				};
		int matrixTwo[][] = { 
				{ 5, 7, 8, 9 },
				{ 5, 3, 2, 11 } 
				};
		int matrixThree[][] = { 
				{ 2, 3 }
			   ,{ 4, 5 }
			   ,{ 6, 7 }
			   };
		int matrixFour[][] = { 
				{ 2, 3, 5 }
			   ,{ 4, 5, 6 }
			   };
		
		System.out.println("===============\n1)add");
		int[][] add = matrixOperationsUtility.add(matrixOne, matrixTwo);
		printtArray(add);
		
		System.out.println("===============\n2)scalarMultiply");
		int[][] scalarMultiply = matrixOperationsUtility.scalarMultiply(3, matrixThree);
		printtArray(scalarMultiply);
		
		System.out.println("===============\n3)transport");
		int[][] transport = matrixOperationsUtility.transport(matrixThree);
		printtArray(transport);
		
		System.out.println("===============\n4)multiply");
		int[][] multiply = matrixOperationsUtility.multiply(matrixThree,matrixFour);
		printtArray(multiply);
	}
	public static void printtArray(int[][] array) {
		if(array==null)
			throw new NullPointerException();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++)
				System.out.print(array[i][j] + "\t");
			System.out.println();
		}
	}
}
