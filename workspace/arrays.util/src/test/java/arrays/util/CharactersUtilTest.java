package arrays.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class CharactersUtilTest {

	@Test
	public void givenCharactersUtil_WhenTestReversArrays_ThenReversedArrays() {
		char[] element = new char[] { 'a', 'b', 'c' };
		char[] reverse = CharactersUtil.reverse(element);
		assertArrayEquals("Reverse failed", new char[] { 'c', 'b', 'a' }, reverse);

	}

	@Test(expected = IllegalArgumentException.class)
	public void givenCharactersUtil_WhenTestNullArrays_ThenArraysIsNullException() {
		CharactersUtil.reverse(null);
	}

	@Test
	public void givenCharactersUtil_WhenTestEmptyArrays_ThenArraysIsEmptyException() {
		char[] element = new char[] {};
		char[] reverse = CharactersUtil.reverse(element);
		assertArrayEquals("Arrays is Empty ", new char[] {}, reverse);
	}
	

}
