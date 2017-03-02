package com.progresssoft.stringstatistices;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class NonWordCharacters implements Operations<String> {
	private Map<String, Integer> mapNonWord = new HashMap<>();

	@Override
	public void doOpertaions(String input) {

		for (int i = 0; i < input.length(); i++) {
			boolean isNonWord = Pattern.compile("[^\\w^\\s]").matcher("" + input.charAt(i)).find();
			if (isNonWord) {

				mapNonWord.put(input, mapNonWord.containsKey(input) ? mapNonWord.get(input) + 1 : 1);
			}
		}

	}

	@Override
	public Map<String, Integer> getMap() {
		return mapNonWord;
	}

}
