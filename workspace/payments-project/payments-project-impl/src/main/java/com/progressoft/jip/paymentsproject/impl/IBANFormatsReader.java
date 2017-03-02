/**
 * 
 */
package com.progressoft.jip.paymentsproject.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import com.progressoft.jip.paymentsproject.accounts.IBANField;
import com.progressoft.jip.paymentsproject.accounts.IBANStructurePattern;
import com.progressoft.jip.paymentsproject.accounts.IBANStructurePattern.IBANPatternType;
import com.progressoft.jip.paymentsproject.data.DataReader;

/**
 * @author u620
 *
 */
public class IBANFormatsReader implements DataReader<IBANStructurePattern> {

	private static final String IBAN_FORMATS_FILEPATH = "C:/Users/u620/Desktop/IBAN.txt";
	private static ArrayList<IBANStructurePattern> patterns = new ArrayList<>();
	private static HashMap<Character, IBANPatternType> patternMapper = new HashMap<>();

	static {
		try {
			patternMapper.put('a', IBANPatternType.ALPHABETIC);
			patternMapper.put('n', IBANPatternType.NUMERIC);
			patternMapper.put('c', IBANPatternType.ALPHANUMERIC);

			loadAllIBANStructures();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadAllIBANStructures() throws FileNotFoundException, IOException {
		String[] tokens;
		String field;
		String countryCode;
		String pattern;
		int length;
		try (BufferedReader reader = new BufferedReader(new FileReader(IBAN_FORMATS_FILEPATH))) {
			while ((field = reader.readLine()) != null) {
				tokens = field.split(",");
				countryCode = tokens[1];
				pattern = tokens[2];
				length = Integer.parseInt(tokens[3]);
				addPattern(countryCode, pattern, length);
			}
		}
	}

	private static void addPattern(String countryCode, String iBANStructure, int ibanLength) {
		String[] iBANPatternTokens = iBANStructure.substring(2).split("!");
		String[] patternTypes = new String[iBANPatternTokens.length - 1];
		int[] patternLength = new int[iBANPatternTokens.length - 1];
		IBANStructurePattern pattern = new IBANStructurePattern();

		pattern.setCountryCode(countryCode);
		pattern.setIbanLength(ibanLength);
		
		patternLength[0] = Integer.parseInt(iBANPatternTokens[0]);
		patternTypes[0] = iBANPatternTokens[1].substring(0, 1);
		pattern.addPattern(patternMapper.get(patternTypes[0]), patternLength[0]);
		for (int i = 1; i < patternLength.length; i++) {
			patternLength[i] = Integer.parseInt(iBANPatternTokens[i].substring(1));
			patternTypes[i] = iBANPatternTokens[i + 1].substring(0, 1);
			pattern.addPattern(patternMapper.get(patternTypes[i]), patternLength[i]);
		}
		patterns.add(pattern);
	}

	@Override
	public Iterator<IBANStructurePattern> iterator() {
		return Collections.unmodifiableList(patterns).iterator();
	}
}
