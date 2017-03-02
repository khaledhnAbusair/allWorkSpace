package com.progressoft.jip.test;

import com.progressoft.jip.iban.impl.IBANFormatValidator;
import com.progressoft.jip.iban.impl.IBANModValidator;

public class CurrentIBANValidator {
	IBANFormatValidator formatValidator = new IBANFormatValidator();
	IBANModValidator modValidator = new IBANModValidator();

	public boolean isValid(String iban) {
		String normalIBAN = removeWhiteSpaces(iban).toUpperCase();
		return formatValidator.isValid(normalIBAN) && modValidator.isValid(normalIBAN);
	}

	private String removeWhiteSpaces(String string) {
		String result = "";
		for (int i = 0; i < string.length(); i++)
			if (string.charAt(i) != ' ')
				result += string.charAt(i);
		return result;
	}
}
