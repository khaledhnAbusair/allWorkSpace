package com.progresssoft.stringstatistices;

import java.util.HashMap;
import java.util.Map;

import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class TotalNumberOfNumbersfound implements Operations<String> {
	private Map<String, Integer> mapNumberOfNumber = new HashMap<>();

	@Override
	public void doOpertaions(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (Character.isDigit(input.charAt(i)))
				mapNumberOfNumber.put(input,
						mapNumberOfNumber.containsKey(input) ? mapNumberOfNumber.get(input) + 1 : 1);
		}

	}

	@Override
	public Map<String, Integer> getMap() {
		return mapNumberOfNumber;
	}

}
