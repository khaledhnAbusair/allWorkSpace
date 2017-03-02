package com.progresssoft.stringstatistices;

import java.util.HashMap;
import java.util.Map;

import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class UpperCaseCharacters implements Operations<String> {
	private Map<String, Integer> mapUpperCase;

	public UpperCaseCharacters() {
		mapUpperCase = new HashMap<>();
	}

	@Override
	public void doOpertaions(String input) {

		for (int i = 0; i < input.length(); i++) {
			if (Character.isUpperCase(input.charAt(i)))
				mapUpperCase.put(input, mapUpperCase.containsKey(input) ? mapUpperCase.get(input) + 1 : 1);
		}

	}

	@Override
	public Map<String, Integer> getMap() {
		return mapUpperCase;
	}

}
