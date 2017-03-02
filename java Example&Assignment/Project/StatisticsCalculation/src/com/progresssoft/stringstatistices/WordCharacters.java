package com.progresssoft.stringstatistices;

import java.util.HashMap;
import java.util.Map;

import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class WordCharacters implements Operations<String> {
	Map<String, Integer> mapWordChar;

	public WordCharacters() {
		mapWordChar = new HashMap<>();
	}

	@Override
	public void doOpertaions(String input) {

		for (int i = 0; i < input.split(" ").length; i++) {
			mapWordChar.put(input, mapWordChar.containsKey(input) ? mapWordChar.get(input) + 1 : 1);
		}
	}

	@Override
	public Map<String, Integer> getMap() {
		return mapWordChar;
	}

}
