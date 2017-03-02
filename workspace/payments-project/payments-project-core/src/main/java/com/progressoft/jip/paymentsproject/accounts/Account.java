package com.progressoft.jip.paymentsproject.accounts;

import com.progressoft.jip.paymentsproject.payments.Currency;

public interface Account {
	public long getNumber();
	public String getName();
	public IBANField getIBAN();
	public Currency getCurrency();
	public AccountStatus getStatus();
	public double getBalance();
	public boolean isActive();
}
