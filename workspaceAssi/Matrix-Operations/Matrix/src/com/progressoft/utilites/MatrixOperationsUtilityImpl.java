package com.progressoft.utilites;

public class MatrixOperationsUtilityImpl implements MatrixOperationsUtility {

	@Override
	public int[][] add(int[][] a, int[][] b) throws IncompatibleArgumentsException {
		if (a == null || b == null)
			throw new NullPointerException();
		if (!(a.length == b.length && a[0].length == b[0].length))
			throw new IncompatibleArgumentsException();

		int[][] result = new int[a.length][a[0].length];
		for (int i = 0; i < result.length; i++)
			for (int j = 0; j < result[i].length; j++)
				result[i][j] = a[i][j] + b[i][j];

		return result;
		

	}

	@Override
	public int[][] scalarMultiply(int scalar, int[][] a) {
		if (a == null)
			throw new NullPointerException();
		int[][] result = new int[a.length][a[0].length];
		for (int i = 0; i < result.length; i++)
			for (int j = 0; j < result[i].length; j++)
				result[i][j] = scalar * a[i][j];
		return result;
	}

	@Override
	public int[][] transport(int[][] a) {
		if(a==null)
			throw new NullPointerException();
		int[][] result = new int[a[0].length][a.length];
		for (int i = 0; i < result.length; i++)
			for (int j = 0; j < result[0].length; j++)
				result[i][j]=a[j][i];
				
		return result;
	}

	@Override
	public int[][] multiply(int[][] a, int[][] b) throws IncompatibleArgumentsException {
		if (a == null || b == null)
			throw new NullPointerException();
		if (a[0].length != b.length)
			throw new IncompatibleArgumentsException();

		int[][] result = new int[a.length][b[0].length];
		for (int i = 0; i < result.length; i++)
			for (int j = 0; j < result[i].length; j++)
				for (int k = 0; k < a[i].length; k++)
					result[i][j] += a[i][k] * b[k][j];

		return result;
	}

	

}
