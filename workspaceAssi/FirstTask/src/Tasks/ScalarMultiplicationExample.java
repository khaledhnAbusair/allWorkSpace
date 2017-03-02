package Tasks;

public class ScalarMultiplicationExample {

	public static void ScalarMultiplication(int x){

		
		
		
		int [][] array1={
				{1,8,-3},
				{4,-2,5},
				{1,2,3}
	
				
		};
		
		for (int i = 0; i < array1.length; i++) {
			
			for (int j = 0; j < array1[i].length; j++) {
				
				int sum = array1[i][j]*x;
				
				System.out.println(sum +",");
				
			}
			System.out.println("\n");
		}
		
		
		
	}
	public static void main(String[] args) {
		
	ScalarMultiplication(5);
	}

}
