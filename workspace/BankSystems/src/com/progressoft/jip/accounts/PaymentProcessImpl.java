package com.progressoft.jip.accounts;

public class PaymentProcessImpl implements PaymentProcess {
	private Account sender;
	private Account receiver;

	public PaymentProcessImpl(Account sender, Account receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}

	@Override
	public void directDebit(Account sender, Account receiver) {
		// sender exists on dataSource
		// check sender account
		// receiver validation
		// complete debit process

	}

}
