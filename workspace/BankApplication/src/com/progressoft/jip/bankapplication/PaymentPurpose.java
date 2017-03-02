package com.progressoft.jip.bankapplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PaymentPurpose {

	public static boolean validatePaymentCode(String purposeCode) throws IOException {
		if (!checkCodeFormat(removeSpaceAndUpperCase(purposeCode)))
			return false;

		return true;

	}

	private static String removeSpaceAndUpperCase(String purposeCode) {
		return purposeCode.toUpperCase().replaceAll(" ", "");
	}

	private static boolean checkCodeFormat(String purposeCode) throws IOException {
		boolean found = false;
		String rows = null;
		String[] formatColumns = new String[2];

		try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/u621/Desktop/PaymentPurpose.txt"))) {
			while ((rows = reader.readLine()) != null) {
				formatColumns = rows.split(",");
				if (!purposeCode.startsWith(formatColumns[1]))
					continue;
				found = true;
				break;
			}
		}
		if (!found)
			return false;
		return found;
	}

}
