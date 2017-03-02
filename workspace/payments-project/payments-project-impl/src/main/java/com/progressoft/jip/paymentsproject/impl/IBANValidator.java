package com.progressoft.jip.paymentsproject.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;

import com.progressoft.jip.paymentsproject.accounts.IBANStructurePattern;
import com.progressoft.jip.paymentsproject.accounts.IBANStructurePattern.IBANPatternType;
import com.progressoft.jip.paymentsproject.accounts.IBANStructurePattern.Pair;
import com.progressoft.jip.paymentsproject.data.DataReader;

public class IBANValidator {
	
	private DataReader<IBANStructurePattern> reader;
	Iban iban;
	IBANStructurePattern pattern;
	
	public boolean validateIBAN(Iban iban) throws IOException {
		return checkFormat() && (getRemainder() == 1);
	}
	
	public void findIBANStructure() throws FileNotFoundException, IOException {
		Iterator<IBANStructurePattern> iterator = reader.iterator();
		while(iterator.hasNext()) {
			IBANStructurePattern pattern = iterator.next();
			if (pattern.getCountryCode().equals(iban.getCountryCode())) {
				this.pattern = pattern;
			}
		}
	}
	
	private boolean checkFormat() throws IOException {
		findIBANStructure(); 
		if (pattern == null || iban.getIBAN().length() != pattern.getIbanLength() || !checkRegex())
			return false;
		return true;
	}

	private boolean checkRegex() {
		return iban.getIBAN().substring(2).matches(getCorrectIBANRegex());
	}

	private String getCorrectIBANRegex() {		
		StringBuilder regex = new StringBuilder("");
		Iterator<Pair<IBANPatternType, Integer>> iterator = pattern.getPatterns();
		while(iterator.hasNext()) {
			Pair<IBANPatternType, Integer> set = iterator.next();
			if (set.getPattern() == IBANPatternType.NUMERIC)
				regex.append("[0-9]");
			else if (set.getPattern() == IBANPatternType.ALPHABETIC)
				regex.append("[A-Z]");
			else
				regex.append("[0-9A-Z]");
			regex.append("{").append(set.getLength()).append("}");
		}
		return regex.toString();
	}

	private int getRemainder() {
		BigInteger bigInt = new BigInteger(rearrangeDigitsForModulus());
		BigInteger[] divAndRem = bigInt.divideAndRemainder(BigInteger.valueOf(97));
		int mod = divAndRem[1].intValue();
		return mod;
	}

	private String rearrangeDigitsForModulus() {
		StringBuilder formattedIBAN = new StringBuilder("");
		char[] ibanArray;
		int numericValue;
		ibanArray = moveCountryCodeToTail().toCharArray();
		for (int i = 0; i < ibanArray.length; i++) {
			numericValue = Character.getNumericValue(ibanArray[i]);
			if (isLetter(numericValue))
				formattedIBAN.append(String.valueOf(numericValue));
			else
				formattedIBAN.append(ibanArray[i]);
		}
		return formattedIBAN.toString();
	}

	private boolean isLetter(int numericValue) {
		return numericValue >= 10 && numericValue <= 35;
	}
	
	private String moveCountryCodeToTail() {
		return iban.getIBAN().substring(4).concat(iban.getCountryCode());
	}
}