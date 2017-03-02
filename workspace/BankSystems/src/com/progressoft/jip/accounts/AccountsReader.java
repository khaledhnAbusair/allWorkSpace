package com.progressoft.jip.accounts;

public interface AccountsReader {

	public boolean accountExists(String number);

	public Account getAccountFromNumber(String number);
}
