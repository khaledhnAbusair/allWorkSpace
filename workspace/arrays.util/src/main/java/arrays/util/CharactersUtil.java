package arrays.util;

public class CharactersUtil {

	public static char[] reverse(char[] chars) {
		if (chars == null) {
			throw new IllegalArgumentException("Arrays is null");
		}
		char[] result = new char[chars.length];
		for (int i = 0; i < chars.length; i++) {
			result[chars.length - 1 - i] = chars[i];
		}
		return result;
	}
}
