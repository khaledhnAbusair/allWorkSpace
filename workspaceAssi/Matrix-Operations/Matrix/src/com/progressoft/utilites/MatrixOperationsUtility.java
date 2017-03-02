package com.progressoft.utilites;

/**
 * @author ProgressSoft
 *
 */
public interface MatrixOperationsUtility {

	/**
	 * Adds the two matrices passed as arguments, and returns a new int[][]
	 * representing the sum.
	 *
	 * throws an IncompatibleArgumentsException if the two matrices passed as
	 * arguments are not of the same size
	 */
	int[][] add(int[][] a, int[][] b) throws IncompatibleArgumentsException;

	/**
	 * performs a scalar multiplication on the passed matrix by the passed
	 * scalar and returns the result in a new int[][]
	 */
	int[][] scalarMultiply(int scalar, int[][] a);

	/**
	 * produce a new matrix from the passed matrix by changing each row in the
	 * matrix to a column
	 */
	int[][] transport(int[][] a);

	/**
	 * performs matrix product on the passed matrices, in the order they were
	 * passed, returning the result in a new int[][]
	 *
	 * throws an IncompatibleArgumentsException if the two matrices passed as
	 * arguments are incompatible for multiplication
	 */
	int[][] multiply(int[][] a, int[][] b) throws IncompatibleArgumentsException;
}
