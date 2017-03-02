package com.progressoft.jip.imple;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

import com.progressoft.jip.entity.Account;
import com.progressoft.jip.framework.BankOperation;

public class JdbcBank implements BankOperation{

	public Iban validationAndGenerationIban(Iban iban) {
		new Iban.Builder().countryCode(CountryCode.AD).bankCode("").accountNumber("").build();
		return iban;
	}

	public void confirmAccount(Account account) {
		
	}

}
