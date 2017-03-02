package com.progressoft.jip.paymentsproject.impl;

import com.progressoft.jip.paymentsproject.accounts.IBANField;

public class Iban implements IBANField {

	
	private String iban;
	
	public Iban(String iban) {
		this.iban = upperCaseNoSpaces(iban);
	}
	
	@Override
	public String getIBAN() {
		return iban;
	}

	@Override
	public String getCountryCode() {
		return iban.substring(0, 2);
	}

	@Override
	public boolean isValid() {
		return false;
	}
	
	private String upperCaseNoSpaces(String ibanArg) {
		return ibanArg.toUpperCase().replaceAll(" ", "");
	}

}
