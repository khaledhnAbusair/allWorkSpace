package com.progressoft.jip.paymentsproject.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.progressoft.jip.paymentsproject.payments.PaymentPurpose;

public class PaymentPurposeImple implements PaymentPurpose {

	@Override
	public double getCode() {
		return 0;
	}

	@Override
	public String getPurpose() {
		return null;
	}

	public static boolean validatePaymentCode(String purposeCode) throws IOException {
		if (!checkCodeFormat(removeSpaceAndUpperCase(purposeCode)))
			return false;

		return true;

	}

	public static boolean checkCodeFormat(String purposeCode) throws IOException {
		boolean found = false;
		String rows = null;
		String[] formatColumns = new String[2];

		try (BufferedReader reader = new BufferedReader(new FileReader("./home/khaled/Desktop"))) {
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

	public static String removeSpaceAndUpperCase(String purposeCode) {
		return purposeCode.toUpperCase().replaceAll(" ", "");
	}

	public static void main(String[] args) {

		String purposeCode = "COLL";

		System.out.println(purposeCode);
		try {
			if (PaymentPurposeImple.validatePaymentCode(purposeCode)) {
				System.out.println("Thank you for adding Payment purpse to your transaction process");
			} else
				System.out.println("I`m sorry your purpose code is invalid ... Please try agin");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
