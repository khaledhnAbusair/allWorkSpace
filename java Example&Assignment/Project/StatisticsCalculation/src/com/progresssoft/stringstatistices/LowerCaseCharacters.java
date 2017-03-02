package com.progresssoft.stringstatistices;

import java.util.HashMap;
import java.util.Map;
import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class LowerCaseCharacters implements Operations<String> {

	private Map<String, Integer> mapLowerCase = new HashMap<>();

	@Override
	public void doOpertaions(String input) {

		for (int i = 0; i < input.length(); i++) {
			boolean lowerCase = Character.isLowerCase(input.charAt(i));
			if (lowerCase) {
				mapLowerCase.put(input, mapLowerCase.containsKey(input) ? mapLowerCase.get(input) + 1 : 1);
			}
		}

	}

	@Override
	public Map<String, Integer> getMap() {
		return mapLowerCase;
	}

}
