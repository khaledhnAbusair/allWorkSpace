package com.progressoft.jip.accounts;

public interface PaymentProcess {
	public void directDebit(Account sender, Account receiver);
}
