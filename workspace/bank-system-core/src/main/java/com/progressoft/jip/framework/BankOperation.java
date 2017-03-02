package com.progressoft.jip.framework;

import org.iban4j.Iban;

import com.progressoft.jip.entity.Account;

public interface BankOperation {

	public Iban validationAndGenerationIban(Iban iban);
	public void confirmAccount(Account account);
}
