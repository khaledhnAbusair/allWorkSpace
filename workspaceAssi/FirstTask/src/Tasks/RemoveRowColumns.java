package Tasks;

public class RemoveRowColumns {

	private static int[][] removeRow(int[][] input, int removedRow) {
		int[][] subMatrix = new int[input.length - 1][input[0].length];
		boolean removed = false;
		for (int i = 0; i < subMatrix.length; i++) {
			for (int j = 0; j < subMatrix[0].length; j++) {
				if (removedRow == i) {
					subMatrix[i][j] = input[i + 1][j];
					removed = true;
				} else if (removed)
					subMatrix[i][j] = input[i + 1][j];
				else
					subMatrix[i][j] = input[i][j];
			}
		}

		return subMatrix;
	}

	public static int[][] subMatrix(int[][] input, int removedRow, int removedColumn) {
		int[][] removedOneRow = removeRow(input, removedRow);
		removedOneRow = TransportationMatrix.transport(removedOneRow);
		int[][] removedOneColumn = removeRow(removedOneRow, removedColumn);
		removedOneColumn = TransportationMatrix.transport(removedOneColumn);
		return removedOneColumn;
	}

	public static void main(String[] args) {
		int[][] arrayMatrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		subMatrix(arrayMatrix, 1, 1);
	}
}
