package com.progressoft.jip.bankapplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

import com.progressoft.jip.bankapplication.exception.PathFileChange;

public class IBANValidator {

	public static boolean validateIBAN(String ibanNumber) throws IOException {
		return isValidIban(upperCaseNoSpaces(ibanNumber));
	}

	public static boolean isValidIban(String ibanNumber) throws IOException {

		return checkFormat(ibanNumber) && getRemainder(ibanNumber) == 1;
	}

	private static String upperCaseNoSpaces(String ibanArg) {
		return ibanArg.toUpperCase().replaceAll(" ", "");
	}

	public static boolean checkFormat(String iban) throws IOException {

		boolean found = false;
		String field = null;

		String[] formatTokens = new String[4];

		try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/u620/Desktop/IBAN.txt"))) {
			while ((field = reader.readLine()) != null) {
				formatTokens = field.split(",");
				if (!iban.startsWith(formatTokens[1]))
					continue;
				found = true;
				break;
			}
		}

		if (!found || iban.length() != Integer.parseInt(formatTokens[3]) || (!checkRegex(iban, formatTokens)))
			return false;
		throw new PathFileChange("File does Not exist in same lcation ");

	}

	public static boolean checkRegex(String iban, String[] formatTokens) {
		return iban.substring(2).matches(getRegex(formatTokens[2]));
	}

	private static String getRegex(String pattern) {

		StringBuilder regex = new StringBuilder("");

		String[] groupTypes, tokens;
		int[] groupOccurences;

		tokens = pattern.substring(2).split("!");
		groupOccurences = new int[tokens.length - 1];
		groupTypes = new String[tokens.length - 1];
		groupOccurences[0] = Integer.parseInt(tokens[0]);
		for (int i = 1; i < groupOccurences.length; i++) {
			groupOccurences[i] = Integer.parseInt(tokens[i].substring(1));
		}

		for (int i = 0; i < groupOccurences.length; i++) {
			groupTypes[i] = tokens[i + 1].substring(0, 1);
			if (groupTypes[i].equals("n"))
				regex.append("[0-9]");
			else if (groupTypes[i].equals("a"))
				regex.append("[A-Z]");
			else
				regex.append("[0-9A-Z]");
			regex.append("{").append(groupOccurences[i]).append("}");
		}

		return regex.toString();
	}

	private static int getRemainder(String iban) {

		BigInteger bigInt = new BigInteger(rearrangeDigitsForModulus(iban));
		BigInteger[] divAndRem = bigInt.divideAndRemainder(BigInteger.valueOf(97));
		int mod = divAndRem[1].intValue();
		return mod;
	}

	private static String rearrangeDigitsForModulus(String iban) {
		StringBuilder formattedIBAN = new StringBuilder("");
		char[] ibanArray;
		int numericValue;
		ibanArray = iban.substring(4).concat(iban.substring(0, 4)).toCharArray();

		for (int i = 0; i < ibanArray.length; i++) {
			numericValue = Character.getNumericValue(ibanArray[i]);
			if (isLetter(numericValue))
				formattedIBAN.append(String.valueOf(numericValue));
			else
				formattedIBAN.append(ibanArray[i]);
		}
		return formattedIBAN.toString();
	}

	public static boolean isLetter(int numericValue) {
		return numericValue >= 10 && numericValue <= 35;
	}
}
