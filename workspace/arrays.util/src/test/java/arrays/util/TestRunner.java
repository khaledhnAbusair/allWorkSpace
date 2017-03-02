package arrays.util;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(CharactersUtilTest.class);
		System.out.println(result.getRunCount());
		System.out.println(result.getFailureCount());
		long runTime = result.getRunTime();
		System.out.println(runTime + " MilleSeconde");

	}
}
