package arrays.util;

import java.util.Objects;

public class MatrixUtils {

	public static int[][] sum(int[][] m1, int[][] m2) {
		validateArrays(m1, m2);
		validateMatrixesSizes(m1, m2);
		return null;
	}

	private static void validateArrays(int[][] m1, int[][] m2) {
		if (Objects.isNull(m1))
			throw new FirstMatrixeIsNull();
		if (Objects.isNull(m2))
			throw new SecondMatrixeIsNull();
		if (!isSameRowLength(m1, m2))
			throw new MatrixeDifferentSizes();
	}

	private static void validateMatrixesSizes(int[][] m1, int[][] m2) {
		int coulmnsCount = m1[0].length;

		for (int i = 0; i < m1.length; i++) {
			if (m1[i].length != m2[i].length) {
				throw new MatrixeDifferentSizes();
			}
			if (coulmnsCount != m1[i].length) {
				throw new ArraysIsNotMatrixe();
			}
		}
	}

	private static boolean isSameRowLength(int[][] m1, int[][] m2) {
		return m1.length != m2.length;
	}

	public static class FirstMatrixeIsNull extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

	public static class SecondMatrixeIsNull extends RuntimeException {
		private static final long serialVersionUID = 5829961427029603676L;
	}

	public static class MatrixeDifferentSizes extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

	public static class ArraysIsNotMatrixe extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
}
