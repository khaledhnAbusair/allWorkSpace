package arrays.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixSumationTest {
	private int[][] m1;
	private int[][] m2;

	@Before
	public void setUp() {
		m1 = new int[][] { { 1, 2 }, { 3, 4 } };
		m2 = new int[][] { { 5, 6 }, { 7, 8 } };
	}

	@Test
	public void givenMatrixSumation_WhentestInvalidMatrixesSizes() {
		int[][] actuals = { { 6, 8 }, { 10, 12 } };
		int[][] result = MatrixUtils.sum(m1, m2);
		Assert.assertArrayEquals("Result matrix is not as expected", result, actuals);
	}

	@Test(expected = IllegalArgumentException.class)
	public void sumIsValidMatrixesSizes() {
		int[][] invalidSize = new int[][] { { 1, 2 }, { 1 } };
		MatrixUtils.sum(invalidSize, m2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFirstMatrixIsNull() {
		// new more enhancement
		MatrixUtils.sum(null, m2);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSecondMatrixIsNull() {
		MatrixUtils.sum(m1, null);
	}

}
