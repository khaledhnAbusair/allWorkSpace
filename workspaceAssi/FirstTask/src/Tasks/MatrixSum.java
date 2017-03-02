package Tasks;

public class MatrixSum {

	public static void main(String[] args) {
		int [][] array1={
				{1,2,3},
				{5,6,7},
				{8,9,7}
		};
		
		int [][] array2={
				{10,22,34},
				{1,4,3},
				{8,7,2}
		};
	
		int thirdMatrix[][]=new int [array1.length][array2.length];
		
		for (int i = 0; i < array1.length; i++) {
			
			for (int j = 0; j < array2.length; j++) {
				
				int sum=array1[i][j]+array2[i][j];
				thirdMatrix[i][j]=sum;
				
				System.out.print(sum + ",");
			}
			System.out.println("\n");
		}
		

	}

}
