package Tasks;

public class TransportationMatrix {

	public static int[][] transport(int[][] input) {
		int[][] transported = new int[input[0].length][input.length];
		for (int i = 0; i < transported.length; i++) {
			for (int j = 0; j < transported[0].length; j++) {
				transported[i][j] = input[j][i];

			}

		}

		for (int[] rowData : transported) {
			for (int cellData : rowData) {
				System.out.println("the indiviual data is " + cellData);
			}
		}

		return transported;
	}

	public static void main(String[] args) {
		int[][] arrayMatrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		transport(arrayMatrix);

	}

	// public static void transportMatrix(int[][] arrayTrasport) {
	//
	// int[][] array = new int[arrayTrasport[0].length][];
	//
	// for (int i = 0; i < arrayTrasport[0].length; i++) {
	// array[i] = new int[arrayTrasport.length];
	// }
	// for (int j = 0; j < arrayTrasport.length; j++) {
	// for (int x = 0; x < arrayTrasport[j].length; x++)
	// array[x][j] = arrayTrasport[j][x];
	// }
	//
	// for (int c = 0; c < array.length; c++) {
	// System.out.println(Arrays.toString(array[c]));
	// }
	//
	// }

}
